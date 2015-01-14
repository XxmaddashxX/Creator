package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class UpdateScreen implements ActionListener{

	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JButton buttook;
	private JButton buttoncancel;

	public UpdateScreen(){
		frame = new JFrame();
		frame.setTitle("Update Available");
		frame.setSize(200, 100);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		buttook = new JButton("Update");
		contentPane.add(buttook);
		buttoncancel = new JButton("Cancel");
		contentPane.add(buttoncancel);
		layout.putConstraint(SpringLayout.NORTH, buttook,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, buttook,
				25,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, buttoncancel,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, buttoncancel,
				100,
				SpringLayout.WEST, contentPane);
		buttoncancel.addActionListener(this);
		buttook.addActionListener(this);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttoncancel)){
			frame.dispose();
			Main.setUpdateopen(false);
		}
		if(e.getSource().equals(buttook)){
			frame.dispose();
			Main.setDoupdate(true);
			Main.setUpdateopen(false);
			
		}
		
	}
	
}
