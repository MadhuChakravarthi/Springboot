package com.nic.DBUtils;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class DBUtils 
{

	
	public static Map<String,Object> getMap(String sql,Connection con,Object[] columnvalues) throws SQLException
	{
		Map<String,Object> records=new HashMap<String,Object>();
		PreparedStatement ps = con.prepareStatement(sql);
             
		if(columnvalues!=null)
		{
			for(int ci=0;ci<columnvalues.length;ci++)
			{
				ps.setObject(ci+1, columnvalues[ci]);
                                System.out.println("PS---"+columnvalues[ci]);
			}
		}
                System.out.println("PS---"+sql);
                
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData rm = rs.getMetaData();
		int cols = rm.getColumnCount();
		if (rs.next()) 
		{
			records = new HashMap<String, Object>(cols);
				 for (int i=1; i<=cols; i++) {
					 String columnName = rm.getColumnName(i);
					 Object columnValue = rs.getObject(i);
					 records.put(columnName.trim(), columnValue);
				 } 
		 }
		
		return records;
	}

	public static Map<String,Object> getMap1(String sql,Connection con,Object[] columnvalues) throws SQLException
	{
            
            System.out.println("IN getMap1");
           Map<String,Object> records=new HashMap<String,Object>();
               PreparedStatement ps = con.prepareStatement(sql);
               if(columnvalues!=null)
		{
			for(int ci=0;ci<columnvalues.length;ci++)
			{
				ps.setObject(ci+1, columnvalues[ci]);
			}
                         System.out.println("PS---"+sql+"---"+columnvalues[0]);
		
		}
                 System.out.println("qry---"+sql);
		ResultSet rs=ps.executeQuery();
		ResultSetMetaData rm = rs.getMetaData();
		int cols = rm.getColumnCount();
		if (rs.next()) 
		{
			records = new HashMap<String, Object>(cols);
				 for (int i=1; i<=cols; i++) {
					 String columnName = rm.getColumnName(i);
					 Object columnValue = rs.getObject(i);
					 records.put(columnName.trim(), columnValue);
				 } 
		 }
		//DBPlugin.closeResultSet(rs);
		//DBPlugin.closePreparedStatement(ps);
		return records;
	}
        
       
        
        
        
        
        
       
}
