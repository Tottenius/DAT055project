package viewer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer {

	static boolean currently_playing = false;
	static Clip clip;

	public MusicPlayer(final String path) {

		playSound(path);
	}

	public static void playSound(final String path) {
		try {

			@SuppressWarnings("resource")
			final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			MusicPlayer.clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			setVolume(0.1);
			clip.start();
			currently_playing = true;

			// If you want the sound to loop infinitely, then put:
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			// If you want to stop the sound, then use clip.stop();
		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void StopMusic() {
		clip.stop();
		currently_playing = false;

	}

	public static boolean isMusicRunning() {

		return currently_playing;
	}

	public static void setVolume(final double d) {

		final FloatControl volume = (FloatControl) MusicPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(20f * (float) Math.log10(d));
	}

	public static void increaseVolume() {
		setVolume(2.0);
	}

	public static void decreaseVolume() {
		setVolume(0.5);
	}
}