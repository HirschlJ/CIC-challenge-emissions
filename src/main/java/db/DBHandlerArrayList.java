package db;

import java.util.ArrayList;
import java.util.List;

import filter.EmissionFilter;
import model.EmissionData;

/**
 * Simple implementation of IDBHandler using and ArrayList as database
 * @author jakobhirschl
 *
 */
public enum DBHandlerArrayList implements IDBHandler
{
	INSTANCE;
	private List<EmissionData> data;
	
	private DBHandlerArrayList()
	{
		data = new ArrayList<EmissionData>();
	}

	public List<EmissionData> getAllEmissionData()
	{
		return data;
	}

	public List<EmissionData> getEmissionDataFiltered(EmissionFilter filter)
	{
		List<EmissionData> filteredData = new ArrayList<EmissionData>();
		for(EmissionData d : data)
		{
			if(filter.filter(d))
				filteredData.add(d);
		}
		
		return filteredData;
	}

	public boolean addData(EmissionData emissionData)
	{
		for(EmissionData d : data)
		{
			if(emissionData.equals(d))
				return false;
			
		}
		data.add(emissionData);
		return true;
	}

	public List<Boolean> addData(List<EmissionData> emissionDataList)
	{
		List<Boolean> returnValues = new ArrayList<Boolean>();
		for(EmissionData ad : emissionDataList)
		{
			boolean add = true;
			for(EmissionData d : data)
			{
				if(ad.equals(d))
				{
					add = false;
					returnValues.add(false);
					break;
				}
				
			}
			if(add)
			{
				returnValues.add(true);
				data.add(ad);
			}
		}
		return returnValues;
	}

	public boolean deleteData(EmissionData emissionData)
	{
		return data.remove(emissionData);
	}

	public List<Boolean> deleteData(List<EmissionData> emissionDataList)
	{
		List<Boolean> returnValues = new ArrayList<Boolean>();
		for(EmissionData rd : emissionDataList)
		{
			returnValues.add(data.remove(rd));
		}
		return returnValues;
	}

	public boolean setData(EmissionData emissionData, EmissionData newEmissionData)
	{
		for(EmissionData d : data)
		{
			if(emissionData.equals(d))
			{
				d.setCommodity(newEmissionData.getCommodity());
				d.setDepartment(newEmissionData.getDepartment());
				d.setEmission(newEmissionData.getEmission());
				return true;
			}
			
		}
		return false;
	}

	public List<Boolean> setData(List<EmissionData> emissionDataList, List<EmissionData> newEmissionDataList)
	{
		if(emissionDataList.size()!=newEmissionDataList.size()) return null;
		List<Boolean> returnValues = new ArrayList<Boolean>();
		for(EmissionData sd : emissionDataList)
		{
			boolean changed = false;
			for(EmissionData d : data)
			{
				if(sd.equals(d))
				{
					EmissionData newEmissionData = newEmissionDataList.get(emissionDataList.indexOf(sd));
					d.setCommodity(newEmissionData.getCommodity());
					d.setDepartment(newEmissionData.getDepartment());
					d.setEmission(newEmissionData.getEmission());
					returnValues.add(true);
					changed = true;
					break;
				}
			}
			if(!changed)
				returnValues.add(false);
		}
		return returnValues;
	}
	
	@Override
	public String toString()
	{
		String stringData = "Data: \n";
		for(int i = 0; i < data.size(); i++)
		{
			stringData += "Number " + i + ": " + data.get(i).toString() + "\n";
		}
		return stringData;
	}
	
}
