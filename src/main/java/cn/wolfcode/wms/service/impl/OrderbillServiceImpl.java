package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.Orderbill;
import cn.wolfcode.wms.domain.Orderbillitem;
import cn.wolfcode.wms.mapper.OrderbillMapper;
import cn.wolfcode.wms.mapper.OrderbillitemMapper;
import cn.wolfcode.wms.mapper.ProductMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IOrderbillService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class OrderbillServiceImpl implements IOrderbillService {
	@Autowired
	OrderbillMapper orderbillMapper;
	@Autowired
	OrderbillitemMapper itemMapper;
	@Autowired
	ProductMapper productMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		itemMapper.deleteByOrderBillId(id);
		return orderbillMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Orderbill orderbill) {
		Employee inputUser = UserContext.getCurrentUser();
		orderbill.setInputUser(inputUser);
		orderbill.setInputTime(new Date());
		List<Orderbillitem> items = orderbill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (Orderbillitem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(costPrice));
		}
		orderbill.setTotalNumber(totalNumber);
		orderbill.setTotalAmount(totalAmount.setScale(2));
		int i = orderbillMapper.insert(orderbill);
		for (Orderbillitem item : items) {
			item.setBillId(orderbill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			item.setAmount(number.multiply(costPrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
		
	}

	@Override
	public Orderbill selectByPrimaryKey(Long id) {
		return orderbillMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Orderbill orderbill) {
		itemMapper.deleteByOrderBillId(orderbill.getId());
		Employee inputUser = UserContext.getCurrentUser();
		orderbill.setInputUser(inputUser);
		orderbill.setInputTime(new Date());
		List<Orderbillitem> items = orderbill.getItems();
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		for (Orderbillitem item : items) {
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			totalNumber = totalNumber.add(number);
			totalAmount = totalAmount.add(number.multiply(costPrice));
		}
		orderbill.setTotalNumber(totalNumber);
		orderbill.setTotalAmount(totalAmount.setScale(2));
		int i = orderbillMapper.updateByPrimaryKey(orderbill);
		for (Orderbillitem item : items) {
			item.setBillId(orderbill.getId());
			BigDecimal number = item.getNumber();
			BigDecimal costPrice = item.getCostPrice();
			item.setAmount(number.multiply(costPrice).setScale(2));
			itemMapper.insert(item);
		}
		return i;
	}


	@Override
	public List<Orderbill> selectAll() {
		return orderbillMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = orderbillMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Orderbill> departments = orderbillMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}
	@Override
	public void audit(Long id) {
		Orderbill oldbill = orderbillMapper.selectByPrimaryKey(id);
		Employee currentUser = UserContext.getCurrentUser();
		if (oldbill.getStatus() == Orderbill.STUTS_NORMAL) {
			oldbill.setAuditor(currentUser);
			oldbill.setAuditTime(new Date());
			oldbill.setStatus(Orderbill.STUTS_AUDITED);
			orderbillMapper.updateByPrimaryKey(oldbill);
		}
	}

}
