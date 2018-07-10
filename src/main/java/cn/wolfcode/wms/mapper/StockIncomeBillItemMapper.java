package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.StockIncomeBillItem;
import java.util.List;

public interface StockIncomeBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StockIncomeBillItem record);

    StockIncomeBillItem selectByPrimaryKey(Long id);

    List<StockIncomeBillItem> selectAll();

    int updateByPrimaryKey(StockIncomeBillItem record);

	void deleteByStockIncomeBillId(Long id);

}