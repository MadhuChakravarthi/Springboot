/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.sms.serviceimpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.ResourceBundle;
import org.nic.sms.services.SMSService;

import com.nic.DBUtils.DBUtils;

/**
 *
 * @author pc
 */
public class EgovSmsServiceImpl implements SMSService{

    @Override
    public int sendSingleSMS(String message, String mobile, Connection con, String typeofConnection,int projectID) 
    {
       
      int resCode;
        String res= "";
        int result=0;
       HttpURLConnection connection = null;
     // username = "APGOVT";
     // password = "esd@123";
     // senderId = "GOVTAP";
       // ResourceBundle rb = ResourceBundle.getBundle("SMSCrdentialsEgov");
        
        try {
            
        
        	
         Map record=DBUtils.getMap("select senderid as SENDERID,sms_username as SMSUSERNAME,sms_password as SMSPASSWORD ,sms_url as SMSURL from sms_credentials where id="+projectID,con,null);
        
            
          
            
            URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setFollowRedirects(true);

            String smsservicetype = "singlemsg"; // For single message.
            String query = "username=" + URLEncoder.encode((String)record.get("smsusername"))
                    + "&password=" + URLEncoder.encode((String)record.get("smspassword"))
                    + "&smsservicetype=" + URLEncoder.encode(smsservicetype)
                    + "&content=" + URLEncoder.encode(message) + "&mobileno="
                    + URLEncoder.encode(mobile) + "&senderid="
                    + URLEncoder.encode((String)record.get("senderid"));
            connection.setRequestProperty("Content-length", String
                    .valueOf(query.length()));
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");
            // open up the output stream of the connection
            DataOutputStream output = new DataOutputStream(connection
                    .getOutputStream());
            // write out the data
            int queryLength = query.length();
            output.writeBytes(query);
 // output.close();
            // get ready to read the response from the cgi script
            DataInputStream input = new DataInputStream(connection
                    .getInputStream());
            // read in each character until end-of-stream is detected
            for (int c = input.read(); c != -1; c = input.read()) {
                System.out.print((char) c);
            }
            input.close();

            res = connection.getResponseMessage();
            resCode = connection.getResponseCode();
            String resMessage = connection.getResponseMessage();
            System.out.println("resCode " + resCode);
            if (resCode == 200) {
                
               result = storeSMSLog(message, mobile, resCode, resMessage, con, typeofConnection,projectID);
               
                connection.disconnect();

            }

        } catch (Exception e) {
            System.out.println("Something bad just happened.");
            System.out.println(e);
            e.printStackTrace();
        }
        return result;
    
    
    
    
    }

    @Override
    public String sendMultipleSMS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String receiveSMS() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int storeSMSLog(String message, String mobile, int responsecode, String responseMessage, Connection con, String typeofConnection,int projectID) 
    {
      

 int i = 0;
           

        switch (typeofConnection) {
            case "1": {

                try {

                    PreparedStatement ps = null;

                    String sqlCreate = "CREATE TABLE IF NOT EXISTS  smsresponse`"+projectID+"` ("
                            + "Id int(11) NOT NULL AUTO_INCREMENT,"
                            + "message varchar(500) DEFAULT NULL,"
                            + "mobileno varchar(15) DEFAULT NULL,"
                            + "responsecode int(15) DEFAULT NULL,"
                            + "responsemessage varchar(500) DEFAULT NULL,"
                            + "sms_received_date DATE,"
                            + "ts timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,"
                            + "PRIMARY KEY (Id));";
                    Statement stmt = con.createStatement();
                    stmt.execute(sqlCreate);
                    ps = con.prepareStatement("insert into smsresponse(message,mobileno,responsecode,responsemessage,sms_received_date)values(?,?,?,?,now())");
                    ps.setString(1, message);
                    ps.setString(2, mobile);
                    ps.setInt(3, responsecode);
                    ps.setString(4, responseMessage);
                    i = ps.executeUpdate();


                } catch (Exception e) {
                    System.out.println(e);
                }

          break;


            }
                
                
                
       case "2": {

                try {

                    PreparedStatement ps = null;

                    String sqlCreate = "CREATE TABLE IF NOT EXISTS  smsresponse_"+projectID+"("
                            + "Id serial,"
                            + "message  character varying,"
                            + "mobileno character varying,"
                            + "responsecode character varying,"
                            + "responsemessage character varying,"
                            + "sms_received_date DATE,"
                            + "PRIMARY KEY (Id))";
                    Statement stmt = con.createStatement();
                    stmt.execute(sqlCreate);
                    ps = con.prepareStatement("insert into smsresponse_"+projectID+" (message,mobileno,responsecode,responsemessage,sms_received_date) values (?,?,?,?,current_date)");
                    ps.setString(1, message);
                    ps.setString(2, mobile);
                    ps.setInt(3, responsecode);
                    ps.setString(4, responseMessage);
                    i = ps.executeUpdate();


                } catch (Exception e) {
                    System.out.println(e);
                }

       break;
       }         

           
           
            case "3": {

                try {

                    PreparedStatement ps = null;

                    String sqlCreate = "CREATE TABLE IF NOT EXISTS  smsresponse ("
                            + "Id NUMBER(5),"
                            + "message   VARCHAR2(600),"
                            + "mobileno  VARCHAR2(15),"
                            + "responsecode  VARCHAR2(19),"
                            + "responsemessage  VARCHAR2(200),"
                            + "sms_received_date DATE,"
                            + "PRIMARY KEY (Id));";
                    Statement stmt = con.createStatement();
                    stmt.execute(sqlCreate);
                    ps = con.prepareStatement("insert into smsresponse(message,mobileno,responsecode,responsemessage,sms_received_date)values(?,?,?,?,now())");
                    ps.setString(1, message);
                    ps.setString(2, mobile);
                    ps.setInt(3, responsecode);
                    ps.setString(4, responseMessage);
                    i = ps.executeUpdate();


                } catch (Exception e) {
                    System.out.println(e);
                }

       break;
       }
                
         default :
            System.out.println("********************Invalid*******************");
           
                }
        return i;

    }
    
}


    
    
    
    
    
    

