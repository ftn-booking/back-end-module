package ftnbooking.logging;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnbooking.backend.admin.AdminController;
import ftnbooking.backend.users.ApplicationUserService;

@Component
public class LoggerManager {
	@Autowired
	private ApplicationUserService userService;
	
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
		try
		{
			customerLogger = Logger.getLogger("Customer");
			customerLogger.setLevel(Level.INFO);
	        FileHandler fileTxt = new FileHandler("CustomerLogs.txt");
	       
	
	        // create a TXT formatter
	        
	        customerLogger.addHandler(fileTxt);
		}catch(Exception ex)
		{
			System.out.println("Failed to create customer logger;");
		}
	}


	private void initializeAdminLogger() {
		// TODO Auto-generated method stub
		try
		{
			adminLogger = Logger.getLogger(AdminController.class.getName());
			adminLogger.setLevel(Level.INFO);
	        FileHandler fileTxt = new FileHandler("AdminLogs.txt");
	       
	
	        // create a TXT formatter
	        
	        adminLogger.addHandler(fileTxt);
		}catch(Exception ex)
		{
			System.out.println("Failed to create admin logger;");
		}
	}


	private void initializeAgentLogger() {
		try
		{
			agentLogger = Logger.getLogger("Agent");
			agentLogger.setLevel(Level.INFO);
	        FileHandler fileTxt = new FileHandler("AgentLogs.txt");
	        
	        agentLogger.addHandler(fileTxt);
		}catch(Exception ex)
		{
			System.out.println("Failed to create agent logger;");
		}	
	}

    public void logAdmin(String message, String userEmail)
    {
    	StringBuilder logMessage = new StringBuilder();
    	if(adminLogger==null)
    		return;
    	logMessage.append(new Date().toString());
    	logMessage.append("\t\t");
    	logMessage.append(userEmail);  
    	logMessage.append("\t\t\t");
    	logMessage.append(message);
    	
    	
    	
    	
    	
    	adminLogger.info(logMessage.toString());
    	
    }
    
    public void logCustomer(String message, String userEmail)
    {
    	StringBuilder logMessage = new StringBuilder();
    	if(customerLogger==null)
    		return;
    	logMessage.append(new Date().toString());
    	logMessage.append("\t\t");
    	logMessage.append(userEmail);  
    	logMessage.append("\t\t\t");
    	logMessage.append(message);
    	
    	
    	
    	
    	
    	customerLogger.info(logMessage.toString());
    	
    }
	
	public void logAgent(String message, String userEmail) {
		StringBuilder logMessage = new StringBuilder();
    	if(agentLogger==null)
    		return;
    	logMessage.append(new Date().toString());
    	logMessage.append("\t\t");
    	logMessage.append(userEmail);  
    	logMessage.append("\t\t\t");
    	logMessage.append(message);
    	
    	agentLogger.info(logMessage.toString());
    	
	}
	
	

}
