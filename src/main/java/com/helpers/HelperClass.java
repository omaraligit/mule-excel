package com.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;


public class HelperClass {

	public HelperClass() {
		// TODO Auto-generated constructor stub
	}
	
	public static String roll(ArrayList lignes,HashMap<String, String> client, HashMap<String, String> devis,String randID) throws Exception {
		
		String path          = "C:\\Users\\oaitbenali\\Documents\\studio-workspace\\excel-rest-devis\\src\\main\\resources\\";
		String excelFilePathI = path + "Exemple-n.xls";
		String excelFilePathO = path + randID + ".xls";
        
		List<Ligne> employees = new ArrayList<Ligne>();
		employees.add(new Ligne("rr", "12" ,"12", "12", "12"));
		employees.add(new Ligne("rer", "12" ,"12", "12", "12"));
		employees.add(new Ligne("rre", "12" ,"12", "12", "12"));
		employees.add(new Ligne("err", "12" ,"12", "12", "12"));
		employees.add(new Ligne("zrr", "12" ,"12", "12", "12"));
		employees.add(new Ligne("rzr", "12" ,"12", "12", "12"));
		employees.add(new Ligne("rrz", "12" ,"12", "12", "12"));

		FileInputStream fileInputStream   = new FileInputStream(new File(excelFilePathI));
		FileOutputStream fileOutputStream = new FileOutputStream(excelFilePathO);
	    Context context = new Context();
	    context.putVar("employees", employees);
	    JxlsHelper.getInstance().processTemplate(fileInputStream, fileOutputStream, context);
	        
	    
	    
			
	    return "file ready to download...";  
       
	}
	
	
	public static void logit(String logtext) {
		  // Create Logger instance 
		  Logger logger = Logger.getLogger(HelperClass.class.getName());
		  // Add ConsoleHandler
		  ConsoleHandler consoleHandler = new ConsoleHandler();
		  logger.addHandler(consoleHandler);
		  if (logger.isLoggable(Level.INFO)) {
			  logger.info(logtext);
		  }
	}

}
