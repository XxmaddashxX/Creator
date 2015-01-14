package net.creator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WindowsDocCreator {
	
	
	public static void createExcelFile(InputStream input, String location){
		try {
			XSSFWorkbook wb = new XSSFWorkbook(OPCPackage.open(input));
			//Workbook wb = WorkbookFactory.create(input);
			FileOutputStream out = new FileOutputStream(location);
			wb.write(out);
			out.close();
		} catch (InvalidFormatException | IOException e) {
			
			e.printStackTrace();
		}
		           
		       
	}

}
