package ftnbooking.backend;

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ftnbooking.backend.lodgings.LodgingServiceSoap;

@Configuration
public class CXFConfig {

	@Autowired
	private LodgingServiceSoap lodgingWebService;

	@Bean
	public Endpoint accomodationWebServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), lodgingWebService);
		endpoint.publish("/LodgingService");
		return endpoint;
	}
	
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		SpringBus springBus = new SpringBus();
		springBus.getInInterceptors().add(getWSS4JInInterceptor());
		springBus.getOutInterceptors().add(getWSS4JOutInterceptor());
		return springBus;
	}
	
	private WSS4JInInterceptor getWSS4JInInterceptor() {
		Map<String, Object> inProps = new HashMap<String, Object>();
		
		return new WSS4JInInterceptor(inProps);
	}

	private WSS4JOutInterceptor getWSS4JOutInterceptor() {
		Map<String, Object> outProps = new HashMap<String, Object>();
		
		return new WSS4JOutInterceptor(outProps);
	}
	
}
