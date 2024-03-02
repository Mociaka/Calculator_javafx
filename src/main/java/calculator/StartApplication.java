package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;



public class StartApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("calculator-tiny-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 325, 610);
        stage.setTitle("Калькулятор");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        //launch();




        ////////////////////DEBUGING///////////////
//        String s = "2+2(10(3*4)+(-10))";////222  |/
        String s = "2(2+2-(100-9))+1 +1";
//        String s = "2(10(100*3))+(2*2)"; ////6004 |/
//        String s = "2+2*5^3";           ////252 |/
//        String s = "2+2*5^3^2";
//        String s = "2+ 2 (3*4)";    ////26
//        String s = "(2+2) (2+2)";
//        String s = "2π + )π + π10 + π(";
//        String s = "5/0";

//        System.out.println(Lexer.convertTokenListToStackList(Lexer.makeTokenList(s.toCharArray())));
//        System.out.println(Lexer.upGrateTokenListOnMultiply(Lexer.makeTokenList(s.toCharArray())));
//        System.out.println(AdvancedMath.expresion(s));
//        var v = );
//        System.out.println(v);
          //System.out.println(Lexer.convertTokenListToStackList2(Lexer.upGrateTokenListOnMultiply(Lexer.makeTokenList(s.toCharArray()))));
//        System.out.println(v);
//        System.out.println(AdvancedMath.expresion(s));
//        System.out.println(Lexer.upGrateTokenListOnPi(Lexer.upGrateTokenListOnMultiply(Lexer.makeTokenList(s.toCharArray())))) ;
//        System.out.println("/////////////////");
        System.out.println(AdvancedMath.expresionToString(s));
    }

}