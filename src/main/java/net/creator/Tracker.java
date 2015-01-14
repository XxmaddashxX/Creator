package net.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

public class Tracker {
	private static ArrayList<String> synclist;
	private static Map<String, DefaultMutableTreeNode> treelist;
	public Tracker(){
		synclist = new ArrayList<String>();
		treelist = new HashMap<String, DefaultMutableTreeNode>();
	}
	
	
	public static void addTrackerSync(String name){
		synclist.add(name);
		return;
	}
	public static void addTrackerTreeList(String name, DefaultMutableTreeNode node){
		treelist.put(name, node);
		
		return;
	}
	
	
	public static ArrayList<String> getSynclist() {
		return synclist;
	}
	public static void setSynclist(ArrayList<String> synclist) {
		Tracker.synclist = synclist;
	}


	public static Map<String, DefaultMutableTreeNode> getTreelist() {
		return treelist;
	}


	public static void setTreelist(Map<String, DefaultMutableTreeNode> treelist) {
		Tracker.treelist = treelist;
	}

}
