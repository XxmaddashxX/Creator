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

import net.creator.Create.CreatePlanet;
import net.creator.Create.CreateSolarSystem;
import net.creator.Create.CreateStar;

public class NewScreen implements ActionListener{
	
	private JFrame frame;
	private JComboBox<String> combobox;
	private Container contentPane;
	private SpringLayout layout;
	private JLabel typelabel;
	private JTextArea nametextfield;
	private JLabel namelabel;
	private JButton createbutton;

	public NewScreen(){
		frame = new JFrame();
		frame.setTitle("Add New Component");
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		frame.setSize(600, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.createComponents();
		this.createComboTypes();
		frame.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 300, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100);
		
		frame.setVisible(true);
	}
	
	public void createComponents(){
		combobox = new JComboBox<String>();
		contentPane.add(combobox);
		layout.putConstraint(SpringLayout.NORTH, combobox,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, combobox,
				25,
				SpringLayout.WEST, contentPane);
		typelabel = new JLabel("Type:");
		contentPane.add(typelabel);
		layout.putConstraint(SpringLayout.NORTH, typelabel,
				5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, typelabel,
				25,
				SpringLayout.WEST, contentPane);
		nametextfield = new JTextArea(1, 25);
		contentPane.add(nametextfield);
		layout.putConstraint(SpringLayout.NORTH, nametextfield,
				75,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, nametextfield,
				25,
				SpringLayout.WEST, contentPane);
		namelabel = new JLabel("Name:");
		contentPane.add(namelabel);
		layout.putConstraint(SpringLayout.NORTH, namelabel,
				55,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, namelabel,
				25,
				SpringLayout.WEST, contentPane);
		createbutton = new JButton("Create");
		contentPane.add(createbutton);
		layout.putConstraint(SpringLayout.NORTH, createbutton,
				125,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, createbutton,
				500,
				SpringLayout.WEST, contentPane);
		createbutton.addActionListener(this);
		
	}
	public void createComboTypes(){
		combobox.addItem("Solar System");
		combobox.addItem("Star");
		combobox.addItem("Planet");
		combobox.addItem("Moon");
		combobox.addItem("Object");
		combobox.addItem("NPC");
		combobox.addItem("AI");
		combobox.addItem("Code");
		combobox.addItem("Recipe");
		combobox.addItem("Ship Part");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(nametextfield.getText().isEmpty()){
			MessageFrame ms = new MessageFrame("Empty Variable", "The Name box needs to be filled", Layouts.OK_ONLY, Levels.ERROR);
			return;
		}
		if(combobox.getSelectedItem().equals("Solar System")){
			Create c = new Create();
			CreateSolarSystem p = c.new CreateSolarSystem(nametextfield.getText());
			frame.dispose();
		}
		if(combobox.getSelectedItem().equals("Planet")){
			if(Main.getScreen().getSystems().isEmpty()){
				MessageFrame ms = new MessageFrame("No Solar Systems", "No solar systems found, create one first before creating this", Layouts.EXIT_ONLY, Levels.ERROR);
			}
			else{
				Create c = new Create();
				CreatePlanet p = c.new CreatePlanet(nametextfield.getText());
				frame.dispose();
			}
		}
		if(combobox.getSelectedItem().equals("Star")){
			Create c = new Create();
			CreateStar p = c.new CreateStar(nametextfield.getText());
			frame.dispose();
		}
		
	}

}
