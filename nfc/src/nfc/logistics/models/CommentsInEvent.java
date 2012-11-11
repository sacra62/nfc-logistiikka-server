package nfc.logistics.models;

public class CommentsInEvent
{
	private int commentId;
	private int eventId;
	
	public void setCommentId(int id)
	{
		this.commentId = id;
	}
	
	public void setEventId(int id)
	{
		this.eventId = id;
	}
	
	public int getCommentId()
	{
	 	return this.commentId;
	}
	
	public int getEventId()
	{
	 	return this.eventId;
	}
}