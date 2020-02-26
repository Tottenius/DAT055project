package viewer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


// REMOVE ALL THIS STATIC, JUST COULDNT MAKE IT WORK WITHOUT IT BEFORE!
public class MusicPlayer {

	 static Clip clip;
	
	public MusicPlayer() {
		
		playSound();
	}
	
	public  void playSound() {
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Music/BackgroundMusic1.aifc"));
	         MusicPlayer.clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        setVolume(-3);
	        clip.start();
	        // If you want the sound to loop infinitely, then put: 
	        clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static  void setVolume(float gain) {
		
		FloatControl volume = (FloatControl) MusicPlayer.clip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(gain);  
	    }
	
	public static void increaseVolume() {
		System.out.println("ubsude ubcrea vikyne");
		setVolume(+4);
	}
	
	public static void decreaseVolume() {
		setVolume(-4);
	}
	}