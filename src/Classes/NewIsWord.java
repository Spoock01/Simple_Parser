package Classes;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class NewIsWord {

    public ArrayList<Tokens> run(String text){
        
        
        
        
        System.out.println("\n\n\n\nRECEBI A FRASE: " + text + "\n\n\n\n\n\n\n");
        
        ArrayList<Tokens> tokens = new ArrayList<>();
        // creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        Annotation document = new Annotation(text.toUpperCase());

        pipeline.annotate(document);

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence : sentences) {

            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                String word = token.get(CoreAnnotations.TextAnnotation.class);
  
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                
                tokens.add(new Tokens(word, NLPClassification.Classification(pos),
                                        NLPClassification.GrammaticalFeatures(pos)));

            }
        }
        
        return (ArrayList<Tokens>) tokens.clone();
    }

}
