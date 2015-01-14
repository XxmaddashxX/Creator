package net.creator;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.google.api.services.drive.model.ParentReference;

public class MainScreen implements ActionListener, MouseListener{
	private ArrayList<String> systems;
	private ArrayList<String> stars;
	private Map<String, DefaultMutableTreeNode> systemsnodes;
	private Map<String, DefaultMutableTreeNode> starsnodes;
	private JMenuBar menubar;
	private JMenu audioMenu;
	private JMenu fileMenu;
	private JTabbedPane tabbedPane;
	private JScrollPane treePanel;
	private JPanel mainPanel;
	private JScrollPane createPanel;
	private JMenu universemenu;
	private JMenu bodiesmenu;
	private JMenu texturemenu;
	private JMenu windowmenu;
	private JMenu testmenu;
	private JMenu shipmenu;
	private JMenu logicmenu;
	private JMenu aimenu;
	private JMenu systemmenu;
	private JMenuItem testAudio;
	private JMenuItem audiodatabase;
	private JMenuItem buttonnew;
	private JMenuItem buttonsave;
	private JTree datatree;
	private JMenuItem buttondebug;
	private JMenuItem buttonsystemprops;
	private JTree unitree;
	private DefaultMutableTreeNode main_node;
	private JMenuItem buttonDrive;
	private JMenuItem buttonsync;
	public DefaultTreeModel getTreemodel() {
		return treemodel;
	}
	public void setTreemodel(DefaultTreeModel treemodel) {
		this.treemodel = treemodel;
	}

