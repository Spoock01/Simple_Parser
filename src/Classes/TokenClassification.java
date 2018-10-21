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
        
        ArrayList<Tokens> tokens = new ArrayList<>();
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        

        Annotation document = new Annotation(text.toUpperCase());

        Tela._pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String word = token.get(CoreAnnotations.TextAnnotation.class);
  
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                
                if(new ValidWord(word.toLowerCase()).run())
                    tokens.add(new Tokens(word, NLPClassification.classification(pos),
                                        NLPClassification.grammaticalFeatures(pos)));
                else{
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
