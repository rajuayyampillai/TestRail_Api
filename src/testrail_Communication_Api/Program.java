/**
 * TestRail API binding for Java
 */
package testrail_Communication_Api;
import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class Program {
	
	APIClient client;
	JSONObject obj;
	
	/**
	 * This function used to intialize client 
	 * API client is same URL that you use in web browser to 
	 * access TestRail.
	 * (e.g., https://<your-name>.testrail.com/ or http://<server>/testrail/).
	 * 
	 */
	public void intializeClient(String url){
		 client = new APIClient(url);
	}
	
	/**
	 * This is used to set user name and password used for authenticating the API requests
	 * 
	 */
	public void setUser(String usr,String pwd){
		client.setUser(usr);
		client.setPassword(pwd);
	}
    
	/**
	 * This is used to add runner to your existing testcase 
	 * 
	 */
    public long add_run(int pid,int suiteid,String run_name,String r_description,int r_assignedto_id,boolean r_include_all) throws MalformedURLException, IOException, APIException{
    	obj= new JSONObject();
    	obj.put("suite_id", suiteid);
    	obj.put("name", r_description);
    	obj.put("assignedto_id", r_assignedto_id);
    	obj.put("include_all", r_include_all);
        JSONObject response= (JSONObject) client.sendPost("add_run/"+pid, obj);
    	long rid=(long)response.get("id");
    	System.out.print("Run id "+rid);
    	return rid;
    	}
	
	/**
	 * This function is used to add result for your testcase 
	 */
	public void add_result_for_Case(long rid,int tid,String status,String cmt) throws MalformedURLException, IOException, APIException{
		
		 obj= new JSONObject();
		 obj.put("comment", cmt);
		if(status.equals("pass")){
			obj.put("status_id", new Integer(1));
		}
		
		else if (status.equals("fail")){
			obj.put("status_id", new Integer(5));
		}
		client.sendPost("add_result_for_case/"+rid+"/"+tid, obj);
	}
	
	

}
