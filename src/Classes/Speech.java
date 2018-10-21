package Classes;


public class Speech {
    public void speak(String message) {
        TextToSpeech tts = new TextToSpeech();
        
        //Voice.getAvailableVoices().stream().forEach(System.out::println);
        
        tts.setVoice("cmu-slt-hsmm");
        tts.speak(message, false, true);
        
             
    }
}
