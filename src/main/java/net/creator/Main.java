package net.creator;

import java.io.IOException;

public class Main {
	private static String User;
	private static boolean logopen, setupopen, updateopen;
	private static MainScreen screen;
	private static boolean bypass;
	private static boolean canUpdate;
	private static boolean doupdate;
	private static String[] type;
	private static String updateID;
	public static void main(String[] args){
		User = null;
		setUpdateID(null);
		doupdate = false;
		canUpdate = false;
		bypass = false;
		type = null;
		if(args.length > 0){
			if(args[0].equals("login_bypass")){
				bypass = true;
			}



		}
		try {
			DriveCommandLine.doStuff();
			while(DriveCommandLine.isRunning()){
				System.out.println("f");
			}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		if(!bypass){
			if(CreatorOptions.isDriveEnabled()){
				Login log = new Login();
				logopen = true;
				while(logopen){
					System.out.println("da");	
				}
			}
			
		}
		if(User == null){
			Main.setUser("UNKNOWN");
		}
		UpdateCheck.isUpdateAvailable();
		System.out.println(canUpdate);
		if(canUpdate){
			updateopen = true;
			UpdateScreen s = new UpdateScreen();
			while(updateopen){
				System.out.println();
			}
			if(doupdate){
				Updater.main(type);
			}
			
		}
		
		new Thread(new WaitScreen("WAIT")).run();
		SetupScreen sc = new SetupScreen();
		sc.setup();
		setupopen = true;
		while(setupopen){
			System.out.println("da");
		}

		Loader.createDirs();


		screen = new MainScreen();
		screen.setupMainScreen();




	}

	public static String getUser() {
		return User;
	}

	public static void setUser(String user) {
		User = user;
	}

	public static boolean isLogopen() {
		return logopen;
	}

	public static void setLogopen(boolean logopen) {
		Main.logopen = logopen;
	}

	public static boolean isSetupopen() {
		return setupopen;
	}

	public static void setSetupopen(boolean setupopen) {
		Main.setupopen = setupopen;
	}

	public static MainScreen getScreen() {
		return screen;
	}

	public static void setScreen(MainScreen screen) {
		Main.screen = screen;
	}

	public static boolean isBypass() {
		return bypass;
	}

	public static void setBypass(boolean bypass) {
		Main.bypass = bypass;
	}

	public static boolean isCanUpdate() {
		return canUpdate;
	}

	public static void setCanUpdate(boolean canUpdate) {
		Main.canUpdate = canUpdate;
	}

	public static String[] getType() {
		return type;
	}

	public static void setType(String[] type) {
		Main.type = type;
	}

	public static boolean isUpdateopen() {
		return updateopen;
	}

	public static void setUpdateopen(boolean updateopen) {
		Main.updateopen = updateopen;
	}

	public static boolean isDoupdate() {
		return doupdate;
	}

	public static void setDoupdate(boolean doupdate) {
		Main.doupdate = doupdate;
	}

	public static String getUpdateID() {
		return updateID;
	}

	public static void setUpdateID(String updateID) {
		Main.updateID = updateID;
	}

}
