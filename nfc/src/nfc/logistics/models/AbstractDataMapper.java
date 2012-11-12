package nfc.logistics.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class AbstractDataMapper
{
	protected DataSource source;
	protected String databaseName;
	protected Connection con;
	protected ResultSet dbResults;
	protected Statement stmt;
	protected PreparedStatement prepStmt;
	
	private final static String MYSQL_DRIVER_URL = "java:comp/env/jdbc/"; 
	
	// Abstract methods
	protected abstract Object find(int id);
	protected abstract Object findAll();
	
	protected AbstractDataMapper(String dbName)
	{
		this.databaseName = dbName;
		Context initCtx;
		
		try 
		{
			initCtx = new InitialContext();
			this.source = (DataSource)initCtx.lookup(MYSQL_DRIVER_URL + this.databaseName);
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected ResultSet query(String sqlQuery)
	{
		try 
		{
			this.con = this.source.getConnection();
			this.stmt = this.con.createStatement();
			this.dbResults = this.stmt.executeQuery(sqlQuery);
		} 
		catch (SQLException e) 
		{
	
		}
		
		return this.dbResults;
	}

	// Later, we'll make the params more flexible...
	protected ResultSet paramQuery(String sqlQuery, int param)
	{
		try
		{
			this.con = this.source.getConnection();
			this.prepStmt = this.con.prepareStatement(sqlQuery);
			this.prepStmt.setInt(1, param);
			this.dbResults = this.prepStmt.executeQuery();
		}
		catch(SQLException e)
		{
			
		}
	
		return this.dbResults;
	}
	
	// Close all resources, connections etc. in a reverse order of acquisition
	protected void closeAll()
	{
		try
		{
			if(this.dbResults != null)
			{
				this.dbResults.close();
				this.dbResults = null;
			}
			if(this.stmt != null)
			{
				this.stmt.close();
				this.stmt = null;
			}
			if(this.prepStmt != null)
			{
				this.prepStmt.close();
				this.prepStmt = null;
			}
			if(this.con != null)
			{
				this.con.close();
				this.con = null;
			}
		}
		catch(SQLException e)
		{
			
		}
	}	
}