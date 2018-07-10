package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.Orderbillitem;
import java.util.List;

public interface OrderbillitemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Orderbillitem record);

    Orderbillitem selectByPrimaryKey(Long id);

    List<Orderbillitem> selectAll();

	void deleteByOrderBillId(Long id);
}