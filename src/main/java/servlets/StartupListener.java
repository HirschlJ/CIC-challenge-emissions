package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import model.EmissionData;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import db.DBHandlerArrayList;
import db.IDBHandler;

public class StartupListener implements ServletContextListener
{
	public void contextInitialized(ServletContextEvent sce)
	{
		String CSV = "San_Francisco_Municipal_Greenhouse_Gas_Inventory.csv";
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CSV);
		InputStreamReader ir = new InputStreamReader(is, StandardCharsets.UTF_8);
		
		IDBHandler db = DBHandlerArrayList.INSTANCE;
		
		List<EmissionData> data = new ArrayList<EmissionData>();
		String department = "";
		String commodity = "";
		double emission = 0.0;
		
		try
		{
			boolean header = true;
			for(CSVRecord record : CSVFormat.DEFAULT.parse(ir))
			{
				if(header) header = false;
				else
				{
					department = record.get(0);
					commodity = record.get(1);
					emission = Double.valueOf(record.get(4));
					if(emission > 0.0) data.add(new EmissionData(department, commodity, emission));
				}
			}
			ir.close();
			is.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		db.addData(data);
	}
}
