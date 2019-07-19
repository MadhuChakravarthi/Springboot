package com.nic.sms.service;
	import java.sql.Connection;
    import java.util.ArrayList;
	import java.util.List;
	import javax.ws.rs.Consumes;
	import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
	import javax.ws.rs.Produces;
	import javax.ws.rs.core.MediaType;
	
    import org.nic.sms.serviceimpl.EgovSmsServiceImpl;
    import org.nic.sms.services.SMSService;

import com.nic.DBUtils.SqlDBUtil;
import com.nic.sms.beans.SMSPojo;
@Path("/smsservice")
	public class SMSRestService {

		
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
        public int sendSingleSMS(SMSPojo ms) throws Exception
		{
     		
		  Connection con=null;
		  int i=0;
     		
             try
             {
     		 
     	     SqlDBUtil sql=new SqlDBUtil();
     	     con=sql.getConnection();
     	     SMSService service=new EgovSmsServiceImpl();
       		  i= service.sendSingleSMS(ms.getMessage(),ms.getMobileno(), con, "2",ms.getProjectID());
     	     }
             catch(Exception e)
             {
            	 System.out.println(e);
             }
       		 
       		 finally
       		 {
       			 con.close();
       		 }
       		 
       		 
       		 return i ;
		
             
             
             
		}
		
		
		
	}



