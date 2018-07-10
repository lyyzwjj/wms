package cn.wolfcode.wms.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class StockOutcomeBill extends BaseDomain{
	private static final long serialVersionUID = 1L;
	public static final int STUTS_NORMAL = 0;
	public static final int STUTS_AUDITED = 1;

	private String sn;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date vdate;

    private int status = StockOutcomeBill.STUTS_NORMAL;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;

    private Date auditTime;

    private Date inputTime;
    
    private List<StockOutcomeBillItem> items = new ArrayList<>();

    private Employee inputUser;

    private Employee auditor;

    private Depot depot;

    private Client client;

}