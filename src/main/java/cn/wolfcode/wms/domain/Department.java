package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:34:30
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter
public class Department extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
}
