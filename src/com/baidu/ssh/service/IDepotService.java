package com.baidu.ssh.service;


import com.baidu.ssh.domain.Depot;
import com.baidu.ssh.query.PageResult;
import com.baidu.ssh.query.QueryObject;

import java.util.List;

public interface IDepotService {

    void save(Depot depot);

    void update(Depot depot);

    void delete(Long id);

    Depot get(Long id);

    List<Depot> list();

    PageResult query(QueryObject qo);
}
