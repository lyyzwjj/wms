package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Orderbill;
import cn.wolfcode.wms.query.QueryObject;

public interface OrderbillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderbill record);

    Orderbill selectByPrimaryKey(Long id);

    List<Orderbill> selectAll();

    int updateByPrimaryKey(Orderbill record);
    
    Integer queryForCount(QueryObject qo);

	List<Orderbill> queryForList(QueryObject qo);
}