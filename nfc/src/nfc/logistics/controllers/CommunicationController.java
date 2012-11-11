package nfc.logistics.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import nfc.logistics.models.*;

@Produces({ MediaType.TEXT_XML })
@Path("/")
public class CommunicationController 
{	
  private ResourceController rsc;	
  	
  public CommunicationController()
  {
	  this.rsc = new ResourceController();
  }

  @POST
  @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
  @Path("getInitialSettings")
  public Response getXML(@FormParam("xmlRequest") String xml) 
  {	   
	return this.rsc.produceInitialSettingsXmlResponse(xml);  
  }
}


