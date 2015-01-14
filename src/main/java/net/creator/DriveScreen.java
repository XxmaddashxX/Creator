package net.creator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SpringLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;

public class DriveScreen implements TreeSelectionListener{
	Map<String, DefaultMutableTreeNode> tracking;
	private ArrayList<JLabel> fields;
	private final static String MAINID = "0B3ZMcou4GFFMbUpsMlAyMEVCUVE";
	private JFrame frame;
	private Container contentPane;
	private BorderLayout layout;
	private JButton buttoncreate;
	private JButton buttonview;
	private Container mainPane;
	private JScrollPane treepanel;
	private JPanel infopanel;
	private JSplitPane splitpane;
	private JMenuBar menubar;
	private JMenuItem menudrive;
	private JTree maintree;
	private DefaultMutableTreeNode top;
	private DefaultTreeModel treemodel;
	private List<com.google.api.services.drive.model.File> list;
	private JLabel labeltitle;
	private JLabel texttitle;
	private JLabel textid;
	private JLabel textdatecr;
	private JLabel textdatemo;
	private JLabel textowner;
	private JLabel texttype;
	private SpringLayout infolay;
	private JLabel piclabel;

	public DriveScreen(){
		tracking = new HashMap<String, DefaultMutableTreeNode>();
		frame = new JFrame();
		fields = new ArrayList<JLabel>();
		System.out.println("YE");
		frame.setTitle("Google Drive");
		frame.setSize(800, 600);
		frame.setResizable(true);
		frame.setLocation(50, 50);
		mainPane = frame.getContentPane();
		layout = new BorderLayout();
		mainPane.setLayout(layout);
		createMenuBar();
		createPanels();
		createComps();
		mainPane.add(menubar, BorderLayout.NORTH);
		mainPane.add(splitpane, BorderLayout.CENTER);
		splitpane.setLeftComponent(treepanel);
		splitpane.setRightComponent(infopanel);
		loadFromDrive(this.getChildren(MAINID), MAINID);
		frame.setVisible(true);
	}
	private void createMenuBar() {
		menubar = new JMenuBar();
		menudrive = new JMenuItem("Drive");
		menubar.add(menudrive);
	}
	private void createPanels(){

		infopanel = new JPanel();
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


	}
	private void createComps(){
		//Tree
		infolay = new SpringLayout();
		infopanel.setLayout(infolay);
		top = new DefaultMutableTreeNode("Circinus");
		treemodel = new DefaultTreeModel(top);
		maintree = new JTree(treemodel);
		maintree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);
		maintree.addTreeSelectionListener(this);
		treepanel = new JScrollPane(maintree);
		tracking.put(MAINID, top);

		//Main Panel Comps

		//textfield
		fields.add(texttitle = new JLabel("Title:"));
		fields.add(textid  = new JLabel("ID:"));
		fields.add(textdatecr = new JLabel("Create Date:"));
		fields.add(textdatemo  = new JLabel("Mod Date:"));
		fields.add(textowner = new JLabel("Owner"));
		fields.add(texttype = new JLabel("Type:"));
		fields.add(piclabel = new JLabel());
		infopanel.add(texttitle).setEnabled(false);
		infopanel.add(textid).setEnabled(false);
		infopanel.add(textdatecr).setEnabled(false);
		infopanel.add(textdatemo).setEnabled(false);
		infopanel.add(textowner).setEnabled(false);
		infopanel.add(texttype).setEnabled(false);
		infopanel.add(piclabel).setEnabled(false);
		infolay.putConstraint(SpringLayout.NORTH, texttitle, 25, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, texttitle, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, textid, 75, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, textid, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, texttype, 125, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, texttype, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, textowner, 175, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, textowner, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, textdatecr, 225, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, textdatecr, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, textdatemo, 275, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, textdatemo, 25, SpringLayout.WEST, infopanel);
		infolay.putConstraint(SpringLayout.NORTH, piclabel, 300, SpringLayout.NORTH, infopanel);
		infolay.putConstraint(SpringLayout.WEST, piclabel, 275, SpringLayout.WEST, infopanel);
		
	}
	private void loadFromDrive(ChildList children, String parentid){
		try {

			list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());

			for (ChildReference child : children.getItems()) {

				for(int i = 0; i < list.size(); i++){
					if(child.getId().equals(list.get(i).getId())){
						String title = list.get(i).getTitle();
						tracking.put(child.getId(), new DefaultMutableTreeNode(title))
						;						treemodel.insertNodeInto(tracking.get(child.getId()), tracking.get(parentid) , tracking.get(parentid).getChildCount());
						if(list.get(i).getMimeType().equals("application/vnd.google-apps.folder")){
							this.loadFromDrive(this.getChildren(child.getId()), child.getId());
						}
					}
				}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public ChildList getChildren(String id){
		try {
			Children.List request = DriveCommandLine.getService().children().list(id);
			return request.execute();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}
	public void showMainGui(String id){
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getTitle().equals(id)){
				textid.setText("ID: " + list.get(i).getId());
				texttitle.setText("Title: " +list.get(i).getTitle());
				textdatecr.setText("Create Date: "+list.get(i).getCreatedDate().toString());
				textdatemo.setText("Mod Date:" +list.get(i).getModifiedDate().toString());
				texttype.setText("Type: " +list.get(i).getFileExtension());
				textowner.setText("Owner: " + list.get(i).getOwners().get(0));
				try {
					piclabel.setIcon(new ImageIcon(ImageIO.read(new URL(list.get(i).getThumbnailLink()))));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				frame.repaint();
				mainPane.repaint();
			
			}
		}
	}
	public List<com.google.api.services.drive.model.File> getList() {
		return list;
	}
	public void setList(List<com.google.api.services.drive.model.File> list) {
		this.list = list;
	}
	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				maintree.getLastSelectedPathComponent();

		if (node == null)
			//Nothing is selected.     
			return;

		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) {
			this.showMainGui(nodeInfo.toString());
			for(int i = 0; i < fields.size(); i++){
				fields.get(i).setVisible(true);
			}
			
		} else {
			for(int i = 0; i < fields.size(); i++){
				fields.get(i).setVisible(false);
				fields.get(i).setText("");
			}
		}

	}

}
