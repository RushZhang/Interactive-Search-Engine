package crawler.queryengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;


public class FormDictionary {
	public static void formNewDictionary(Map<String,String> dictionary,String docID,String filepath){
		Element root = new Element("dictionary");
		
		Document mydictionary = new Document(root);
		
		for(Map.Entry<String, String> entry : dictionary.entrySet()){
			Element word = new Element("word");
			word.setAttribute("name",entry.getKey());
			word.addContent(new Element(docID).setText(entry.getValue()));
			root.addContent(word);
		}
		
		XMLOutputter XMLOut = new XMLOutputter();
		
		try {
			XMLOut.output(mydictionary, new FileOutputStream(filepath+"/dictionary.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	test formdictionary
//	public static void main(String[] args){
//		Map dictionary = new HashMap();
//		dictionary.put("ChengCheng", "23 34 23 34 45 56");
//		
//		FormDictionary FDtool = new FormDictionary();
//		FDtool.formDictionary(dictionary, "doc1");
//		
//		
//	}
}
