package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午6:35:30
 *@mail   wzzst310@163.com
 *@desp
 */
@Getter@Setter
public class Role extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	private List<Permission> permissions;
	private List<SystemMenu> menus;
}
