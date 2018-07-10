package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.StockIncomeBill;
import cn.wolfcode.wms.query.QueryObject;

public interface StockIncomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBill record);

    StockIncomeBill selectByPrimaryKey(Long id);

    List<StockIncomeBill> selectAll();

    int updateByPrimaryKey(StockIncomeBill record);
    
    Integer queryForCount(QueryObject qo);

  	List<StockIncomeBill> queryForList(QueryObject qo);
}