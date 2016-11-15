package edu.uci.ics.crawler4j.examples.basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class DuplicateRemover {
	public static boolean isDuplicate(String filepath,String text){
		try{
			String encoding = "UTF-8";
			String wholeContent = "";
			
			for(int i = 1;i<100;i++){
				File file = new File(filepath+"/Pages/"+i+".txt");
				if(file.isFile()&&file.exists()){
					InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),encoding);
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String lineText = "";
					
					while((lineText = bufferedReader.readLine())!=null){
						wholeContent = wholeContent +lineText;
					}
				}
				if(text.equals(wholeContent)){
					return true;
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
//	public static void main(String[] args){
//		String lineText = "";
//		String wholeContent = "";
//		try{
//		File file = new File("/Users/Alex/Desktop/Crawler/Pages/8.txt");
//		InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
//		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		while((lineText = bufferedReader.readLine())!=null){
//			wholeContent = wholeContent +lineText;
//		}
//		String text = wholeContent;
//		System.out.println(isDuplicate("/Users/Alex/Desktop/Crawler",text));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}

