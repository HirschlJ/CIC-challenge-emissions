package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import db.DBHandlerArrayList;
import db.IDBHandler;
import filter.EmissionFilter;
import filter.EmissionFilter.EmissionOperator;

/**
 * Servlet implementation for REST Interface using a database as datasource
 */
public class RESTInterface extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	IDBHandler dbHandler;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RESTInterface()
	{
		super();
		dbHandler = DBHandlerArrayList.INSTANCE;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//create Filter
		EmissionFilter filter = EmissionFilter.createFilter(request);
		
		if(filter.getDepartment() == null && filter.getCommodity() == null && filter.getEmission() == null)
		{
			JSONArray jsArray = new JSONArray(dbHandler.getAllEmissionData());
			response.getWriter().append(jsArray.toString());
		}
		else
		{
			JSONArray jsArray = new JSONArray(
					dbHandler.getEmissionDataFiltered(filter));
			response.getWriter().append(jsArray.toString());
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO get model depending on filter
		doGet(request, response);
	}
	
}
