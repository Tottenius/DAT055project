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
 * @version 2020-03-13
 *
 */
public class MusicPlayer {

	private static boolean currently_playing = false;
	private  static Clip clip;
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
			
			System.out.println(path);

			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			MusicPlayer.clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			setVolume(0.5);
			clip.start();
			MusicPlayer.currently_playing = true;

			// If you want the sound to loop infinitely, then put:
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// If you want to stop the sound, then use clip.stop();
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Returns the level music path
	 * 
	 * @param level
	 * @return String
	 */
	public String getLevelMusic(final String level) {
		return levelMusic.get(level);
	}
/**
 * Intermediate method for handling the music playing
 * 
 * @param path
 */
	public void playMusic(final String path) {
		System.out.println(path);
		
		if (isMusicRunning()) {
			StopMusic();
			MusicPlayer.this.playSound(MusicPlayer.this.levelMusic.get(path));

		} else {
			MusicPlayer.this.playSound(MusicPlayer.this.levelMusic.get(path));
		}
	}
	
	
	
/**
 * Stops the currently playing song.
 */
	public void StopMusic() {
		MusicPlayer.clip.stop();
		MusicPlayer.currently_playing = false;

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