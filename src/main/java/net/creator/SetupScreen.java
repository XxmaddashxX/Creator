package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class SetupScreen implements ActionListener{
	private JTextArea exportDirectory, configDirectory;
	private JComboBox<String> model;
	private JButton buttonok;
	private JFrame frame;
	public boolean isOpen;
	public SetupScreen(){
		
	}
	public void setup(){
		isOpen = true;
		frame = new JFrame();
		frame.setTitle("Setup Export Directories");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		frame.setSize(400, 300);
		JLabel label_dropdown = new JLabel("Select Game");
		frame.setAlwaysOnTop(true);
		frame.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
		model = new JComboBox<String>();
		model.addItem("Circinus");
		model.addItem("Sahara");
		exportDirectory = new JTextArea(1, 25);
		configDirectory = new JTextArea(1, 25);
		contentPane.add(model);
		contentPane.add(label_dropdown);
		contentPane.add(exportDirectory);
		contentPane.add(configDirectory);
		buttonok = new JButton("Ok");
		contentPane.add(buttonok);
		layout.putConstraint(SpringLayout.NORTH, model,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, model,
				25,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label_dropdown,
				8,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, label_dropdown,
				25,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, exportDirectory,
				100,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, exportDirectory,
				25,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, configDirectory,
				140,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, configDirectory,
				25,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, buttonok,
				200,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, buttonok,
				300,
				SpringLayout.WEST, contentPane);
		buttonok.addActionListener(this);
		exportDirectory.setText(CreatorOptions.FILEPATH_FILES);
		frame.setVisible(true);
				
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		CreatorOptions.setMAIN_FILEPATH(exportDirectory.getText());
		Main.setSetupopen(false);
		isOpen = false;
		frame.dispose();
		
		
	}

}
