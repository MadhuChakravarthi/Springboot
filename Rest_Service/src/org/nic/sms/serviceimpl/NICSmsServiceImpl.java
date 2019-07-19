/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nic.sms.serviceimpl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author pc
 */
public class NICSmsServiceImpl implements org.nic.sms.services.SMSService {

    private HttpsURLConnection httpURLCon;

    @Override
    public int sendSingleSMS(String message, String mobile, Connection con, String typeOfConnection,int projectID) {

        int result = 0;

        try {

            String url = null;
            String https_url = null;
            String res = null;

            int resCode = 0;

            ResourceBundle rb = ResourceBundle.getBundle("SMSCredentials");


            try {

                url = rb.getString("project.sms.url");
                message = URLEncoder.encode(message, "UTF-8");
                https_url = (new StringBuilder()).append(url).append("?username=").append(rb.getString("project.sms.username")).append("&pin=").append(rb.getString("project.sms.password")).append("&message=").append(message).append("&mnumber=").append(mobile).append("&signature=").append(rb.getString("project.sms.signature")).toString();
                SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String t) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String t) {
                }
            }};

                if (trust_mgr != null) {
                    ssl_ctx.init(null, trust_mgr, new SecureRandom());
                    HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
                    URL urlCon = new URL(https_url);
                    httpURLCon = (HttpsURLConnection) urlCon.openConnection();
                    if (httpURLCon != null) {
                        res = httpURLCon.getResponseMessage();
                        resCode = httpURLCon.getResponseCode();
                        String resMessage = httpURLCon.getResponseMessage();
                        System.out.println("resCode " + resCode);
                        if (resCode == 200) {
                            result = storeSMSLog(message, mobile, resCode, resMessage, con, typeOfConnection,projectID);
                            if (result == 1) {
                                result = 1;
                            } else {
                                result = 0;
                            }
                            httpURLCon.disconnect();
                        }
                    } else {
                        System.out.println("httpURLCon is null");
                    }
                } else {
                    System.out.println("trust_mgr is null");
                }


            } catch (UnsupportedEncodingException uex) {
                System.out.println("UnsupportedEncodingException SMS Failed");
            } catch (MalformedURLException muex) {
                System.out.println("MalformedURLException SMS Failed");
            } catch (IOException ioex) {
                System.out.println("IOException SMS Failed");
            } finally {
                if (httpURLCon != null) {
                    httpURLCon.disconnect();
                }
            }



        } catch (Exception e) {
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
    public int storeSMSLog(String message, String mobile, int responsecode, String responseMessage, Connection con, String typeofConnection,int projectID) {


        int i = 0;


        switch (typeofConnection) {
            case "1": {

                try {

                    PreparedStatement ps = null;

                    String sqlCreate = "CREATE TABLE IF NOT EXISTS  smsresponse ("
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




            }
                
                
                
       case "2": {

                try {

                    PreparedStatement ps = null;

                    String sqlCreate = "CREATE TABLE IF NOT EXISTS  smsresponse ("
                            + "Id integer(11) serial,"
                            + "message  characater varying,"
                            + "mobileno characater varying,"
                            + "responsecode characater varying,"
                            + "responsemessage characater varying,"
                            + "sms_received_date DATE,"
                            + "PRIMARY KEY (Id));";
                    Statement stmt = con.createStatement();
                    stmt.execute(sqlCreate);
                    ps = con.prepareStatement("insert into smsresponse(message,mobileno,responsecode,responsemessage,sms_received_date)values(?,?,?,?,current_date())");
                    ps.setString(1, message);
                    ps.setString(2, mobile);
                    ps.setInt(3, responsecode);
                    ps.setString(4, responseMessage);
                    i = ps.executeUpdate();


                } catch (Exception e) {
                    System.out.println(e);
                }

       
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

       
       }
           
           
           
           
           


        }
        return i;

    }
}