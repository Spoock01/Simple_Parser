package Classes;

import java.util.Collection;
import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.modules.synthesis.Voice;
import marytts.util.data.audio.AudioPlayer;


public class TextToSpeech {
    private AudioPlayer tts;
    private MaryInterface marytts;

    public TextToSpeech() {
        try{
            
            marytts = new LocalMaryInterface();
            
        }catch(MaryConfigurationException ex){
            System.out.println("Mary exception.");
        }

    }
    
    public Collection<Voice> getAvailableVoices(){
        return Voice.getAvailableVoices();
    }
    
    public void speak(String text, boolean daemon, boolean join){
        
        stopSpeaking();
        
        try(AudioInputStream audio = marytts.generateAudio(text)){
            tts = new AudioPlayer();
            tts.setAudio(audio);
            tts.setDaemon(daemon);
            tts.start();
            if(join)
                tts.join();
            
        }catch(Exception e){
            System.out.println("Exception no speak: " + e.getMessage());
        }
    }

    private void stopSpeaking() {

        if(tts!= null)
            tts.cancel();

    }
    
    public void setVoice(String voice){
        marytts.setVoice(voice);
    }
}