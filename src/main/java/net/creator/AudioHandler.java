package net.creator;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class AudioHandler {
	
	public static Audio loadAudio(String name, String format){
		try {
			return AudioLoader.getAudio(format.toUpperCase(), ResourceLoader.getResourceAsStream(CreatorOptions.FILEPATH_FILES + "Data/Audio/" + name));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void playSoundEffect(Audio audio, int pitch, int gain, boolean loop){
		audio.playAsSoundEffect(pitch, gain, loop);
	}
	public static void playSoundMusic(Audio audio, int pitch, int gain, boolean loop){
		audio.playAsMusic(pitch, gain, loop);
	}

}
