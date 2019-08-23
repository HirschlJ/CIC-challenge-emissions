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
		String department = request.getParameter("department");
		String commodity = request.getParameter("commodity");
		Double emission = null;
		if(request.getParameter("emission") != null) emission = Double.valueOf(request.getParameter("emission"));
		if(department == null && commodity == null && emission == null)
		{
			JSONArray jsArray = new JSONArray(dbHandler.getAllEmissionData());
			response.getWriter().append(jsArray.toString());
		}
		else
		{
			JSONArray jsArray = new JSONArray(
					dbHandler.getEmissionDataFiltered(new EmissionFilter(department, commodity, emission)));
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
