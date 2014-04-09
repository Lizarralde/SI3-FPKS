package devint.view;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class AudioPlayerThread implements Runnable {
    private File f;
    private List<AudioDoneListener> listeners;

    public AudioPlayerThread(String filename){
        f = new File(filename);
        listeners = new LinkedList<>();
    }

    public void addListener(AudioDoneListener listener){
        this.listeners.add(listener);
    }

    @Override
    public void run() {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(f);
            AudioFormat format;
            format = audio.getFormat();
            SourceDataLine auline;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
            auline.start();
            int nBytesRead = 0;
            byte[] abData = new byte[524288];
            while (nBytesRead != -1) {
                nBytesRead = audio.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    auline.write(abData, 0, nBytesRead);
                }
            }
        } catch (Exception e) {e.printStackTrace();}
        finally {
            for(AudioDoneListener l : this.listeners){
                l.onAudioDone();
            }
        }
    }
}