package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class Create {

	public static void setupFrame(JFrame frame, String title){
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 300, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200);

		frame.setTitle("Create: " + title);
	}

	public class CreateSolarSystem implements ActionListener{

		private JFrame frame;
		private Container contentPane;
		private SpringLayout layout;
		private JTextArea desctextarea;
		private String name;
		private JButton createbutton;
		private JLabel desclabel;
		public CreateSolarSystem(String name){
			frame = new JFrame();
			this.name = name;
			Create.setupFrame(frame, "Solar System");
			contentPane = frame.getContentPane();
			layout = new SpringLayout();
			contentPane.setLayout(layout);
			this.createComponents();
			frame.setVisible(true);
		}
		public void createComponents(){
			desctextarea = new JTextArea(18, 25);
			contentPane.add(desctextarea);
			layout.putConstraint(SpringLayout.NORTH, desctextarea,
					50,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, desctextarea,
					25,
					SpringLayout.WEST, contentPane);
			desctextarea.setWrapStyleWord(true);
			desctextarea.setLineWrap(true);
			createbutton = new JButton("Create");
			contentPane.add(createbutton);
			createbutton.addActionListener(this);
			layout.putConstraint(SpringLayout.NORTH, createbutton,
					300,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, createbutton,
					500,
					SpringLayout.WEST, contentPane);
			desclabel = new JLabel("Description:");
			contentPane.add(desclabel);
			layout.putConstraint(SpringLayout.NORTH, desclabel,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, desclabel,
					25,
					SpringLayout.WEST, contentPane);

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(desctextarea.getText().isEmpty()){
				//MessageFrame ms = new MessageFrame("Empty Description Box", "Are you sure you want to leave the description box empty? It will require extra work to add the description later on", Layouts.YES_NO, Levels.INFO);
			}
			Main.getScreen().addSystemNode(Main.getScreen().getMain_node(), name);
			File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + name);
			f.mkdirs();
			try {
				BufferedWriter w = new BufferedWriter(new FileWriter(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + name + "/" + name + "_props.txt")));
				w.write("N:" + name);
				w.newLine();
				w.write("D:" + desctextarea.getText());
				w.close();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

			frame.dispose();
		}

	}
	public class CreatePlanet implements ActionListener{
		private String name;
		private JFrame frame;
		private JComboBox<String> aggcombobox;
		private Container contentPane;
		private SpringLayout layout;
		private JCheckBox canlandcheck;
		private JCheckBox hastownscheck;
		private JComboBox<String> planetclassbox;
		private JComboBox<String> systembox;
		private JComboBox<String> starbox;
		private JButton createbutton;
		private JTextArea velocityarea;
		//private JComboBox<String> planetbox; 
		public CreatePlanet(String name){
			this.name = name;
			frame = new JFrame();
			contentPane = frame.getContentPane();
			layout = new SpringLayout();
			contentPane.setLayout(layout);
			Create.setupFrame(frame, "Planet");
			this.createComponents();
			frame.setVisible(true);
		}
		public void createComponents(){
			aggcombobox = new JComboBox<String>();
			aggcombobox.addItem("FRIENDLY");
			aggcombobox.addItem("NEUTRAL");
			aggcombobox.addItem("VARIES");
			aggcombobox.addItem("AGRESSIVE");
			aggcombobox.addItem("GENEROUS");
			aggcombobox.addItem("SCARED");
			contentPane.add(aggcombobox);
			layout.putConstraint(SpringLayout.NORTH, aggcombobox,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, aggcombobox,
					25,
					SpringLayout.WEST, contentPane);
			canlandcheck = new JCheckBox("Can Land on");
			contentPane.add(canlandcheck);
			layout.putConstraint(SpringLayout.NORTH, canlandcheck,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, canlandcheck,
					175,
					SpringLayout.WEST, contentPane);
			hastownscheck = new JCheckBox("Has Towns");
			contentPane.add(hastownscheck);
			layout.putConstraint(SpringLayout.NORTH, hastownscheck,
					50,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, hastownscheck,
					175,
					SpringLayout.WEST, contentPane);
			planetclassbox = new JComboBox<String>();
			planetclassbox.addItem("O");
			planetclassbox.addItem("B");
			planetclassbox.addItem("A");
			planetclassbox.addItem("F");
			planetclassbox.addItem("G");
			planetclassbox.addItem("K");
			planetclassbox.addItem("M");
			planetclassbox.addItem("L");
			planetclassbox.addItem("T");
			planetclassbox.addItem("Y");
			contentPane.add(planetclassbox);
			layout.putConstraint(SpringLayout.NORTH, planetclassbox,
					75,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, planetclassbox,
					25,
					SpringLayout.WEST, contentPane);
			systembox = new JComboBox<String>();
			for(int i = 0; i < Main.getScreen().getSystems().size(); i++){
				systembox.addItem(Main.getScreen().getSystems().get(i));
			}
			contentPane.add(systembox);
			layout.putConstraint(SpringLayout.NORTH, systembox,
					125,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, systembox,
					25,
					SpringLayout.WEST, contentPane);
			starbox = new JComboBox<String>();
			contentPane.add(starbox);
			layout.putConstraint(SpringLayout.NORTH, starbox,
					165,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, starbox,
					25,
					SpringLayout.WEST, contentPane);
			for(int i = 0; i < Main.getScreen().getStars().size(); i++){
				starbox.addItem(Main.getScreen().getStars().get(i));
			}
			createbutton = new JButton("Create");
			contentPane.add(createbutton);
			createbutton.addActionListener(this);
			layout.putConstraint(SpringLayout.NORTH, createbutton,
					300,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, createbutton,
					500,
					SpringLayout.WEST, contentPane);
			velocityarea = new JTextArea(1, 3);
			contentPane.add(velocityarea);

			layout.putConstraint(SpringLayout.NORTH, velocityarea,
					55,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, velocityarea,
					400,
					SpringLayout.WEST, contentPane);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource().equals(createbutton)){
				Main.getScreen().addNode(Tracker.getTreelist().get(starbox.getSelectedItem()), name);
				File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + systembox.getSelectedItem() + "/Planets");
				f.mkdirs();
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + systembox.getSelectedItem() + "/Planets/"  + name + "_props.txt")));
					w.write("N:" + name);
					w.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				frame.dispose();
			}
		}
	}
	public class CreateStar implements ActionListener{


		private String name;
		private JFrame frame;
		private Container contentPane;
		private SpringLayout layout;
		private JButton createbutton;
		private JTextArea descbox;
		private JLabel desclabel;
		private JComboBox<String> systembox;
		private JTextArea posxarea;
		private JTextArea posyarea;
		private JLabel posxlabel;
		private JLabel posylabel;
		private JComboBox<String> texturebox;
		private JTextArea velocityarea;
		private JComboBox<String> starclass;
		private JButton buttonadv;
		private StarADVData data;
		public CreateStar(String name){
			this.name = name;
			frame = new JFrame();
			contentPane = frame.getContentPane();
			layout = new SpringLayout();
			contentPane.setLayout(layout);
			Create.setupFrame(frame, "Star");
			this.createComponents();
			data = new StarADVData(name, this);
			frame.setVisible(true);
		}
		public void createComponents(){
			createbutton = new JButton("Create");
			contentPane.add(createbutton);
			createbutton.addActionListener(this);
			layout.putConstraint(SpringLayout.NORTH, createbutton,
					300,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, createbutton,
					500,
					SpringLayout.WEST, contentPane);
			descbox = new JTextArea(18, 18);
			contentPane.add(descbox);
			descbox.setWrapStyleWord(true);
			descbox.setLineWrap(true);
			layout.putConstraint(SpringLayout.NORTH, descbox,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, descbox,
					25,
					SpringLayout.WEST, contentPane);
			desclabel = new JLabel("Description:");
			contentPane.add(desclabel);
			layout.putConstraint(SpringLayout.NORTH, desclabel,
					5,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, desclabel,
					25,
					SpringLayout.WEST, contentPane);
			systembox = new JComboBox<String>();
			for(int i = 0; i < Main.getScreen().getSystems().size(); i++){
				systembox.addItem(Main.getScreen().getSystems().get(i));
			}
			contentPane.add(systembox);
			layout.putConstraint(SpringLayout.NORTH, systembox,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, systembox,
					300,
					SpringLayout.WEST, contentPane);
			posxarea = new JTextArea(1, 3);
			contentPane.add(posxarea);
			layout.putConstraint(SpringLayout.NORTH, posxarea,
					100,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, posxarea,
					300,
					SpringLayout.WEST, contentPane);
			posyarea = new JTextArea(1, 3);
			contentPane.add(posyarea);
			layout.putConstraint(SpringLayout.NORTH, posyarea,
					150,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, posyarea,
					300,
					SpringLayout.WEST, contentPane);
			posxlabel = new JLabel("X");
			contentPane.add(posxlabel);
			layout.putConstraint(SpringLayout.NORTH, posxlabel,
					80,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, posxlabel,
					300,
					SpringLayout.WEST, contentPane);
			posylabel = new JLabel("Y");
			contentPane.add(posylabel);
			layout.putConstraint(SpringLayout.NORTH, posylabel,
					130,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, posylabel,
					300,
					SpringLayout.WEST, contentPane);
			texturebox = new JComboBox<String>();
			contentPane.add(texturebox);
			ArrayList<String> temp = Loader.listFilesForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Textures"));
			for(int i = 0; i < temp.size(); i++){
				texturebox.addItem(temp.get(i));
			}
			layout.putConstraint(SpringLayout.NORTH, texturebox,
					25,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, texturebox,
					400,
					SpringLayout.WEST, contentPane);
			starclass = new JComboBox<String>();
			starclass.addItem("O");
			starclass.addItem("B");
			starclass.addItem("A");
			starclass.addItem("F");
			starclass.addItem("G");
			starclass.addItem("K");
			starclass.addItem("M");
			starclass.addItem("L");
			starclass.addItem("T");
			starclass.addItem("Y");
			contentPane.add(starclass);
			layout.putConstraint(SpringLayout.NORTH, starclass,
					55,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, starclass,
					400,
					SpringLayout.WEST, contentPane);
			buttonadv = new JButton("Advanced");
			buttonadv.addActionListener(this);
			contentPane.add(buttonadv);
			layout.putConstraint(SpringLayout.NORTH, buttonadv,
					250,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, buttonadv,
					500,
					SpringLayout.WEST, contentPane);
			

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(createbutton)){
				Main.getScreen().addNode(Tracker.getTreelist().get(systembox.getSelectedItem()), name);
				File f = new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + systembox.getSelectedItem() + "/Stars");
				f.mkdirs();
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + systembox.getSelectedItem() + "/Stars/"  + name + "_props.txt")));
					w.write("N:" + name);
					w.newLine();
					w.write("D:" + descbox.getText());
					w.newLine();
					w.write("T:" + texturebox.getSelectedItem());
					w.newLine();
					w.write("X:" + posxarea.getText());
					w.newLine();
					w.write("Y:" + posyarea.getText());
					w.close();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				frame.dispose();
			}
			if(e.getSource().equals(buttonadv)){
				data.getFrame().setVisible(true);
			}
		}
		public JFrame getFrame() {
			return frame;
		}
		public void setFrame(JFrame frame) {
			this.frame = frame;
		}

	}
	public class CreateObject implements ActionListener{

		private String name;
		private JFrame frame;
		private Container contentPane;
		private SpringLayout layout;
		public CreateObject(String name){
			this.name = name;
			frame = new JFrame();
			Create.setupFrame(frame, "Game Object");
			contentPane = frame.getContentPane();
			layout = new SpringLayout();
			contentPane.setLayout(layout);
			createComps();
			frame.setVisible(true);
		}
		public void createComps(){

		}

		@Override
		public void actionPerformed(ActionEvent e) {


		}
	}
	public class CreateShipPart{
		private String name;
		private JFrame frame;
		private Container contentPane;
		private SpringLayout layout;
		public CreateShipPart(String name){
			this.name = name;
			frame = new JFrame();
			Create.setupFrame(frame, "Ship Part");
			contentPane = frame.getContentPane();
			layout = new SpringLayout();
			contentPane.setLayout(layout);
			createComps();
			frame.setVisible(true);
		}
		private void createComps() {
			
		}
	}

}
