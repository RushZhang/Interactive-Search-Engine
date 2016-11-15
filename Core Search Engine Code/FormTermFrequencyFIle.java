package crawler.queryengine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class FormTermFrequencyFIle {
	public static void formNewTermFrequencyFIle(Map<String,Integer> termFrequency,String docID,String filepath){
		Element root = new Element("frequency");
		
		Document termFrequencyFile = new Document(root);
		double pow2Sum=0;
		for(Map.Entry<String, Integer> entry : termFrequency.entrySet()){
			pow2Sum = pow2Sum + Math.pow(entry.getValue(), 2);
		}
		
		for(Map.Entry<String, Integer> entry : termFrequency.entrySet()){
			Element word = new Element("word");
			word.setAttribute("name",entry.getKey());
			String value = String.valueOf(Math.sqrt(entry.getValue()/pow2Sum));
			word.addContent(new Element(docID).setText(value));
			root.addContent(word);
		}
		
		XMLOutputter FrequencyXMLOut = new XMLOutputter();
		
		try {
			FrequencyXMLOut.output(termFrequencyFile, new FileOutputStream(filepath+"/TermFrequency.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
