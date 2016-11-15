package crawler.queryengine;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class QueryProcess {
	public static double[] queryProcess(String queryword,String filepath,int wordsnumber){
		double[] result = new double[40];
		double vector = 0;
		vector = 1/Math.sqrt(wordsnumber);
		
		File file = new File(filepath+"/TermFrequency.xml");
		SAXReader saxReader = new SAXReader();
		try{
			Document document =saxReader.read(file);
			Element root =document.getRootElement();
			for(Iterator i= root.elementIterator();i.hasNext();){
				Element word =(Element)i.next();
				
					if(word.attribute("name").getValue().equals(queryword)){
						for( Iterator l = word.elementIterator();l.hasNext();){
							Element docfreq = (Element)l.next();
//							System.out.println(docfreq.getName()+":"+docfreq.getText());
//							result.put(docfreq.getName(), docfreq.getText());
							String docID = docfreq.getName().replace("doc","");
							int docid = Integer.parseInt(docID);
							double docfrequency = Double.parseDouble(docfreq.getText());
							result[docid] = docfrequency*vector;
						}
					}
				}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
//	public static void main(String[] args){
//		String[] queryword = {"moore","smu"};
//		new QueryProcess().queryProcess(queryword, "/Users/Alex/Desktop/Crawler");
//	}
}
