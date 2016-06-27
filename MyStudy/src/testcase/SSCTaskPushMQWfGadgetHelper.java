package testcase;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 * 发送 WebService
 * 
 * @author zhaojianc
 * 
 */
public class SSCTaskPushMQWfGadgetHelper {
	/**
	 * 生成xml文件
	 * 
	 * @param pk_group
	 * @param billtype
	 * @param transtype
	 * @param billId
	 * @return
	 */
	public String generateXml(String pk_group, String billtype,
			String transtype, String billId) {
		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
				.append("<params>").append("<operate>addToSSCTask</operate>")
				.append("<pk_group>").append(pk_group).append("</pk_group>")
				.append("<billtype>").append(billtype).append("</billtype>")
				.append("<transtype>").append(transtype).append("</transtype>")
				.append("<billid>").append(billId).append("</billid>")
				.append("</params>");
		return xmlBuilder.toString();
	}
	
	/**
	 * SSC端调用此方法
	 * 
	 * @param xmlInfo
	 * @throws Exception 
	 */
	public void executeOperate(String xmlInfo)
			throws Exception {

		Map<String, Object> mapValues = new HashMap<String, Object>();
		parseXml(xmlInfo, mapValues);
		String pk_group = (String) mapValues.get("pk_group");
		String billtype = (String) mapValues.get("billtype");
		String transtype = (String) mapValues.get("transtype");
		String billid = (String) mapValues.get("billid");
		
		int i=0;
		i++;
	}

	
	private void parseXml(String xmlInfo, Map<String, Object> mapValues)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document doc = db.parse(new InputSource(new StringReader(xmlInfo)));
		Element root = doc.getDocumentElement();
		NodeList xmlNodes = root.getElementsByTagName("pk_group");
		String pk_group = xmlNodes.item(0).getTextContent();
		mapValues.put("pk_group", pk_group);
		xmlNodes = root.getElementsByTagName("billtype");
		String billtype = xmlNodes.item(0).getTextContent();
		mapValues.put("billtype", billtype);
		xmlNodes = root.getElementsByTagName("transtype");
		String transtype = xmlNodes.item(0).getTextContent();
		mapValues.put("transtype", transtype);
		xmlNodes = root.getElementsByTagName("billid");
		String billid = xmlNodes.item(0).getTextContent();
		mapValues.put("billid", billid);
	}
}
