import java.awt.Toolkit;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpConnectTimeoutException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RockAuto
{

//========LEVELS ----  ALL ==========================================================================	
	public Elements getCatElems(String url, int level,int delay,int maxtries) throws IOException, InterruptedException
	{
		Document doc=null;
		Elements res=new Elements();
		
		int count = 0;
		while (true)
		{
			try 
			{
				doc=Jsoup.connect(url)
					.timeout(delay)
				    .get();
			break;
			}catch (Exception e)
			{
				//Toolkit.getDefaultToolkit().beep();Toolkit.getDefaultToolkit().beep();
				System.out.println("!!!!!!!!!!!!!!!! ");

//				String answer="";
//				do
//				{
//					answer = JOptionPane.showInputDialog("Keyword?");						
//				}
//				while (!answer.equals("go"));
//				System.out.println("...continue");	
//				
//				if (++count == maxtries) 
//				{
//					System.out.println("Fuck!");
//					break;
//				}
			}
		}
			
		Elements els=doc.getElementsByClass("nlabel");
		int threshold=(doc.getElementsByClass("ra-btn closeouts-btn").size()>0?els.size()-1:els.size());
		for (int i = level; i < threshold; i++)
		{
			res.add(els.get(i));
		}
		return res;
	}
//========IS PRODUCT PAGE ??? ==============================================================
	public Boolean isProductListPage(String url) throws IOException
	{
		Document doc=Jsoup.connect(url)
			    .get();
		return (doc.getElementsByClass("tblz tbl-input-button-attached attached-filter-size").size()>0?true:false); 
	}
	
//========PRODUCT LINKS ==========================================================================		
	public ArrayList<Item> getProductList(String url, int delay, int maxtries) throws IOException, InterruptedException
	{
		Document doc=null;
		ArrayList<Item> res=new ArrayList<>();

		int count = 0;
		while (true)
		{	
			try
			{
				doc=Jsoup.connect(url)
						.timeout(delay)
						.get();
				break;
			}catch (Exception e)
			{
				//Toolkit.getDefaultToolkit().beep();Toolkit.getDefaultToolkit().beep();
				System.out.println("!!!!!!!!!!!!!!!! ");
				
//				String answer="";
//				do
//				{
//					answer = JOptionPane.showInputDialog("Keyword?");
//				}
//				while (!answer.equals("go"));
//				System.out.println("...continue");	
//
//				if (++count == maxtries) 
//				{
//					System.out.println("Fuck!");
//					break;
//				}	
			}
		}
			
		//Elements els=doc.getElementsByClass("listing-border-top-line listing-inner-content");
		Elements els=doc.getElementsByAttributeValueStarting("class", "listing-inner altrow-a-");
		String manufacturer,pn,adinfo,price,link;
		//System.out.println(els);
		for (Element el : els)
		{
			try
			{
				manufacturer=el.getElementsByClass("listing-final-manufacturer ").get(0).text();	
			} catch (IndexOutOfBoundsException e)
			{
				manufacturer="";
			}
			try
			{
				pn=el.getElementsByClass("listing-final-partnumber  as-link-if-js").get(0).text();	
			} catch (IndexOutOfBoundsException e)
			{
				pn="";
			}
			try
			{
				adinfo=el.getElementsByClass("span-link-underline-remover").get(0).text();	
			} catch (IndexOutOfBoundsException e)
			{
				adinfo="";
			}
			try
			{
				price=el.getElementsByClass("ra-formatted-amount listing-total").get(0).text();

			} catch (IndexOutOfBoundsException e)
			{
				price="";
			}
			try
			{
				link=el.getElementsByTag("a").get(0).attr("href");	
			} catch (IndexOutOfBoundsException e)
			{
				link="";
			}
			res.add(new Item("", "", "", "", "", "", manufacturer, pn, adinfo, price, link));
		}
		return res;
	}
	
//========SERVICE ==========================================================================		
	public String getElemUrl(Element el)
	{
		return "https://www.rockauto.com"+el.getElementsByTag("a").get(0).attr("href");
	}
	public String getElemCategory(Element el)
	{
		return el.getElementsByTag("td").get(0).text();
	}
//
}
