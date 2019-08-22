package db;

import java.util.List;

import filter.EmissionFilter;
import model.EmissionData;

public interface IDBHandler
{
	
	/**
	 * 
	 * @return returns a list of all data objects in the database
	 */
	public List<EmissionData> getAllEmissionData();
	/**
	 * 
	 * @param filter - the filter used on the databases data
	 * @return returns a filtered list of the data objects in the database
	 */
	public List<EmissionData> getEmissionDataFiltered(EmissionFilter filter);
	/**
	 * 
	 * @param emissionData - object to add to the database
	 * @return true if the operation is successful. otherwise false.
	 */
	public boolean addData(EmissionData emissionData);
	/**
	 * 
	 * @param emissionDataList - a list of objects to add to the database
	 * @return a list with boolean values corresponding to the success of 
	 * the elements in emissionDataList (true if successful, otherwise false).
	 */
	public List<Boolean> addData(List<EmissionData> emissionDataList);
	/**
	 * 
	 * @param emissionData - object to remove from the database
	 * @return true if the operation is successful. otherwise false.
	 */
	public boolean deleteData(EmissionData emissionData);
	/**
	 * 
	 * @param emissionDataList - a list of objects to remove from the database
	 * @return a list with boolean values corresponding to the success of 
	 * the elements in emissionDataList (true if successful, otherwise false).
	 */
	public List<Boolean> deleteData(List<EmissionData> emissionDataList);
	/**
	 * 
	 * @param emissionData - object to change in the database
	 * @param newEmissionData - object containing the changes
	 * @return true if the operation is successful. otherwise false.
	 */
	public boolean setData(EmissionData emissionData, EmissionData newEmissionData);
	/**
	 * 
	 * @param emissionDataList - list of objects to change in the database
	 * @param newEmissionDataList - list of objects containing the changes
	 * @return a list with boolean values corresponding to the success of 
	 * the elements in emissionDataList (true if successful, otherwise false).
	 * Returns null if the lists don't match in size.
	 */
	public List<Boolean> setData(List<EmissionData> emissionDataList,List<EmissionData> newEmissionDataList);
}
