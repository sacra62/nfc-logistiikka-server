package nfc.logistics.models;

import java.sql.SQLException;
import java.util.ArrayList;

public class ChainDataMapper extends AbstractDataMapper
{
	private ArrayList<Chain> domainContainer;
	
	public ChainDataMapper()
	{
		super("nfc_logistics");
		this.domainContainer = new ArrayList<Chain>();
	}
	
	public ArrayList<Chain> getEveryChain()
	{
		try 
		{
			this.dbResults = this.query("SELECT * FROM LogisticsChain");
			
			while(this.dbResults.next())
			{
				Chain c = new Chain();
				c.setChainId(this.dbResults.getInt("chainId"));
				c.setChainShortName(this.dbResults.getString("shortName"));
				c.setChainName(this.dbResults.getString("humanReadableName"));
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