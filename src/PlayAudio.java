import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.*;

public class PlayAudio {

    Clip clip;

    public PlayAudio(String file){
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // create an audiostream from the inputstream
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AudioPlayer.player.start(audioStream);
    }

}
