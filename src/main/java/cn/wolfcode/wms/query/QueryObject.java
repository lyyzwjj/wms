package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午6:28:53
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter@ToString
public class QueryObject {
	private Integer currentPage = 1;
	private Integer pageSize = 3;
	public Integer getStartIndex(){
		return (currentPage - 1) * pageSize;
	}
}
	