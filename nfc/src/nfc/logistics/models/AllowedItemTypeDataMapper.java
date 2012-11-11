package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllowedItemTypeDataMapper extends AbstractDataMapper
{
	private ArrayList<AllowedItemType> domainContainer;
	
	public AllowedItemTypeDataMapper()
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<AllowedItemType>();
	}
	
	public ArrayList<AllowedItemType> getEveryItemType()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM ItemType");
			
			while(this.dbResults.next())
			{
				AllowedItemType it = new AllowedItemType();
				it.setItemTypeId(this.dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(this.dbResults.getString("humanReadableName"));
				this.domainContainer.add(it);
			}
			this.closeAll();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return this.domainContainer;
	}
	
	public AllowedItemType find(int id)
	{
		AllowedItemType it = null;
		
		try
		{
			this.dbResults = this.query("SELECT * FROM ItemType WHERE itemTypeId=" + id);
			
			while(this.dbResults.next())
			{
				it = new AllowedItemType();
				it.setItemTypeId(this.dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(this.dbResults.getString("humanReadableName"));
			}
		}
		catch(SQLException exc)
		{
			
		}
		
		return it;
	}
	/* Currently does NOT work. The SQL statement has a weird behaviour...
	public ArrayList<AllowedItemType> getAllowedItemTypesForChain(int id)
	{	
		try
		{
			this.dbResults = this.paramQuery("SELECT ItemType.* FROM AllowedItemTypesInChain LEFT JOIN ItemType" +
										" ON AllowedItemTypesInChain.itemTypeId = ItemType.itemTypeId" +
										" WHERE AllowedItemTypesInChain.chainId = ?", id);
			
			while(this.dbResults.next())
			{
				AllowedItemType it = new AllowedItemType();
				it.setItemTypeId(this.dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(this.dbResults.getString("humanReadableName"));
				this.domainContainer.add(it);
			}
		}
		catch(SQLException exc)
		{
			
		}
	
		return this.domainContainer;
	}
	*/
	
	public ArrayList<AllowedItemType> getAllowedItemTypesForChain(int id)
	{	
		try
		{	
			this.dbResults = this.query("SELECT * FROM AllowedItemTypesInChain");
			
			while(this.dbResults.next())
			{	
				if(this.dbResults.getInt("chainId") == id)
				{
					int itemId = this.dbResults.getInt("itemTypeId");
					AllowedItemType a = this.find(itemId);
					this.domainContainer.add(a);
				}
			}
		}
		catch(SQLException exc)
		{
			
		}
	
		return this.domainContainer;
	}
	
}