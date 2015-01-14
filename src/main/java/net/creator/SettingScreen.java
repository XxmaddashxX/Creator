package net.creator;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class SettingScreen {
	
	private JFrame frame;
	private Container contentPane;
	private BorderLayout layout;
	private JTabbedPane tabbedpane;
	private JPanel generalpanel;
	private JPanel drivepanel;
	private JPanel fontpanel;
	private JPanel miscpanel;
	private JPanel advancedpanel;

	public SettingScreen(){
		frame = new JFrame();
		frame.setTitle("Settings");
		frame.setSize(800, 600);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new BorderLayout();
		contentPane.setLayout(layout);
		createpanels();
		createTabs();
		generalcomps();
		frame.add(tabbedpane);
		frame.setVisible(true);
		
	}
	private void createpanels(){
		generalpanel = new JPanel();
		drivepanel = new JPanel();
		fontpanel = new JPanel();
		miscpanel = new JPanel();
		advancedpanel = new JPanel();
		
		
	}
	private void createTabs(){
		tabbedpane = new JTabbedPane();
		tabbedpane.addTab("General", generalpanel);
		tabbedpane.addTab("Drive", drivepanel);
		tabbedpane.addTab("Font", fontpanel);
		tabbedpane.addTab("Misc", miscpanel);
		tabbedpane.addTab("Advanced", advancedpanel);
	}
	private void generalcomps(){
		
	}

}
