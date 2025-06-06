package skia;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleAudioTester {

	

	static Clip backgroundMusic;
	static HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	static String key = "";
	
    public static void playSound(String soundFileName) {
        try {
            // Use getResource to get the audio file from the classpath
            URL soundURL = SimpleAudioTester.class.getResource("/audio/" + soundFileName + ".wav");

            if (soundURL == null) {
                System.err.println("Sound file not found boooo: " + soundFileName);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start(); // Plays the clip
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public static void loopSound(String soundFileName) {
        if(!sounds.containsKey(soundFileName)) {
	    	try {
	            // Use getResource to get the audio file from the classpath
	            URL soundURL = SimpleAudioTester.class.getResource("/audio/" + soundFileName + ".wav");
	
	            if (soundURL == null) {
	                System.err.println("Sound file not found boooo: " + soundFileName);
	                return;
	            }
	
	            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
	            Clip clip = AudioSystem.getClip();
	
	            sounds.put(soundFileName, clip);
	            clip.open(audioIn);
	            clip.loop(-1); // Plays the clip
	        } catch (UnsupportedAudioFileException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public static void stopMusic() {
    	backgroundMusic.stop();
    }
    
    public static void stopSound(String soundFileName) {
    	if(sounds.containsKey(soundFileName)) {
    		sounds.get(soundFileName).stop();
    		sounds.remove(soundFileName);
    	}
    }
    
    public static void removeInactive() {
    	
    	if(sounds.size() > 0) {
    		sounds.forEach( (k, v) -> { 
        		if(!v.isRunning()) {
        			key = k;
        		}
        	} );
    	}
    	if(key!=null) {
    		sounds.remove(key);
    	}
    	
    }
    
}
        	