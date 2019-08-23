package filter;

import model.EmissionData;

/**
 * Emission Filter - Filters EmissionData objects
 * @author jakobhirschl
 *
 */
public class EmissionFilter
{
	public enum EmissionOperator
	{
		EQUALS,
		GREATERTHAN,
		SMALLERTHAN,
		GREATEREQUAL,
		SMALLEREQUAL;
	}
	
	private String department;
	private String commodity;
	private Double emission;
	private EmissionOperator op = EmissionOperator.EQUALS;
	
	/**
	 * 
	 * @param department - department to filter out. Set to null to not filter.
	 * @param commodity - commodity to filter out. Set to null to not filter.
	 * @param emission - emission to filter out. Set to null to not filter.
	 */
	public EmissionFilter(String department, String commodity, Double emission)
	{
		super();
		this.department = department;
		this.commodity = commodity;
		this.emission = emission;
	}

	/**
	 * 
	 * @param data - EmissionData object to filter
	 * @return returns true if the object fits the filter. false otherwise.
	 */
	public boolean filter(EmissionData data)
	{
		if(department != null && !data.getDepartment().equals(department))
			return false;
		if(commodity != null && !data.getCommodity().equals(commodity))
			return false;
		if(emission != null)
		{
			if(op == EmissionOperator.EQUALS)
			{
				if(data.getEmission() != emission) return false;
			}
			if(op == EmissionOperator.GREATERTHAN)
			{
				if(!(data.getEmission() > emission)) return false;
			}
			if(op == EmissionOperator.SMALLERTHAN)
			{
				if(!(data.getEmission() < emission)) return false;
			}
			if(op == EmissionOperator.GREATEREQUAL)
			{
				if(!(data.getEmission() >= emission)) return false;
			}
			if(op == EmissionOperator.SMALLEREQUAL)
			{
				if(!(data.getEmission() <= emission)) return false;
			}
			
		}
		return true;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String department)
	{
		this.department = department;
	}

	public String getCommodity()
	{
		return commodity;
	}

	public void setCommodity(String commodity)
	{
		this.commodity = commodity;
	}

	public Double getEmission()
	{
		return emission;
	}

	public void setEmission(Double emission)
	{
		this.emission = emission;
	}

	public EmissionOperator getOp()
	{
		return op;
	}

	public void setOp(EmissionOperator op)
	{
		this.op = op;
	}
	
	
}
