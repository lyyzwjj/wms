package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IStockIncomeBillService {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill stockIncomeBill);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill stockIncomeBill);
    
	PageResult query(QueryObject qo);

    void audit(Long id);

}
