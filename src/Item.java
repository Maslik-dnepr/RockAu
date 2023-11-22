
public class Item
{
	String cat0;
	String cat1;
	String cat2;
	String cat3;
	String cat4;
	String cat5;
	String manufacturer;
	String pn;
	String adinfo;
	String price;
	String link;

	
	public Item(String cat0, String cat1, String cat2, String cat3, String cat4, String cat5, String manufacturer,
			String pn, String adinfo, String price, String link)
	{
		this.cat0 = cat0;
		this.cat1 = cat1;
		this.cat2 = cat2;
		this.cat3 = cat3;
		this.cat4 = cat4;
		this.cat5 = cat5;
		this.manufacturer = manufacturer;
		this.pn = pn;
		this.adinfo = adinfo;
		this.price = price;
		this.link = link;
	}


	@Override
	public String toString()
	{
		return "Item [cat0=" + cat0 + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3 + ", cat4=" + cat4
				+ ", cat5=" + cat5 + ", manufacturer=" + manufacturer + ", pn=" + pn + ", adinfo=" + adinfo + ", price="
				+ price + ", link=" + link + "]";
	}
	
}
