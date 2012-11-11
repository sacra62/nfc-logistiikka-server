package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentsInEventDataMapper extends AbstractDataMapper
{
	private ArrayList<CommentsInEvent> domainContainer;
	
	public CommentsInEventDataMapper() 
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<CommentsInEvent>();
	}
	
	public ArrayList<CommentsInEvent> getCommentsInEvents()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM CommentsInEvent");
			
			while(this.dbResults.next())
			{
				CommentsInEvent c = new CommentsInEvent();
				c.setEventId(this.dbResults.getInt("eventId"));
				c.setCommentId(this.dbResults.getInt("commentId"));
				this.domainContainer.add(c);
			}
			this.closeAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return this.domainContainer;
	}
	
}