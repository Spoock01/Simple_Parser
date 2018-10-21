package Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFiles {
    
    private Tokens tokens;
    private String path;
    private ArrayList<Tokens> fileList;
    
    public ReadFiles(Tokens tokens, String path){
        this.tokens = tokens;
        this.path = path;
        fileList = new ArrayList<>();
    }
    
    public void openFile(){
        String line;
        File arquivo = new File(path);
        String splitLine[];
        
        
        try{
            
            FileReader lerArquivo = new FileReader(arquivo);
            BufferedReader bufferArquivo = new BufferedReader(lerArquivo);
            
            while(bufferArquivo.ready()){
                
                line = bufferArquivo.readLine();
                splitLine = line.split("/");
                fileList.add(new Tokens(splitLine[0], splitLine[1], splitLine[2]));
                                
            }
            
            bufferArquivo.close();
            lerArquivo.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
              
    }
    
    public Tokens checkTokenFile(){
        
        for(Tokens a: fileList){
            if(a.getToken().equalsIgnoreCase(tokens.getToken())){
                tokens.setClassification(a.getClassification());
                tokens.setGrammaticalFeatures(a.getGrammaticalFeatures());
                break;
            }
            
        }        
        return tokens;
    }
    
    
}
