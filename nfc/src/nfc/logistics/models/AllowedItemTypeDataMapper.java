package nfc.logistics.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AllowedItemTypeDataMapper extends AbstractDataMapper
{	
	private final static String DATABASE_NAME = "nfc_logistics";
	private final static String MAPPED_TABLE_NAME = "ItemType";
	
	public AllowedItemTypeDataMapper()
	{
		super(DATABASE_NAME);
	}
	
	public ArrayList<AllowedItemType> findAll()
	{
		ArrayList<AllowedItemType> domainContainer = new ArrayList<AllowedItemType>();
		
		try 
		{
			ResultSet dbResults = this.query("SELECT * FROM " + MAPPED_TABLE_NAME);
			
			while(dbResults.next())
			{
				AllowedItemType it = new AllowedItemType();
				it.setItemTypeId(dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(dbResults.getString("humanReadableName"));
				domainContainer.add(it);
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
	
	public AllowedItemType find(int id)
	{
		AllowedItemType it = null;
		
		try
		{
			ResultSet dbResults = this.query("SELECT * FROM " + MAPPED_TABLE_NAME + " WHERE itemTypeId=" + id);
			
			if(dbResults.first())
			{
				it = new AllowedItemType();
				it.setItemTypeId(dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(dbResults.getString("humanReadableName"));
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
		
		return it;
	}

	public ArrayList<AllowedItemType> findAllowedItemTypesForChain(int id)
	{	
		ArrayList<AllowedItemType> domainContainer = new ArrayList<AllowedItemType>();
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT " + MAPPED_TABLE_NAME + ".* FROM AllowedItemTypesInChain LEFT JOIN (" + MAPPED_TABLE_NAME + ")" +
										" ON (AllowedItemTypesInChain.itemTypeId = " + MAPPED_TABLE_NAME + ".itemTypeId)" +
										" WHERE AllowedItemTypesInChain.chainId = ?", id);
			
			while(dbResults.next())
			{
				AllowedItemType it = new AllowedItemType();
				it.setItemTypeId(dbResults.getInt("itemTypeId"));
				it.setHumanReadableName(dbResults.getString("humanReadableName"));
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