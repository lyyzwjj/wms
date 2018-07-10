package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *@author WangZhe
 *@time	  2018年6月22日下午12:53:43
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter@ToString
public class SystemMenu extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String url;
	private String sn;
	private SystemMenu parent;
}
