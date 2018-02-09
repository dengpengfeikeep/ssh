package com.baidu.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

import org.hibernate.cfg.HbmBinder;
import org.springframework.dao.support.DaoSupport;

import com.baidu.ssh.domain.Brand;
import com.baidu.ssh.domain.Cat;
import com.baidu.ssh.domain.Client;
import com.baidu.ssh.domain.Depot;
import com.baidu.ssh.domain.OrderBill;
import com.baidu.ssh.domain.Product;
import com.baidu.ssh.domain.ProductStock;
import com.baidu.ssh.domain.StockIncomeBill;
import com.baidu.ssh.domain.Supplier;
import com.baidu.ssh.domain.SystemMenu;
import com.baidu.ssh.util.XmlUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("deprecation")
public class CodeGenerator {

	private static Configuration config;
	static {
		try {
			config = new Configuration();
			config.setDirectoryForTemplateLoading(new File("templates"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		// creat();
	}

	private static void creat() throws Exception {
		// DAO模板
		ClassInfo classInfo = new ClassInfo(Client.class);
		creatFile(classInfo, "IDAO.java", "src/{0}/dao" + "/I{1}DAO.java");
		// DAOImpl模板
		creatFile(classInfo, "DAOImpl.java", "src/{0}/dao/impl"
				+ "/{1}DAOImpl.java");
		// service模板
		creatFile(classInfo, "IService.java", "src/{0}/service"
				+ "/I{1}Service.java");
		// serviceImpl模板
		creatFile(classInfo, "ServiceImpl.java", "src/{0}/service/impl"
				+ "/{1}ServiceImpl.java");
		// 生成query对象
		creatFile(classInfo, "QueryObject.java",
				"src/{0}/query/{1}QueryObject.java");
		// 生成Action对象
		creatFile(classInfo, "Action.java", "src/{0}/web/action/{1}Action.java");
		// 生成jsp文件
		creatFile(classInfo, "list.jsp",
				"WebContent/WEB-INF/views/{2}/list.jsp");
		creatFile(classInfo, "input.jsp",
				"WebContent/WEB-INF/views/{2}/input.jsp");
		// 生成映射文件
		creatFile(classInfo, "hbm.xml", "src/{0}/domain/{1}.hbm.xml");
		System.out.println("生产完成");
		// 追加配置文件
		appendToXml(classInfo, "dao.xml",
				"resources/applicationContext-dao.xml");
		appendToXml(classInfo, "service.xml",
				"resources/applicationContext-service.xml");
		appendToXml(classInfo, "action.xml",
				"resources/applicationContext-action.xml");
	}

	@SuppressWarnings("all")
	private static void appendToXml(ClassInfo classInfo, String templateName,
			String targetFile) throws Exception {
		Template template = config.getTemplate(templateName);
		StringWriter out = new StringWriter();
		template.process(classInfo, out);
		String appendXml = out.toString();
		XmlUtil.mergeXML(new File(targetFile), appendXml);

	}

	/**
	 * @Description 
	 * @param classInfo classInfo对象
	 * @param templateName  模板名称
	 * @param path 保存路径
	 * @throws Exception
	 */
	private static void creatFile(ClassInfo classInfo, String templateName,
			String path) throws Exception {
		Template template = config.getTemplate(templateName);
		String format = MessageFormat.format(path, classInfo.getBasePkg()
				.replace(".", "/"), classInfo.getClassName(), classInfo
				.getObjectName());
		File file = new File(format);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		template.process(classInfo, new FileWriter(file));
	}
}
