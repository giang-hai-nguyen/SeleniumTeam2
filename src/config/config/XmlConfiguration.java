package config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlConfiguration {

	public static String getXmlData(String nodeelement) {
		String nodevalue = null;
		try {	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(new File(new File("src/config", "datatest.xml").getAbsolutePath()));
			Element root = doc.getDocumentElement();

			NodeList configlist = root.getElementsByTagName("config");

			for (int i = 0; i < configlist.getLength(); i++) {
				Node configdetail = configlist.item(i);
				if (configdetail.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) configdetail;
					nodevalue = element.getElementsByTagName(nodeelement).item(0).getTextContent().toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return nodevalue;
	}

	public static List getDataProvider(String providername) {
		List resultList  = new ArrayList();
		try {	
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = (Document) dBuilder.parse(new File(new File("src/Dataset", "datatest.xml").getAbsolutePath()));
			Element root = doc.getDocumentElement();

			NodeList configlist = root.getElementsByTagName("config");

			for (int i = 0; i < configlist.getLength(); i++) {
				Node configdetail = configlist.item(i);
				if (configdetail.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) configdetail;

					if (element.getAttribute("name").equals(providername)) {
						resultList.add(element.getElementsByTagName("browser").item(0).getTextContent().toString());
						resultList.add(element.getElementsByTagName("url").item(0).getTextContent().toString());
						resultList.add(element.getElementsByTagName("username").item(0).getTextContent().toString());
						resultList.add(element.getElementsByTagName("password").item(0).getTextContent().toString());
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultList;
	}

}
