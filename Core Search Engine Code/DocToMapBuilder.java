package crawler.queryengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DocToMapBuilder {
	public static List<HashMap> docToMapBuild(String filepath,String docID){
		List<HashMap> list = new ArrayList();
		HashMap dictionary = new HashMap();
		HashMap termFrequency  = new HashMap();
		StopWordsRemover stopWordsRemover = new StopWordsRemover();
		String content = stopWordsRemover.removeStopWord(filepath, docID);
				String[] words = content.split(" ");
				for(int i =0 ; i<words.length;i++){
					if(dictionary.isEmpty()){
						dictionary.put(words[i], String.valueOf(i+1));
					}
					else{
						if(dictionary.containsKey(words[i])){
							dictionary.put(words[i], (String)dictionary.get(words[i])+" "+(i+1));
						}
						else{
							dictionary.put(words[i], String.valueOf(i+1));
						}
						}
					if(termFrequency.isEmpty()){
						termFrequency.put(words[i], 1);
					}
					else if(termFrequency.containsKey(words[i])){
						int value = (int)termFrequency.get(words[i]);
						value++;
						termFrequency.put(words[i], value);
					}else{
						termFrequency.put(words[i], 1);
					}
					}
				
			list.add(dictionary);
			list.add(termFrequency);
			return list;
	}
	public static void CreatXMLFile(String filePath,int docID){
		DocToMapBuilder doctomapbuilder = new DocToMapBuilder();
		List<HashMap> list = doctomapbuilder.docToMapBuild(filePath,String.valueOf(docID));
		FormDictionary formDictionary = new FormDictionary();
		FormTermFrequencyFIle formTermFrequencyFIle = new FormTermFrequencyFIle();
		String docNumber = "doc"+docID;
		formDictionary.formNewDictionary(list.get(0), docNumber,filePath);
		formTermFrequencyFIle.formNewTermFrequencyFIle(list.get(1), docNumber,filePath);
	}
	public static void UpdataXMLFile(String filePath,int docID){
		DocToMapBuilder doctomapbuilder = new DocToMapBuilder();
		List<HashMap> list =  doctomapbuilder.docToMapBuild(filePath,String.valueOf(docID));
		UpdataDictionary updateDictionary = new UpdataDictionary();
		UpdateTermFrequencyFIle updateTermFrequencyFIle = new UpdateTermFrequencyFIle();
		String docNumber = "doc"+docID;
		updateDictionary.UpdateDictionary(list.get(0), docNumber,filePath);
		updateTermFrequencyFIle.UpdateTermFrequencyFIle(list.get(1), docNumber,filePath);
	}
}
