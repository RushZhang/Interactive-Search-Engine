package crawler.queryengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class StringTestMode {
	public static void main(String[] args){
		String content = "";
		try{
			File file = new File("/Users/Alex/Desktop/Crawler/Pages/7.txt");
			InputStreamReader read = new InputStreamReader(new FileInputStream(file));
			BufferedReader bufferedreader = new BufferedReader(read); 
			String lineTxt = null;
			while ((lineTxt = bufferedreader.readLine())!=null){
				content = content+lineTxt+" ";
				System.out.println(content);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
