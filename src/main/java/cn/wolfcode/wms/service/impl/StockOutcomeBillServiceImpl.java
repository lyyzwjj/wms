package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.SaleAccount;
import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.domain.StockOutcomeBillItem;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.SaleAccountMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockOutcomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IStockOutcomeBillService;
import cn.wolfcode.wms.util.LogicException;
import cn.wolfcode.wms.util.UserContext;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
	@Autowired
	StockOutcomeBillMapper stockOutcomeBillMapper;
	@Autowired
	StockOutcomeBillItemMapper itemMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductStockMapper productStockMapper;
	@Autowired
	SaleAccountMapper saleAccountMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		itemMapper.deleteByStockOutcomeBillId(id);
		return stockOutcomeBillMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int insert(StockOutcomeBill stockOutcomeBill) {
		Employee inputUser = UserContext.getCurrentUser();
		stockOutcomeBill.setInputUser(inputUser);
		stockOutcomeBill.setInputTime(new Date());
		List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockOutcomeBillItem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal salePrice = item.getSalePrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(salePrice));
		}
		stockOutcomeBill.setTotalNumber(totalNumber);
		stockOutcomeBill.setTotalAmount(totalAmount.setScale(2));
		int i = stockOutcomeBillMapper.insert(stockOutcomeBill);
		for (StockOutcomeBillItem item : items) {
			item.setBillId(stockOutcomeBill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal salePrice = item.getSalePrice();
			item.setAmount(number.multiply(salePrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
		
	}

	@Override
	public StockOutcomeBill selectByPrimaryKey(Long id) {
		return stockOutcomeBillMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(StockOutcomeBill stockOutcomeBill) {
		itemMapper.deleteByStockOutcomeBillId(stockOutcomeBill.getId());
		Employee inputUser = UserContext.getCurrentUser();
		stockOutcomeBill.setInputUser(inputUser);
		stockOutcomeBill.setInputTime(new Date());
		List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockOutcomeBillItem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal salePrice = item.getSalePrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(salePrice));
		}
		stockOutcomeBill.setTotalNumber(totalNumber);
		stockOutcomeBill.setTotalAmount(totalAmount.setScale(2));
		int i = stockOutcomeBillMapper.updateByPrimaryKey(stockOutcomeBill);
		for (StockOutcomeBillItem item : items) {
			item.setBillId(stockOutcomeBill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal salePrice = item.getSalePrice();
			item.setAmount(number.multiply(salePrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
	}


	@Override
	public List<StockOutcomeBill> selectAll() {
		return stockOutcomeBillMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = stockOutcomeBillMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<StockOutcomeBill> departments = stockOutcomeBillMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}
	@Override
	public void audit(Long id) {
		StockOutcomeBill oldbill = stockOutcomeBillMapper.selectByPrimaryKey(id);
		Employee currentUser = UserContext.getCurrentUser();
		if (oldbill.getStatus() == StockOutcomeBill.STUTS_NORMAL) {
			oldbill.setAuditor(currentUser);
			oldbill.setAuditTime(new Date());
			oldbill.setStatus(StockOutcomeBill.STUTS_AUDITED);
			stockOutcomeBillMapper.updateByPrimaryKey(oldbill);
		}
		Long depotId = oldbill.getDepot().getId();
		List<StockOutcomeBillItem> items = oldbill.getItems();
		for (StockOutcomeBillItem item : items) {
			Long productId = item.getProduct().getId();
			ProductStock productStock = productStockMapper.selectByProductIdAndDepotId(depotId,productId);
			String productName = item.getProduct().getName();
			String depotName = oldbill.getDepot().getName();
			BigDecimal itemNumber = item.getNumber();
			if (productStock == null) {
				throw new LogicException("亲,[" + productName + "]在["+ depotName +"]仓库中没有库存了");
			}else{
				BigDecimal storeNumber = productStock.getStoreNumber();
				BigDecimal amount = productStock.getAmount();
				BigDecimal price = productStock.getPrice();
				if (itemNumber.compareTo(storeNumber) > 0) {
					throw new LogicException("亲,["+ productName + "]在["+ depotName +"]仓库中库存["+ storeNumber +"]不足["+ itemNumber +"]");
				}else {
					productStock.setStoreNumber(storeNumber.subtract(itemNumber));
					productStock.setAmount(amount.subtract(itemNumber.multiply(price)));
					productStockMapper.updateByPrimaryKey(productStock);
				}
				SaleAccount account = new SaleAccount();
				account.setVdate(oldbill.getVdate());
				account.setNumber(item.getNumber());
				account.setCostPrice(productStock.getPrice());
				account.setCostAmount(item.getAmount());
				account.setSalePrice(item.getSalePrice());
				account.setSaleAmount(item.getAmount());
				account.setProductId(productId);
				account.setSalemanId(oldbill.getInputUser().getId());
				account.setClientId(oldbill.getClient().getId());
				saleAccountMapper.insert(account);
			}
		}
	}

}
