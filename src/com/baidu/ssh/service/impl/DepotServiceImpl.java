package com.baidu.ssh.service.impl;

import com.baidu.ssh.dao.IDepotDAO;
import com.baidu.ssh.domain.Depot;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;
import com.baidu.ssh.service.IDepotService;
import lombok.Setter;

import java.util.List;

public class DepotServiceImpl implements IDepotService {
    @Setter
    private IDepotDAO depotDAO;
    public void save(Depot depot) {
        depotDAO.save(depot);
    }

    public void update(Depot depot) {
        depotDAO.update(depot);
    }

    public void delete(Long id) {
        depotDAO.delete(id);
    }

    public Depot get(Long id) {
        return depotDAO.get(id);
    }

    public List<Depot> list() {
        return depotDAO.list();
    }

    public PageResult query(QueryObject qo) {
        return depotDAO.query(qo);
    }
}
