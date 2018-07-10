package cn.wolfcode.wms.service;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.PageResult;
import cn.wolfcode.wms.query.QueryObject;

import java.util.List;

/**
 *@author WangZhe
 *@time	  2018年6月19日下午2:44:45
 *@mail   wzzst310@163.com
 *@desp
 */
public interface IClientService {
    int deleteByPrimaryKey(Long id);

    int insert(Client client);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client client);
    
	PageResult query(QueryObject qo);
}
