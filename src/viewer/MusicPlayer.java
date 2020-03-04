package viewer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


// REMOVE ALL THIS STATIC, JUST COULDNT MAKE IT WORK WITHOUT IT BEFORE!
public class MusicPlayer{

	static boolean currently_playing = false;
	 static Clip clip;
	
	public MusicPlayer(String path) {
		
		playSound(path);
	}
	
	public static void playSound(String path) {
	    try {

	    	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
	         MusicPlayer.clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        setVolume(-20);
	        clip.start();
	        currently_playing = true;
	        
	        // If you want the sound to loop infinitely, then put: 
	        clip.loop(Clip.LOOP_CONTINUOUSLY); 
	        // If you want to stop the sound, then use clip.stop();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}
	
	public static void StopMusic() {
		clip.stop();
		 currently_playing = false;
		
	}
	
	public static boolean isMusicRunning(){
		
		return currently_playing;
	}
	
	public static void setVolume(float gain) {
		
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