package testingclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class RestClient {

	public static void main(String[] args) throws Exception {
		
		Connection con=null;
		PreparedStatement ps=null;
		util.MyConnection myConnection=new util.MyConnection();
	    ResultSet rs=null;
	  try {
	    con = myConnection.getConnection();	
        System.out.println("**********************************");
        String sumResult="";
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet("http://localhost:8082/RestExample/rest/bhunakshaservice");
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
             System.out.println(jsonobj.getString("concat"));
             
             
            String sql = "insert into collabland_data_main(gis_code,data,created_by,mapscale,scale_units) values (?,?,?,?,?)"; 
             ps = con.prepareStatement(sql);
             ps.setString(1,jsonobj.getString("gis_code"));
             ps.setString(2,jsonobj.getString("data"));
             ps.setString(3,jsonobj.getString("created_by"));
             ps.setString(4,jsonobj.getString("mapscale"));
             ps.setString(5,jsonobj.getString("scale_units"));
             
             ps.executeUpdate();
            
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
