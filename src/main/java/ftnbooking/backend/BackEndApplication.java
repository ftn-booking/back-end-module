package ftnbooking.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BackEndApplication {
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){

	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("localhost")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}
}
