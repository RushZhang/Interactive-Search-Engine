package crawler.queryengine;

import java.io.File;
import java.io.IOException;

public class DictionaryBuilder {
	
	public static void dictionaryBuilder(String filepath){
		new DuplicateOperater().duplicateOperate(filepath+"/");
		int docTotal = 40;
		for(int i=1; i<=docTotal;i++){
			File docFile = new File(filepath+"/Pages/"+i+".txt");
			File xmlFile = new File(filepath+"/dictionary.xml") ;
			File frequencyFile = new File(filepath+"/TermFrequency.xml");
//			System.out.println(docFile.exists()&&xmlFile.exists()&&frequencyFile.exists());
			if(docFile.exists()&&!xmlFile.exists()&&!frequencyFile.exists()){
				DocToMapBuilder docToMapBuilder = new DocToMapBuilder();
				docToMapBuilder.CreatXMLFile(filepath+"/", i);
			}
			else if(docFile.exists()&&xmlFile.exists()&&frequencyFile.exists()){
				DocToMapBuilder docToMapBuilder = new DocToMapBuilder();
				docToMapBuilder.UpdataXMLFile(filepath+"/", i);
			}
		}
		
	}
}
