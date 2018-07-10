package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Employee;
import cn.wolfcode.wms.mapper.EmployeeMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IEmployeeService;
import cn.wolfcode.wms.util.LogicException;
import cn.wolfcode.wms.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author WangZhe
 * @time 2018年6月19日下午2:45:49
 * @mail wzzst310@163.com
 * @desp
 */
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public void save(Employee employee, Long[] ids) {
        employeeMapper.save(employee);
        if (ids != null) {
            for (Long roleId : ids) {
                employeeMapper.saveEmployeeRoleRelation(employee.getId(), roleId);
            }
        }
    }

    @Override
    public void delete(Long id) {
        employeeMapper.deleteEmployeeRoleRelation(id);
        employeeMapper.delete(id);

    }

    @Override
    public void update(Employee employee, Long[] ids) {
        employeeMapper.deleteEmployeeRoleRelation(employee.getId());
        employeeMapper.update(employee);
        if (ids != null) {
            for (Long roleId : ids) {
                employeeMapper.saveEmployeeRoleRelation(employee.getId(), roleId);
            }
        }
    }

    @Override
    public Employee get(Long id) {
        return employeeMapper.get(id);
    }

    @Override
    public List<Employee> list() {
        return employeeMapper.list();
    }

    @Override
    public PageResult query(QueryObject qo) {
        Integer totalCount = employeeMapper.queryForCount(qo);
        if (totalCount == 0) {
            return new PageResult(3);
        }
        List<Employee> employees = employeeMapper.queryForList(qo);
        return new PageResult(employees, totalCount, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void login(String username, String password) {
        Employee currentUser = employeeMapper.selectByUsernameAndPassword(username, password);
        if (currentUser == null) {
            throw new LogicException("账号或者密码不正确");
        } else {
            Set<String> expressions = employeeMapper.selectAllExpressionByEmployeeId(currentUser.getId());
            UserContext.setCurrentUser(currentUser);
            UserContext.setExpressions(expressions);
        }
    }

    @Override
    public void batchDelete(Long[] ids) {
            employeeMapper.batchDelete(ids);
    }

    @Override
    public Employee selectByUsername(String name) {
        return employeeMapper.selectByUsername(name);
    }

}
