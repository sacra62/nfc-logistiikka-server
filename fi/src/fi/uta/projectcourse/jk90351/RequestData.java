package fi.uta.projectcourse.jk90351;

import java.util.ArrayList;

public class RequestData
{	
	private String query;
	private ArrayList<String> params;
	
	public RequestData()
	{
		this.params = new ArrayList<String>();
	}
	
	public void setQueryName(String str)
	{
		this.query = str;
	}
	
	public String getQueryName()
	{
		return this.query;
	}
	
	public ArrayList<String> getParams()
	{
		return this.params;
	}
	
	/* Needed for release II. 
	public void addParam(String key, String value)
	{
		this.params.add(value);
	}
	*/
	
	public String getParam(int i)
	{
		return this.params.get(i);
	}
}