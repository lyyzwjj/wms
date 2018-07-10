package cn.wolfcode.wms.mapper;

import java.util.List;

import cn.wolfcode.wms.domain.Client;
import cn.wolfcode.wms.query.QueryObject;

public interface ClientMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Client record);

    Client selectByPrimaryKey(Long id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);
    
    Integer queryForCount(QueryObject qo);

    List<Client> queryForList(QueryObject qo);
}