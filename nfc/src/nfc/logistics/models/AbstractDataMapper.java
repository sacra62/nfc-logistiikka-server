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
	
	protected AbstractDataMapper(String dbName)
	{
		this.databaseName = dbName;
		Context initCtx;
		
		try 
		{
			initCtx = new InitialContext();
			this.source = (DataSource)initCtx.lookup("java:comp/env/jdbc/" + this.databaseName);
		} 
		catch (NamingException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected ResultSet query(String sqlQuery) throws SQLException
	{
		this.con = source.getConnection();
		this.stmt = this.con.createStatement();
		return this.stmt.executeQuery(sqlQuery);
	}

	// Later, we'll make the params more flexible...
	protected ResultSet paramQuery(String sqlQuery, int param) throws SQLException
	{
		this.con = source.getConnection();
		this.prepStmt = this.con.prepareStatement(sqlQuery);
		this.prepStmt.setInt(1, param);
		return this.prepStmt.executeQuery();
	}
	
	protected void closeAll() throws SQLException 
	{
		if(this.dbResults != null)
			this.dbResults.close();
		if(this.con != null)
			this.con.close();
		if(this.stmt != null)
			this.stmt.close();
	}
}