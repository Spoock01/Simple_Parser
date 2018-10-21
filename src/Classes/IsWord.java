package Classes;

import Interface.Tela;
import JsonObject.ApiData;
import JsonObject.GrammaticalFeature;
import JsonObject.LexicalEntry;
import JsonObject.Result;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;

public class IsWord {

    private final String _word;
    private final String _language = "en";
    private URL _url;
    private BufferedReader _reader;
    private StringBuilder _stringBuilder;

    public IsWord(String _word) {
        this._word = _word;
    }

    public Tokens run() {

        if (_word.isEmpty()) {
            return null;
        }

        String str = "https://od-api.oxforddictionaries.com:443/api/v1/inflections/" + _language + "/" + _word.toLowerCase();
        String line, classification = "", grammaticalFeatures = "";;
        Gson parser = new Gson();
        ApiData dataFromApi;

        try {
            _url = new URL(str);
            HttpsURLConnection urlConnection = (HttpsURLConnection) _url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", "b290c54c");
            urlConnection.setRequestProperty("app_key", "d07d430d82bf2220c62f7f11e081f610");

            // read the output from the server
            _reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            _stringBuilder = new StringBuilder();

            
            while ((line = _reader.readLine()) != null){ 
//                System.out.println(line);
                _stringBuilder.append(line).append("\n");
            }
            
            /*
                Convertendo o Json da API para classe ApiData
            */
            dataFromApi = (ApiData) parser.fromJson(_stringBuilder.toString().trim(), ApiData.class);
            
            List<Result> l = dataFromApi.getResults();
            
            
            
            /*
                Pegando todas as caracteristicas lexicas e gramáticas
                para classificar o token.
            
                Caracteristicas lexicas = Pronome, Verbo, Substantivo, etc
                Caracteristicas Gramaticas = Singular, Plural, Masculino, etc
            */
            
            for(int i = 0; i < l.size(); i++){
                List<LexicalEntry> lex = l.get(i).getLexicalEntries();
                
                for(LexicalEntry le : lex){
                    
                    List<GrammaticalFeature> grammar = le.getGrammaticalFeatures();
                    
                    if(grammar != null)
                        for(int g = 0; g < grammar.size(); g++ )
                            if(!grammaticalFeatures.contains(grammar.get(g).getText()))
                                grammaticalFeatures += "[" + grammar.get(g).getText() + "]";
                    
                    if(!classification.contains(le.getLexicalCategory()))
                        classification = classification + "{" + le.getLexicalCategory() + "}";
                }
                
            }
            //System.out.println(_word + " --> Classification: " + classification + " === GrammarFeatures: " + grammaticalFeatures);
 
        } catch (MalformedURLException ex) {
            System.out.println("Malformed");
        } catch (IOException ex) {
            System.out.println("IOException ex");
        }
        return new Tokens(_word, classification, grammaticalFeatures);
    }

}
