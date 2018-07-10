package cn.wolfcode.wms.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class StockIncomeBillItem extends BaseDomain{
	private static final long serialVersionUID = 1L;

    private BigDecimal costPrice;

    private BigDecimal number;

    private BigDecimal amount;

    private String remark;

    private Product product;

    private Long billId;

}