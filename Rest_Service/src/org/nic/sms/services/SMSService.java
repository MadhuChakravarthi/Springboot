
package org.nic.sms.services;

import java.sql.Connection;


public interface SMSService {

    public int sendSingleSMS(String message,String mobile,Connection con,String typeofConnection,int projectID);
    public String sendMultipleSMS();
    public String receiveSMS();
    public int storeSMSLog(String message,String mobile,int responsecode,String responseMessage,Connection con,String typeofConnection,int projectID);
    
}
