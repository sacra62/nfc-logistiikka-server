package nfc.logistics.models;

public class AllowedEventsInLogisticsChain
{
	private int chainId;
	private int eventId;
	private int nextEventId;

	public void setChainId(int id)
	{
		this.chainId = id;
	}
	
	public void setEventId(int id)
	{
		this.eventId = id;
	}
	
	public void setNextEventId(int id)
	{
		this.nextEventId = id;
	}
	
	public int getEventId()
	{
		return this.eventId;
	}
	
	public int getChainId()
	{
		return this.chainId;
	}
	
	public int getNextEventId()
	{
		return this.nextEventId;
	}
}