package nfc.logistics.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EntryCommentDataMapper extends AbstractDataMapper
{
	private final static String DATABASE_NAME = "nfc_logistics";
	private final static String MAPPED_TABLE_NAME = "EntryComment";
	
	public EntryCommentDataMapper()
	{
		super(DATABASE_NAME);
	}
	
	public ArrayList<EntryComment> findAll()
	{
		ArrayList<EntryComment> domainContainer = new ArrayList<EntryComment>();
		
		try 
		{
			ResultSet dbResults = this.query("SELECT * FROM " + MAPPED_TABLE_NAME);
			
			while(dbResults.next())
			{
				EntryComment e = new EntryComment();
				e.setEntryCommentId(dbResults.getInt("EntryCommentId"));
				e.setEntryComment(dbResults.getString("comment"));
				domainContainer.add(e);
			}
			
			dbResults.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			this.closeAll();
		}
		
		return domainContainer;
	}

	public EntryComment find(int id)
	{
		EntryComment e = null;
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT * FROM " + MAPPED_TABLE_NAME + " WHERE entryCommentId=", id);
			
			if(dbResults.first())
			{
				e = new EntryComment();
				e.setEntryCommentId(dbResults.getInt("entryCommentId"));
				e.setEntryComment(dbResults.getString("comment"));
			}
			
			dbResults.close();
			
		}
		catch(SQLException exc)
		{
			
		}
		finally
		{
			this.closeAll();
		}
		
		return e;
	}
	
	public ArrayList<EntryComment> findEntryCommentsForEvent(int id)
	{	
		ArrayList<EntryComment> domainContainer = new ArrayList<EntryComment>();
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT " + MAPPED_TABLE_NAME + ".* FROM CommentsInEvent LEFT JOIN (" + MAPPED_TABLE_NAME + ")" +
										" ON (CommentsInEvent.commentId = " + MAPPED_TABLE_NAME + ".entryCommentId)" +
										" WHERE CommentsInEvent.eventId = ?", id);
			
			while(dbResults.next())
			{
				EntryComment it = new EntryComment();
				it.setEntryCommentId(dbResults.getInt("entryCommentId"));
				it.setEntryComment(dbResults.getString("comment"));
				domainContainer.add(it);
			}
			
			dbResults.close();
		}
		catch(SQLException exc)
		{
			
		}
		finally
		{
			this.closeAll();
		}
	
		return domainContainer;
	}
}