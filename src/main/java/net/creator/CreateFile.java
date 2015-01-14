package net.creator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CreateFile {
	
	
	
	public static void createFile(InputStream stream, String location){
		 
		InputStream inputStream = stream;
		FileOutputStream outputStream= null;
		try {
			// read this file into InputStream
			
			// write the inputStream to a FileOutputStream
			File p = new File(location);
			if(!p.getParentFile().mkdirs()){
				
			}
			outputStream = 
	                    new FileOutputStream(p);
			
			int read = 0;
			byte[] bytes = new byte[1024];
	 
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
	 
			System.out.println("Done!");
	 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}
	    
	
	}

}
