package ftnbooking.backend.rating;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import ftnbooking.backend.comments.Approval;

public class RemoteCommentService {
private static String serviceUrl = "http://remote-rating-service.herokuapp.com";
	
	public static boolean addProfanity(String profanity)
	{
		try
		{
			
			HttpPost httppost = new HttpPost( serviceUrl+"/ratingService/profanity");
			StringEntity params = new StringEntity(profanity);
			httppost.setEntity(params);
			HttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(httppost);
			InputStream in=response.getEntity().getContent();
			String body = IOUtils.toString(in);
			return body.equals("true");
			

		}catch(Exception ex)
		{
			System.out.println(ex.toString());
			
		}
		return false;
	}
	
	public static boolean removeProfanity(String profanity)
	{
		try
		{
			
			HttpPut httpput = new HttpPut(  serviceUrl+"/ratingService/profanity");
			StringEntity params = new StringEntity(profanity);
			httpput.setEntity(params);
			HttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(httpput);
			InputStream in=response.getEntity().getContent();
			String body = IOUtils.toString(in);
			return body.equals("true");
			
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
			
		}
		return false;
	}
	public static ArrayList<String> getProfanities()
	{
		ArrayList<String> retVal = new ArrayList<>();
		
		try
		{
			HttpGet httpGet = new HttpGet(  serviceUrl+"/ratingService/profanity");
			
			HttpClient client = HttpClients.createDefault();
			HttpResponse response = client.execute(httpGet);
			InputStream in=response.getEntity().getContent();
			String body = IOUtils.toString(in);
			System.out.println(body);
			String[] splits = body.replace("[", "").replace("]", "").replace("\"", "").split(",");
			for (String string : splits) 
			{
				retVal.add(string.trim());
			}
			
		}catch(Exception ex)
		{
			ex.toString();
		}
		
		
		return retVal;
	}
	
	
	public static Approval checkComment(String comment)
	{
		try
		{
		HttpPost httppost = new HttpPost( serviceUrl+"/ratingService/comments");
		StringEntity params = new StringEntity(comment);
		httppost.setEntity(params);
		HttpClient client = HttpClients.createDefault();
		HttpResponse response = client.execute(httppost);
		InputStream in=response.getEntity().getContent();
		String body = IOUtils.toString(in);
		
		if(body.equals("true"))
		{
			return Approval.NOTAPPROVED;
		}
		
		
		return Approval.PENDING;
		}catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
		return Approval.PENDING;
	}
}
