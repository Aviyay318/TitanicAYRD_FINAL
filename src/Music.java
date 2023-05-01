import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    private Clip clip;
    private AudioInputStream music;

    public Music(){
        try {
            this.music = AudioSystem.getAudioInputStream(new File("music/background.wav"));
            this.clip = AudioSystem.getClip();
            clip.open(this.music);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }

    }

    public void play(){
        clip.setMicrosecondPosition(0);
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
        clip.close();
    }
}
