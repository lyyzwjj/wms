package cn.wolfcode.wms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.wms.domain.ProductStock;
import cn.wolfcode.wms.query.ProductStockQueryObject;
import cn.wolfcode.wms.query.QueryObject;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock record);

    ProductStock selectByPrimaryKey(Long id);

    List<ProductStock> selectAll();

    int updateByPrimaryKey(ProductStock record);
    
    Integer queryForCount(QueryObject qo);

    List<ProductStock> queryForList(ProductStockQueryObject qo);

	ProductStock selectByProductIdAndDepotId(@Param("depotId")Long depotId,@Param("productId")Long productId);
}