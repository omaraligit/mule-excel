package com.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
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
		
		String path           = "C:\\Users\\oaitbenali\\Documents\\studio-workspace\\excel-rest-devis\\src\\main\\resources\\";
		logit(path.toString());
		String excelFilePathI = path + "Exemple-n.xls";
		String excelFilePathO = path + randID + ".xls";

	    int totalHT = 0;
	    
		List<Ligne> xlsLignes = new ArrayList<Ligne>();
		for (int i = 0; i < lignes.size(); i++) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> oneLine  = (HashMap<String, String>) lignes.get(i);
			xlsLignes.add(new Ligne(
					String.valueOf(oneLine.get("description")),
					String.valueOf(oneLine.get("unite")),
					String.valueOf(oneLine.get("qte")),
					String.valueOf(oneLine.get("Punit")),
					String.valueOf(oneLine.get("tva"))
			));

		    totalHT = 
		    		Integer.valueOf(String.valueOf(oneLine.get("unite"))) * 
		    		Integer.valueOf(String.valueOf(oneLine.get("qte"))) * 
		    		Integer.valueOf(String.valueOf(oneLine.get("Punit")));
		}

		FileInputStream fileInputStream   = new FileInputStream(new File(excelFilePathI));
		FileOutputStream fileOutputStream = new FileOutputStream(excelFilePathO);
	    Context context = new Context();
	    context.putVar("lines", xlsLignes);
	    context.putVar("devis", devis);
	    context.putVar("client", client);
		context.putVar("totalHT", totalHT);
	    
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
