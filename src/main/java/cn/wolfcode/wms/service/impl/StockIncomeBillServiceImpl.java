package cn.wolfcode.wms.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.domain.StockIncomeBillItem;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.mapper.ProductStockMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillItemMapper;
import cn.wolfcode.wms.mapper.StockIncomeBillMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IStockIncomeBillService;
import cn.wolfcode.wms.util.UserContext;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Autowired
	StockIncomeBillMapper stockIncomeBillMapper;
	@Autowired
	StockIncomeBillItemMapper itemMapper;
	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductStockMapper productStockMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		itemMapper.deleteByStockIncomeBillId(id);
		return stockIncomeBillMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(StockIncomeBill stockIncomeBill) {
		Employee inputUser = UserContext.getCurrentUser();
		stockIncomeBill.setInputUser(inputUser);
		stockIncomeBill.setInputTime(new Date());
		List<StockIncomeBillItem> items = stockIncomeBill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockIncomeBillItem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(costPrice));
		}
		stockIncomeBill.setTotalNumber(totalNumber);
		stockIncomeBill.setTotalAmount(totalAmount.setScale(2));
		int i = stockIncomeBillMapper.insert(stockIncomeBill);
		for (StockIncomeBillItem item : items) {
			item.setBillId(stockIncomeBill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			item.setAmount(number.multiply(costPrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
		
	}

	@Override
	public StockIncomeBill selectByPrimaryKey(Long id) {
		return stockIncomeBillMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(StockIncomeBill stockIncomeBill) {
		itemMapper.deleteByStockIncomeBillId(stockIncomeBill.getId());
		Employee inputUser = UserContext.getCurrentUser();
		stockIncomeBill.setInputUser(inputUser);
		stockIncomeBill.setInputTime(new Date());
		List<StockIncomeBillItem> items = stockIncomeBill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (StockIncomeBillItem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(costPrice));
		}
		stockIncomeBill.setTotalNumber(totalNumber);
		stockIncomeBill.setTotalAmount(totalAmount.setScale(2));
		int i = stockIncomeBillMapper.updateByPrimaryKey(stockIncomeBill);
		for (StockIncomeBillItem item : items) {
			item.setBillId(stockIncomeBill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			item.setAmount(number.multiply(costPrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
	}


	@Override
	public List<StockIncomeBill> selectAll() {
		return stockIncomeBillMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = stockIncomeBillMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<StockIncomeBill> departments = stockIncomeBillMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}
	@Override
	public void audit(Long id) {
		StockIncomeBill oldbill = stockIncomeBillMapper.selectByPrimaryKey(id);
		Employee currentUser = UserContext.getCurrentUser();
		if (oldbill.getStatus() == StockIncomeBill.STUTS_NORMAL) {
			oldbill.setAuditor(currentUser);
			oldbill.setAuditTime(new Date());
			oldbill.setStatus(StockIncomeBill.STUTS_AUDITED);
			stockIncomeBillMapper.updateByPrimaryKey(oldbill);
		}
		List<StockIncomeBillItem> items = oldbill.getItems();
		for (StockIncomeBillItem item : items) {
			Long productId = item.getProduct().getId();
			ProductStock productStock = productStockMapper.selectByProductIdAndDepotId(oldbill.getDepot().getId(),productId);
			if (productStock != null) {
				BigDecimal storeNumber = productStock.getStoreNumber();
				BigDecimal number = item.getNumber();
				productStock.setStoreNumber(storeNumber.add(number));
				productStock.setAmount(productStock.getAmount().add(item.getAmount()));
				productStock.setPrice(item.getAmount().divide(item.getNumber(),2,BigDecimal.ROUND_HALF_UP));
				productStockMapper.updateByPrimaryKey(productStock);
			}else {
				ProductStock newProductStock = new ProductStock();
				newProductStock.setProduct(item.getProduct());
				newProductStock.setDepot(oldbill.getDepot());
				newProductStock.setAmount(item.getAmount());
				newProductStock.setStoreNumber(item.getNumber());
				newProductStock.setPrice(item.getAmount().divide(item.getNumber(),2,BigDecimal.ROUND_HALF_UP));
				productStockMapper.insert(newProductStock);
			}
		}
	}

}
