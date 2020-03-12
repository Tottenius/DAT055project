package model;

import java.io.File;

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

	public MusicPlayer(final String path) {

		playSound(path);
	}
/**
 * Plays the song that is determined by the path parameter.
 * @param path
 */
	public static void playSound(final String path) {
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
/**
 * Stops the currently playing song.
 */
	public static void StopMusic() {
		clip.stop();
		currently_playing = false;

	}
/**
 * Checks if any music is currently running.
 * @return True if music is running.
 */
	public static boolean isMusicRunning() {

		return currently_playing;
	}
/**
 * Sets the volume of the music that can be played. 
 * @param d
 */
	public static void setVolume(final double d) {

		final FloatControl volume = (FloatControl) MusicPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(20f * (float) Math.log10(d));
	}
/**
 * Increases the volume of the music.
 */
	public static void increaseVolume() {
		setVolume(1.0);
	}
/**
 * Decreases the volume of the the music.
 */
	public static void decreaseVolume() {
		setVolume(0.1);
	}
}