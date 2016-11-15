package crawler.queryengine;

import java.io.File;
import java.util.Scanner;

public class RetriveMessages {
	public static String retriveMessages(int docid,String filepath){
		String messages = "";
		String content = "";
		File Url = new File(filepath+"/URL.txt");
		File stemmedFile = new File(filepath+"/StemedFile/"+docid+".txt");
		try{
		if(Url.isFile()&&Url.exists()){
			Scanner scanner = new Scanner(Url);
//			String regex = "^host[\\s\\=]+[^\r\n]+$"；
			String regex = "doc"+docid+"\\:\\S{0,}";
			while(scanner.hasNextLine()){
				String url = scanner.nextLine();
				if(url.matches(regex)){
					messages=messages+"URL:"+url.replace("doc"+docid+":", "")+"\n";
				}
			}
		}
		if(stemmedFile.isFile()&&stemmedFile.exists()){
			Scanner scanner = new Scanner(stemmedFile);
			while(scanner.hasNextLine()){
				content = content+scanner.nextLine();
			}
			messages = messages+"Content:";
			String[] contentword = content.split(" ");
			for(int i =0;i<=20;i++){
				messages= messages+contentword[i]+" ";
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return messages;
	}
//	public static void main(String[] args){
//		String messages = new RetriveMessages().retriveMessages(6, "/Users/Alex/Desktop/Crawler");
//		System.out.println(messages);
////	
////			int i =1;
////		        String str = "doc1season.abcdefg1$";
////		        String regex ="doc" +i+"\\:season.\\S{0,}";
////		        System.out.println(str.matches(regex));
//		    
//	}
}
