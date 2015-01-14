package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class QSimSetup implements ActionListener{
	
	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JComboBox<String> combobox;
	private JButton button;

	public QSimSetup(){
		frame = new JFrame();
		frame.setTitle("Setup");
		frame.setSize(300, 80);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		createComps();
		frame.setVisible(true);
	}
	private void createComps(){
		combobox = new JComboBox<String>();
		contentPane.add(combobox);
		for(int i = 0; i < Main.getScreen().getSystems().size(); i++){
			combobox.addItem(Main.getScreen().getSystems().get(i));
		}
		layout.putConstraint(SpringLayout.NORTH, combobox,
				15,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, combobox,
				25,
				SpringLayout.WEST, contentPane);
		button = new JButton("Proceed");
		contentPane.add(button);
		layout.putConstraint(SpringLayout.NORTH, button,
				15,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, button,
				150,
				SpringLayout.WEST, contentPane);
		button.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		frame.dispose();
		QuickSimulate s = new QuickSimulate("TEST"/*combobox.getSelectedItem().toString()*/);
		new Thread(s).start();
	}

}
