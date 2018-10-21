package Classes;

import Interface.Tela;
import java.util.ArrayList;

public class IsPhrase {

    private final ArrayList<Tokens> _tokenTable;
    private final String _phrase;
    public static boolean _wrongWord;

    public IsPhrase(String phrase) {
        _phrase = phrase;
        _tokenTable = new ArrayList<>();
        _wrongWord = false;
    }

    void generateTokens() {

        String tokens[] = _phrase.split(" ");
        
        /*
            Println para melhorar visualização de tokens no console
        */
        System.out.println("\n");
        
        for (String str : tokens) {
            
            if(!(str.isEmpty() || str.equals(" ")) && !str.equals("."))
                _tokenTable.add(new IsWord(str).run());
            
            if (str.equals(".")) {
                _tokenTable.add(new Tokens(".", "Dot", "Dot"));
            }
            
        }
    }

    void printTokens() {
        _tokenTable.forEach((t) -> {
            System.out.println(t.toString());
        });
    }

    public void run() {

        generateTokens();
        printTokens();
        if(!_wrongWord){
            new Syntax(_tokenTable).analysis();
            Tela._check = true;
        }else
            Tela._check = false;
    }
}
