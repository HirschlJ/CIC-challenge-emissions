package tests;

import java.util.ArrayList;
import java.util.List;

import db.DBHandlerArrayList;
import db.IDBHandler;
import filter.EmissionFilter;
import model.EmissionData;

public class TestClass
{
	
	public static void main(String[] args)
	{
		testDBHandler(DBHandlerArrayList.INSTANCE);
	}
	
	private static void testDBHandler(IDBHandler db)
	{
		boolean test;
		List<Boolean> testList;
		
		System.err.println("add Data test");
		//add data
		test = db.addData(new EmissionData("d1", "c1", 1));
		if(!test) System.err.println("Test: add data failed");
		
		//add data list
		List<EmissionData> list = new ArrayList<EmissionData>();
		list.add(new EmissionData("d2", "c2", 2));
		list.add(new EmissionData("d3", "c3", 3));
		testList = db.addData(list);
		for(Boolean t : testList)
		{
			if(!t) System.err.println("Test: add data list failed");
		}
		
		System.err.println(db.toString());
		
		
		System.err.println("add Data duplicate test");
		//add data duplicate
		test = db.addData(new EmissionData("d1", "c1", 1));
		if(test) System.err.println("Test: add data duplicate failed");
				
		//add data list duplicate
		testList = db.addData(list);
		for(Boolean t : testList)
		{
			if(t) System.err.println("Test: add data list duplicate failed");
		}
		
		System.err.println(db.toString());
		
		System.err.println("Get Data Test");
		
		List<EmissionData> getDataList = new ArrayList<EmissionData>();
		getDataList.add(new EmissionData("d1", "c1", 1));
		getDataList.add(new EmissionData("d2", "c2", 2));
		getDataList.add(new EmissionData("d3", "c3", 3));
		
		//get all data test
		if(!getDataList.containsAll(db.getAllEmissionData())) System.err.println("Test: get all data test failed");
		
		//get data filtered test
		EmissionFilter filter1 = new EmissionFilter("d2", "c2", 2.0);
		EmissionFilter filter2 = new EmissionFilter("d2", null, null);
		EmissionFilter filter3 = new EmissionFilter(null, null, 3.0);
		if(!getDataList.get(1).equals(db.getEmissionDataFiltered(filter1).get(0))) System.err.println("Test1: get data filtered test failed");
		if(!getDataList.get(1).equals(db.getEmissionDataFiltered(filter2).get(0))) System.err.println("Test2: get data filtered test failed");
		if(!getDataList.get(2).equals(db.getEmissionDataFiltered(filter3).get(0))) System.err.println("Test3: get data filtered test failed");
		
		System.err.println(db.toString());
		
		System.err.println("Set Data Test");
		//set data test
		if(db.setData(new EmissionData("d1", "c1", 1), new EmissionData("d1", "c1", 2)))
		{
			if(!db.getAllEmissionData().get(0).equals(new EmissionData("d1", "c1", 2)))
				System.err.println("Test compare: set data test failed");
		}
		else System.err.println("Test set: set data test failed");
		if(db.setData(new EmissionData("d1", "c1", 1), new EmissionData("d1", "c1", 2)))
			System.err.println("Test set: set data test failed");
		
		List<EmissionData> chageToList = new ArrayList<EmissionData>();
		chageToList.add(new EmissionData("d1", "c1", 2));
		chageToList.add(new EmissionData("d2", "c2", 3));
		chageToList.add(new EmissionData("d3", "c3", 4));
		
		//set data list test
		testList = db.setData(getDataList, chageToList);
		test = false;
		for(Boolean t : testList)
		{
			if(!test==t) System.err.println("Test: set data list failed");
			test = true;
		}
		
		System.err.println(db.toString());
		
		System.err.println("Remove data test");
		//remove data test
		if(db.deleteData(new EmissionData("d1", "c1", 2)))
		{
			if(db.getAllEmissionData().size()!=2)
				System.err.println("Test1 compare: set data test failed");
		}
		else System.err.println("Test1 delete: remove data test failed");
		if(db.deleteData(new EmissionData("d1", "c1", 2)))
			System.err.println("Test2: remove data test failed");
		
		//remove data list test
		testList = db.deleteData(chageToList);
		test = false;
		for(Boolean t : testList)
		{
			if(!test==t) System.err.println("Test: remove data list failed");
			test = true;
		}
		
		System.err.println(db.toString());
		
		System.err.println("All Tests finished!");
	}
}
