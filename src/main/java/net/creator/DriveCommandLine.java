package net.creator;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;


public class DriveCommandLine {

	private static String CLIENT_ID = "235546520438-8joat3umfv9lngep7aj2ffihci6705bp.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "jaaKTcDNyWRFB-me9FiKfYij";

	private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	private static Drive service;
	private static NetHttpTransport httpTransport;
	private static JacksonFactory jsonFactory;
	private static GoogleAuthorizationCodeFlow flow;
	private static JTextArea input;
	private static JButton button;
	private static JFrame frame;
	private static boolean isRunning;

	public static void doStuff() throws IOException {
		setRunning(true);
		httpTransport = new NetHttpTransport();
		jsonFactory = new JacksonFactory();

		flow = new GoogleAuthorizationCodeFlow.Builder(
				httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		.setAccessType("online")
		.setApprovalPrompt("auto").build();

		String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		System.out.println("Please open the following URL in your browser then type the authorization code:");
		System.out.println("  " + url);
		frame = new JFrame();
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Google Drive Activation Setup");
		JTextArea area = new JTextArea(1, 49);
		Container contentPane = frame.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		contentPane.add(area);
		layout.putConstraint(SpringLayout.NORTH, area,
				25,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, area,
				25,
				SpringLayout.WEST, contentPane);
		area.setEditable(false);
		area.setText(url);
		area.setLineWrap(true);
		JLabel urlLabel = new JLabel("Please open link in a browser and enter the code below.");
		contentPane.add(urlLabel);
		layout.putConstraint(SpringLayout.NORTH, urlLabel,
				5,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, urlLabel,
				25,
				SpringLayout.WEST, contentPane);

		input = new JTextArea(1, 49);
		contentPane.add(input);
		layout.putConstraint(SpringLayout.NORTH, input,
				180,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, input,
				25,
				SpringLayout.WEST, contentPane);

		button = new JButton("Check code");
		contentPane.add(button);
		layout.putConstraint(SpringLayout.NORTH, button,
				240,
				SpringLayout.NORTH, contentPane);

		layout.putConstraint(SpringLayout.WEST, button,
				25,
				SpringLayout.WEST, contentPane);
		button.addActionListener(new DriveCheck());
		frame.setVisible(true);

	}

	public static Drive getService() {
		return service;
	}

	public static void setService(Drive service) {
		DriveCommandLine.service = service;
	}

	public static String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public static void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public static String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public static void setCLIENT_SECRET(String cLIENT_SECRET) {
		CLIENT_SECRET = cLIENT_SECRET;
	}

	public static String getREDIRECT_URI() {
		return REDIRECT_URI;
	}

	public static void setREDIRECT_URI(String rEDIRECT_URI) {
		REDIRECT_URI = rEDIRECT_URI;
	}

	public static NetHttpTransport getHttpTransport() {
		return httpTransport;
	}

	public static void setHttpTransport(NetHttpTransport httpTransport) {
		DriveCommandLine.httpTransport = httpTransport;
	}

	public static JacksonFactory getJsonFactory() {
		return jsonFactory;
	}

	public static void setJsonFactory(JacksonFactory jsonFactory) {
		DriveCommandLine.jsonFactory = jsonFactory;
	}

	public static GoogleAuthorizationCodeFlow getFlow() {
		return flow;
	}

	public static void setFlow(GoogleAuthorizationCodeFlow flow) {
		DriveCommandLine.flow = flow;
	}

	public static JTextArea getInput() {
		return input;
	}

	public static void setInput(JTextArea input) {
		DriveCommandLine.input = input;
	}

	public static JButton getButton() {
		return button;
	}

	public static void setButton(JButton button) {
		DriveCommandLine.button = button;
	}

	public static JFrame getFrame() {
		return frame;
	}

	public static void setFrame(JFrame frame) {
		DriveCommandLine.frame = frame;
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		DriveCommandLine.isRunning = isRunning;
	}


}
