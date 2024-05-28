package gamePackage;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/powerup.wav");
		soundURL[3] = getClass().getResource("/sound/unlock.wav");
		soundURL[4] = getClass().getResource("/sound/fanfare.wav");
		soundURL[5] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/sound/swingsword.wav");
		soundURL[8] = getClass().getResource("/sound/stairs.wav");
	}
	public void setFile(int n) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[n]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			
		}
	}
	public void play() {
		clip.start();
	}
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
	
	public void setVolume(float volume) { // valeur entre -80f et 6f
        if (clip != null) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(volume);
        }
    }
	
}