	private DefaultMutableTreeNode top;
	private DefaultTreeModel treemodel;
	private JMenuItem buttonadmin;
	private Tracker tracker;
	private DefaultMutableTreeNode category;
	private DefaultMutableTreeNode category1;
	private DefaultMutableTreeNode category2;
	private DefaultTreeModel unimodel;
	private JPopupMenu unipopup;
	private JMenuItem popveiw;
	private JMenuItem buttonsettings;
	private JMenuItem buttonquickmenu;
	private JMenuItem buttonexportdrive;
	private JMenuItem buttonexportjar;
	private JMenuItem buttonsimulate;
	private JMenuItem buttonqsimulate;
	private JMenuItem buttonclassdata;
	private JMenuItem buttonnews;
	private JMenuItem editmenu;
	private JMenuItem buttonmanager;
	public MainScreen() {
		//DebugScreen d = new DebugScreen();
		setTracker(new Tracker());
	}
	public void setupMainScreen(){
		systems = new ArrayList<String>();
		systemsnodes = new HashMap<String, DefaultMutableTreeNode>();
		stars = new ArrayList<String>();
		starsnodes = new HashMap<String , DefaultMutableTreeNode>();
		JFrame frame = new JFrame();
		this.createPanels();
		this.createTree();
		this.createTabbedPaneItems();
		this.createMenuBarItems();
		frame.setTitle("Drifting Colossus Creator - User[" + Main.getUser() + "]");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(1200, 700);
		Container contentPane = frame.getContentPane();
		BorderLayout layout = new BorderLayout();
		contentPane.setLayout(layout);
		try {
			frame.setIconImage(ImageIO.read(new File("res/textures/icons/Drifting_Colossus_icon.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setLocation(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 600, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 350);
		JSplitPane p = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT){
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
		p.setDividerLocation(200);

		contentPane.add(p, BorderLayout.CENTER);
		contentPane.add(menubar, BorderLayout.NORTH);
		p.setRightComponent(mainPanel);
		p.setLeftComponent(tabbedPane);
		this.treeload();
		createPopup();
		frame.setVisible(true);
	}
	public void createMenuBarItems(){
		menubar = new JMenuBar();
		audioMenu = new JMenu("Audio");
		fileMenu = new JMenu("File");
		universemenu = new JMenu("Universe");
		bodiesmenu = new JMenu("Bodies");
		texturemenu = new JMenu("Textures");
		testmenu = new JMenu("Test");
		windowmenu = new JMenu("Window");
		shipmenu = new JMenu("Ship");
		logicmenu = new JMenu("Logic");
		aimenu = new JMenu("AI");
		systemmenu = new JMenu("System");
		menubar.add(fileMenu);
		menubar.add(universemenu);
		menubar.add(bodiesmenu);
		menubar.add(shipmenu);
		menubar.add(logicmenu);
		menubar.add(aimenu);
		menubar.add(texturemenu);
		menubar.add(audioMenu);
		menubar.add(testmenu);
		menubar.add(windowmenu);
		menubar.add(systemmenu);
		//Audio Items
		testAudio = new JMenuItem("Test Audio");
		audiodatabase = new JMenuItem("Audio Database");
		audioMenu.add(testAudio);
		audioMenu.add(audiodatabase);
		testAudio.addActionListener(this);
		audiodatabase.addActionListener(this);
		//System Items
		buttondebug = new JMenuItem("Messages");

		buttonsystemprops = new JMenuItem("System Properties");
		buttonquickmenu = new JMenuItem("QuickMenu Settings");
		buttonnews = new JMenuItem("News");
		buttonsettings = new JMenuItem("Settings");
		buttonnews.addActionListener(this);
		buttonquickmenu.addActionListener(this);
		buttondebug.addActionListener(this);
		buttonsystemprops.addActionListener(this);
		buttonsettings.addActionListener(this);
		systemmenu.add(buttondebug);
		systemmenu.addSeparator();
		systemmenu.add(buttonsystemprops);
		systemmenu.add(buttonsettings);
		systemmenu.add(buttonquickmenu);
		systemmenu.addSeparator();
		systemmenu.add(buttonnews);


		//File Items
		buttonnew = new JMenuItem("New..", new ImageIcon(CreatorOptions.MAIN_FILEPATH + "/Creator_Bin/images/newasset-icon.png"));
		buttonsave = new JMenuItem("Save", new ImageIcon(CreatorOptions.MAIN_FILEPATH + "/Creator_Bin/images/save-icon.png"));

		fileMenu.add(buttonnew);
		fileMenu.add(buttonsave);
		buttonnew.addActionListener(this);
		buttonsave.addActionListener(this);
		fileMenu.addSeparator();
		buttonDrive = new JMenuItem("Google Drive", new ImageIcon(CreatorOptions.MAIN_FILEPATH + "/Creator_Bin/images/Google-Drive-icon.png"));
		fileMenu.add(buttonDrive);
		buttonsync = new JMenuItem("Sync");
		fileMenu.add(buttonsync);
		buttonsync.addActionListener(this);
		buttonadmin = new JMenuItem("Admin");
		fileMenu.addSeparator();
		fileMenu.add(buttonadmin);
		buttonadmin.addActionListener(this);
		fileMenu.addSeparator();
		buttonexportdrive = new JMenuItem("Export to Drive");
		fileMenu.add(buttonexportdrive);
		buttonexportdrive.addActionListener(this);
		buttonexportjar = new JMenuItem("Export to .jar");
		fileMenu.add(buttonexportjar);
		buttonexportjar.addActionListener(this);
		buttonDrive.addActionListener(this);
		//test menu
		buttonsimulate = new JMenuItem("Simulate");
		testmenu.add(buttonsimulate);
		buttonsimulate.addActionListener(this);
		buttonqsimulate = new JMenuItem("Quick Simulate");
		testmenu.add(buttonqsimulate);
		buttonqsimulate.addActionListener(this);
		//Bodies
		buttonclassdata = new JMenuItem("Class Data");
		bodiesmenu.add(buttonclassdata);
		buttonclassdata.addActionListener(this);
		//AI
		buttonmanager = new JMenuItem("Dialogue Manager"); 
		aimenu.add(buttonmanager);
		buttonmanager.addActionListener(this);
	}
	public void createTabbedPaneItems(){
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Data", treePanel);
		tabbedPane.addTab("Create", createPanel);
	}
	public void createPanels(){

		mainPanel = new JPanel();

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(buttonnew)){
			NewScreen n = new NewScreen();
		}
		if(e.getSource().equals(buttondebug)){
			DebugMessage d  = new DebugMessage();
			PrintStream con=new PrintStream(new TextAreaOutputStream(d.getMessagearea()));
			System.setOut(con);
			try {
				List<com.google.api.services.drive.model.File> list = DriveDownloader.retrieveAllFiles(DriveCommandLine.getService());
				for(int i = 0; i < list.size(); i++){
					System.out.println("---------------------------");
					System.out.println(list.get(i).getTitle());
					System.out.println("   " + list.get(i).getId());
					System.out.println("   " + list.get(i).getFileExtension());
					List<ParentReference> temp = list.get(i).getParents();
				
				}
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}
		if(e.getSource().equals(testAudio)){
			AudioTest t = new AudioTest();
		}
		if(e.getSource().equals(buttonsync)){
			if(!CreatorOptions.isDriveEnabled()){
				MessageFrame m = new MessageFrame("Drive Not Setup", "Google Drive has not been setup, which results to this feature to be disabled", Layouts.OK_ONLY, Levels.ERROR);
			}

			else{
				DriveSync s = new DriveSync();
			}

		}
		if(e.getSource().equals(buttonsettings)){
			SettingScreen s = new SettingScreen();
		}
		if(e.getSource().equals(buttonclassdata)){
			ClassData s = new ClassData();
		}
		if(e.getSource().equals(buttonadmin)){
			AdminScreen s = new AdminScreen(Main.getUser());
		}
		if(e.getSource().equals(buttonnews)){
			NewsScreen n = new NewsScreen();
		}
		if(e.getSource().equals(buttonqsimulate)){
			QSimSetup m = new QSimSetup();
		}
		if(e.getSource().equals(buttonDrive)){
			System.out.println(4);
			DriveScreen a = new DriveScreen();
		}
		if(e.getSource().equals(buttonmanager)){
			DialogueManager m = new DialogueManager();
		}
	}
	public void createTree(){
		//Data Tree

		top =
				new DefaultMutableTreeNode("Data");
		createNodes(top);
		treemodel = new DefaultTreeModel(top);
		datatree = new JTree(treemodel);

		treePanel = new JScrollPane(datatree);
		datatree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);
		//universe tree
		main_node =
				new DefaultMutableTreeNode("Universe");
		createUniNodes(main_node);
		unimodel = new DefaultTreeModel(main_node);

		unitree = new JTree(unimodel);
		unitree.addMouseListener(this);
		createPanel = new JScrollPane(unitree);
		unitree.getSelectionModel().setSelectionMode
		(TreeSelectionModel.SINGLE_TREE_SELECTION);


	}
	public JMenuBar getMenubar() {
		return menubar;
	}
	public void setMenubar(JMenuBar menubar) {
		this.menubar = menubar;
	}
	public JMenu getAudioMenu() {
		return audioMenu;
	}
	public void setAudioMenu(JMenu audioMenu) {
		this.audioMenu = audioMenu;
	}
	public JMenu getFileMenu() {
		return fileMenu;
	}
	public void setFileMenu(JMenu fileMenu) {
		this.fileMenu = fileMenu;
	}
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	public JScrollPane getTreePanel() {
		return treePanel;
	}
	public void setTreePanel(JScrollPane treePanel) {
		this.treePanel = treePanel;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public JScrollPane getCreatePanel() {
		return createPanel;
	}
	public void setCreatePanel(JScrollPane createPanel) {
		this.createPanel = createPanel;
	}
	public JMenu getUniversemenu() {
		return universemenu;
	}
	public void setUniversemenu(JMenu universemenu) {
		this.universemenu = universemenu;
	}
	public JMenu getBodiesmenu() {
		return bodiesmenu;
	}
	public void setBodiesmenu(JMenu bodiesmenu) {
		this.bodiesmenu = bodiesmenu;
	}
	public JMenu getTexturemenu() {
		return texturemenu;
	}
	public void setTexturemenu(JMenu texturemenu) {
		this.texturemenu = texturemenu;
	}
	public JMenu getWindowmenu() {
		return windowmenu;
	}
	public void setWindowmenu(JMenu windowmenu) {
		this.windowmenu = windowmenu;
	}
	public JMenu getTestmenu() {
		return testmenu;
	}
	public void setTestmenu(JMenu testmenu) {
		this.testmenu = testmenu;
	}
	public JMenu getShipmenu() {
		return shipmenu;
	}
	public void setShipmenu(JMenu shipmenu) {
		this.shipmenu = shipmenu;
	}
	public JMenu getLogicmenu() {
		return logicmenu;
	}
	public void setLogicmenu(JMenu logicmenu) {
		this.logicmenu = logicmenu;
	}
	public JMenu getAimenu() {
		return aimenu;
	}
	public void setAimenu(JMenu aimenu) {
		this.aimenu = aimenu;
	}
	public JMenu getSystemmenu() {
		return systemmenu;
	}
	public void setSystemmenu(JMenu systemmenu) {
		this.systemmenu = systemmenu;
	}
	public JMenuItem getTestAudio() {
		return testAudio;
	}
	public void setTestAudio(JMenuItem testAudio) {
		this.testAudio = testAudio;
	}
	public JMenuItem getAudiodatabase() {
		return audiodatabase;
	}
	public void setAudiodatabase(JMenuItem audiodatabase) {
		this.audiodatabase = audiodatabase;
	}
	public JMenuItem getButtonnew() {
		return buttonnew;
	}
	public void setButtonnew(JMenuItem buttonnew) {
		this.buttonnew = buttonnew;
	}
	public JMenuItem getButtonsave() {
		return buttonsave;
	}
	public void setButtonsave(JMenuItem buttonsave) {
		this.buttonsave = buttonsave;
	}
	public JTree getDatatree() {
		return datatree;
	}
	public void setDatatree(JTree datatree) {
		this.datatree = datatree;
	}
	public JMenuItem getButtondebug() {
		return buttondebug;
	}
	public void setButtondebug(JMenuItem buttondebug) {
		this.buttondebug = buttondebug;
	}
	public JMenuItem getButtonsystemprops() {
		return buttonsystemprops;
	}
	public void setButtonsystemprops(JMenuItem buttonsystemprops) {
		this.buttonsystemprops = buttonsystemprops;
	}
	public JTree getUnitree() {
		return unitree;
	}
	public void setUnitree(JTree unitree) {
		this.unitree = unitree;
	}
	public JMenuItem getButtonDrive() {
		return buttonDrive;
	}
	public void setButtonDrive(JMenuItem buttonDrive) {
		this.buttonDrive = buttonDrive;
	}
	public JMenuItem getButtonsync() {
		return buttonsync;
	}
	public void setButtonsync(JMenuItem buttonsync) {
		this.buttonsync = buttonsync;
	}
	public DefaultMutableTreeNode getTop() {
		return top;
	}
	public void setTop(DefaultMutableTreeNode top) {
		this.top = top;
	}
	private void createUniNodes(DefaultMutableTreeNode uni) {


	}
	public void addSystemNode(DefaultMutableTreeNode uni, String name){
		DefaultMutableTreeNode category = new DefaultMutableTreeNode(name);
		//uni.add(category);
		this.addObject(uni, name, true);
		systemsnodes.put(name, category);

		systems.add(name);
	}
	public void addNode(DefaultMutableTreeNode node, String name){
		//DefaultMutableTreeNode cat = new DefaultMutableTreeNode(name);
		this.addObject(node, name, true);


	}

	public void createNodes(DefaultMutableTreeNode top) {
		category = new DefaultMutableTreeNode("Universe");
		top.add(category);
		category1 = new DefaultMutableTreeNode("Audio");
		top.add(category1);
		category2 = new DefaultMutableTreeNode("Textures");
		top.add(category2);


	}
	public DefaultMutableTreeNode getMain_node() {
		return main_node;
	}
	public void setMain_node(DefaultMutableTreeNode main_node) {
		this.main_node = main_node;
	}
	public ArrayList<String> getSystems() {
		return systems;
	}
	public void setSystems(ArrayList<String> systems) {
		this.systems = systems;
	}
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = datatree.getSelectionPath();

		if (parentPath == null) {
			//There is no selection. Default to the root node.
			parentNode = top;
		} else {
			parentNode = (DefaultMutableTreeNode)
					(parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent,
			Object child,
			boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode =
				new DefaultMutableTreeNode(child);
		Tracker.addTrackerTreeList(child.toString(), childNode);
		treemodel.insertNodeInto(childNode, parent,
				parent.getChildCount());

		//Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			datatree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}
	public Tracker getTracker() {
		return tracker;
	}
	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}
	public DefaultMutableTreeNode getCategory() {
		return category;
	}
	public void setCategory(DefaultMutableTreeNode category) {
		this.category = category;
	}
	public DefaultMutableTreeNode getCategory1() {
		return category1;
	}
	public void setCategory1(DefaultMutableTreeNode category1) {
		this.category1 = category1;
	}
	public DefaultMutableTreeNode getCategory2() {
		return category2;
	}
	public void setCategory2(DefaultMutableTreeNode category2) {
		this.category2 = category2;
	}
	public Map<String, DefaultMutableTreeNode> getSystemsnodes() {
		return systemsnodes;
	}
	public void setSystemsnodes(Map<String, DefaultMutableTreeNode> systemsnodes) {
		this.systemsnodes = systemsnodes;
	}
	public ArrayList<String> getStars() {
		return stars;
	}
	public void setStars(ArrayList<String> stars) {
		this.stars = stars;
	}
	public Map<String, DefaultMutableTreeNode> getStarsnodes() {
		return starsnodes;
	}
	public void setStarsnodes(Map<String, DefaultMutableTreeNode> starsnodes) {
		this.starsnodes = starsnodes;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.equals(MouseEvent.BUTTON2)){
			if(e.isPopupTrigger()){
				unipopup.show(e.getComponent(), e.getX(), e.getY());
			}
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
		if(e.equals(MouseEvent.BUTTON2)){
			if(e.isPopupTrigger()){
				unipopup.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	public DefaultMutableTreeNode addLoadObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = datatree.getSelectionPath();

		if (parentPath == null) {
			//There is no selection. Default to the root node.
			parentNode = top;
		} else {
			parentNode = (DefaultMutableTreeNode)
					(parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addLoadObject(DefaultMutableTreeNode parent,
			Object child,
			boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode =
				new DefaultMutableTreeNode(child);
		Tracker.addTrackerTreeList(child.toString(), childNode);
		treemodel.insertNodeInto(childNode, parent,
				parent.getChildCount());
		
		//Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			datatree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}
	public void treeload(){
		ArrayList<String> temp = Loader.listFoldersForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Textures"));
		for(int i = 0; i < temp.size(); i++){
			this.addLoadObject(category2, temp.get(i), false);
			ArrayList<String> t = Loader.listFilesForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Textures/" + temp.get(i)));
			for(int p = 0; p < t.size(); p++){
				this.addLoadObject(Tracker.getTreelist().get(temp.get(i)), t.get(p), false);
			}

		}
		ArrayList<String> temp2 = Loader.listFoldersForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Audio"));
		for(int i = 0; i < temp2.size(); i++){
			this.addLoadObject(category, temp2.get(i), false);
			ArrayList<String> t = Loader.listFilesForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Audio/" + temp.get(i)));
			for(int p = 0; p < t.size(); p++){
				this.addLoadObject(Tracker.getTreelist().get(temp2.get(i)), t.get(p), false);

			}

		}
		ArrayList<String> temp3 = Loader.listFoldersForFolder(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems"));
		for(int p = 0; p < temp3.size(); p++){
			this.addNode(main_node, temp3.get(p));
			System.out.println(1);
			this.loadSystem(new File(CreatorOptions.FILEPATH_FILES + "Data/Systems/" + temp3.get(p)), Tracker.getTreelist().get(temp3.get(p).toString()));
		}


	}
	public void createPopup(){
		unipopup = new JPopupMenu();
		editmenu = new JMenuItem("Edit");
		unipopup.add(editmenu);
		unipopup.addMouseListener(this);
		mainPanel.add(unipopup);
	}
	public void loadSystem(File file, DefaultMutableTreeNode node){
		for(File path: file.listFiles()){
			System.out.println(2);
			
			System.out.println(3);
			if(path.isDirectory()){
				this.loadSystem(path, Tracker.getTreelist().get(file.getName()));
			}
			this.addObject(node, path.getName(), false);
		}
	}


}
