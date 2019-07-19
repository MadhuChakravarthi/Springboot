package com.nic.digitalsign;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.nic.DBUtils.MyConnection;


@Path("/signplainmessages")
public class RetrivalPlainSign {

	//MessageService messageService = new MessageService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<SignPlainValues> getSignedInfo() throws SQLException 
	{
	    PreparedStatement ps=null;
		ResultSet rs=null;
		String encodedByte=null;
		Connection con=null;
		List<SignPlainValues> li=new ArrayList<>();
		try
		{
		MyConnection db=new MyConnection();
	    con=db.getConnection();		
	    System.out.println("Connection"+con);
	    ps = con.prepareStatement("select regnum,stu_name,course_trngat from regnumbers where status='pending' ");
        rs = ps.executeQuery();
        while (rs.next()) {
        	SignPlainValues si = new SignPlainValues();
            si.setRegnum(rs.getString(1));
            si.setName(rs.getString(2));
            si.setCoursetrngat(rs.getString(3));
            si.setRecordkey(rs.getString(1));
            si.setConcat(rs.getString(1) + "&" + rs.getString(2) + "&" + rs.getString(3));
            li.add(si);
        }
		}
		catch(Exception e)
		{
		    System.out.println("EXception"+e);
		}
		finally
		{
		    con.close();
		    ps.close();
		    rs.close();
		}
		return li;
		
		
	}


	
}
