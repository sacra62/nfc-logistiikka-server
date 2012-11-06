package browserClient;

import java.net.URI;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class testClient
{
  public static void main(String[] args) 
  {
    ClientConfig config = new DefaultClientConfig();
    Client client = Client.create(config);
    WebResource service = client.resource(getBaseURI());
   
    Form form = new Form();
    form.add("xmlRequest", new String("<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><version/><query><name>getItemById</name><params><itemId/></params></query></request>"));
    ClientResponse response = service.path("data").path("getInitialSettings").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
    System.out.println(response.getEntity(String.class));
  }

  private static URI getBaseURI() 
  {
    return UriBuilder.fromUri("http://153.1.62.153/testnfc").build();
  }
} 