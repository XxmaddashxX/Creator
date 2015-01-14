package net.creator;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

public class DebugMessage {
	
	private JFrame frame;
	private Container contentPane;
	private SpringLayout layout;
	private JTextArea messagearea;
	private JScrollPane messagescroll;

	public DebugMessage(){
		frame = new JFrame();
		frame.setTitle("Debug [Messages]");
		frame.setSize(500, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		messagearea = new JTextArea(25, 8);
		messagescroll = new JScrollPane(messagearea);
		contentPane.add(messagescroll);
		messagescroll.setPreferredSize(new Dimension(485,160));
		layout.putConstraint(SpringLayout.NORTH, messagescroll,
				5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, messagescroll,
				5,
				SpringLayout.WEST, contentPane);
		messagearea.setEditable(false);
		messagearea.setLineWrap(true);
		messagearea.setWrapStyleWord(true);
		frame.setVisible(true);
	}

	public JTextArea getMessagearea() {
		return messagearea;
	}

	public void setMessagearea(JTextArea messagearea) {
		this.messagearea = messagearea;
	}

	public JScrollPane getMessagescroll() {
		return messagescroll;
	}

	public void setMessagescroll(JScrollPane messagescroll) {
		this.messagescroll = messagescroll;
	}
	

}
