package cn.wolfcode.wms.service.impl;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.mapper.ClientMapper;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;
import cn.wolfcode.wms.service.IClientService;
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
public class ClientServiceImpl implements IClientService {
	@Autowired
	ClientMapper clientMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return clientMapper.deleteByPrimaryKey(id);

	}
	
	@Override
	public int insert(Client client) {
		return clientMapper.insert(client);
		
	}

	@Override
	public Client selectByPrimaryKey(Long id) {
		return clientMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int updateByPrimaryKey(Client client) {
		return clientMapper.updateByPrimaryKey(client);

	}


	@Override
	public List<Client> selectAll() {
		return clientMapper.selectAll();
	}

	@Override
	public PageResult query(QueryObject qo) {
		Integer totalCount = clientMapper.queryForCount(qo);
		if(totalCount == 0) {
			return new PageResult(3);
		}
		List<Client> departments = clientMapper.queryForList(qo);
		return new PageResult(departments, totalCount, qo.getCurrentPage(), qo.getPageSize());
	}

}
