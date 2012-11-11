package fi.uta.projectcourse.jk90351;

import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import fi.uta.projectcourse.jk90351.XmlParserLibraryException;
import fi.uta.projectcourse.jk90351.RequestData;

public class xmlParserLibrary extends DefaultHandler
{
	private SAXParser parser;
	private InputSource xmlData;
	private RequestData parsedData; 
	private String elementValue;
	
	public xmlParserLibrary()
	{
		this.parsedData = new RequestData();
		
		try 
		{
			this.initializeParser();
		} 
		catch (XmlParserLibraryException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void initializeParser() throws XmlParserLibraryException
	{
		try 
		{ 
  	       SAXParserFactory factory = SAXParserFactory.newInstance();
  	       SAXParser saxParser = factory.newSAXParser();
  	       this.parser = saxParser; 
		}
		catch(Exception e)
		{
			throw new XmlParserLibraryException(e);
		}
	}
	
	public void setXmlData(String xml)
	{
		InputSource input = new InputSource(new StringReader(xml));
		input.setEncoding("UTF-8");
		this.xmlData = input;
	}
	
	public SAXParser getParser()
	{
		return this.parser;
	}
	
	public void parseXmlData() throws XmlParserLibraryException
	{
		try 
		{
			this.parser.parse(this.xmlData, this);
		} 
		catch (Exception e) 
		{
			throw new XmlParserLibraryException(e);
		}
	}
	
	public RequestData getParsedData()
	{
		return this.parsedData;
	}
	
    @Override
    public void endElement(String uri, String localName, String elementName) throws SAXException
    {
        if(elementName.equalsIgnoreCase("name")) 
        {
        	this.parsedData.setQueryName(this.elementValue);
        }
        /* to be implemented later...
        if(elementName.equalsIgnoreCase("params")) 
        {
        	this.parsedData.addParam(elementName, this.elementValue);
        }
        */
    }
    
    @Override
    public void characters(char[] ac, int i, int j) throws SAXException 
    {
        this.elementValue = new String(ac, i, j);
    }
}