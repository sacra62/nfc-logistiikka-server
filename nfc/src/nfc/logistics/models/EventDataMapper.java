package nfc.logistics.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDataMapper extends AbstractDataMapper
{	
	private final static String DATABASE_NAME = "nfc_logistics";
	private final static String MAPPED_TABLE_NAME = "Event";
	
	public EventDataMapper()
	{
		super(DATABASE_NAME);
	}
	
	public ArrayList<Event> findAll()
	{
		ArrayList<Event> domainContainer = new ArrayList<Event>();
		
		try 
		{
			ResultSet dbResults = this.query("SELECT * FROM " + MAPPED_TABLE_NAME);
			
			while(dbResults.next())
			{
				Event e = new Event();
				e.setEventId(dbResults.getInt("eventId"));
				e.setEventShortName(dbResults.getString("shortName"));
				e.setEventName(dbResults.getString("humanReadableName"));
				domainContainer.add(e);
			}
			
			dbResults.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			this.closeAll();
		}
		
		return domainContainer;
	}
	
	public Event find(int id)
	{
		Event e = null;
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT * FROM " + MAPPED_TABLE_NAME + " WHERE eventId=", id);
			
			while(dbResults.next())
			{
				e = new Event();
				e.setEventId(dbResults.getInt("eventId"));
				e.setEventShortName(dbResults.getString("shortName"));
				e.setEventName(dbResults.getString("humanReadableName"));	
			}
			
			dbResults.close();
		}
		catch(SQLException exc)
		{
			
		}
		finally
		{
			this.closeAll();
		}
		
		return e;
	}
	
	public ArrayList<Event> findAllowedEventsForChain(int id)
	{	
		ArrayList<Event> domainContainer = new ArrayList<Event>();
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT " + MAPPED_TABLE_NAME + ".* FROM AllowedEventsInLogisticsChain LEFT JOIN (" + MAPPED_TABLE_NAME + ")" +
										" ON (AllowedEventsInLogisticsChain.eventId = " + MAPPED_TABLE_NAME + ".eventId)" +
										" WHERE AllowedEventsInLogisticsChain.chainId = ?", id);
			
			while(dbResults.next())
			{
				Event e = new Event();
				e.setEventId(dbResults.getInt("eventId"));
				e.setEventShortName(dbResults.getString("shortName"));
				e.setEventName(dbResults.getString("humanReadableName"));
				domainContainer.add(e);
			}
			
			dbResults.close();
		}
		catch(SQLException exc)
		{
			
		}
		finally
		{
			this.closeAll();
		}
	
		return domainContainer;
	}
}