package com.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import sun.util.logging.resources.logging;

public class HelperClass {

	public HelperClass() {
		// TODO Auto-generated constructor stub
	}
	
	public static String roll(ArrayList lignes,HashMap<String, String> client, HashMap<String, String> devis,String randID) throws Exception {

		
		String path          = "C:\\Users\\oaitbenali\\Documents\\studio-workspace\\excel-rest-devis\\src\\main\\resources\\";
		String excelFilePath = path + "Exemple-n.xlsx";
        
           
				FileInputStream file = new FileInputStream(new File(excelFilePath));
	            Workbook workbook;
				workbook = new XSSFWorkbook(file);
				
	            Sheet sheet = workbook.getSheetAt(0);
 
	            
	            // setting up left side info
	            
	            int rowCount = 13;
	            Row row = sheet.getRow(rowCount);
    			Cell cellDev = row.getCell(2);
    			cellDev.setCellValue(devis.get("date"));
    			rowCount = 14;
    			row = sheet.getRow(rowCount);
    			cellDev = row.getCell(2);
    			cellDev.setCellValue(client.get("numero"));
    			rowCount = 15;
    			row = sheet.getRow(rowCount);
    			cellDev = row.getCell(2);
    			cellDev.setCellValue(devis.get("numero"));
	            // setting up right side info
    			rowCount = 8;
    			row = sheet.getRow(rowCount);
    			cellDev = row.getCell(7);
    			cellDev.setCellValue(client.get("nom"));
    			rowCount = 9;
    			row = sheet.getRow(rowCount);
    			cellDev = row.getCell(7);
    			cellDev.setCellValue(client.get("adresse"));
    			rowCount = 10;
    			row = sheet.getRow(rowCount);
    			cellDev = row.getCell(7);
    			cellDev.setCellValue(client.get("ville"));
            
 
            	rowCount = 17;
 
                row = sheet.getRow(rowCount);

                int descCellPos = 1;
                int unitCellPos = 5;
                int qteCellPos = 6;
                int punitCellPos = 7;
                int tvaCellPos = 8;
                int thtCellPos = 9;

                int totaleHT = 0;
                int totaleTVA = 0;


                int rows=sheet.getLastRowNum();
                
                
                for (int i = 0; i < lignes.size(); i++) {
                	rowCount++;
                	sheet.shiftRows(rowCount,rows,1); 
                    row = sheet.createRow(rowCount);
                    
                	HashMap<String, String> line = (HashMap<String, String>) lignes.get(i);
        			
                	Cell descCellPoscell = row.createCell(descCellPos);
        			Cell unitCellPoscell = row.createCell(unitCellPos);
        			Cell qteCellPoscell = row.createCell(qteCellPos);
        			Cell punitCellPoscell = row.createCell(punitCellPos);
        			Cell tvaCellPoscell = row.createCell(tvaCellPos);
        			Cell totalHTCellPoscell = row.createCell(thtCellPos);
        			
        			descCellPoscell.setCellValue(line.get("description"));
                    unitCellPoscell.setCellValue(Integer.valueOf(String.valueOf(line.get("unite"))));
                    qteCellPoscell.setCellValue(Integer.valueOf(String.valueOf(line.get("qte"))));
                    punitCellPoscell.setCellValue(Integer.valueOf(String.valueOf(line.get("Punit"))));
                    tvaCellPoscell.setCellValue(Integer.valueOf(String.valueOf(line.get("tva"))));
                    totalHTCellPoscell.setCellValue(
                    		Integer.valueOf(String.valueOf(line.get("unite"))) *
                    		Integer.valueOf(String.valueOf(line.get("qte"))) *
                    		Integer.valueOf(String.valueOf(line.get("Punit")))
                    );
                    totaleHT +=
                    		Integer.valueOf(String.valueOf(line.get("unite"))) *
                    		Integer.valueOf(String.valueOf(line.get("qte"))) *
                    		Integer.valueOf(String.valueOf(line.get("Punit")));
                    
                    totaleTVA += Integer.valueOf(String.valueOf(line.get("tva")));
        		}
			    
                
                rowCount = rowCount + 4;
                
                // filling the data in the footer
                row = sheet.getRow(rowCount);
    			cellDev = row.getCell(9);
    			cellDev.setCellValue(totaleHT);
    			// total TVA cell
    			row = sheet.getRow(rowCount+1);
    			cellDev = row.getCell(9);
    			cellDev.setCellValue(totaleHT * 0.1);
    			// total TTC
    			row = sheet.getRow(rowCount+2);
    			cellDev = row.getCell(9);
    			cellDev.setCellValue((totaleHT * 0.1) + totaleHT);
    			
    			
    			
    			// total TTC - 20
    			row = sheet.getRow(rowCount+1);
    			cellDev = row.getCell(1);
    			cellDev.setCellValue(totaleHT);
    			// total TTC - 10
    			row = sheet.getRow(rowCount+2);
    			cellDev = row.getCell(1);
    			cellDev.setCellValue(totaleHT);
    			// total TTC - 05
    			row = sheet.getRow(rowCount+3);
    			cellDev = row.getCell(1);
    			cellDev.setCellValue(totaleHT);
    			
    			// workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
    			workbook.setForceFormulaRecalculation(true);
                
                file.close();
 
	            FileOutputStream outputStream = new FileOutputStream(path + randID + ".xlsx");
	            workbook.write(outputStream);
	            workbook.close();
	            outputStream.close();
			
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
