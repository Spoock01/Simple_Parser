package Classes;

import Interface.Tela;
import java.util.ArrayList;

public class NewIsPhrase {

    private ArrayList<Tokens> _tokenTable;
    private final String _phrase;
    public static boolean _wrongWord;

    public NewIsPhrase(String phrase) {
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
        String message = "";
        
        for (String str : tokens) {
            if(!str.equals("."))
                message += str + " ";
        }
        
        
        NewIsWord niw = new NewIsWord();
        _tokenTable = niw.run(message);
        _tokenTable.add(new Tokens(".", "Dot", "Dot"));
    }

    void printTokens() {
        System.out.println("\n\n\n ++++++++++++Printing Tokens+++++++++++++");
        _tokenTable.forEach((t) -> {
            System.out.println(t.toString());
        });
        System.out.println("\n\n\n\n");
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
