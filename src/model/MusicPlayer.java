package model;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
/**
 * A class for playing music.
 * @author Anton
 *
 */
public class MusicPlayer {

	static boolean currently_playing = false;
	static Clip clip;
	// music for each level
	private  HashMap<String, String> levelMusic = new HashMap<>();
	
	public MusicPlayer() {
		loadInLevelMusicPaths();
	}
	
	private void loadInLevelMusicPaths() {
		levelMusic.put("level1", "src/Music/level1.aifc");
		levelMusic.put("level2", "src/Music/level2.aifc");
		levelMusic.put("level3", "src/Music/level3.aifc");
		levelMusic.put("level4", "src/Music/level4.aifc");
		levelMusic.put("level5", "src/Music/level5.aifc");
		levelMusic.put("level6", "src/Music/level6.wav");
		levelMusic.put("level7", "src/Music/level7.aifc");
		levelMusic.put("level8", "src/Music/level8.wav");
		levelMusic.put("level9", "src/Music/level9.wav");
		levelMusic.put("level10","src/Music/level10.wav");
		levelMusic.put("saveLevel", "src/Music/level1.aifc");
		// menu sound path
		levelMusic.put("menu", "src/Music/MainMenuSong.aifc");
		
	}
/**
 * Plays the song that is determined by the path parameter.
 * @param path
 */
	private void playSound(final String path) {
		try {

			@SuppressWarnings("resource")
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			MusicPlayer.clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			setVolume(0.5);
			clip.start();
			currently_playing = true;

			// If you want the sound to loop infinitely, then put:
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// If you want to stop the sound, then use clip.stop();
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getLevelMusic(final String level) {
		return levelMusic.get(level);
	}

	public void playMusic(final String path) {
		// precaution
		if (!path.equals("src/Music/level1.aifc") && !path.equals(null)) {
			StopMusic();
			playSound(path);
		
		} else if (isMusicRunning()) {
			StopMusic();
			playSound(path);

		} else {
			playSound(path);
		}
	}
	
	
	
/**
 * Stops the currently playing song.
 */
	public void StopMusic() {
		clip.stop();
		currently_playing = false;

	}
/**
 * Checks if any music is currently running.
 * @return True if music is running.
 */
	public boolean isMusicRunning() {

		return currently_playing;
	}
/**
 * Sets the volume of the music that can be played. 
 * @param d
 */
	public void setVolume(final double d) {

		final FloatControl volume = (FloatControl) MusicPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(20f * (float) Math.log10(d));
	}
/**
 * Increases the volume of the music.
 */
	public void increaseVolume() {
		setVolume(1.0);
	}
/**
 * Decreases the volume of the the music.
 */
	public void decreaseVolume() {
		setVolume(0.1);
	}
}