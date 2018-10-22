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
    
    public boolean checkAgreement(){
        
        for(int i = 1; i < symbolTable.size()-1; i++){
            
            if(symbolTable.get(i-1).getClassification().equalsIgnoreCase("Pronoun") &&
               symbolTable.get(i).getClassification().equalsIgnoreCase("Verb")){
                
                if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("First Person Singular") &&
                   !symbolTable.get(i).getGrammaticalFeatures().contains("First Person Singular")){
                    
                    System.out.println("Erro de concordância First Person Pronoun Verb");
                    return false;
                }else if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Third Person Singular") &&
                   !symbolTable.get(i).getGrammaticalFeatures().contains("Third Person Singular")){
                    
                    System.out.println("Erro de concordância Third Person Singular Pronoun Verb");
                    return false;
                }else if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Third Person Plural") &&
                   !symbolTable.get(i).getGrammaticalFeatures().contains("Third Person Plural")){
                    
                    System.out.println("Erro de concordância Third Person Plural Pronoun Verb");
                    return false;
                }else if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Second Person") &&
                   !symbolTable.get(i).getGrammaticalFeatures().contains("Second Person")){
                    
                    System.out.println("Erro de concordância Second Person Pronoun Verb");
                    return false;
                }
                
            }else if(symbolTable.get(i-1).getClassification().equalsIgnoreCase("Special Determiner") &&
                     symbolTable.get(i).getClassification().equalsIgnoreCase("Noun")){
                
                if(!(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase(symbolTable.get(i).getGrammaticalFeatures()))){
                    
                    System.out.println("Erro de concordância Special Determiner Noun");
                    return false;
                }
                
            }else if(symbolTable.get(i-1).getClassification().equalsIgnoreCase("Noun") &&
                     symbolTable.get(i).getClassification().equalsIgnoreCase("Verb")){
                
                if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Singular") &&
                   !(symbolTable.get(i).getGrammaticalFeatures().contains("Singular"))){
                    
                    System.out.println("Erro de concordância Singular Noun Verb");
                    return false;
                }else if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Plural") &&
                         !(symbolTable.get(i).getGrammaticalFeatures().contains("Plural"))){
                    System.out.println("Erro de concordância Plural Noun Verb");
                    return false;
                }
            }else if(symbolTable.get(i-1).getClassification().equalsIgnoreCase("Number") &&
                     symbolTable.get(i).getClassification().equalsIgnoreCase("Noun")){
                
                if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Singular") &&
                   !(symbolTable.get(i).getGrammaticalFeatures().contains("Singular"))){
                    
                    System.out.println("Erro de concordância Singular Number Noun");
                    return false;
                }else if(symbolTable.get(i-1).getGrammaticalFeatures().equalsIgnoreCase("Plural") &&
                         !(symbolTable.get(i).getGrammaticalFeatures().contains("Plural"))){
                    System.out.println("Erro de concordância Plural Number Noun");
                    return false;
                }
            }
            
        }
        return true;
    }
    
    public void analysis(){
        
        if(checkRepeatedWords()){
            if(sintagmaNominal()){
                if(sintagmaVerbal()){
                    if(currentToken.getClassification().equals("Dot")){
                        if(checkAgreement()){
                            new Speech().speak("OK");
                        }else{
                            speak("Agreement Error.");
                        }
                            
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
        
        if(preposicao() || verbo() || substantivo()
        || adverbio() || adjetivo() || pronome()){
            
           return continuacaoFrase();
        }else if (determinador()){
            if(substantivo()){
               return continuacaoFrase();
            }else if(adjetivo()){
                return continuacaoFrase();
            }else{
                speak("Expecting Noun or Adjective.");
                return false;
            }
        }else if(numero()){
            if(substantivo()){
                return continuacaoFrase();
            }else{
                speak("Expecting Noun");
                return false;
            }
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
    
    public boolean numero(){
        
        if(currentToken.getClassification().contains("Number")){
            nextToken();
            return true;
        }
        return false;
    }
    
}
