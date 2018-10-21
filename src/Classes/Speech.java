package Classes;


public class Speech {
    public void speak(String message) {
        TextToSpeech tts = new TextToSpeech();

        tts.setVoice("cmu-slt-hsmm");
        tts.speak(message, false, true);
        
             
    }
}
