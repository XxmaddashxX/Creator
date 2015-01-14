package net.creator;

import javax.swing.JFrame;

public class WaitScreen extends JFrame implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private boolean isRunning;
	public WaitScreen(String title){
		isRunning = true;
		this.setTitle(title);
		this.setSize(400, 500);
		this.setResizable(false);
	}

	@Override
	public void run() {
		isRunning = true;
		while(isRunning){
			
		}
		
	}

}
