package com.baidu.ssh.service;


import com.baidu.ssh.domain.Client;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

import java.util.List;

public interface IClientService {

    void save(Client client);

    void update(Client client);

    void delete(Long id);

    Client get(Long id);

    List<Client> list();

    PageResult query(QueryObject qo);
}
