package Miscelaneos;

import java.io.File;
import javax.sound.sampled.*;

public class ReproductorAudio {
    private Clip clip;

    public void reproducir(String ruta) {
        try {
            File archivo = new File(ruta);
            if (archivo.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // loop infinito
            } else {
                System.out.println("No se encontró el archivo de audio: " + ruta);
            }
        } catch (Exception e) {
            System.out.println("Error al reproducir el audio: " + e.getMessage());
        }
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
