/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NIC
 */
public class MyConnection implements Serializable{
    
    public Connection getConnection() throws Exception
    {
        Connection con=null;
       Class.forName("org.postgresql.Driver");
       
      // con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/bhunaksha","postgres", "root");
       con=DriverManager.getConnection("jdbc:postgresql://10.160.19.164/mahila","mahila", "mahila");

       //  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       //  con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=shisd","sa","sa123");
       //  con = DriverManager.getConnection("jdbc:sqlserver://10.160.19.162:1433;databaseName=shisd","shisd","shisd");
         
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
