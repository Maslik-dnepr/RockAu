import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CSV
{
	String csv_file;
	public CSV(String csv_file)
	{
		this.csv_file = csv_file;
	}
	
	public void writeCSV(ArrayList<Item> ar) throws IOException
	{	
//		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream(csv_file), "UTF8"));
		FileWriter writer = new FileWriter(csv_file, true);
		
		for (Item item : ar)
		{	
			writer.write(item.cat0+"~"+item.cat1+"~"+item.cat2+"~"+
						item.cat3+"~"+item.cat4+"~"+
						item.cat5+"~"+item.manufacturer+"~"+item.pn+"~"+
						item.adinfo+"~"+item.price+"~"+item.link+"\n");
		}
//		writer.flush();
		writer.close();
		
		
		
	}
}
