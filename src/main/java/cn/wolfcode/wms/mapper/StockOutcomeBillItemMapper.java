package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockOutcomeBillItem;
import java.util.List;

public interface StockOutcomeBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockOutcomeBillItem record);

    StockOutcomeBillItem selectByPrimaryKey(Long id);

    List<StockOutcomeBillItem> selectAll();

    int updateByPrimaryKey(StockOutcomeBillItem record);

	void deleteByStockOutcomeBillId(Long id);

}