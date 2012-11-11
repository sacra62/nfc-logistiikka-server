package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class EventDataMapper extends AbstractDataMapper
{
	private ArrayList<Event> domainContainer;
	
	public EventDataMapper()
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<Event>();
	}
	
	public ArrayList<Event> getEveryEvent()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM Event");
			
			while(this.dbResults.next())
			{
				Event e = new Event();
				e.setEventId(this.dbResults.getInt("eventId"));
				e.setEventShortName(this.dbResults.getString("shortName"));
				e.setEventName(this.dbResults.getString("humanReadableName"));
				this.domainContainer.add(e);
			}
			this.closeAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return this.domainContainer;
	}
	
	public Event findEvent(int id)
	{
		Event e = null;
		
		try
		{
			this.dbResults = this.query("SELECT * FROM Event WHERE eventId=" + id);
			
			while(this.dbResults.next())
			{
				e = new Event();
				e.setEventId(this.dbResults.getInt("eventId"));
				e.setEventShortName(this.dbResults.getString("shortName"));
				e.setEventName(this.dbResults.getString("humanReadableName"));	
			}
		}
		catch(SQLException exc)
		{
			
		}
		
		return e;
	}
}