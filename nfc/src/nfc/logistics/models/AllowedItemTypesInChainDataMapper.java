package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class AllowedItemTypesInChainDataMapper extends AbstractDataMapper
{
	private ArrayList<AllowedItemTypesInChain> domainContainer;
	
	public AllowedItemTypesInChainDataMapper() 
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<AllowedItemTypesInChain>();
	}
	
	public ArrayList<AllowedItemTypesInChain> getAllowedItemTypesInChains()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM AllowedItemTypesInChain");
			
			while(this.dbResults.next())
			{
				AllowedItemTypesInChain c = new AllowedItemTypesInChain();
				c.setItemTypeId(this.dbResults.getInt("itemTypeId"));
				c.setChainId(this.dbResults.getInt("chainId"));
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