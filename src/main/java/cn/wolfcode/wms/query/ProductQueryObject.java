package cn.wolfcode.wms.query;

import lombok.Getter;
import lombok.Setter;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午10:02:55
 *@mail   wzzst310@163.com
 *@desp
 */
@Setter@Getter
public class ProductQueryObject extends QueryObject {
	private String keywords;
	private Long brandId = -1L;
	public String getKeywords() {
		if(keywords == null || keywords.trim().length() == 0) {
		return null;
		}
		return keywords;
	}
}
