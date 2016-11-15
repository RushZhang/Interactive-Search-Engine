package edu.uci.ics.crawler4j.examples.basic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DocIDtoURLWriter {
	public static void docIDtoURLWrite(String filepath,int Docid,String URL){
		String data = "doc"+Docid+":" +URL+"\n";
		try{
			File file = new File(filepath+"/URL.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file,true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//			System.out.println(data);
			bufferedWriter.write(data);
			bufferedWriter.flush();
			bufferedWriter.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
//	public static void main(String[] args){
//		new DocIDtoURLWriter().docIDtoURLWrite("/Users/Alex/Desktop", 1, "this is the URL");
//	}
}
