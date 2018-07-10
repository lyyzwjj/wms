package cn.wolfcode.wms.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter@Setter
public class Product extends BaseDomain{

	private static final long serialVersionUID = 1L;

	private String name;

    private String sn;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String imagePath;

    private String intro;

    private Long brandId;

    private String brandName;

    public String getSmallImagePath(){
        String[] split = imagePath.split("\\.");
        return split[0] + "_small." + split[1];
    }
    public String getProductInfo(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",getId());
        map.put("name",name);
        map.put("costPrice",costPrice);
        map.put("salePrice",salePrice);
        map.put("brandName",brandName);
       return  JSON.toJSONString(map);
    }
}