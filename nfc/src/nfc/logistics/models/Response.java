package nfc.logistics.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
public class Response extends AbstractResponse
{
	public String getResponseType()
	{
		return this.responseType;
	}
	
	public void setResponseType(String str)
	{
		this.responseType = str;
	}
}