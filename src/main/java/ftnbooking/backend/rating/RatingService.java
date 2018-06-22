package ftnbooking.backend.rating;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import ftnbooking.backend.rating.contract.IRatingService;

public class RatingService {

	private static String serviceUrl = "http://remote-rating-service.herokuapp.com";
	public static double getGrade(RatingData ratingData)
	{
		double retVal = 0;
		
		try
		{
			
			
			
			InputStream in ;
		  //StringEntity entity = new StringEntity(inputXML, ContentType.create("text/json", Consts.UTF_8));
		  //entity.setChunked(true);
		  String json = ratingData.toJSON();
		  HttpPost httppost = new HttpPost( serviceUrl +"/ratingService/rating");
		  httppost.setHeader("Content-type", "application/json");
		  StringEntity params = new StringEntity(json);
		  httppost.setEntity(params);
		  
		  HttpClient client = HttpClients.createDefault();

		  
			  
		   HttpResponse response = client.execute(httppost);
		   //System.out.println(response.toString());
		   
		   in=response.getEntity().getContent();
		   //System.out.println(in.toString());
		   String body = IOUtils.toString(in);
		   retVal= Double.parseDouble(body);
		 
			
		}catch(Exception ex)
		{
			retVal = calculateLocally(ratingData);
		}
		return retVal;
	}

	private static double calculateLocally(RatingData ratingData) {
		// TODO Auto-generated method stub
		double sumOfGrades = ratingData.getOldGrade() * ratingData.getNumberOfGrades();
        sumOfGrades += ratingData.getNewGrade();
        double retVal = sumOfGrades / (ratingData.getNumberOfGrades() + 1);
        return retVal;
	}
}
