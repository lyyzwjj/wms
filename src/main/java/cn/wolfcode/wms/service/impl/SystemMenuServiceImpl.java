package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.domain.SystemMenu;
import cn.wolfcode.wms.mapper.SystemMenuMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.ISystemMenuService;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
	@Autowired
	SystemMenuMapper menuMapper;
	@Override
	public void save(SystemMenu menu) {
		menuMapper.save(menu);

	}

	@Override
	public void delete(Long id) {
		menuMapper.delete(id);

	}

	@Override
	public void update(SystemMenu menu) {
		menuMapper.update(menu);

	}

	@Override
	public SystemMenu get(Long id) {
		return menuMapper.get(id);
	}

	@Override
	public List<SystemMenu> list() {
		return menuMapper.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = menuMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<SystemMenu> menus = menuMapper.queryForList(qo);
		return new PageResult(menus, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

	public List<Map<String, Object>> selectByParentSn(String parentSn) {
		Employee currentUser = UserContext.getCurrentUser();
		if (currentUser.isAdmin()){
			return menuMapper.selectByParentSn(parentSn);
		}else{
			return menuMapper.selectByParentSnAndEmplyeeId(currentUser.getId(), parentSn);
		}
	}

}
