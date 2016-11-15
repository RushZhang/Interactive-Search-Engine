package crawler.queryengine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
* @author Icer
*/
public class TestDOM4j{
/**
*
* @param fileName 生成的xml文件名
* @param txtName 包含的对账文件txt文件名
*/
public void createXml(String fileName,String txtName) {
Document document = DocumentHelper.createDocument();
Element root = document.addElement("root");
Element head = root.addElement("head");
Element type = head.addAttribute("type", "0");
Element code = head.addAttribute("code", "3003");
Element yhlb = head.addElement("yhlb");
yhlb.setText("01");
Element username = head.addElement("username");
username.setText("gsyh");
Element password = head.addElement("password");
password.setText("zheshimima");
Element body = root.addElement("body");
Element data = body.addElement("data");
Element dzwjm = data.addElement("dzwjm");
dzwjm.setText(txtName);
try {
//写入文件
Writer fileWriter = new FileWriter(fileName);
OutputFormat format = OutputFormat.createPrettyPrint();
XMLWriter xmlWriter = new XMLWriter(fileWriter,format);
xmlWriter.write(document);
xmlWriter.close();
} catch (IOException e) {
System.out.println(e.getMessage());
}
}
/**
*
* @param fileName 要解析的文件名
* @return 解析xml文件得到的需要对账的文件名
*/
public String parserXml(String fileName) {
String findFileName = "";
File inputXml = new File(fileName);
SAXReader saxReader = new SAXReader();
try {
Document document = saxReader.read(inputXml);
Element root = document.getRootElement();
for(Iterator i = root.elementIterator();i.hasNext();){
Element head = (Element) i.next();
for(Iterator j = head.elementIterator();j.hasNext();){
Element elem = (Element) j.next();
System.out.println(elem.getName()+":"+elem.getText());
for(Iterator k=elem.elementIterator();k.hasNext();){
Element last = (Element) k.next();
System.out.println(last.getName()+":"+last.getText());
findFileName = last.getText();
}
}
}
} catch (DocumentException e) {
System.out.println(e.getMessage()+"hello");
}
System.out.println("dom4j parserXml");
return findFileName;
}
/**
* 测试main方法
* @param args
*/
public static void main(String[] args){
TestDOM4j demo = new TestDOM4j();
demo.createXml("/Users/Alex/Desktop/request.xml","test.txt");
demo.parserXml("e://request.xml");
}
}