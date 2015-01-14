package net.creator;

import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import net.creator.Create.CreateStar;

public class StarADVData implements ItemListener{

	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JCheckBox randombox;
	private JTextArea tempbox;
	private JLabel templabel;
	private JTextArea lumarea;
	private JLabel lumlabel;

	public StarADVData(String name, CreateStar fr){
		frame = new JFrame();
		frame.setTitle(name + ": Advanced");
		frame.setResizable(false);
		frame.setSize(200, 400);
		frame.setLocation(fr.getFrame().getLocation().x + 600, fr.getFrame().getLocation().y);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		createComps();
	
		
		
	}

	private void createComps() {
		randombox = new JCheckBox("Random data?");
		randombox.addItemListener(this);
		contentPane.add(randombox);
		layout.putConstraint(SpringLayout.NORTH, randombox,
				15,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, randombox,
				25,
				SpringLayout.WEST, contentPane);
		//randombox.setSelected(true);
		tempbox = new JTextArea(1, 5);
		contentPane.add(tempbox);
		layout.putConstraint(SpringLayout.NORTH, tempbox,
				65,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, tempbox,
				25,
				SpringLayout.WEST, contentPane);
		templabel = new JLabel("Temperature (K)");
		contentPane.add(templabel);
		layout.putConstraint(SpringLayout.NORTH, templabel,
				45,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, templabel,
				25,
				SpringLayout.WEST, contentPane);
		lumarea = new JTextArea(1, 5);
		contentPane.add(lumarea);
		layout.putConstraint(SpringLayout.NORTH, lumarea,
				105,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, lumarea,
				25,
				SpringLayout.WEST, contentPane);
		
		
		lumlabel = new JLabel("Luminosity");
		layout.putConstraint(SpringLayout.NORTH, lumlabel,
				85,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, lumlabel,
				25,
				SpringLayout.WEST, contentPane);
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == ItemEvent.DESELECTED){
			tempbox.setVisible(true);
			templabel.setVisible(true);
			lumarea.setVisible(true);
			lumlabel.setVisible(true);
		}
		if(e.getStateChange() == ItemEvent.SELECTED){
			tempbox.setVisible(false);
			templabel.setVisible(false);
			lumarea.setVisible(false);
			lumlabel.setVisible(false);
			
		}
		
	}
	
}
