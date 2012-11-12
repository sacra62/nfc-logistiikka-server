package nfc.logistics.controllers;

import java.util.ArrayList;
import nfc.logistics.models.*;
import fi.uta.projectcourse.jk90351.RequestData;
import fi.uta.projectcourse.jk90351.XmlParserLibraryException;
import fi.uta.projectcourse.jk90351.xmlParserLibrary;

public class ResourceController
{
	private xmlParserLibrary xmlParser;
	private ChainDataMapper chainMapper;
	private EventDataMapper eventMapper;
	private EntryCommentDataMapper commentMapper;
	private AllowedItemTypeDataMapper itemMapper;

	public ResourceController()
	{
	   this.xmlParser = new xmlParserLibrary();
	   this.chainMapper = new ChainDataMapper();
	   this.eventMapper = new EventDataMapper();
	   this.commentMapper = new EntryCommentDataMapper();
	   this.itemMapper = new AllowedItemTypeDataMapper();
	}
	
	public Response produceInitialSettingsXmlResponse(String xml)
	{
	    RequestData parsedData = this.sendRequestXmlToParser(xml);	    
	    InitialSettingsResponse settings = new InitialSettingsResponse();
	    
	    if(parsedData.getQueryName().equals("getInitialSettings"))
	    {
	    	ArrayList<Chain> chains = this.chainMapper.findAll();
	    
		    for(int i = 0; i < chains.size(); i++)
		    {
		    	Chain currentChain = chains.get(i);
		    	
		    	ArrayList<Event> events = this.eventMapper.findAllowedEventsForChain(currentChain.getChainId());
		    	
		    	for(int j = 0; j < events.size(); j++)
		    	{
		    		int eventId = events.get(j).getEventId();
		    		ArrayList<EntryComment> comments = this.commentMapper.findEntryCommentsForEvent(eventId);
		    		events.get(j).setEntryComments(comments);
		    	}
		    	
		    	currentChain.setAllowedEvents(events);
		    	ArrayList<AllowedItemType> itemTypes = this.itemMapper.findAllowedItemTypesForChain(currentChain.getChainId());
		    	currentChain.setAllowedItemTypes(itemTypes);
		    	
		    	settings.addChain(currentChain);
		    }
	    
	    	settings.setResponseType("getInitialSettingsSuccess");
	    }
	    return settings;
	}
	
	private RequestData sendRequestXmlToParser(String xml)
	{
		this.xmlParser.setXmlData(xml);
		  
	    try 
	    {
	    	this.xmlParser.parseXmlData();
	    }
	    catch (XmlParserLibraryException e) 
	    {
		   e.printStackTrace();
	    }	
	    
	    return xmlParser.getParsedData();
	}
}