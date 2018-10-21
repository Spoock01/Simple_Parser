package Classes;

public class NLPClassification {
    
    public static String classification (String abr){
        
        switch (abr) {
            case "DT":
            case "WDT":
                return "Determiner";
            case "IN":
                return "Preposition";
            case "JJ":
            case "JJR":
            case "JJS":
                return "Adjective";
            case "NN":
            case "NNS":
            case "NNP":
            case "NNPS":
                return "Noun";
            case "PRP":
            case "WP":
            case "WP$":
                return "Pronoun";
            case "RB":
            case "RBR":
            case "RBS":
            case "WRB":
                return "Adverb";
            case "TO":
                return "Preposition";
            case "UH":
                return "Interjection";
            case "VB":
            case "VBD":
            case "VBG":
            case "VBN":
            case "VBP":
            case "VBZ":
                return "Verb";
            default:
                return "Error.";
        }
    }
    
    public static String grammaticalFeatures(String abr){
      
        switch (abr) {
            case "NN":
            case "NNP":
                return "Singular";
            case "NNS":
            case "NNPS":
                return "Plural";
            case "VBP":
                return "First Second Singular";      
            case "VBZ":
                return "Third Singular";
            default:
                break;
        }
        return "None";
    }
    
    
}
