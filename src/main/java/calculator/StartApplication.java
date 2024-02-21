package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.script.*;

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
//        launch();
        String s = "2+2(10(3*4)+(-10))";
        //System.out.println(Lexer.convertTokenList(Lexer.makeTokenList(s.toCharArray())));
        System.out.println(Lexer.upGrateTokenListOnMultiply(Lexer.makeTokenList(s.toCharArray())));


    }

}