package net.creator;

import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class Updater {
	
	
	private static JFrame frame;
	private static Container contentPane;
	private static SpringLayout layout;
	private static JLabel label;
	private final static String VERSIONID = "0B_xX5ZPVd6EzaURpdGZHaUVWaFE";

	public static void main(String[] args){
		getUpdate(args[0]);
	}
	
	
	public static void getUpdate(String type){
		frame = new JFrame();
		frame.setTitle("Please wait");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(200, 100);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		label = new JLabel("Downloading, The Program will close when done");
		layout.putConstraint(SpringLayout.NORTH, label,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, label,
				25,
				SpringLayout.WEST, contentPane);
		frame.setVisible(true);
		try {
			List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId().equals(VERSIONID)){
					InputStream temp = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
					CreateFile.createFile(temp, CreatorOptions.FILEPATH_FILES + "Data/Admin/update.txt");
					BufferedReader b = new BufferedReader(new FileReader(new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/update.txt")));
					b.readLine();
					b.readLine();
					if(type.equals("STABLE")){
						b.readLine();
					}
					String[] in = b.readLine().split(":");
					b.close();
					for(int u = 0; u < list.size(); u++){
						if(list.get(u).getId().equals(in[1])){
							InputStream p = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(u));
							CreateFile.createFile(p, CreatorOptions.FILEPATH_FILES + "Creator_Bin/Creator.exe");
						}
					}
					File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/update.txt");
					if(f.delete()){
						
					}
				
					
				}
			}
			frame.dispose();
		
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
