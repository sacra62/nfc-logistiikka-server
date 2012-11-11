package fi.uta.projectcourse.jk90351;

@SuppressWarnings("serial")
public class XmlParserLibraryException extends Exception
{
	private final static String EXCEPTION_IDENTIFIER = "An exception for the xmlParserLibrary was caught: ";
	
	public XmlParserLibraryException(Exception exc)
	{
		super(EXCEPTION_IDENTIFIER + exc);
	}
}