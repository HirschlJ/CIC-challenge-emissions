package model;

import javax.persistence.*;

@Entity
public class EmissionData
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ID;
	
	private String department;
	private String commodity;
	private double emissions;
	
	public long getID()
	{
		return ID;
	}
	
	public void setID(long iD)
	{
		ID = iD;
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
	
	public double getEmissions()
	{
		return emissions;
	}
	
	public void setEmissions(double emissions)
	{
		this.emissions = emissions;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (ID ^ (ID >>> 32));
		result = prime * result + ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		long temp;
		temp = Double.doubleToLongBits(emissions);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this == obj) return true;
		if(obj == null) return false;
		if(getClass() != obj.getClass()) return false;
		EmissionData other = (EmissionData) obj;
		if(ID != other.ID) return false;
		if(commodity == null)
		{
			if(other.commodity != null) return false;
		}
		else if(!commodity.equals(other.commodity)) return false;
		if(department == null)
		{
			if(other.department != null) return false;
		}
		else if(!department.equals(other.department)) return false;
		if(Double.doubleToLongBits(emissions) != Double.doubleToLongBits(other.emissions)) return false;
		return true;
	}
	
	
}
