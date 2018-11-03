
package gui.demo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author Omar
 */
public class FXMLDocumentController implements Initializable {
    
  
    @FXML private Label fileLabel;
    @FXML private TextArea codeTextArea;
    
    
    public void scanButtonPushed() throws IOException{
        
        //String input = new String(Files.readAllBytes(Paths.get("input.txt")));
        String input = codeTextArea.getText();
        File file = new File("Tokens.csv");
        PrintWriter pw = new PrintWriter(file);
        String word="";
        boolean reserved=false;
        String regexReserved[]={"if","then","else","end","repeat","until","read","write"};
        System.out.println("Token, TokenType");
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='{')
                i=input.indexOf('}', i)+1;
            if(input.charAt(i)=='\r')
                i++;
            
                
            switch(input.charAt(i))
            {
                //special symbols
                case '+':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '-':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '*':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '/':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case ':':
                    checker(word,reserved,regexReserved,pw);
                    word=":=";
                    i++;
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '=':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '<':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '(':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case ')':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case ';':
                    checker(word,reserved,regexReserved,pw);
                    word = Character.toString(input.charAt(i));
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case ' ':
                    checker(word,reserved,regexReserved,pw);
                    word="";
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;
                case '\n':
                    checker(word,reserved,regexReserved,pw);
                    word="";
                    //to make sure in the next word check it's not reserved by default
                    reserved=false;
                    break;    
                //any normal character or digit
                default:
                    if(isSpecialChar(word)){
                        System.out.println(word+", Special Symbol");
                        pw.write(word+", Special Symbol\n");
                        word=Character.toString(input.charAt(i));
                        break;
                    }
                    word+=Character.toString(input.charAt(i));
                    break;
            }
        }
        //For the last word
        checker(word,reserved,regexReserved,pw);
        pw.close();
        fileLabel.setText(".csv file has been generated in this path \n" + file.getAbsolutePath());
        
    }
        static boolean isSpecialChar(String word){
        return word.matches("[^A-Za-z0-9]");
    }
    static void checker(String word,boolean reserved,String regexReserved[],PrintWriter pw){
        //Special symbol with size>1 is handled here
        if(word==":="){
             System.out.println(word+", Special Symbol");
             pw.write(word+", Special Symbol\n");
             return;
        }
        if(word.length()>0){
                        for(String reservedWord:regexReserved)
                        {
                            if(word.equals(reservedWord)){
                                System.out.println(word+", Reserved Word");
                                pw.write(word+", Reserved Word\n");
                                reserved=true;
                                break;
                            }
                        }
                        if(!reserved){
                            if(word.matches("\\d+(\\.\\d+)?")){
                                System.out.println(word+", Number");
                                pw.write(word+", Number\n");
                            }
                            //Here im ignoring special symbols with size>1 for now
                            else if(isSpecialChar(word)){
                                System.out.println(word+", Special Symbol");
                                pw.write(word+", Special Symbol\n");
                            }
                            else{
                                System.out.println(word+", Identifier");
                                pw.write(word+", Identifier\n");
                            }
                        }
                    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileLabel.setText("");
        
    }    
    
}
