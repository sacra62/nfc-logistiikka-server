package nfc.logistics.models;

import java.util.ArrayList;

public abstract class AbstractEvent
{
	protected int eventId;
	protected ArrayList<String> entryComments;
	
	protected abstract ArrayList<String> getEntryComments();
	protected abstract void addEntryComment(String e);
}