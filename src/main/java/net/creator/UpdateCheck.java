package net.creator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UpdateCheck {
	private final static String LOGINSID = "0B_xX5ZPVd6EzQ05ESmRKUkY4Mjg";
	private final static String VERSIONID = "0B_xX5ZPVd6EzaURpdGZHaUVWaFE";
	public static void isUpdateAvailable(){
		if(!CreatorOptions.isDriveEnabled()){
			return;
		}
		if(Main.getUser() == null){
			return;
		}
		try {
			List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId().equals(LOGINSID)){
					InputStream temp = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
					CreateFile.createFile(temp, CreatorOptions.FILEPATH_FILES + "Data/Admin/temp.txt");
					BufferedReader b = new BufferedReader(new FileReader(new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/temp.txt")));

					for(String line; (line = b.readLine()) != null; ) {
						String[] string = line.split(",");
						if(string[0].equals(Main.getUser())){
							if(compareVersions(string[2])){
								String[] p = {string[2]};
								Main.setCanUpdate(true);
								Main.setType(p);
							}
							
						}
						
					}
					b.close();
				}
			}

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		
		
	}
	public static boolean compareVersions(String st){
		try {
			List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId().equals(VERSIONID)){
					InputStream temp = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
					CreateFile.createFile(temp, CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
					BufferedReader b = new BufferedReader(new FileReader(new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt")));
					if(st.equals("LATEST")){
						System.out.println("L");
						String[] latest = b.readLine().split(":");
						double t = Double.parseDouble(latest[1]);
						System.out.println(t);
						if(t > CreatorProps.CREATOR_VERSION){
							
							b.close();
							File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
							if(f.delete()){
								
							}
							return true;
						}
						else{
							b.close();
							File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
							if(f.delete()){
								
							}
							return false;
						}
					}
					if(st.equals("STABLE")){
						System.out.println("ST");
						b.readLine();
						String[] stable = b.readLine().split(":");
						double t = Double.parseDouble(stable[1]);
						if(t > CreatorProps.CREATOR_VERSION){
							b.close();
							File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
							if(f.delete()){
								
							}
							return true;
						}
						else{
							b.close();
							File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
							if(f.delete()){
								
							}
							return false;
						}
					}
					b.close();
					File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/Vtemp.txt");
					if(f.delete()){
						
					}
					
				}
			}

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}

		
		return false;
	}

}
