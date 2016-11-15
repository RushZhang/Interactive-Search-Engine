package crawler.queryengine;

import java.io.File;
import java.util.HashMap;
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
import org.w3c.dom.Text;

public class UpdataDictionary {
	public static void UpdateDictionary(Map<String,String> dictionary,String docID,String filepath){
		Element root = null;
		File dictionaryXML = new File(filepath+"/dictionary.xml");
		DocumentBuilder documentBuilder = null;
		DocumentBuilderFactory documentBuilderFactory = null; 
		try{
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(dictionaryXML);
			root = document.getDocumentElement();
//			System.out.println("root:"+element.getNodeName());
			NodeList words = root.getChildNodes();
			for(int i =0; i<words.getLength();i++){
				Node word = words.item(i);
				if("word".equals(word.getNodeName())){
					if(dictionary.containsKey(word.getAttributes().getNamedItem("name").getTextContent())){
						String value = dictionary.get(word.getAttributes().getNamedItem("name").getTextContent());
						Element newdoc = document.createElement(docID);
						newdoc.setTextContent(value);
						word.appendChild(newdoc);
						dictionary.remove(word.getAttributes().getNamedItem("name").getTextContent());
					}
				}
			}
			for(Map.Entry<String, String> entry : dictionary.entrySet()){
				Element word = document.createElement("word");
				word.setAttribute("name",entry.getKey());
				Element newdoc = document.createElement(docID);
				newdoc.setTextContent(entry.getValue());
				word.appendChild(newdoc);
				root.appendChild(word);
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filepath+"/dictionary.xml"));
			transformer.transform(source, result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	public static void main(String[] args){
//		UpdataDictionary updateDictionary = new UpdataDictionary();
//		DocToMapBuilder doctomapbuilder = new DocToMapBuilder();
//		HashMap dictionary = doctomapbuilder.docToMapBuild("/Users/Alex/Desktop/Crawler/Pages/1.txt");
//		
//		String docNumber = "doc1";
//		updateDictionary.UpdateDictionary(dictionary, docNumber);
//	}
}
