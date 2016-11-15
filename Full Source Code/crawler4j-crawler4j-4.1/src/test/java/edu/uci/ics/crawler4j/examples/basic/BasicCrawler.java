/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uci.ics.crawler4j.examples.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.http.Header;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.Frequency;
import edu.uci.ics.crawler4j.crawler.MyStemmer;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author Yasser Ganjisaffar [lastname at gmail dot com]
 */
public class BasicCrawler extends WebCrawler {

	public static String crawlStorageFolder;
	public static int NumberofFiletoStem=0;
	String pageList="";
	String whole = "";
	static String Frequencycount="";
	int NumberOfjpgfile=0;
  private final static Pattern BINARY_FILES_EXTENSIONS =
        Pattern.compile(".*\\.(bmp|gif|png|)" +
        "(\\?.*)?$"); 
  private final static Pattern JPG_FILES_EXTENSIONS =
	        Pattern.compile(".*\\.(jpg)" +"(\\?.*)?$");
  
	
	//Checks if word is on stopWordsList
	public static boolean isStopWord(List<String> stopWordsList, String word)
	{
		if (stopWordsList.contains(word))
		{
			return true; 
		}
		else
		{
			return false;
		}
	}
	
	
  /**
   * You should implement this function to specify whether the given url
   * should be crawled or not (based on your crawling logic).
   */
  @Override
  public boolean shouldVisit(Page page, WebURL url) {
    String href = url.getURL().toLowerCase();
    if ( JPG_FILES_EXTENSIONS.matcher(href).matches()&&href.startsWith("http://lyle.smu.edu/~fmoore/")){
    	NumberOfjpgfile++;
    }
    return !BINARY_FILES_EXTENSIONS.matcher(href).matches() && href.startsWith("http://lyle.smu.edu/~fmoore/");
  }

  /**
   * This function is called when a page is fetched and ready to be processed
   * by your program.
   */
  @Override
  public void visit(Page page) {
    int docid = page.getWebURL().getDocid();
    String url = page.getWebURL().getURL();
    String domain = page.getWebURL().getDomain();
    String path = page.getWebURL().getPath();
    String subDomain = page.getWebURL().getSubDomain();
    String parentUrl = page.getWebURL().getParentUrl();
    String anchor = page.getWebURL().getAnchor();
    
    File pageListAndOutgoingNumber = new File(crawlStorageFolder+"/pageListAndOutgoingNumber.txt");
    File wholeFile = new File(crawlStorageFolder+"/wholeFile.txt");
    new DocIDtoURLWriter().docIDtoURLWrite(crawlStorageFolder, docid, url);
    //File CounterFile = new File(crawlStorageFolder+"/FrequencyCount.txt");
    

    logger.debug("Docid: {}", docid);
    logger.info("URL: ", url);
    logger.debug("Domain: '{}'", domain);
    logger.debug("Sub-domain: '{}'", subDomain);
    logger.debug("Path: '{}'", path);
    logger.debug("Parent page: {}", parentUrl);
    logger.debug("Anchor text: {}", anchor);
//    Scanner scanner;
   	try {
   		
//   		scanner = new Scanner(new File(crawlStorageFolder+"/stopwords.txt"));
//   		ArrayList<String> sw = new ArrayList<String>();
//   	      while (scanner.hasNext()){
//   	          sw.add(scanner.next());
// //  	          System.out.println(sw);
//   	      }
//   	      scanner.close();
   
    if (page.getParseData() instanceof HtmlParseData) {
      HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
      String text = htmlParseData.getText();
     text = text.toLowerCase();
//     for(int i=0;i<sw.size();i++){
//    	 text = text.replace(" "+sw.get(i) +" "," ");
//     	}

  
	File storagesFolder = new File(crawlStorageFolder+"/Pages");
    if (!storagesFolder.exists()) {
      storagesFolder.mkdirs();
    }
      String filename = storagesFolder.getAbsolutePath() + "/" + docid+".txt";
        FileWriter filewrite = new FileWriter(new File(filename));
        filewrite.write(text);
        filewrite.flush();
        filewrite.close();
        NumberofFiletoStem=docid;
    	logger.info("Stored: {}", url);
      String html = htmlParseData.getHtml();
      Set<WebURL> links = htmlParseData.getOutgoingUrls();
      
      pageList = pageList +url.toString()+"\n"+"Number of outgoing links:"+links.size()+"\n";
      whole = whole+text+"\n";
 //     filewrite1.write(url.toString());
//      filewrite1.write("\n");
//      filewrite1.write("Number of outgoing links:"+links.size());
//      filewrite1.flush();
//      filewrite1.close();
      logger.debug("Text length: {}", text.length());
      logger.debug("Html length: {}", html.length());
      logger.debug("Number of outgoing links: {}", links.size());
    }
	}
  catch (FileNotFoundException e) {
 		// TODO Auto-generated catch block
 		e.printStackTrace();
 	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   	try {
   		FileWriter filewrite1 = new FileWriter(pageListAndOutgoingNumber);
		filewrite1.write(pageList);
		filewrite1.flush();
		filewrite1.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
   		FileWriter filewrite2 = new FileWriter(wholeFile);
		filewrite2.write(whole);
		filewrite2.flush();
		filewrite2.close();
		
	      
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("Number of jpg File:"+NumberOfjpgfile);
    Header[] responseHeaders = page.getFetchResponseHeaders();
    if (responseHeaders != null) {
      logger.debug("Response headers:");
      for (Header header : responseHeaders) {
        logger.debug("\t{}: {}", header.getName(), header.getValue());
      }
    }

    logger.debug("=============");
  }
}