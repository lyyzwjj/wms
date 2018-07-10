package cn.wolfcode.wms.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SaleAccount extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private Date vdate;

    private BigDecimal number;

    private BigDecimal costPrice;

    private BigDecimal costAmount;

    private BigDecimal salePrice;

    private BigDecimal saleAmount;

    private Long productId;

    private Long salemanId;

    private Long clientId;

}