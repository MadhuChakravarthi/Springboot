package com.nic.digitalsign;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.nic.DBUtils.MyConnection;
import com.restservice.prac.service.MessageService;


@Path("/signmessages")
public class RetrivalSignInfo {

	//MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<SignValues> getSignedInfo() throws SQLException 
	{
	    PreparedStatement ps=null;
		ResultSet rs=null;
		String encodedByte=null;
		Connection con=null;
		List<SignValues> li=new ArrayList<>();
		try
		{
		MyConnection db=new MyConnection();
	    con=db.getConnection();		
	    System.out.println("Connection"+con);
		ps=con.prepareStatement("select recordId,filename,file_encoded,username,filecontent_original from filestatus_track where bank_status='accepted' and username='edshmap@gmail.com' limit 5  ");
		rs=ps.executeQuery();
		while(rs.next())
		{
	    SignValues si=new SignValues();
		si.setRecordkey(rs.getString(1));
		si.setFilename(rs.getString(2));
		encodedByte=rs.getString(3);
		si.setOriginalFile(new String(encodedByte));
		si.setUsername(rs.getString(4));
		si.setOriginalContent(rs.getString(5));
		li.add(si);
		
		//System.out.println()
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
