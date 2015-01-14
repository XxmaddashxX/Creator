package net.creator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.services.drive.Drive;

public class DriveCheck implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String code = DriveCommandLine.getInput().getText();
	    if(code.equals("bypass")){
	    	 DriveCommandLine.getFrame().dispose();
		     DriveCommandLine.setRunning(false);
		     CreatorOptions.setDriveEnabled(false);
	    	return;
	    }
	    GoogleTokenResponse response = null;
		try {
			response = DriveCommandLine.getFlow().newTokenRequest(code).setRedirectUri(DriveCommandLine.getREDIRECT_URI()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response !=null){
			GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
		    CreatorOptions.setDriveEnabled(true);
		    //Create a new authorized API client
		     DriveCommandLine.setService(new Drive.Builder(DriveCommandLine.getHttpTransport(), DriveCommandLine.getJsonFactory(), credential).build());
		     DriveCommandLine.getFrame().dispose();
		     DriveCommandLine.setRunning(false);
		     
		    //Insert a file  
		    /*File body = new File();
		    body.setTitle("My document");
		    body.setDescription("A test document");
		    body.setMimeType("text/plain");
		    
		    java.io.File fileContent = new java.io.File("src/main/java/net/creator/document.txt");
		    FileContent mediaContent = new FileContent("text/plain", fileContent);

		    File file = service.files().insert(body, mediaContent).execute();
		    System.out.println("File ID: " + file.getId());*/
		}
	    
		
	}

}
