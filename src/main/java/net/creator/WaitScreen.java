package net.creator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaitScreen extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private boolean isRunning;
	private double orbitRotation1;
	private double x1;
	private double y1;
	private double orbitRotation2;
	private double x2;
	private double y2;
	private UI ui;
	public WaitScreen(String title){
		isRunning = true;
		this.setTitle(title);
		this.setSize(400, 500);
		this.setResizable(false);
		this.add(ui = new UI());
		this.setVisible(true);
	}

	@Override
	public void run() {
		isRunning = true;
		while(isRunning){
			ui.repaint();
		}
		
	}

	
	
	public class UI extends JPanel{
		private void graphics(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			
			orbitRotation1 = orbitRotation1 + (0.0003);
			x1 = (Math.cos( orbitRotation1 ) * 150 + 200);
			y1 = (Math.sin( orbitRotation1) * 150 + 225);
			orbitRotation2 = orbitRotation2 - (0.0005);
			x2 = (Math.cos( orbitRotation2 ) * 50 + (x1 + 12.5));
			y2 = (Math.sin( orbitRotation2) * 50 + (y1 + 12.5));
			g2d.setColor(Color.red);
			g2d.drawOval((int)175, (int)200, 50, 50);
			g2d.setColor(Color.blue);
			g2d.drawOval((int)x1, (int)y1, 25, 25);
			g2d.setColor(Color.MAGENTA);
			g2d.drawOval((int)x2, (int)y2, 10, 10);
			
		
			
		}
		@Override
		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			graphics(g);
		}
	}

}
