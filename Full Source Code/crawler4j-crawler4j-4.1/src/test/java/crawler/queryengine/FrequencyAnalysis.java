package crawler.queryengine;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FrequencyAnalysis {
	public static void main(String[] args){
		int docTotal = 40;
		double sum = 0;
		for(int i =1 ;i<=docTotal;i++){
			String docTag  = "doc"+i;
			Element element = null;
			File file = new File("/Users/Alex/Desktop/Crawler/TermFrequency.xml");
			DocumentBuilder documentBuilder = null;
			DocumentBuilderFactory documentBuilderFactory = null;
			try{
				documentBuilderFactory = DocumentBuilderFactory.newInstance();
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document document = documentBuilder.parse(file);
				element = document.getDocumentElement();
				NodeList words = element.getChildNodes();
				for(int j = 0;j<words.getLength();j++){
					Node node1 = words.item(j);
					if("word".equals(node1.getNodeName())){
						NodeList docfreq = node1.getChildNodes();
						for(int k =0 ;k<=docfreq.getLength();k++){
							Node mydocfreq = docfreq.item(k);
							if(docTag.equals(mydocfreq.getNodeName())){
								System.out.println(mydocfreq.getNodeName());
								int freq = Integer.parseInt(mydocfreq.getTextContent());
								sum = sum + Math.pow(freq, 2);
								break;
							}
							else if(mydocfreq.getNodeName()==null){
								continue;
							}
						}
					}
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(sum);
			sum = 0;
		}
	}
}
