package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter@Setter
public class Orderbillitem extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private BigDecimal costPrice;

    private BigDecimal number;

    private BigDecimal amount;

    private String remark ;

    private Product product;

    private Long billId;

}