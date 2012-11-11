package nfc.logistics.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import nfc.logistics.models.AbstractEvent;


public class Event extends AbstractEvent
{
	private String eventName;
	private String eventShortName;
	
	public Event()
	{
		this.entryComments = new ArrayList<String>();
	}
	
	public void setEventId(int id)
	{
		this.eventId = id;
	}
	
	public int getEventId()
	{
		return this.eventId;
	}
	
	public void setEventName(String name)
	{
		this.eventName = name;
	}
	
	public String getEventName()
	{
		return this.eventName;
	}
	
	public void setEventShortName(String name)
	{
		this.eventShortName = name;
	}
	
	public String getEventShortName()
	{
		return this.eventShortName;
	}
	
	@XmlElementWrapper(name="entryComments")
	@XmlElements(
		@XmlElement(name="entryComment", type=String.class)
	)
	public ArrayList<String> getEntryComments()
	{
		return this.entryComments;
	}
	
	public void addEntryComment(String e)
	{
		entryComments.add(e);
	}
}