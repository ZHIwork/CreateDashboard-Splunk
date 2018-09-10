package com.umrfSplunk.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.splunk.JobArgs;
import com.splunk.Service;

public class DashboardController {
	
	/**
	 * Create dashboard XML definitions.
	 * 
	 * @param splunkService, The {@code Service} to start the service.
	 * @param xmlFileName, Name of XML File.
	 * @param dashboardName, Name of dashboard.
     * @return int, Statues of Http response.
     */
	public static int createDashboard(Service splunkService, String xmlFileName, String dashboardName) throws IOException {
		//read XML file and convert it to String
		String fileXML = readXMLasString(xmlFileName);
		JobArgs jobArgs = new JobArgs();
		//request parameters: (String) Name, Dashboard name;
		//and (XML document) eai:data, Dashboard XML definition.  
		jobArgs.put("name", dashboardName); 
		jobArgs.put("eai:data", fileXML);
		//endpoint for creating dashboard "/servicesNS/{user}/{app_name}/data/ui/views",
		//and return HTTP response status.
		int status = splunkService.post("/servicesNS/admin/search/data/ui/views",jobArgs).getStatus();
		return status;
	}
	
	
	
	/**
	 * Update a specific dashboard XML definition.
	 * 
	 * @param splunkService, The {@code Service} to start the service.
	 * @param xmlFileName, Name of updated XML File.
	 * @param dashboardName, Name of updating dashboard.
     * @return int, Statues of Http response.
     */
	public static int updateDashboard(Service splunkService, String xmlFileName, String dashboardName) throws IOException {
		//read XML file and convert it to String
		String fileXML = readXMLasString(xmlFileName);
		JobArgs jobArgs = new JobArgs();
		//request parameters: (XML document) eai:data, Dashboard XML definition.  
		jobArgs.put("eai:data", fileXML);
		//endpoint for creating dashboard "/servicesNS/{user}/{app_name}/data/ui/views/{name}",
		//and return HTTP response status.
		String endpointUpdateDashboard = "/servicesNS/admin/search/data/ui/views/" + dashboardName;
		int status = splunkService.post(endpointUpdateDashboard,jobArgs).getStatus();
		return status;
	}
	
	
	
	/**
	 * Delete a specific dashboard XML definition. 
	 * 
	 * @param splunkService, The {@code Service} to start the service.
	 * @param dashboardName, Name of deleting dashboard.
     * @return int, Statues of Http response.
     */
	public static int updateDashboard(Service splunkService, String dashboardName) throws IOException {
		//endpoint for creating dashboard "/servicesNS/{user}/{app_name}/data/ui/views/{name}",
		//and return HTTP response status.
		String endpointUpdateDashboard = "/servicesNS/admin/search/data/ui/views/" + dashboardName;
		int status = splunkService.delete(endpointUpdateDashboard).getStatus();
		return status;
	}
	
	
	
	
	/**
     * Returns the XML file in String Format.
     *
     * @param xmlFileName, name of XML File.
     * @return The String.
     */
	public static String readXMLasString(String xmlFileName) throws IOException{
		// XML file for this example
	       File xmlFile = new File(xmlFileName);
	        
	    // Get XML file as String using BufferedReader
	    // FileReader uses platform's default character encoding
	    // if you need to specify a different encoding, use InputStreamReader
	       Reader fileReader = new FileReader(xmlFile);
	       BufferedReader bufReader = new BufferedReader(fileReader);
	        
	       StringBuilder sb = new StringBuilder();
	       String line = bufReader.readLine();
	       while( line != null){
	           sb.append(line).append("\n");
	           line = bufReader.readLine();
	       }
	       String xmltoString = sb.toString();
	       bufReader.close();
	       return xmltoString;
	}
}
