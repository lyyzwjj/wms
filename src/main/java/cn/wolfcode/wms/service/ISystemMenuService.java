package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface ISystemMenuService {
	void save(SystemMenu menu);
	void delete(Long id);
	void update(SystemMenu menu);
	SystemMenu get(Long id);
	List<SystemMenu> list();
	PageResult query(QueryObject qo);
    List<Map<String,Object>> selectByParentSn(String parentSn);
}
