// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MyConnection.java

package com.nic.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author moin
 */
public class MyConnection
{

    public MyConnection()
    {
    }

    public  Connection getConnection() throws Exception
    {
        Connection con = null;
        if(con == null)
        {
            try
            {
                Class.forName("org.gjt.mm.mysql.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost/hmistelan", "root", "root");
            
            }
            catch(Exception exception)
            {
                System.out.println("in myconnection:" + exception);
            }
           // System.out.println("in Myconnection getconn:" + con);
            return con;
        } else
        {
            
            return con;
        }
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

   //

}
