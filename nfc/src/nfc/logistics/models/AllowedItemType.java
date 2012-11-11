package nfc.logistics.models;

public class AllowedItemType
{
	private int itemTypeId;
	private String itemTypeHumanReadableName;
	
	public void setItemTypeId(int id)
	{
		this.itemTypeId = id;
	}
	
	public int getItemTypeId()
	{
		return this.itemTypeId;
	}
	
	public void setHumanReadableName(String str)
	{
		this.itemTypeHumanReadableName = str;
	}
	
	public String getHumanReadableName()
	{
		return this.itemTypeHumanReadableName;
	}
}