package crawler.queryengine;

import java.io.File;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UpdateTermFrequencyFIle {
	public static void UpdateTermFrequencyFIle(Map<String,Integer> termFrequency,String docID,String filepath){
		Element root = null;
		double pow2Sum = 0;
		File termFrequencyXML = new File(filepath+"/TermFrequency.xml");
		DocumentBuilder documentBuilder = null;
		DocumentBuilderFactory documentBuilderFactory = null; 
		try{
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(termFrequencyXML);
			root = document.getDocumentElement();
//			System.out.println("root:"+element.getNodeName());
			NodeList words = root.getChildNodes();
			for(Map.Entry<String, Integer> entry : termFrequency.entrySet()){
				pow2Sum = pow2Sum + Math.pow(entry.getValue(), 2);
			}
			for(int i =0; i<words.getLength();i++){
				Node word = words.item(i);
				if("word".equals(word.getNodeName())){
					if(termFrequency.containsKey(word.getAttributes().getNamedItem("name").getTextContent())){
						String value = String.valueOf(Math.sqrt(termFrequency.get(word.getAttributes().getNamedItem("name").getTextContent())/pow2Sum));
						Element newdoc = document.createElement(docID);
						newdoc.setTextContent(value);
						word.appendChild(newdoc);
						termFrequency.remove(word.getAttributes().getNamedItem("name").getTextContent());
					}
				}
			}
			for(Map.Entry<String, Integer> entry : termFrequency.entrySet()){
				Element word = document.createElement("word");
				word.setAttribute("name",entry.getKey());
				Element newdoc = document.createElement(docID);
				newdoc.setTextContent(String.valueOf(Math.sqrt(entry.getValue()/pow2Sum)));
				word.appendChild(newdoc);
				root.appendChild(word);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filepath+"/TermFrequency.xml"));
			transformer.transform(source, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
