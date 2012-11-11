package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllowedEventsInLogisticsChainDataMapper extends AbstractDataMapper
{
	private ArrayList<AllowedEventsInLogisticsChain> domainContainer;
	
	public AllowedEventsInLogisticsChainDataMapper() 
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<AllowedEventsInLogisticsChain>();
	}
	
	public ArrayList<AllowedEventsInLogisticsChain> getAllowedEvents()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM AllowedEventsInLogisticsChain");
			
			while(this.dbResults.next())
			{
				AllowedEventsInLogisticsChain a = new AllowedEventsInLogisticsChain();
				a.setEventId(this.dbResults.getInt("eventId"));
				a.setChainId(this.dbResults.getInt("chainId"));
				a.setNextEventId(this.dbResults.getInt("nextEventId"));
				this.domainContainer.add(a);
			}
			this.closeAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return this.domainContainer;
	}
	
}