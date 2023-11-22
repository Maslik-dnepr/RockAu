import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RockAuto
{
	public void getItemsFromPage(String url) throws IOException
	{
		Document doc=Jsoup.connect("https://www.rockauto.com/en/catalog")
			    .get();
		Element el=doc.getElementById("section-tabs");
		
		FileWriter writer = new FileWriter("res.txt", true);
		writer.write(el.toString());
		writer.close();
		//System.out.println(el);
	}

//========LEVEL ----  1 ==========================================================================	
	public Elements get0CatElems() throws IOException
	{
		Elements res;
		Document doc=Jsoup.connect("https://www.rockauto.com/en/catalog")
			    .get();
		res=doc.getElementsByClass("nlabel");
		return res;
	}
	
//========LEVEL ----  OTHER ==========================================================================	
	public Elements getCatElems(String url, int level) throws IOException
	{
		Elements res=new Elements();
		Document doc=Jsoup.connect(url)
			    .get();
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
	public ArrayList<Item> getProductList(String url) throws IOException
	{
		ArrayList<Item> res=new ArrayList<>();
		//listing-text-row-moreinfo-truck
		Document doc=Jsoup.connect(url)
			    .get();
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
				price=el.getElementsByClass("ra-formatted-amount listing-price listing-amount-bold").get(0).text();	
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
