package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class Login implements ActionListener{
	private final String LOGINSID = "0B_xX5ZPVd6EzQ05ESmRKUkY4Mjg";
	private JFrame frame;

	private JButton buttonlogin;
	private JTextArea textareauser;
	private JTextArea textareapass;
	private JLabel labeluser;
	private JLabel labelpassword;
	public Login(){

		frame = new JFrame();
		frame.setTitle("Login");
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		buttonlogin = new JButton("Login");
		textareauser = new JTextArea(1, 30);
		textareapass = new JTextArea(1, 30);
		buttonlogin.addActionListener(this);
		labeluser = new JLabel("Username:");
		labelpassword = new JLabel("Password:");
		contentPane.add(buttonlogin);
		contentPane.add(textareapass);
		contentPane.add(textareauser);
		contentPane.add(labeluser);
		contentPane.add(labelpassword);
		layout.putConstraint(SpringLayout.NORTH, buttonlogin,
				200,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, buttonlogin,
				250,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, textareauser,
				60,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, textareauser,
				20,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, textareapass,
				120,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, textareapass,
				20,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, labeluser,
				40,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, labeluser,
				20,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, labelpassword,
				100,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, labelpassword,
				20,
				SpringLayout.WEST, contentPane);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
			for(int i = 0; i < list.size(); i++){
				if(list.get(i).getId().equals(LOGINSID)){
					InputStream temp = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
					CreateFile.createFile(temp, CreatorOptions.FILEPATH_FILES + "Data/Admin/temp.txt");
					BufferedReader b = new BufferedReader(new FileReader(new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/temp.txt")));

					for(String line; (line = b.readLine()) != null; ) {
						String[] string = line.split(",");
						System.out.println(line);
						if(string[0].equals(textareauser.getText())){
							if(string[1].equals(textareapass.getText())){
								Main.setUser(string[0]);
								frame.dispose();
								b.close();
								File t = new File(CreatorOptions.FILEPATH_FILES + "Data/Admin/temp.txt");
								if(t.delete()){
									
								}
								Main.setLogopen(false);
								return;
							}
						}
					}
					b.close();
				}
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

}
