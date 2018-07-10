package cn.wolfcode.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ProductStock extends BaseDomain{
	private static final long serialVersionUID = 1L;

    private BigDecimal price;

    private BigDecimal storeNumber;

    private BigDecimal amount;

    private Product product;

    private Depot depot;
   
}