package db;

import java.util.List;

import model.EmissionData;

public interface IDBHandler
{
	public List<EmissionData> getAllEmissionData();
	public List<EmissionData> getEmissionDataFiltered(String department, String commodity, Double emissions);
	public boolean addData(EmissionData emissionData);
	public boolean addData(List<EmissionData> emissionDataList);
	public boolean deleteData(EmissionData emissionData);
	public boolean deleteData(List<EmissionData> emissionDataList);
	public boolean setData(EmissionData emissionData, EmissionData newEmissionData);
	public boolean setData(List<EmissionData> emissionDataList,List<EmissionData> newEmissionDataList);
}
