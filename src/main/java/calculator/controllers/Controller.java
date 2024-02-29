package calculator.controllers;
import calculator.AdvancedMath;
import calculator.Lexer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private Button backSpace;

    @FXML
    private Button c;

    @FXML
    private Button close_parentheses;

    @FXML
    private Button comma;

    @FXML
    private Button divide;

    @FXML
    private Button eight;

    @FXML
    private Button equal;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private TextArea input = new TextArea();;

    @FXML
    private Button multiply;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button open_parentheses;

    @FXML
    private TextArea output;

    @FXML
    private Button plus;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button sub;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;

    @FXML
    void initialize() {

        zero.setOnAction(event -> {
            setInput("0");
        });
        one.setOnAction(event -> {
            setInput("1");
        });
        two.setOnAction(event -> {
            setInput("2");
        });
        three.setOnAction(event -> {
            setInput("3");
        });
        four.setOnAction(event -> {
            setInput("4");
        });
        five.setOnAction(event -> {
            setInput("5");
        });
        six.setOnAction(event -> {
            setInput("6");
        });
        seven.setOnAction(event -> {
            setInput("7");
        });
        eight.setOnAction(event -> {
            setInput("8");
        });
        nine.setOnAction(event -> {
            setInput("9");
        });

        plus.setOnAction(event -> {
            setInput("+");
        });
        sub.setOnAction(event -> {
            setInput("-");
        });
        multiply.setOnAction(event -> {
            setInput("×");
        });
        divide.setOnAction(event -> {
            setInput("÷");
        });
        c.setOnAction(event -> {
            input.setText(null);
        });
        open_parentheses.setOnAction(event -> {
            setInput("(");
        });
        close_parentheses.setOnAction(event -> {
            setInput(")");
        });
        backSpace.setOnAction(event -> {
            String s = input.getText();
            input.setText(s.substring(0,s.length()-1));
        });
        comma.setOnAction(event -> {
            setInput(",");
        });


        equal.setOnAction(event -> {


            output.setText(String.valueOf( AdvancedMath.expresion(input.getText())));
            ;
        });


    }
    public void setInput(String s){
        if (input.getText() == null){
            input.setText(s);
        }
        else {
            input.setText(input.getText() + s);
        }
    }
}
