package ${basePkg}.web.action;

import ${basePkg}.domain.${className};
import ${basePkg}.query.${className}QueryObject;
import ${basePkg}.service.I${className}Service;
import ${basePkg}.util.RequiredPermission;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;
import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction {

    @Setter
    private I${className}Service ${objectName}Service;

    @Getter
    private ${className} ${objectName} = new ${className}();

    @Getter
    private ${className}QueryObject qo = new ${className}QueryObject();

    @RequiredPermission("${className}列表")
    @InputConfig(methodName = "input")
    public String execute() throws Exception {
        try {
            putContext("pageResult", ${objectName}Service.query(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "list";
    }

    @RequiredPermission("${className}编辑")
    public String input() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
        return "input";
    }

    @RequiredPermission("${className}删除")
    public String delete() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName}Service.delete(${objectName}.getId());
            putContextText("删除成功!");
        }
        return "success";
    }

    @RequiredPermission("${className}保存或更新")
    public String saveOrUpdate() throws Exception {
        try {
            if (${objectName}.getId() == null) {
                ${objectName}Service.save(${objectName});
                addActionMessage("保存成功!");
            } else {
                ${objectName}Service.update(${objectName});
                addActionMessage("更改成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
        }
        return "success";
    }

    public void prepareSaveOrUpdate() throws Exception {
        if (${objectName}.getId() != null) {
            ${objectName} = ${objectName}Service.get(${objectName}.getId());
        }
    }
}
