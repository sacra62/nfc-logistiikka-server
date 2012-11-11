package nfc.logistics.models;

import java.sql.SQLException;


public class EntryCommentDataMapper extends AbstractDataMapper
{
	public EntryCommentDataMapper()
	{
		super("nfc_logistics");
	}

	public EntryComment findComment(int id)
	{
		EntryComment e = null;
		
		try
		{
			this.dbResults = this.query("SELECT * FROM EntryComment WHERE entryCommentId=" + id);
			
			while(this.dbResults.next())
			{
				e = new EntryComment();
				e.setEntryCommentId(this.dbResults.getInt("entryCommentId"));
				e.setEntryComment(this.dbResults.getString("comment"));
			}
		}
		catch(SQLException exc)
		{
			
		}
		
		return e;
	}
	
	
}