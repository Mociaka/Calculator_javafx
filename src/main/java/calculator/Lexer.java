package calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Lexer {

    public static int callmethods(char[] arr){



        return 0;
    }
    public static boolean isNamber(char x){
        if (x == '0' || x == '1' || x == '2' || x == '3' || x == '4' || x == '5' || x == '6' || x == '7' || x == '8' || x == '9' ){
            return true;
        }
        else return false;
    }

    public static List<Token> makeTokenList(char[] arr){
        LinkedList<Token> list = new LinkedList<>();
        String boffer = "";


        for (int i = 0; i < arr.length; i++) {


            while (i<arr.length  && isNamber(arr[i]) ){
                boffer += arr[i];
                i++;
            }

            if (!boffer.isEmpty()){
                list.add(new Token(Integer.valueOf(boffer), '#'));
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
                        list.add(new Token(0,')'));
                        break;
                    case 'π':
                        list.add(new Token(0,'π'));
                        break;
                }

            }
        }
        if (!boffer.isEmpty()){
            int value = Integer.valueOf(boffer);
            list.add(new Token(value, '#'));
        }
        return list;
    }

    public static Stack<List> convertTokenList(List<Token> list){
        Stack<List> stack = new Stack<>();
        Stack<Character> part = new Stack<>();
        List<Token> mainList =new  LinkedList<>();
        List<Token> interList =new  LinkedList<>();

        boolean inParenteses = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == '(' || inParenteses){

                while (inParenteses){
                    interList.add(list.get(i));
                }
                inParenteses = true;
                part.push('(');
            }

                mainList.add(list.get(i));
        }


        return  stack;
    }

}
