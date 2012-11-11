package nfc.logistics.models;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

//Should not be needed... But currently won't work without.
@XmlRootElement(name="response")
public class InitialSettingsResponse extends Response
{
	private ArrayList<Chain> chains;
	
	public InitialSettingsResponse()
	{
		this.chains = new ArrayList<Chain>();
	}
	
	@XmlElementWrapper(name="logisticsChains")
	@XmlElements({ 
	    @XmlElement(name="chain", type=Chain.class)
	})
	public ArrayList<Chain> getEvents()
	{
		return this.chains;
	}
	
	public void addChain(Chain c)
	{
		chains.add(c);
	}
	

}