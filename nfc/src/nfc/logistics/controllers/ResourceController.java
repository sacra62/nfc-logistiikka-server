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
	private AllowedEventsInLogisticsChainDataMapper allowedEventMapper;
	private CommentsInEventDataMapper eventCommentMapper;
	private AllowedItemTypeDataMapper itemMapper;
	private AllowedItemTypesInChainDataMapper itemChainMapper;
	
	public ResourceController()
	{
	   this.xmlParser = new xmlParserLibrary();
	   this.chainMapper = new ChainDataMapper();
	   this.eventMapper = new EventDataMapper();
	   this.commentMapper = new EntryCommentDataMapper();
	   this.allowedEventMapper = new AllowedEventsInLogisticsChainDataMapper();
	   this.eventCommentMapper = new CommentsInEventDataMapper();
	   this.itemMapper = new AllowedItemTypeDataMapper();
	   this.itemChainMapper = new AllowedItemTypesInChainDataMapper();
	}
	
	public Response produceInitialSettingsXmlResponse(String xml)
	{
	    RequestData parsedData = this.sendRequestXmlToParser(xml);	    
	    InitialSettingsResponse settings = new InitialSettingsResponse();
	    ArrayList<Chain> chains = null;
	    ArrayList<Event> events = null;
	    ArrayList<AllowedEventsInLogisticsChain> allowedEvents = null;
	    ArrayList<CommentsInEvent> eventComments = null;
	    ArrayList<AllowedItemTypesInChain> itemChainTypes = null;
	    
	    if(parsedData.getQueryName().equals("getInitialSettings"))
	    {
	        chains = this.chainMapper.getEveryChain();
	        events = this.eventMapper.getEveryEvent();
	        allowedEvents = this.allowedEventMapper.getAllowedEvents();
	        eventComments = this.eventCommentMapper.getCommentsInEvents();
	        itemChainTypes = this.itemChainMapper.getAllowedItemTypesInChains();
	    }

	    for(int i = 0; i < chains.size(); i++)
	    {
	    	Chain currentChain = chains.get(i);
	    	
	    	for(int j = 0; j < allowedEvents.size(); j++)
	    	{
	    		AllowedEventsInLogisticsChain a = allowedEvents.get(j);
	    		
	    		if(a.getChainId() == currentChain.getChainId())
	    		{
	    			Event e = this.eventMapper.findEvent(a.getEventId());
	    		
	    			if(e != null)
	    			{
	    			
	    			for(int k = 0; k < eventComments.size(); k++)
	    			{
	    				CommentsInEvent eventCom = eventComments.get(k);
	    				
	    				if(eventCom.getEventId() == e.getEventId())
	    				{
	    					EntryComment entryCom = this.commentMapper.findComment(eventCom.getCommentId());
	    					e.addEntryComment(entryCom.getEntryComment());
	    				}
	    			}
	    			}
	    			
	    			currentChain.addEvent(e);
	    		}
	    	}
	    	
	    	for(int y = 0; y < itemChainTypes.size(); y++)
	    	{
	    		AllowedItemTypesInChain a = itemChainTypes.get(y);
	    		
	    		if(a.getChainId() == currentChain.getChainId())
	    			currentChain.addAllowedItemType(this.itemMapper.find(a.getItemTypeId()));
	    	}
	    	
	    	/* Does NOT work...
	    	ArrayList<AllowedItemType> itemTypes = this.itemMapper.getAllowedItemTypesForChain(currentChain.getChainId());
	    	currentChain.setAllowedItemTypes(itemTypes);
	    	*/
	   
	    	settings.addChain(currentChain);
	    }
	    
	    settings.setResponseType("getInitialSettingsSuccess");
	    
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