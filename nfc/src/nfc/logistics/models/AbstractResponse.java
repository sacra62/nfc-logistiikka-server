package nfc.logistics.models;

import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractResponse 
{
	@XmlElement
	protected String responseType;
	
	protected abstract String getResponseType();
	protected abstract void setResponseType(String str);
}