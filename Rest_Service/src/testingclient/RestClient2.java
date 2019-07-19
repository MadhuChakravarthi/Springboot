package testingclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class RestClient2 {

	public static void main(String[] args) throws Exception {
		 Connection con=null;
	  try {
       
        PreparedStatement pst = null; 
        util.MyConnection myConnection=new util.MyConnection();
        con=myConnection.getConnection();		
        String sumResult="";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet("http://localhost:8082/RestExample/rest/bhunakshaservicepush");
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                         new InputStreamReader((response.getEntity().getContent())));

		String output;
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		
			   sumResult = output + "";
		
		}
		
		
		 JSONArray jsonarr = new JSONArray(sumResult);
		 
		 for (int i = 0; i < jsonarr.length(); i++) {
             JSONObject jsonobj = jsonarr.getJSONObject(i);
             System.out.println(jsonobj.getString("gis_code"));
             //gis_code,digital_signature,digital_verified,signed_by,signed_date,tokennum
           
           
          
            
    String sql="update  collabland_data_main_push set digital_signature=?,digital_verified=?,signed_by=?,signed_date=to_date(?,'YYYY-MM-DD'),tokennum=? where gis_code=?";
    pst=con.prepareStatement(sql);
    pst.setString(1,jsonobj.getString("digital_signature"));
    pst.setString(2,jsonobj.getString("digital_verified"));
    pst.setString(3,jsonobj.getString("signed_by"));
    pst.setString(4,jsonobj.getString("signed_date"));
    pst.setString(5,jsonobj.getString("tokennum"));
    pst.setString(6,jsonobj.getString("gis_code"));
    int k =pst.executeUpdate();
    
              
		 
		 
		 }
		 

		httpClient.getConnectionManager().shutdown();

	  } catch (ClientProtocolException e) {

		System.out.println(e);

	  } catch (IOException e) {

		  System.out.println(e);
	  }
	  
	  finally
		{
			con.close();
		}

	}
	
	

}
