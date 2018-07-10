package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter@Setter
public class StockIncomeBill extends BaseDomain{
	private static final long serialVersionUID = 1L;
	public static final int STUTS_NORMAL = 0;
	public static final int STUTS_AUDITED = 1;

	private String sn;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date vdate;

    private int status = StockIncomeBill.STUTS_NORMAL;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;

    private Date auditTime;

    private Date inputTime;

    private List<StockIncomeBillItem> items = new ArrayList<>();

    private Employee inputUser;

    private Employee auditor;

    private Depot depot;

}