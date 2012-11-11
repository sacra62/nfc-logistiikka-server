package nfc.logistics.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;

public class Chain
{
	private int chainId;
	private String chainName;
	private String chainShortName;
	private ArrayList<Event> events;
	private ArrayList<AllowedItemType> allowedItemTypes;
	
	public Chain()
	{
		this.events = new ArrayList<Event>();
		this.allowedItemTypes = new ArrayList<AllowedItemType>();
	}
	
	public void setChainId(int id)
	{
		this.chainId = id;
	}
	
	public int getChainId()
	{
		return this.chainId;
	}
	
	public void setChainName(String name)
	{
		this.chainName = name;
	}
	
	public String getChainName()
	{
		return this.chainName;
	}
	
	public void setChainShortName(String name)
	{
		this.chainShortName = name;
	}
	
	public String getChainShortName()
	{
		return this.chainShortName;
	}
	
	@XmlElementWrapper(name="events")
	@XmlElements({ 
	    @XmlElement(name="event", type=Event.class)
	})
	public ArrayList<Event> getEvents()
	{
		return this.events;
	}
	
	public void addEvent(Event e)
	{
		events.add(e);
	}
	
	@XmlElementWrapper(name="allowedItemTypes")
	@XmlElements(
		@XmlElement(name="allowedItemType", type=AllowedItemType.class)
	)
	public ArrayList<AllowedItemType> getAllowedItemTypes()
	{
		return this.allowedItemTypes;
	}
	
	public void addAllowedItemType(AllowedItemType a)
	{
		this.allowedItemTypes.add(a);
	}
	
	public void setAllowedItemTypes(ArrayList<AllowedItemType> list)
	{
		this.allowedItemTypes = list;
	}
}