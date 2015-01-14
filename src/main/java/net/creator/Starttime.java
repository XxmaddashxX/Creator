package net.creator;

import java.io.File;
import java.util.ArrayList;

public class Starttime {
	
	
	public static void onLoad(){
		ArrayList<String> temp  = Loader.listFoldersForFolder(new File(CreatorOptions.FILEPATH_FILES + "Systems"));
		for(int i = 0; i < temp.size(); i++){
			Main.getScreen().addLoadObject(Main.getScreen().getMain_node(), temp.get(i), false);
		}
	
	}
	public static void onClose(){
		
	}
	public static void addNodes(ArrayList<String> temp){
		for(int i = 0; i < temp.size(); i++){
		
		}
	}

}
