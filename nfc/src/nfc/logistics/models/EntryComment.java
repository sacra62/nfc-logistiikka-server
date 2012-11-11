package nfc.logistics.models;

public class EntryComment
{
	private int entryCommentId;
	private String comment;
	
	public void setEntryCommentId(int id)
	{
		this.entryCommentId = id;
	}
	
	public void setEntryComment(String str)
	{
		this.comment = str;
	}
	
	public int getEntryCommentId()
	{
	 	return this.entryCommentId;
	}
	
	public String getEntryComment()
	{
	 	return this.comment;
	}
}