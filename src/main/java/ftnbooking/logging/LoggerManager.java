package ftnbooking.logging;

import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class LoggerManager {
	
	Logger agentLogger;
	Logger adminLogger;
	Logger customerLogger;
	
	
	public LoggerManager(){
		initializeAgentLogger();
		initializeAdminLogger();
		initializeCustomerLogger();
	}


	private void initializeCustomerLogger() {
		// TODO Auto-generated method stub
		
	}


	private void initializeAdminLogger() {
		// TODO Auto-generated method stub
		
	}


	private void initializeAgentLogger() {
		// TODO Auto-generated method stub
		
	}


	public Logger getAgentLogger() {
		return agentLogger;
	}


	public Logger getAdminLogger() {
		return adminLogger;
	}


	public Logger getCustomerLogger() {
		return customerLogger;
	}
	
	
	
	

}
