package net.creator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Loader {
	
	public static Map<String, String> getFilesFolders(File Folder){
		Map<String, String> temp = new HashMap<String, String>();
		for(File path : Folder.listFiles()){
			if(path.isFile()){
				temp.put("file", path.getName());
			}
			if(path.isDirectory()){
				temp.put("folder", path.getName());
			}
		}
		return temp;
		
	}
	
	public static ArrayList<String> listFilesForFolder(File folder) {
	    ArrayList<String> temp = new ArrayList<String>();
		for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            temp.addAll(listFilesForFolder(fileEntry));
	            
	        } else {
	            temp.add(fileEntry.getName());
	        }
	    }
		return temp;
	}
	public static ArrayList<String> listFoldersForFolder(final File folder) {
	    ArrayList<String> temp = new ArrayList<String>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	temp.add(fileEntry.getName());
	            
	        } else {
	            
	        }
	    }
		return temp;
	}
	public static void createDirs(){
		ArrayList<String> folders = new ArrayList<String>();
		
		folders.add("Data/Objects");
		folders.add("Data/Textures");
		folders.add("Data/Audio");
		folders.add("Data/Systems");
		folders.add("Data/Admin");
		String settingsDir = System.getenv("APPDATA") + "/Drifting Colossus/Creator";
		File f = new File(settingsDir);
		try{
			if(f.mkdir()) { 
				
			}
			else{

			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		for(int i =0; i < folders.size(); i++){
			String temp = System.getenv("APPDATA") + "/Drifting Colossus/Creator/" + folders.get(i);
			File p = new File(temp);
			if(!p.exists()){
				if(p.mkdirs()){
					
				}
			}
			else{
				
			}
		}
		
	}
	public static ArrayList<File> listFilesOnly(final File folder) {
	    ArrayList<File> temp = new ArrayList<File>();
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	
	            
	        } else {
	        	temp.add(fileEntry);
	        }
	    }
		return temp;
	}
	
	

}
