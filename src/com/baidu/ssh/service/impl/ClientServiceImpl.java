package com.baidu.ssh.service.impl;

import com.baidu.ssh.dao.IClientDAO;
import com.baidu.ssh.domain.Client;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IClientService;
import lombok.Setter;

import java.util.List;

public class ClientServiceImpl implements IClientService {
    @Setter
    private IClientDAO clientDAO;
    public void save(Client client) {
        clientDAO.save(client);
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Long id) {
        clientDAO.delete(id);
    }

    public Client get(Long id) {
        return clientDAO.get(id);
    }

    public List<Client> list() {
        return clientDAO.list();
    }

    public PageResult query(QueryObject qo) {
        return clientDAO.query(qo);
    }
}
