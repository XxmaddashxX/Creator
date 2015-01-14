package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.tree.DefaultMutableTreeNode;

public class DriveSync implements ActionListener{


	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JButton button_download;
	private DriveProgress p;
	
	
	
	
	

	public DriveSync(){

		frame = new JFrame();
		frame.setTitle("Sync");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		this.createComps();
		frame.setVisible(true);


	}
	
	private void createComps(){

		button_download = new JButton("Download Files and Sync Files");
		contentPane.add(button_download);
		layout.putConstraint(SpringLayout.NORTH, button_download,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, button_download,
				25,
				SpringLayout.WEST, contentPane);
		button_download.addActionListener(this);
		
		

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(button_download)){
			
			try {
				List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
				
			
					
				
				
				for(int i = 0 ; i < list.size(); i++){
					//System.out.println(list.get(i).getTitle()  +" " + list.get(i).getId());
					
					for(int o = 0; o < list.get(i).getParents().size(); o++){
						
						if(list.get(i).getParents().get(o).getId().equals("0B_xX5ZPVd6EzRkdYQmg2OFYyclU")){
							//c.setDown((list.get(i).getTitle()));
							System.out.println(list.get(i).getTitle());
							InputStream test = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
							CreateFile.createFile(test, CreatorOptions.FILEPATH_FILES + "Data/Audio/" + list.get(i).getTitle());
						}
						if(list.get(i).getParents().get(o).getId().equals("0B_xX5ZPVd6EzdzBENm12WUl3Vm8")){
							//c.setDown((list.get(i).getTitle()));
							System.out.println(list.get(i).getTitle());
							InputStream test = DriveDownloader.downloadFile(DriveCommandLine.getService(), list.get(i));
							if(list.get(i).getTitle().contains("-")){
								String[] temp = list.get(i).getTitle().split("-");
								boolean justFile = false;
								if(!Tracker.getSynclist().isEmpty()){
									for(int u = 0; u < Tracker.getSynclist().size(); u++){
										if(temp[0].equals(Tracker.getSynclist().get(u))){
											justFile = true;
										}
									}
								}
								if(justFile){
									CreateFile.createFile(test, CreatorOptions.FILEPATH_FILES + "Data/Textures/" + temp[0] + "/" + temp[1]);
									Main.getScreen().addObject(Tracker.getTreelist().get(temp[0]), new DefaultMutableTreeNode(temp[1]), true);
								}
								else{
									CreateFile.createFile(test, CreatorOptions.FILEPATH_FILES + "Data/Textures/" + temp[0] + "/" + temp[1]);
									Main.getScreen().addObject(Main.getScreen().getCategory2(), new DefaultMutableTreeNode(temp[0]), true);
									Main.getScreen().addObject(Tracker.getTreelist().get(temp[0]), new DefaultMutableTreeNode(temp[1]), true);
									Tracker.addTrackerSync(temp[0]);
								}
								
								
							}
						
							
						}
						
						
					}



				}
				

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();


			}


		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public Container getContentPane() {
		return contentPane;
	}

	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

	public SpringLayout getLayout() {
		return layout;
	}

	public void setLayout(SpringLayout layout) {
		this.layout = layout;
	}

	public JButton getButton_download() {
		return button_download;
	}

	public void setButton_download(JButton button_download) {
		this.button_download = button_download;
	}

	
}
