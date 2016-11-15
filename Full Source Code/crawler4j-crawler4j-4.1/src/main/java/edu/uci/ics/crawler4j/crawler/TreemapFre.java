package edu.uci.ics.crawler4j.crawler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class TreemapFre{
	private static String path="";
	public static TreeMap <String, Integer> generateFrequencyList()
		    throws IOException {
		    TreeMap<String, Integer> wordsFrequencyMap = new TreeMap<String, Integer>();
		    String file = path;
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    while( (line = br.readLine()) != null){
		         String [] tokens = line.split("\\s+");
		      for (String token : tokens) {
		        token = removePunctuation(token);
		        if (!wordsFrequencyMap.containsKey(token.toLowerCase())) {
		          wordsFrequencyMap.put(token.toLowerCase(), 1);
		        } else {
		          int count = wordsFrequencyMap.get(token.toLowerCase());
		          wordsFrequencyMap.put(token.toLowerCase(), count + 1);
		        }
		      }
		    }
		    return wordsFrequencyMap;
		  }
/*		public static <K, V extends Comparable<V>> Map<K, V> sortByValues(final Map<K, V> map) {
			Comparator<K> valueComparator =  new Comparator<K>() {
				public int compare(K k1, K k2) {
					int compare = map.get(k2).compareTo(map.get(k1));
					if (compare == 0) return 1;
					else return compare;
				}
		};
	    Map<K, V> sortedByValues = new TreeMap<K, V>(valueComparator);
	    sortedByValues.putAll(map);
	    return sortedByValues;
	}
	*/
		public void setPath(String path){
			this.path = path;
		}
		  private static String removePunctuation(String token) {
		    token = token.replaceAll("[^a-zA-Z]", "");
		    return token;
		  }
		}