package com.umrfSplunk.Dashboard;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.splunk.JobArgs;
import com.splunk.SSLSecurityProtocol;
import com.splunk.Service;
import com.splunk.ServiceArgs;

import com.umrfSplunk.Controller.*;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) throws IOException {
		//set up the SSL version 
				//this only happened when the JavaSE version is newer than 1.7 
				Service.setSslSecurityProtocol(SSLSecurityProtocol.TLSv1_2);
				
				//set up parameters when login in 
				ServiceArgs serviceArgs = new ServiceArgs();
				serviceArgs.setUsername("admin");//username to login in.
				serviceArgs.setPassword("S374354715shenlan");//password to login in
				serviceArgs.setHost("localhost");//host address to login in
				serviceArgs.setPort(8089);//8089 is the Splunk Management Port 
				
				
				//will login and save the session key which gets put in the HTTP Authorization header
				Service splunkService = Service.connect(serviceArgs);
				
				//create dashboard
				int status = DashboardController.createDashboard(splunkService, "DashboardXml.xml", "Test1");
				System.out.printf("status: %s%n", status);
				
				//update dashboard
				//int status = updateDashboard(splunkService, "UpdateDashboardXml.xml", "Test1");
				//System.out.printf("status: %s%n", status);
				
				//delete dashboard
				//int status = updateDashboard(splunkService, "Test1");
				//System.out.printf("status: %s%n", status);
				SpringApplication.run(DashboardApplication.class, args);
	}
}
