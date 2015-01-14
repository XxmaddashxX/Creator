package net.creator;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class DriveProgress implements Runnable{
	
	
	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JTextArea progresslabel;
	private JTextArea downlabel;
	private JTextArea extlabel;
	private JTextArea madelabel;
	private boolean isRunning;
	public DriveProgress(){
		
	}
	private void createComponents(){
		
		
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public Container getContentPane() {
		return contentPane;
	}
	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}
	public SpringLayout getLayout() {
		return layout;
	}
	public void setLayout(SpringLayout layout) {
		this.layout = layout;
	}
	public JTextArea getProgresslabel() {
		return progresslabel;
	}
	public void setProgresslabel(JTextArea progresslabel) {
		this.progresslabel = progresslabel;
	}
	public JTextArea getDownlabel() {
		return downlabel;
	}
	public void setDownlabel(JTextArea downlabel) {
		this.downlabel = downlabel;
	}
	public JTextArea getExtlabel() {
		return extlabel;
	}
	public void setExtlabel(JTextArea extlabel) {
		this.extlabel = extlabel;
	}
	public JTextArea getMadelabel() {
		return madelabel;
	}
	public void setMadelabel(JTextArea madelabel) {
		this.madelabel = madelabel;
	}
	@Override
	public void run() {
		frame = new JFrame();
		frame.setTitle("Drive Sync Progress");
		frame.setSize(600, 600);
		frame.setResizable(false);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		this.createComponents();
		progresslabel = new JTextArea(1, 30);
		downlabel = new JTextArea(1, 30);
		extlabel = new JTextArea(1, 30); 
		madelabel = new JTextArea(1, 30);
		contentPane.add(downlabel);
		contentPane.add(progresslabel);
		contentPane.add(extlabel);
		contentPane.add(madelabel);
		layout.putConstraint(SpringLayout.NORTH, progresslabel,
				10,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, progresslabel,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, downlabel,
				35,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, downlabel,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, extlabel,
				60,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, extlabel,
				10,
				SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, madelabel,
				85,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, madelabel,
				10,
				SpringLayout.WEST, contentPane);
		frame.setVisible(true);
		isRunning = true;
		while(isRunning){
			frame.repaint();
		}
		
	}
	public void setDown(String text){
		this.downlabel.setText(text);
	}
	
	
}
