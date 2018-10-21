package Classes;

public class NLPClassification {
    
    public static String classification (String abr){
        
        if(abr.equals("DT") || abr.equals("WDT"))
            return "Determiner";
        else if(abr.equals("IN"))
            return "Preposition";
        else if(abr.equals("JJ") || abr.equals("JJR") || abr.equals("JJS"))
            return "Adjective";
        else if(abr.equals("NN") || abr.equals("NNS") || abr.equals("NNP") ||  abr.equals("NNP  S"))
            return "Noun";
        else if(abr.equals("PRP") || abr.equals("WP") || abr.equals("WP$"))
            return "Pronoun";
        else if(abr.equals("RB") || abr.equals("RBR") || abr.equals("RBS") || abr.equals("WRB"))
            return "Adverb";
        else if(abr.equals("TO"))
            return "Preposition";
        else if(abr.equals("UH"))
            return "Interjection";
        else if(abr.equals("VB") || abr.equals("VBD") || abr.equals("VBG")
             || abr.equals("VBN") || abr.equals("VBP") || abr.equals("VBZ"))
            return "Verb";
        else
            return "Error.";
    }
    
    public static String grammaticalFeatures(String abr){
      
        if(abr.equals("NN") || abr.equals("NNP")){
            return "Singular";
        }else if(abr.equals("NNS") || abr.equals("NNPS")){
            return "Plural";
        }else if(abr.equals("VBP")){
            return "First Second Singular";
        }else if(abr.equals("VBZ")){
            return "Third Singular";
        }      
        return "None";
    }
    
    
}
