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
	
	// Didn't find any other way to NOT have duplicate elements!
	// If the type referred to an entryComment class we would
	// typically have entryComment's elements as XML...
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

	// Should find a nicer way to accomplish this...
	// See @XmlElementsWrapper part
	public void setEntryComments(ArrayList<EntryComment> comments) 
	{
		for(int i = 0; i < comments.size(); i++)
		{
			this.entryComments.add(comments.get(i).getEntryComment());
		}
	}
}