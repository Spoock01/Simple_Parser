package Classes;


import Interface.Tela;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;


public class TokenClassification {

    public ArrayList<Tokens> run(String text){
        
        ReadFiles rf = null;
        
        ArrayList<Tokens> tokens = new ArrayList<>();
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        

        Annotation document = new Annotation(text.toUpperCase());

        Tela._pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String word = token.get(CoreAnnotations.TextAnnotation.class);
  
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                
                if(new ValidWord(word.toLowerCase()).run()){
                    tokens.add(new Tokens(word, NLPClassification.classification(pos),
                                        NLPClassification.grammaticalFeatures(pos)));
                    
                    if(tokens.get(tokens.size()-1).getClassification().equals("Verb")){
                        
                        rf = new ReadFiles(tokens.get(tokens.size()-1), "src/Files/verbs.txt");
                        rf.openFile();
                        tokens.set(tokens.size()-1, rf.checkTokenFile());
                        
                    }else if(tokens.get(tokens.size()-1).getClassification().equals("Pronoun") ||
                             tokens.get(tokens.size()-1).getClassification().equals("Noun") ||
                             tokens.get(tokens.size()-1).getClassification().equals("Determiner")){
                        rf = new ReadFiles(tokens.get(tokens.size()-1), "src/Files/person.txt");
                        rf.openFile();
                        tokens.set(tokens.size()-1, rf.checkTokenFile());
                    }
                }else{
                    String str = "";
                    for(int i = 0; i < word.length(); i++)
                        str += word.charAt(i) + ".\n";
                    
                    new Speech().speak("Incorrect word was found. " + str);
                    return null;
                }
            }
        }
        
        return (ArrayList<Tokens>) tokens.clone();
    }

}
