/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nic.DBUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NIC
 */
public class SqlDBUtil implements Serializable{
    
    public Connection getConnection() throws Exception
    {
        Connection con=null;
       Class.forName("org.postgresql.Driver");
       
       con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/smsservice","postgres", "root");
      
         
         return con;

    }

    public void closeConnection(Connection con)
    {
        
        if(con!=null)
        {
            try{
            con.close();
            }
            catch(Exception e)
            {
                con=null;
            }
           
        }
        
    }
     public void closeStatements(PreparedStatement pst)
    {
        
        if(pst!=null)
        {
            try{
            pst.close();
            }
            catch(Exception e)
            {
                pst=null;
            }
           
        }
        
    }
     
     public void closeResultSet(ResultSet rs)
    {
        
        if(rs!=null)
        {
            try{
            rs.close();
            }
            catch(Exception e)
            {
                rs=null;
            }
           
        }
        
    }
    
}
