package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Client extends BaseDomain{
	private static final long serialVersionUID = 1L;

	private String name;

    private String sn;

    private String phone;
    
}