package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import filter.EmissionFilter;
import filter.EmissionFilter.EmissionOperator;
import model.EmissionData;

/**
 * Servlet implementation for REST Interface using a REST Interface as
 * datasource
 */
public class RESTInterfaceAlternative extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RESTInterfaceAlternative()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//create Filter
		EmissionFilter filter = EmissionFilter.createFilter(request);
		
		// get data from website
		String urlString = "https://data.sfgov.org/resource/pxac-sadh.json?$limit=50000&$where=emissions_mtco2e%20>%200.00";
		String data = getStringDataFromURL(urlString);

		// create ArrayList from JSON data and remove doubles
		List<EmissionData> listData = jsonArrayStringToList(data);
		
		//return complete List..
		if(filter.getDepartment() == null && filter.getCommodity() == null && filter.getEmission() == null)
		{
			JSONArray jsArray = new JSONArray(listData);
			response.getWriter().append(jsArray.toString());
		}
		//or filtered List
		else
		{
			List<EmissionData> filteredList = new ArrayList<EmissionData>();
			for(EmissionData d : listData)
			{
				if(filter.filter(d)) filteredList.add(d);
			}
			JSONArray jsArray = new JSONArray(filteredList);
			response.getWriter().append(jsArray.toString());
		}
	}

	/**
	 * @param data - String containing the JSON Array with EmissionData Objects
	 * @return - a List containing all EmissionData Objects from the Array
	 */
	private List<EmissionData> jsonArrayStringToList(String data)
	{
		List<EmissionData> listData = new ArrayList<EmissionData>();
		JSONArray jsonData = new JSONArray(data);
		Set<EmissionData> setData = new LinkedHashSet<EmissionData>();
		for(int i = 0; i < jsonData.length(); i++)
		{
			JSONObject o = jsonData.getJSONObject(i);
			if(o.getDouble("emissions_mtco2e") > 0.0)
			{
				setData.add(new EmissionData(o.getString("department"), o.getString("source_type"),
						o.getDouble("emissions_mtco2e")));
			}
		}
		listData.addAll(setData);
		return listData;
	}

	/**
	 * Method used to get a String containing JSON data from a website.
	 * @param urlString - URL of the website as String.
	 * @return Data from the website as String.
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private String getStringDataFromURL(String urlString) throws MalformedURLException, IOException
	{
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();
		InputStream is = connection.getInputStream();
		String data = IOUtils.toString(is, StandardCharsets.UTF_8);
		return data;
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
	
}
