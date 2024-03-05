package calculator;

import calculator.exceptions.VolumeOfParenthesesException;

import java.util.LinkedList;
import java.util.List;


public class Lexer {

    public static boolean isNamber(char x){
        if (x == '0' || x == '1' || x == '2' || x == '3' || x == '4' || x == '5' || x == '6' || x == '7' || x == '8' || x == '9' || x == '.' || x == ',' ){
            return true;
        }
        else return false;
    }

    public static List<Token> makeTokenList(char[] arr) {

        LinkedList<Token> list = new LinkedList<>();
        String boffer = "";


        for (int i = 0; i < arr.length; i++) {


            while (i<arr.length  && isNamber(arr[i]) ){
                boffer += arr[i];
                i++;
            }

            if (!boffer.isEmpty()){
                list.add(new Token(Double.valueOf(boffer), '#'));
                boffer = "";}

            if (i<arr.length  &&  !isNamber(arr[i])){


                switch (arr[i]){
                    case '+':
                        list.add(new Token(0,'+'));
                        break;
                    case '-':
                        list.add(new Token(0,'-'));
                        break;
                    case '×':
                        list.add(new Token(0,'×'));
                        break;
                    case '÷':
                        list.add(new Token(0,'÷'));
                        break;
                    case '*':
                        list.add(new Token(0,'×'));
                        break;
                    case '/':
                        list.add(new Token(0,'÷'));
                        break;
                    case '(':
                        list.add(new Token(0,'('));
                        break;
                    case ')':
                        list.add(new Token(0,')'));
                        break;
                    case '^':
                        list.add(new Token(0,'^'));
                        break;
                    case 'π':
                        list.add(new Token(0,'π'));
                        break;
                }

            }
        }
        if (!boffer.isEmpty()){
            double value = Double.valueOf(boffer);
            list.add(new Token(value, '#'));
        }
        return list;
    }

    public static List<Token> upGrateTokenListOnMultiply(List<Token> list) {
        List<Token> newTokenList = new LinkedList<>();

        for (int i = 0; i < list.size() ; i++) {
            try {
                newTokenList.add(list.get(i));
                if ((list.get(i).getType() == '#' || list.get(i).getType() == ')') && (list.get(i+1).getType() =='(' || list.get(i+1).getType() =='π') ){

                    newTokenList.add(new Token(0,'×'));
                }


            }catch (IndexOutOfBoundsException e){

            }

        }
        return newTokenList;
    }


    public static List<Token> cut(List<Token> list){

        List<Token> internalList = new LinkedList<>();
        int nParentheses = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == '('){
                nParentheses++;
                i++;
                while (nParentheses !=0){
                    if (list.get(i).getType() ==')'){
                        nParentheses--;
                    }
                    if (list.get(i).getType() =='('){
                        nParentheses++;
                    }
                    if (nParentheses != 0) {
                        internalList.add(list.get(i));

                    }
                    i++;
                }

                return internalList;
            }

        }
        return null;
    }
    public static boolean parenthesesAreEqual(List<Token> list){
        int valueOfOpen = 0;
        int valueOfClose = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == '(')valueOfOpen++;
            if (list.get(i).getType() == ')')valueOfClose++;
        }
        return valueOfOpen==valueOfClose;
    }

    public static List<Token> upGrateTokenListNegativeNumbers(List<Token> list) throws VolumeOfParenthesesException {
        if (!parenthesesAreEqual(list)){
            throw new VolumeOfParenthesesException();
        }
        int i = 0;
        if (list.size() <=1){return list;}
        if ( ( list.get(i).getType()=='-' ||  list.get(i).getType()=='+') && list.get(i+1).getType()=='#'){
            if (list.get(i).getType() =='-'){


                list.get(i+1).setValue(-list.get(i+1).getValue());
                list.remove(i);
                i=1;

            }
        }
        for (; i < list.size() - 2; i++) {
            if (list.get(i).getType() !='#' && (list.get(i+1).getType() =='-' ) && list.get(i+2).getType() =='#' ){
                list.get(i+2).setValue(-list.get(i+2).getValue() );
                list.remove(i+1);
            }
        }

        return list;
    }
    public static List<Token> upGrateTokenListOnPi(List<Token> list) {

        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i-1).getType()=='#' || list.get(i-1).getType()==')' ) && list.get(i).getType()=='π'){
                list.add(i,new Token(0,'×'));
            }
            if ((list.get(i).getType()=='#' || list.get(i).getType()=='(' ) && list.get(i-1).getType()=='π'){
                list.add(i,new Token(0,'×'));
            }
        }
        return list;
    }
    public static List<Token> rePlacePiOnValue(List<Token> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == 'π'){
                list.set(i, new Token( 3.14159265359,'#')) ;
            }
        }
        return list;
    }
}
