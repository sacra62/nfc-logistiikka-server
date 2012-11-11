package nfc.logistics.models;

public class AllowedItemTypesInChain
{
	private int itemTypeId;
	private int chainId;
	
	public void setItemTypeId(int id)
	{
		this.itemTypeId = id;
	}
	
	public int getItemTypeId()
	{
		return this.itemTypeId;
	}
	
	public void setChainId(int id)
	{
		this.chainId = id;
	}
	
	public int getChainId()
	{
		return this.chainId;
	}
}