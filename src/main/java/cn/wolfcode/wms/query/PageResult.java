package cn.wolfcode.wms.query;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author WangZhe
 * @time 2018年6月19日下午6:55:21
 * @mail wzzst310@163.com
 * @desp
 */
@Getter@Setter@ToString
public class PageResult {
	private List<?> listData;
	private Integer totalCount;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalPage;
	private Integer prevPage;
	private Integer nextPage;

	public PageResult(List<?> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
		this.listData = listData;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		if (totalCount < pageSize) {
			this.prevPage = 1;
			this.nextPage = 1;
			this.totalPage = 1;
		}
		this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		this.prevPage = currentPage - 1 > 0 ? currentPage - 1 : 1;
		this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
	}

	public PageResult(Integer pageSize) {
		this(Collections.EMPTY_LIST, 0, 1, pageSize);
	}

}
