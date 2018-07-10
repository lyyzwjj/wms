package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月20日上午9:04:23
 *@mail   wzzst310@163.com
 *@desp
 */
@Getter@Setter
public class Permission extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String expression;
}
