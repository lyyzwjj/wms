package cn.wolfcode.wms.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:34:30
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter
public class Employee extends BaseDomain{
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin;
	private Department department;
	private List<Role> roles;
	
}
