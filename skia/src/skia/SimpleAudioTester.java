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

	Clip backgroundMusic;
	HashMap<String, Clip> sounds = new HashMap<String, Clip>();
	String key = "";
	
	public void backgroundMusic() {
		//dedicated background music method that will loop
		//almost indefinitely, until another method is called
		try {
            // Use getResource to get the audio file from the classpath
            URL soundURL = SimpleAudioTester.class.getResource("/audio/cooking.wav");

            if (soundURL == null) {
                System.err.println("Sound file not found boooo: backgroundMusic");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioIn);
            // Plays the clip
            backgroundMusic.loop(-1);
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
	
    public void playSound(String soundFileName) {
    	//plays the given file name once
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
    
    public void loopSound(String soundFileName) {
    	//loops the given file name until told to stop
    	//stores all given files in a hashmap with a name and its clip
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
    
    public void stopMusic() {
    	//stops the background music
    	if(backgroundMusic.isRunning()) {
    		backgroundMusic.stop();
    	}
    }
    
    public void stopSound(String soundFileName) {
    	//stops the given sound from playing
    	if(sounds.get(soundFileName) != null) {
    		sounds.get(soundFileName).stop();
    		sounds.remove(soundFileName);
    	}
    }
    
    public void clearAllSound() {
    	//stops any and all sounds that are currently looping
    	if(sounds.size() > 0) {
    		sounds.forEach( (k, v) -> { 
        		key = k;
        	} );
    	}
    	if(key!=null && sounds.get(key) != null) {
    		sounds.get(key).stop();
    		sounds.remove(key);
    	}
    }
    
}
        	