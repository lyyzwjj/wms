package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Getter@Setter
public class Orderbill extends BaseDomain{
	private static final long serialVersionUID = 1L;
	public static final int STUTS_NORMAL = 0;
	public static final int STUTS_AUDITED = 1;

	private String sn;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date vdate;

    private int status = STUTS_NORMAL;

    private BigDecimal totalAmount;

    private BigDecimal totalNumber;
    
    private Date inputTime;
    
    private Date auditTime;

    private List<Orderbillitem> items = new ArrayList<>();

    private Employee inputUser;

    private Employee auditor;

    private Supplier supplier;
}