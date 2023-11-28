import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

public class run
{

	public static void main(String[] args) throws IOException, InterruptedException, ParserConfigurationException, SAXException
	{
		System.out.println("Reading config:");
		File configfile=new File(".\\config.xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document configdoc = db.parse(configfile);
		String resultcsv=configdoc.getElementsByTagName("resultcsv").item(0).getTextContent();
		int index0=Integer.valueOf(configdoc.getElementsByTagName("cat0").item(0).getTextContent());
		int index1=Integer.valueOf(configdoc.getElementsByTagName("cat1").item(0).getTextContent());
		int index2=Integer.valueOf(configdoc.getElementsByTagName("cat2").item(0).getTextContent());
		
		int delay =Integer.valueOf(configdoc.getElementsByTagName("delay").item(0).getTextContent());
		int maxtries =Integer.valueOf(configdoc.getElementsByTagName("maxtries").item(0).getTextContent());
		
		System.out.println(resultcsv);
		System.out.println(index0+" - "+index1+" - "+index2);
		System.out.println(delay+" - "+maxtries);
		
		
		
		ArrayList<Item> result=new ArrayList<>();
		
//		Document doc=Jsoup.connect("https://www.rockauto.com/en/catalog/acura,2021,ilx,2.4l+l4,3447134,brake+&+wheel+hub,brake+fluid,11389")
//			    .get();
//		FileWriter writer = new FileWriter("page.txt", false);
//		writer.write(doc.toString());
//		writer.close();
		
		
		RockAuto parser=new RockAuto();
		Element el0=parser.getCatElems("https://www.rockauto.com/en/catalog/", 0,delay,maxtries).get(index0);
		String cat0=parser.getElemCategory(el0);
		System.out.println(cat0);
		
		Elements els1=parser.getCatElems(parser.getElemUrl(el0), 1, delay,maxtries);
		Element el1=els1.get(index1);
		String cat1=parser.getElemCategory(el1);
		System.out.println(" - "+cat1);
		
		Elements els2=parser.getCatElems(parser.getElemUrl(el1), 2, delay,maxtries);
		Element el2=els2.get(index2);
		String cat2=parser.getElemCategory(el2);
		System.out.println("   -"+cat2);
		
		CSV csv=new CSV(resultcsv);
		
		Elements els3=parser.getCatElems(parser.getElemUrl(el2), 3, delay,maxtries);
		for (Element el3 : els3)
		{
			String cat3=parser.getElemCategory(el3);
			System.out.println("       "+cat3);
			Elements els4=parser.getCatElems(parser.getElemUrl(el3), 4,delay,maxtries);
			for (Element el4 : els4)
			{
				String cat4=parser.getElemCategory(el4);
				System.out.println("         "+cat4);
				Elements els5=parser.getCatElems(parser.getElemUrl(el4), 5, delay,maxtries);
				for (Element el5 : els5)
				{
					String cat5=parser.getElemCategory(el5);
					System.out.println("           "+cat5);
					Thread.sleep(delay);
					ArrayList<Item> temp=parser.getProductList(parser.getElemUrl(el5), delay,maxtries);
					for (Item i : temp)
					{
						i.cat0=cat0;
						i.cat1=cat1;
						i.cat2=cat2;
						i.cat3=cat3;
						i.cat4=cat4;
						i.cat5=cat5;
					}
					result.addAll(temp);
				}
				
			}
		}
		csv.writeCSV(result);
		
		
		
		
		
		
//		
//		Element el0=parser.getCatElems("https://www.rockauto.com/en/catalog/", 0).get(Integer.valueOf(categoryid));
//		String cat0=parser.getElemCategory(el0);
		
//		CSV csv=new CSV(cat0+".csv");
		
//		System.out.println(cat0);
//		Elements els1=parser.getCatElems(parser.getElemUrl(el0), 1);
//		for (Element el1 : els1)
//		{
//			String cat1=parser.getElemCategory(el1);
//			System.out.println("  "+cat1);
//			Thread.sleep(delay);
//			Elements els2=parser.getCatElems(parser.getElemUrl(el1), 2);
//			for (Element el2 : els2)
//			{
//				String cat2=parser.getElemCategory(el2);
//				System.out.println("   "+cat2);
//				Thread.sleep(delay);
//				Elements els3=parser.getCatElems(parser.getElemUrl(el2), 3);
//				for (Element el3 : els3)
//				{
//					String cat3=parser.getElemCategory(el3);
//					System.out.println("     "+cat3);
//					Thread.sleep(delay);
//					Elements els4=parser.getCatElems(parser.getElemUrl(el3), 4);
//					for (Element el4 : els4)
//					{
//						String cat4=parser.getElemCategory(el4);
//						System.out.println("       "+cat4);
//						Thread.sleep(delay);
//						Elements els5=parser.getCatElems(parser.getElemUrl(el4), 5);
//						for (Element el5 : els5)
//						{
//							String cat5=parser.getElemCategory(el5);
//							System.out.println("         "+cat5);
//							Thread.sleep(delay);
//							ArrayList<Item> temp=parser.getProductList(parser.getElemUrl(el5));
//							for (Item i : temp)
//							{
//								i.cat0=cat0;
//								i.cat1=cat1;
//								i.cat2=cat2;
//								i.cat3=cat3;
//								i.cat4=cat4;
//								i.cat5=cat5;
//							}
//							result.addAll(temp);
//							csv.writeCSV(result);
//						}
//						
//					}
//				}
//			}
//		}
			

		
	}

}
