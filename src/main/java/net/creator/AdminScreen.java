package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class AdminScreen implements ActionListener{
	
	private final String KEYSID = "0B_xX5ZPVd6EzQU5BZTc0dXBmeUk";
	private String User;
	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JButton buttonkeys;
	public AdminScreen(String user){
		User = user;
		
		frame = new JFrame();
		frame.setSize(200,600);
		frame.setTitle("Admin Kit: " + User);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		createComps();
		frame.setVisible(true);
		
	}
	private void createComps(){
		buttonkeys = new JButton("Keys File Edit");
		contentPane.add(buttonkeys);
		buttonkeys.addActionListener(this);
		layout.putConstraint(SpringLayout.NORTH, buttonkeys,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, buttonkeys,
				25,
				SpringLayout.WEST, contentPane);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonkeys)){
			try {
				List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
				for(int i = 0 ; i < list.size(); i++){
					if(list.get(i).getId().equals(KEYSID)){
						InputStream test = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
						WindowsDocCreator.createExcelFile(test, CreatorOptions.FILEPATH_FILES + "Data/Admin/"  + list.get(i).getTitle());
						

					}
				}
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
	}

}
