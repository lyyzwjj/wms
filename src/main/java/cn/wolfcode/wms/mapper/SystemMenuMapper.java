package cn.wolfcode.wms.mapper;

import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午12:35:58
 *@mail   wzzst310@163.com
 *@desp
 */
public interface SystemMenuMapper {
	void save(SystemMenu menu);
	void delete(Long id);
	void update(SystemMenu menu);
	SystemMenu get(Long id);
	List<SystemMenu> list();
	List<SystemMenu> queryForList(QueryObject qo);
	Integer queryForCount(QueryObject qo);

    List<Map<String,Object>> selectByParentSn(String parentSn);

	List<Map<String,Object>> selectByParentSnAndEmplyeeId(@Param("empId") Long id, @Param("parentId") String parentSn);
}
