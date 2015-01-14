package net.creator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;

import net.creator.QBodies.QPlanet;
import net.creator.QBodies.QStar;

public class QuickSimulate extends JFrame implements Runnable, ActionListener{

	private boolean isRunning;

	private static final long serialVersionUID = 1L;
	private String system;
	private UI ui;
	private JPanel controlpane;
	private JButton buttonplus;
	private SpringLayout layout;
	private JSplitPane p;
	private JButton buttonminus;

	private JButton buttonup;

	public QuickSimulate(String system){
		this.system = system;
	}
	public void setup(){
		
		
		this.setTitle("Quick Simulate");
		this.setSize(800, 600);
		ui = new UI();
		controlpane = new JPanel();
		p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
			private static final long serialVersionUID = 1L;
			private final int location = 100;
			{
				setDividerLocation( location );
			}
			@Override
			public int getDividerLocation() {
				return location ;
			}
			@Override
			public int getLastDividerLocation() {
				return location ;
			}
		};
		p.setDividerLocation(100);
		p.setRightComponent(ui);
		p.setLeftComponent(controlpane);
		add(p);
		this.setResizable(false);
		layout = new SpringLayout();
		controlpane.setLayout(layout);
		ui.setScreeny(0);
		ui.setScreenx(0);
		createControlComps();
	}


	private void createControlComps() {
		buttonplus = new JButton("+ Speed");
		controlpane.add(buttonplus);
		layout.putConstraint(SpringLayout.NORTH, buttonplus,
				5,
				SpringLayout.NORTH, controlpane);

		layout.putConstraint(SpringLayout.WEST, buttonplus,
				5,
				SpringLayout.WEST, controlpane);
		buttonplus.addActionListener(this);
		buttonminus = new JButton("- Speed");
		controlpane.add(buttonminus);
		layout.putConstraint(SpringLayout.NORTH, buttonminus,
				40,
				SpringLayout.NORTH, controlpane);

		layout.putConstraint(SpringLayout.WEST, buttonminus,
				5,
				SpringLayout.WEST, controlpane);
		buttonminus.addActionListener(this);
		buttonup = new JButton("^");
		controlpane.add(buttonup);
		layout.putConstraint(SpringLayout.NORTH, buttonup,
				80,
				SpringLayout.NORTH, controlpane);

		layout.putConstraint(SpringLayout.WEST, buttonup,
				25,
				SpringLayout.WEST, controlpane);
		buttonup.addActionListener(this);
	}
	public void doDraw(Graphics g) {
		super.print(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		g2d.drawOval(100, 100, 100, 100);
	}


	@Override
	public void run() {
		setup();
		//load();
		this.setVisible(true);
		isRunning = true;
		while(isRunning){
			input();
			update();
			render();
		}

	}
	private void render() {


	}
	private void update() {
		ui.update();
		ui.repaint();
	}
	private void input() {
		

	}
	private void load(){
		ui.load();
	}
	public class UI extends JPanel{
		private double x, y, orbitRotation, screenx, screeny;
		private int speed = 1;
		private ArrayList<QStar> stars;
		private ArrayList<QPlanet> planets;
		private void doDrawing(Graphics g) {

			Graphics2D g2d = (Graphics2D) g;

			g2d.setColor(Color.blue);
			g2d.drawOval((int)x, (int)y, 100, 100);
			/*for(int i = 0 ; i < stars.size(); i++){
				g2d.drawOval((int)stars.get(i).getPosx(), (int)stars.get(i).getPosy(), 100, 100);
			}*/
		}
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			doDrawing(g);
		}
		public void update(){

			orbitRotation = orbitRotation + (0.0000001 * speed);
			x = screenx + (Math.cos( orbitRotation ) * 100 + 250);
			y = screeny + (Math.sin( orbitRotation) * 100 + 250);
		
		}
		public int getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		public void load(){
			stars = new ArrayList<QStar>();
			planets = new ArrayList<QPlanet>();
			ArrayList<File> temp = Loader.listFilesOnly(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + system + "/Stars"));
			QBodies q = new QBodies();
			for(int i = 0; i < temp.size(); i++){
				try {
					BufferedReader b = new BufferedReader(new FileReader(temp.get(i)));
					String[] p = b.readLine().split(":");
					String[] o = b.readLine().split(":");
					b.readLine();
					String[] l = b.readLine().split(":");
					String[] k = b.readLine().split(":");
					stars.add(q.new QStar(p[1], o[1], Double.parseDouble(l[1]), Double.parseDouble(k[1])));
					b.close();
				} catch (FileNotFoundException	 e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		public double getScreenx() {
			return screenx;
		}
		public void setScreenx(double screenx) {
			this.screenx = screenx;
		}
		public double getScreeny() {
			return screeny;
		}
		public void setScreeny(double sceeny) {
			this.screeny = sceeny;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonplus)){
			if(ui.getSpeed() == 64){
				
			}
			else{
				ui.setSpeed(ui.getSpeed() * 2);
			}
			
		}
		if(e.getSource().equals(buttonminus)){
			if(ui.getSpeed() == 1){
				
			}
			else{
				ui.setSpeed(ui.getSpeed() / 2);
			}
			
		}
		if(e.getSource().equals(buttonup)){
			ui.setScreeny(ui.getScreeny() + 25);
		}
		
	}



}
