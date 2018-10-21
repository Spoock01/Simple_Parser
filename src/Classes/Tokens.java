package Classes;

public class Tokens {

    private String _token;
    private String _classification;
    private String _grammaticalFeatures;

     public Tokens(String token, String classification, String grammaticalFeatures) {
        this._token = token;
        this._classification = classification;
        this._grammaticalFeatures = grammaticalFeatures;
    }
    
    public String getToken() {
        return _token;
    }

    public String getGrammaticalFeatures() {
        return _grammaticalFeatures;
    }

    public void setGrammaticalFeatures(String _grammaticalFeatures) {
        this._grammaticalFeatures = _grammaticalFeatures;
    }

    public void setToken(String token) {
        this._token = token;
    }

    public String getClassification() {
        return _classification;
    }

    public void setClassification(String classification) {
        this._classification = classification;
    }
    
    @Override
    public String toString(){
       return "Token: "  + _token  + " Classification: "  + _classification 
            + " GrammaticalFeatures: " + _grammaticalFeatures;
    }
    
}
