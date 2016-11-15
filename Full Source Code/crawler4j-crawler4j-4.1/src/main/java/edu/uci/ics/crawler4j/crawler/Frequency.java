package edu.uci.ics.crawler4j.crawler;


	import java.util.Map;
import java.util.TreeMap;

	public class Frequency {
		private TreemapFre treemapfre = new TreemapFre();
		private TreeMap<String , Integer> myTreemap;
		public void setTreemapfre(String path){
			treemapfre.setPath(path);
		}
		public TreeMap<String, Integer> getTreemapfre(){
			return this.myTreemap;
		}
		public void FrequencyCounter() {
			
			try {
	      int totalWords = 0;   
	      TreeMap<String, Integer> freqMap = treemapfre.generateFrequencyList();
	      myTreemap =freqMap;
	      for (String key : freqMap.keySet()) {
	        totalWords += freqMap.get(key);
	      }

	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	}

