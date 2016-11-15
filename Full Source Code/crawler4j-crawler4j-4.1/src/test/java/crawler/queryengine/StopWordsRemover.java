package crawler.queryengine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StopWordsRemover {
	public static String[] stopWordsAnalysis(String filepath){
		String[] stopWord = new String[20];
		try{
			String stopWords = "";
			File file = new File(filepath+"/StopWords.txt");
			if(file.isFile()&&file.exists()){
				Scanner scanner = new Scanner(file);
				while(scanner.hasNextLine()){
					stopWords = stopWords + scanner.nextLine()+" ";
				}
			}
			stopWords = stopWords.trim();
			stopWord = stopWords.split("[\\p{Space}]+");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return stopWord;
	}
	public static String removeStopWord(String filepath,String docID){
		String contentAfterDeleteStopWords = "";
		try{
//			System.out.println(filepath);
			String[] stopWord = stopWordsAnalysis(filepath);
			String encoding = "UTF-8";
			
			File docfile = new File(filepath+"/Pages/"+docID+".txt");
			if(docfile.exists()&&docfile.isFile()){
				String content ="";
				String[] words = null;
				String postingList = null;
				int counter = 1;

				InputStreamReader read = new InputStreamReader(new FileInputStream(docfile),encoding);
				BufferedReader bufferedreader = new BufferedReader(read); 
				String lineTxt = null;
				while ((lineTxt = bufferedreader.readLine())!=null){
					content = content+lineTxt+" ";
				}

				int adjustcounter = 0;
				char[] s =content.toCharArray();
				char[] afteradjust = new char[s.length];
				for(int i = 0 ; i<s.length;i++){
					if(s[i]>20&&s[i]<127){
						afteradjust[adjustcounter] = s[i];
						adjustcounter++;
					}
				}
				content= String.valueOf(afteradjust);
				content = content.replaceAll("=", " ");	
				content = content.replaceAll("\\{", " ");
				content = content.replaceAll("\n{1,}", " ");
				content = content.replaceAll("\r{1,}", " ");
				content = content.replaceAll("\\}", "");
				content = content.replaceAll(";", " ");
				content = content.replaceAll("~", " ");
				content = content.replaceAll("\\(", " ");
				content = content.replaceAll("\\)", " ");
				content = content.replaceAll("\\'", " ");
				content = content.replaceAll("_{1,}", "");
				content = content.replaceAll("!", " ");
				content = content.replaceAll("\\?", " ");
				content = content.replaceAll("\\d+", " ");
				content = content.replaceAll("\\.", " ");
				content = content.replaceAll(",", " ");
				content = content.replaceAll(":", " ");
				content = content.replaceAll("-", " ");
				content = content.replaceAll("@", " ");
				content = content.replaceAll("\\/", " ");
				content = content.replaceAll("  {1,}", " ");
				content = content.trim();
				words = content.split(" ");
				String[] wordsAfterDeleteStopWords = arrContrast(words,stopWord);
//				System.out.println(wordsAfterDeleteStopWords);
//				contentAfterDeleteStopWords = String.valueOf(wordsAfterDeleteStopWords);
				for(int i =0;i<wordsAfterDeleteStopWords.length;i++){
					contentAfterDeleteStopWords = contentAfterDeleteStopWords +wordsAfterDeleteStopWords[i] +" ";
				}
//				System.out.println(contentAfterDeleteStopWords);
				File filestopwordsStored = new File(filepath+"/PagesAfterDeleteStopWords");
				if(!filestopwordsStored.exists()){
					filestopwordsStored.mkdirs();
				}
				File filestopwords = new File(filepath+"/PagesAfterDeleteStopWords/"+docID+".txt");
				if(!filestopwords.exists()){
					filestopwords.createNewFile();
				}
				FileWriter fileWriter = new FileWriter(filestopwords);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(contentAfterDeleteStopWords);
				bufferedWriter.flush();
				bufferedWriter.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return contentAfterDeleteStopWords;
		
	}
	public static String[] arrContrast(String[] arr1,String[] arr2){
		List<String> list = new LinkedList<String>();
		List<String> list2 = new LinkedList<String>();
		for(String str:arr1){
				list.add(str);
		}
		for(String str:arr2){
				list2.add(str);
		}
		list.removeAll(list2);
		String[] result = {};
		return list.toArray(result);
	}
//	public static void main(String[] args){
//		String[] str1 = {"this","is","my","test"};
//		String[] str2 = {"my"};
//		String[] result = arrContrast(str1,str2);
//		for(int i=0;i<result.length;i++){
//			System.out.println(result[i]);
//		}
//	}
}
