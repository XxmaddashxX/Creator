package net.creator;

import java.awt.Container;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class NewsScreen {
	private final String NEWSID = "0B_xX5ZPVd6Ezb2JILXFPYVJpd0U";
	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JTextArea textarea;
	private JScrollPane scrollpane;

	
	public NewsScreen(){
		frame = new JFrame();
		frame.setTitle("News");
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		frame.setSize(400, 300);
		textarea = new JTextArea(21, 34);
		textarea.setWrapStyleWord(true);
		textarea.setLineWrap(true);
		textarea.setEditable(false);
		scrollpane = new JScrollPane(textarea);
		scrollpane.setPreferredSize(new Dimension(380,260));
		contentPane.add(scrollpane);
		try {
			List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId().equals(NEWSID)){
					InputStream test = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
					CreateFile.createFile(test, CreatorOptions.FILEPATH_FILES + "Data/Admin/OfflineNews.txt");
					BufferedReader b = new BufferedReader(new FileReader(new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/OfflineNews.txt")));
					
					for(String line; (line = b.readLine()) != null; ) {
				        textarea.append(line);
				        textarea.append("\n");
				    }
					b.close();
				}
					
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.setVisible(true);
	}
	
	
}
