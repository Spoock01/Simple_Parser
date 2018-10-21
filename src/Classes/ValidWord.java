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
import javax.net.ssl.HttpsURLConnection;

public class ValidWord {

    private final String _word;
    private final String _language = "en";
    private URL _url;
    private BufferedReader _reader;
    private StringBuilder _stringBuilder;

    public ValidWord(String _word) {
        this._word = _word;
    }

    public boolean run() {

        if (!_word.isEmpty()){
        
            String str = "https://od-api.oxforddictionaries.com:443/api/v1/inflections/" + _language + "/" + _word.toLowerCase();

            try {
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println("Conectando a api\n\n\n");
                _url = new URL(str);
                HttpsURLConnection urlConnection = (HttpsURLConnection) _url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("app_id", "b290c54c");
                urlConnection.setRequestProperty("app_key", "d07d430d82bf2220c62f7f11e081f610");
                
                _reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            } catch (MalformedURLException ex) {
                System.out.println("Malformed");
            } catch (IOException ex) {
                return false;
            }
        }
        return true;

    }
}
