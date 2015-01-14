package net.creator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

public class DialogueManager implements ActionListener, MouseListener{
	
	
	
	private JFrame frame;
	private JScrollPane treepanel;
	private JPanel mainpanel;
	private JSplitPane splitpane;
	private Container mainPane;
	private BorderLayout layout;
	private JMenuBar menubar;
	private JMenu menuFile;
	private JMenu menuMisc;
	private JTree tree;
	private JPopupMenu popup;
	private JMenuItem buttonnew;
	private JMenuItem buttonnewdia;
	public DialogueManager(){
		frame  = new JFrame();
		frame.setSize(1200, 750);
		frame.setResizable(false);
		frame.setTitle("Dialogue Manager");
		tree = new JTree();
		tree.addMouseListener(this);
		treepanel = new JScrollPane(tree);
		mainpanel = new JPanel();
		menubar = new JMenuBar();
		splitpane = new JSplitPane();
		mainPane = frame.getContentPane();
		layout = new BorderLayout();
		mainPane.setLayout(layout);
		
		splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
			private static final long serialVersionUID = 1L;
			private final int location = 200;
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
		splitpane.setDividerLocation(200);
		splitpane.setLeftComponent(treepanel);
		splitpane.setRightComponent(mainpanel);
		frame.add(BorderLayout.CENTER, splitpane);
		frame.add(BorderLayout.NORTH, menubar);
		load();
		createComps();
		frame.setVisible(true);
		
	}
	private void load(){
		
		
	}
	private void createComps(){
		
		//Menu
		menuFile = new JMenu("File");
		menubar.add(menuFile);
		menuMisc = new JMenu("Misc");
		menubar.add(menuMisc);
		//Popup
		popup = new JPopupMenu();
		buttonnew = new JMenuItem("New Package");
		popup.add(buttonnew);
		buttonnewdia = new JMenuItem("New Dialogue");
		popup.add(buttonnewdia);
		//Tree
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {

	        int row = tree.getClosestRowForLocation(e.getX(), e.getY());
	        tree.setSelectionRow(row);
	        popup.show(e.getComponent(), e.getX(), e.getY());
	    }
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
