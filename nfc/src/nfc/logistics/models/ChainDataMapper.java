package nfc.logistics.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ChainDataMapper extends AbstractDataMapper
{	
	private final static String DATABASE_NAME = "nfc_logistics";
	private final static String MAPPED_TABLE_NAME = "LogisticsChain";
	
	public ChainDataMapper()
	{
		super(DATABASE_NAME);
	}
	
	public ArrayList<Chain> findAll()
	{
		ArrayList<Chain> domainContainer = new ArrayList<Chain>();
		
		try 
		{
			ResultSet dbResults = this.query("SELECT * FROM " + MAPPED_TABLE_NAME);
			
			while(dbResults.next())
			{
				Chain c = new Chain();
				c.setChainId(dbResults.getInt("chainId"));
				c.setChainShortName(dbResults.getString("shortName"));
				c.setChainName(dbResults.getString("humanReadableName"));
				domainContainer.add(c);
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
	
	public Chain find(int id)
	{
		Chain c = null;
		
		try
		{
			ResultSet dbResults = this.paramQuery("SELECT * FROM " + MAPPED_TABLE_NAME + " WHERE chainId=", id);
			
			while(dbResults.next())
			{
				c = new Chain();
				c.setChainId(dbResults.getInt("chainId"));
				c.setChainShortName(dbResults.getString("shortName"));
				c.setChainName(dbResults.getString("humanReadableName"));	
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
		
		return c;
	}
}