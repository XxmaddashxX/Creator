package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class MessageFrame implements ActionListener{
	
	private String frametitle, framedescription;
	private Enum<Layouts> framelayout;
	private Enum<Levels> framelevel;
	private JButton exitbutton;
	private JButton okbutton;
	private JFrame frame;
	public MessageFrame(String title, String label, Enum<Layouts> layout, Enum<Levels> level){
		this.framedescription = label;
		this.framelayout = layout;
		this.frametitle = title;
		this.framelevel = level;
		this.createFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okbutton)){
			frame.dispose();
		}
		if(e.getSource().equals(exitbutton)){
			frame.dispose();
		}
		
	}
	public void createFrame(){
		frame = new JFrame();
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		if(this.framelevel.equals(Levels.INFO)){
			frame.setTitle("INFO: " + frametitle);
		}
		else if(this.framelevel.equals(Levels.ERROR)){
			frame.setTitle("ERROR: " + frametitle);
		}
		else{
			frame.setTitle(frametitle);
		}
		frame.setSize(400, 300);
		frame.setResizable(false);
		frame.setAlwaysOnTop(true);
		frame.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 150);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		if(framelayout.equals(Layouts.EXIT_ONLY)){
			exitbutton = new JButton("Exit");
			exitbutton.addActionListener(this);
			contentPane.add(exitbutton);
			layout.putConstraint(SpringLayout.NORTH, exitbutton,
					200,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, exitbutton,
					175,
					SpringLayout.WEST, contentPane);
			
		}
		if(framelayout.equals(Layouts.OK_ONLY)){
			okbutton = new JButton("Ok");
			okbutton.addActionListener(this);
			contentPane.add(okbutton);
			layout.putConstraint(SpringLayout.NORTH, okbutton,
					200,
					SpringLayout.NORTH, contentPane);

			layout.putConstraint(SpringLayout.WEST, okbutton,
					175,
					SpringLayout.WEST, contentPane);
			
		}
		
		JTextArea area = new JTextArea(1, 25);
		area.setEditable(false);
		area.setText(framedescription);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		contentPane.add(area);
		layout.putConstraint(SpringLayout.NORTH, area,
				50,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, area,
				50,
				SpringLayout.WEST, contentPane);
		frame.setVisible(true);
	}

}
