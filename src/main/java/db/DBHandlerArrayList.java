package db;

import java.util.ArrayList;
import java.util.List;

import model.EmissionData;

public class DBHandlerArrayList implements IDBHandler
{
	List<EmissionData> data;
	
	public DBHandlerArrayList()
	{
		data = new ArrayList<EmissionData>();
	}
	
	public DBHandlerArrayList(List<EmissionData> data)
	{
		this.data = data;
	}

	public List<EmissionData> getAllEmissionData()
	{
		return data;
	}

	public List<EmissionData> getEmissionDataFiltered(String department, String commodity, Double emissions)
	{
		List<EmissionData> filteredData = new ArrayList<EmissionData>();
		if(department != null)
		{
			
		}
		if(commodity != null)
		{
			
		}
		if(emissions != null)
		{
			
		}
		
		return filteredData;
	}

	public boolean addData(EmissionData emissionData)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addData(List<EmissionData> emissionDataList)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteData(EmissionData emissionData)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteData(List<EmissionData> emissionDataList)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setData(EmissionData emissionData, EmissionData newEmissionData)
	{
		// TODO Auto-generated method stub
		return false;
	}

	public boolean setData(List<EmissionData> emissionDataList, List<EmissionData> newEmissionDataList)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
