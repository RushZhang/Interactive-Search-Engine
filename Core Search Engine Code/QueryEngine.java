package crawler.queryengine;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import edu.uci.ics.crawler4j.examples.basic.BasicCrawlController;

public class QueryEngine {
	public static void main(String[] args) {
		String filepath = args[0];
		int PagesToFetch = 100;
		try {
			new BasicCrawlController().basicCrawlController(filepath, PagesToFetch);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new DictionaryBuilder().dictionaryBuilder(filepath);
		QueryProcess queryProcess = new QueryProcess();
		String[] queryword = {};
		String queryWords = "";
		Scanner scanner= new Scanner(System.in);
		while(!"quit".equals(queryWords)){
			System.out.println("Please input words you want to query:(Input 'QUIT' to exit)");
			queryWords = scanner.nextLine();
			queryWords = queryWords.toLowerCase();
			if("quit".equals(queryWords)){
				System.out.println("Finished!");
				break;
			}
			queryword = queryWords.split(" {1,}");
			double[][] result = new double[queryword.length][];
			for(int i =0;i<queryword.length;i++){
				result[i] = queryProcess.queryProcess(queryword[i], filepath, queryword.length);
			}
			double[] score = new double[result[0].length];
			for(int i =0;i<result[0].length;i++){
				for(int j=0;j<queryword.length;j++){
					score[i] = score[i] + result[j][i];
				}
				
				
			}
			double total = 0;
			for(int i = 0;i<score.length;i++){
				total = total+ score[i];
			}
			if(total<=0){
				System.out.println("File Not Found!");
			}
			double[][] scoreRank = new double[2][score.length];
			for(int i =0;i<score.length;i++){
				scoreRank[0][i]=i;
				scoreRank[1][i]=score[i];
			}
			for(int i = 0 ;i<score.length;i++){
				for(int j = 0;j<score.length;j++){
					if(scoreRank[1][j]<scoreRank[1][i]){
						double tem = 0;
						tem = scoreRank[1][i];
						scoreRank[1][i]=scoreRank[1][j];
						scoreRank[1][j]=tem;
						tem = scoreRank[0][i];
						scoreRank[0][i]=scoreRank[0][j];
						scoreRank[0][j]=tem;
						
					}
				}
			}
			for(int i =0;i<score.length;i++){
				if(scoreRank[1][i]>0){
					
					int docid =(int)scoreRank[0][i];
					System.out.println("docID:"+docid+"-----rank score:"+scoreRank[1][i]);
					System.out.println(new RetriveMessages().retriveMessages(docid, filepath));
					
				}
			}
			}
		}
		
}
