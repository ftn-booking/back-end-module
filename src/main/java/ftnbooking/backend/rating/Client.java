package ftnbooking.backend.rating;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
 
public class Client{
	
	private static RatingData dataToSend = new RatingData(5, 1, 1);
	
 	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
 		
 		
 		newTry4();	
 		
 	}
 	
 	private static void newTry4() throws ClientProtocolException, IOException
 	{
 		//String inputXML = dataToSend.toJSON();
		  InputStream in ;
		  //StringEntity entity = new StringEntity(inputXML, ContentType.create("text/json", Consts.UTF_8));
		  //entity.setChunked(true);
		  String json = "{ \"oldGrade\" : \"4\" , \"numberOfGrades\" : \"4\", \"newGrade\" : \"4\"}";
		  HttpPost httppost = new HttpPost( "http://localhost:7145/ratingService/rating");
		  httppost.setHeader("Content-type", "application/json");
		  StringEntity params = new StringEntity(json);
		  httppost.setEntity(params);
		  
		  HttpClient client = HttpClients.createDefault();

		  try {
			  
		   HttpResponse response = client.execute(httppost);
		   //System.out.println(response.toString());
		   
		   in=response.getEntity().getContent();
		   //System.out.println(in.toString());
		   String body = IOUtils.toString(in);
		   System.out.println(body);
		  } catch (ClientProtocolException e) {
		   System.out.println(e);
		  } catch (IOException e) {
		   System.out.println(e);
		  }

 	}
 	
 	
 	
}