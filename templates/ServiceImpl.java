package ${basePkg}.service.impl;

import ${basePkg}.dao.I${className}DAO;
import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;
import ${basePkg}.service.I${className}Service;
import lombok.Setter;

import java.util.List;

public class ${className}ServiceImpl implements I${className}Service {
    @Setter
    private I${className}DAO ${objectName}DAO;
    public void save(${className} ${objectName}) {
        ${objectName}DAO.save(${objectName});
    }

    public void update(${className} ${objectName}) {
        ${objectName}DAO.update(${objectName});
    }

    public void delete(Long id) {
        ${objectName}DAO.delete(id);
    }

    public ${className} get(Long id) {
        return ${objectName}DAO.get(id);
    }

    public List<${className}> list() {
        return ${objectName}DAO.list();
    }

    public PageResult query(QueryObject qo) {
        return ${objectName}DAO.query(qo);
    }
}
