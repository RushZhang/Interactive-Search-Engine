package crawler.queryengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DuplicateOperater {
	public static void duplicateOperate(String filepath){
		String contextFileOne = "";
		String contextFileTwo = "";
		try{
			for(int i=1 ; i<=40; i++){
				File file1 = new File(filepath+"/Pages/"+i+".txt");
				if(file1.exists()){
					InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file1));
					BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
					String lineText = "";
					
					while((lineText = bufferedReader.readLine())!=null){
						contextFileOne = contextFileOne +lineText;
					}
//					System.out.println(file1.getName());
					
					
					for(int j = i+1;j<=40;j++){
						File file2 = new File(filepath+"/Pages/"+j+".txt");
						if(file2.exists()){
							InputStreamReader inputStreamReadertwo = new InputStreamReader(new FileInputStream(file2));
							BufferedReader bufferedReadertwo = new BufferedReader(inputStreamReadertwo);
							String lineText2= "";
							
							while((lineText2 = bufferedReadertwo.readLine())!=null){
								contextFileTwo = contextFileTwo +lineText2;
							}
//							System.out.println(file2.getName());
					}
//						System.out.println("contextFileOne:"+contextFileOne);
//						System.out.println("contextFileTwo:"+contextFileTwo);
						if(contextFileOne.equals(contextFileTwo)){
//							System.out.println(file2.getName());
							file2.delete();
							}
						
						contextFileTwo = "";
						}
				}
				contextFileOne = "";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
//	public static void main(String[] args){
//		new DuplicateOperater().duplicateOperate("/Users/Alex/Desktop/Crawler/");
//	}
}
