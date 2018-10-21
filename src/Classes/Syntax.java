package Classes;

import Interface.Tela;
import java.util.ArrayList;

public class Syntax {
    
    private final ArrayList<Tokens> symbolTable;
    private int nextTokenIndex;
    private Tokens currentToken;
    private final Speech speech;

    public Syntax(ArrayList<Tokens> symbolTable) {
        this.symbolTable = symbolTable;
        this.nextTokenIndex = 0;
        currentToken = symbolTable.get(0);
        speech = new Speech();
    }
    
    private void nextToken(){
        
        if(nextTokenIndex + 1 < symbolTable.size()){
            ++nextTokenIndex;
            currentToken = symbolTable.get(nextTokenIndex);
        }
    }
    
    public void speak(String message){
        speech.speak(message);
    }
    
    public boolean checkRepeatedWords(){
        
        Tokens t = symbolTable.get(0);
        for(int i = 1; i < symbolTable.size() - 1; i++){
            if(t.getToken().equals(symbolTable.get(i).getToken())){
                speak("Repeated words were found. " + t.getToken());
                Tela.lblResult.setText("Repeated words.");
                return false;
            }else
                t = symbolTable.get(i);
        }
        
        return true;
    }
    
    public void analysis(){
        
        if(checkRepeatedWords()){
            if(sintagmaNominal()){
                if(sintagmaVerbal()){
                    if(currentToken.getClassification().equals("Dot")){
                        new Speech().speak("OK");
                    }else{
                        speak("Missing dot.");
                        Tela.lblResult.setText("Missing dot.");
                    }
                }
            }else{
                speak("Excepting Determiner, Pronoun or Noun.");
                Tela.lblResult.setText("Excepting Determiner, Pronoun or Noun.");
            }
        }
    }
    
    public boolean sintagmaNominal(){
        
        if(determinador()){
            if(substantivo()){
                return true;
            }else{
                speak("Expecting noun.");
                Tela.lblResult.setText("Expecting noun.");
                
                return false;
            }
        }else if(pronome()){
            return true;
        }else if(substantivo()){
            return true;
        }
        
        return false;
    }
    
    public boolean sintagmaVerbal(){
        
        if(verbo()){
            if(continuacaoFrase()){
                return true;
            }
        }else{
            speak("Expecting verb.");
            return false;
        }
        
        return false;
    }

    public boolean continuacaoFrase(){
        
        if(preposicao() || verbo() || determinador() || substantivo()
        || adverbio() || adjetivo() || pronome()){
            
            continuacaoFrase();
        }
        
        return true;
    }
    
    public boolean determinador(){
        
        if(currentToken.getClassification().contains("Determiner")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean substantivo(){
        
        if(currentToken.getClassification().contains("Noun")){
            
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean pronome(){
        
        if(currentToken.getClassification().contains("Pronoun")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean verbo(){
        
        if(currentToken.getClassification().contains("Verb")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean preposicao(){
        
        if(currentToken.getClassification().contains("Preposition")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean adverbio(){
        
        if(currentToken.getClassification().contains("Adverb")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
    public boolean adjetivo(){
        
        if(currentToken.getClassification().contains("Adjective")){
            nextToken();
            return true;
        }
        
        return false;
    }
    
}
