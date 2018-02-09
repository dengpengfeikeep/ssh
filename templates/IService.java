package ${basePkg}.service;


import ${basePkg}.domain.${className};
import ${basePkg}.query.PageResult;
import ${basePkg}.query.QueryObject;

import java.util.List;

public interface I${className}Service {

    void save(${className} ${objectName});

    void update(${className} ${objectName});

    void delete(Long id);

    ${className} get(Long id);

    List<${className}> list();

    PageResult query(QueryObject qo);
}
