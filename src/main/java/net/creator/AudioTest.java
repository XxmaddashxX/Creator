package net.creator;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.newdawn.slick.openal.Audio;

public class AudioTest implements ActionListener, ChangeListener{
	
	private JFrame frame;
	private JComboBox<String> audiocombo;
	private Container contentPane;
	private SpringLayout layout;
	private int AUDIO_MAX = 20, AUDIO_MIN = 1;
	private JSlider volumeslider;
	private JLabel label_pitch;
	private JSlider slider2;
	private JComboBox<String> loopbox;
	private JLabel looplabel;
	private JTextArea textareapoint;
	private JButton button;
	public AudioTest(){
		frame = new JFrame();
		frame.setTitle("Audio Tester");
		frame.setSize(500, 200);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = frame.getContentPane();
		layout = new SpringLayout();
		contentPane.setLayout(layout);
		createComponents();
		
		frame.setVisible(true);
	}
	public void createComponents(){
		audiocombo = new JComboBox<String>();
		ArrayList<String> temp = Loader.listFilesForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Audio"));
		for(int i = 0; i < temp.size(); i++){
			audiocombo.addItem(temp.get(i));
		}
		contentPane.add(audiocombo);
		layout.putConstraint(SpringLayout.NORTH, audiocombo,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, audiocombo,
				25,
				SpringLayout.WEST, contentPane);
		loopbox = new JComboBox<String>();
		loopbox.addItem("0");
		loopbox.addItem("1");
		loopbox.addItem("2");
		loopbox.addItem("3");
		loopbox.addItem("4");
		loopbox.addItem("5");
		loopbox.addItem("6");
		loopbox.addItem("7");
		loopbox.addItem("8");
		loopbox.addItem("9");
		loopbox.addItem("10");
		
		contentPane.add(loopbox);
		layout.putConstraint(SpringLayout.NORTH, loopbox,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, loopbox,
				300,
				SpringLayout.WEST, contentPane);
		
		
		looplabel = new JLabel("Loop");
		contentPane.add(looplabel);
		layout.putConstraint(SpringLayout.NORTH, looplabel,
				5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, looplabel,
				300,
				SpringLayout.WEST, contentPane);
		volumeslider = new JSlider(JSlider.HORIZONTAL,this.AUDIO_MIN, this.AUDIO_MAX, 5);
		volumeslider.setMajorTickSpacing(1);
		volumeslider.setPaintTicks(true);
		volumeslider.addChangeListener(this);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put( new Integer( this.AUDIO_MIN ), new JLabel("1") );
		labelTable.put( new Integer( this.AUDIO_MAX ), new JLabel("20") );
		volumeslider.setLabelTable(labelTable);
		volumeslider.setPaintLabels(true);
		
		contentPane.add(volumeslider);
		layout.putConstraint(SpringLayout.NORTH, volumeslider,
				100,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, volumeslider,
				25,
				SpringLayout.WEST, contentPane);
		label_pitch = new JLabel("Volume");
		contentPane.add(label_pitch);
		layout.putConstraint(SpringLayout.NORTH, label_pitch,
				80,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, label_pitch,
				110,
				SpringLayout.WEST, contentPane);
		textareapoint = new JTextArea(1, 2);
		textareapoint.setEditable(false);
		contentPane.add(textareapoint);
		textareapoint.setText("5");
		layout.putConstraint(SpringLayout.NORTH, textareapoint,
				100,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, textareapoint,
				300,
				SpringLayout.WEST, contentPane);
		button = new JButton("Play");
		contentPane.add(button);
		layout.putConstraint(SpringLayout.NORTH, button,
				130,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, button,
				400,
				SpringLayout.WEST, contentPane);
		button.addActionListener(this);
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(arg0.getSource().equals(volumeslider)){
			int temp = (int)volumeslider.getValue();
			textareapoint.setText("" + temp);
			
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Audio temp = AudioHandler.loadAudio(audiocombo.getSelectedItem().toString(), "WAV");
		int i = 0;
		while(i != Integer.parseInt(loopbox.getSelectedItem().toString())){
			if(!temp.isPlaying()){
				 i = i + 1;
				 temp.playAsMusic((int)volumeslider.getValue(), (int)volumeslider.getValue(), false);			}
		}
		
	}
	
		
	
	

}
