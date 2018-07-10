package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Role;
import cn.wolfcode.wms.mapper.RoleMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:45:49
 *@mail   wzzst310@163.com
 *@desp
 */
@Service
public class RoleService implements IRoleService {
	@Autowired
	RoleMapper roleMapper;
	@Override
	public void save(Role role, Long[] permissionIds, Long[] menuIds) {
		roleMapper.save(role);
		if(permissionIds != null) {
			for (Long pd : permissionIds) {
				roleMapper.saveRolePermissionRelation(role.getId(), pd);
			}
		}
		if (menuIds != null) {
			for (Long md : menuIds) {
				roleMapper.saveRoleMenuRelation(role.getId(), md);
			}
		}

	}

	@Override
	public void delete(Long id) {
		roleMapper.deleteRolePermissionRelation(id);
		roleMapper.deleteRoleMenuRelation(id);
		roleMapper.delete(id);

	}

	@Override
	public void update(Role role, Long[] permissionIds, Long[] menuIds) {
		roleMapper.deleteRolePermissionRelation(role.getId());
		roleMapper.deleteRoleMenuRelation(role.getId());
		roleMapper.update(role);
		if(permissionIds != null) {
			for (Long pd : permissionIds) {
				roleMapper.saveRolePermissionRelation(role.getId(), pd);
			}
		}
		if (menuIds != null) {
			for (Long md : menuIds) {
				roleMapper.saveRoleMenuRelation(role.getId(), md);
			}
		}
	}

	@Override
	public Role get(Long id) {
		return roleMapper.get(id);
	}

	@Override
	public List<Role> list() {
		return roleMapper.list();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = roleMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Role> roles = roleMapper.queryForList(qo);
		return new PageResult(roles, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

	@Override
	public List<Role> selectByEmployeeId(Long id) {
		return roleMapper.selectByEmployeeId(id);
	}

}
