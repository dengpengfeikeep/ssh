package com.baidu.ssh.util;

import java.io.File;
import java.io.FileWriter;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtil {

	public static void mergeXML(File xml, String appendingXml) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(xml);

		Document flagment = DocumentHelper.parseText(appendingXml);
		Element flagEle = flagment.getRootElement();
		flagEle.setQName(new QName(flagEle.getName(), doc.getRootElement()
				.getNamespace()));
		if (flagEle.elements().size() > 0) {
			for (Object c : flagEle.elements()) {
				Element cel = (Element) c;
				cel.setQName(new QName(cel.getName(), flagEle.getNamespace()));
			}
		}
		doc.getRootElement().add(flagEle);

		XMLWriter writer = new XMLWriter(new FileWriter(xml));
		writer.write(doc.getRootElement());
		writer.close();
	}
}
