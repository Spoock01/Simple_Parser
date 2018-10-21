package Classes;

import Interface.Tela;
import java.util.ArrayList;

public class IsPhrase {

    private ArrayList<Tokens> _tokenTable;
    private final String _phrase;
    public static boolean _wrongWord;

    public IsPhrase(String phrase) {
        _phrase = phrase;
        _tokenTable = new ArrayList<>();
        _wrongWord = false;
    }

    void generateTokens() {

        String tokens[] = _phrase.split(" ");

        String message = "";

        for (String str : tokens) {
            if (!str.equals(".")) {
                message += str + " ";
            }
        }

        TokenClassification niw = new TokenClassification();
        _tokenTable = niw.run(message);

        if (_tokenTable != null) {
            _tokenTable.add(new Tokens(".", "Dot", "Dot"));
        }
    }

    void printTokens() {

        if (_tokenTable != null) {
            System.out.println("\n\n====Printing Tokens====");
            _tokenTable.forEach((t) -> {
                System.out.println(t.toString());
            });
            System.out.println("==============================\n");
        }

    }

    public void run() {

        generateTokens();
        printTokens();
        if (!_wrongWord && _tokenTable != null) {
            new Syntax(_tokenTable).analysis();
            Tela._check = true;
        } else {
            Tela._check = false;
        }
    }
}
