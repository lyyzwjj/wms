package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.StockOutcomeBill;
import cn.wolfcode.wms.query.QueryObject;

public interface StockOutcomeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBill record);

    StockOutcomeBill selectByPrimaryKey(Long id);

    List<StockOutcomeBill> selectAll();

    int updateByPrimaryKey(StockOutcomeBill record);
    
    Integer queryForCount(QueryObject qo);

  	List<StockOutcomeBill> queryForList(QueryObject qo);
}