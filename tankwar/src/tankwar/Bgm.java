package tankwar;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.File;

public class Bgm {
    public static void sound(String s) {
        File file = new File(s);

        AudioInputStream am;
        try {
            am = AudioSystem.getAudioInputStream(file);
            AudioFormat af = am.getFormat();
            SourceDataLine sd;
            sd = AudioSystem.getSourceDataLine(af);
            sd.open();
            sd.start();
            int sumByteRead = 0;
            byte[] b = new byte[128];
            while (sumByteRead != -1) {
                sumByteRead = am.read(b, 0, b.length);
                if (sumByteRead >= 0) {
                    sd.write(b, 0, b.length);
                }
            }
            sd.drain();
            sd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class Atk implements Runnable {
    @Override
    public void run() {

        Bgm.sound("C:\\Users\\sjr\\Desktop\\子弹.wav");

    }

}

class Bei implements Runnable {
    @Override
    public void run() {
        while (true) {
            Bgm.sound("C:\\Users\\sjr\\Desktop\\第二关.wav");
        }
    }
}

