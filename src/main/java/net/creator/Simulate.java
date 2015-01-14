package net.creator;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Simulate {

	private boolean isRunning;
	public Simulate(){

		setup();
		init();
		load();
		gameLoop();
	}



	private void setup() {
		try{
			Display.setResizable(true);
			DisplayMode displayMode = null;
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			for (int i = 0; i < modes.length; i++)
			{
				if (modes[i].getWidth() == 800
						&& modes[i].getHeight() == 600
						&& modes[i].isFullscreenCapable())
				{
					displayMode = modes[i];
				}
			}
			Display.setDisplayMode(displayMode);
			Display.setTitle("Simulate");
			Display.sync(60);
			Display.setVSyncEnabled(true);
			Display.create();

			Mouse.create();
			Keyboard.create();


		}
		catch (LWJGLException e1) {

			e1.printStackTrace();
		}

	}
	private void init(){
		glEnable(GL_TEXTURE_2D);
		glClearColor(0,0,0,0);

		glEnable(GL_BLEND);
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity(); // Resets any previous projection matrices
		//glOrtho(0, Display.getWidth() * amount, Display.getHeight() * amount, 0, 1, -1);
		
		glMatrixMode(GL_MODELVIEW);
	}
	private void load() {


	}
	private void gameLoop(){
		while(isRunning){
			input();
			update();
			render();
		}
	}



	private void render() {
		
		
	}



	private void update() {
		
		
	}



	private void input() {
		
		
	}

}
