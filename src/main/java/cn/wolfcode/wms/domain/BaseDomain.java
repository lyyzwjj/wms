package cn.wolfcode.wms.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *@author WangZhe
 *@time	  2018年6月21日上午12:03:57
 *@mail   wzzst310@163.com
 *@desp
 */
@Getter@Setter
public class BaseDomain implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
}
