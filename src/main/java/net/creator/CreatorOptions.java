package net.creator;

public class CreatorOptions {
	//DC_Dev_Account
	//DC$6D$C!464$4@6ge$s
	private static boolean isDriveEnabled = false;
	public static String MAIN_FILEPATH;
	public static String getMAIN_FILEPATH() {
		return MAIN_FILEPATH;
	}
	public static void setMAIN_FILEPATH(String mAIN_FILEPATH) {
		MAIN_FILEPATH = mAIN_FILEPATH;
	}
	public static boolean isDriveEnabled() {
		return isDriveEnabled;
	}
	public static void setDriveEnabled(boolean isDriveEnabled) {
		CreatorOptions.isDriveEnabled = isDriveEnabled;
	}
	public static String FILEPATH_FILES = System.getenv("APPDATA") + "/Drifting Colossus/Creator/";
	public static String FILEPATH_CONFIGS = "";
	public static  String DEV_ACCOUNT_USERNAME = "DC_Dev_Account";
	public static  String DEV_ACCOUNT_PASSWORD = "DC$6D$C!464$4@6ge$s";
}
