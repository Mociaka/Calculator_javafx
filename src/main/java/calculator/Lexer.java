package calculator;

import calculator.exceptions.VolumeOfParenthesesException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Lexer {

    public static int callmethods(char[] arr){
        return 0;
    }
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

    public static Stack<List> convertTokenListToStackList(List<Token> list){
        Stack<List> stack = new Stack<>();
        Stack<Character> part = new Stack<>();
        List<Token> mainList =new  LinkedList<>();
        List<Token> interList =new  LinkedList<>();

        //[[2#], [0+], [2#], [0(], [10#], [0(], [3#], [0×], [4#], [0)], [0+], [0(], [0-], [10#], [0)], [0)]]
        //2+2(10(3*4)+(-10))

            //2(2+2-(100-9)) + 1
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getType() == '('){
                part.push('(');

                mainList.add(new Token(0,'@'));

                while (!part.isEmpty() && i < list.size()){

//                    if (list.get(i).getType() == '('){
//                        part.push('(');
//                    }
                    if (list.get(i).getType() == ')'){
                        part.pop();
                    }
                    interList.add(list.get(i));
                    if (i==11){
                        System.out.println("Debug");
                    }

                    i++;
                }

            }

                try {
                    mainList.add(list.get(i));
                }catch (IndexOutOfBoundsException e){
                    System.out.println("Lexer.convertTokenListToStackList 115 exeption");
                }

        }
        stack.push(mainList);


//        stack.push(convertTokenListToStackList(interList));


        return  stack;
    }
    public static List<Token> upGrateTokenListOnMultiply(List<Token> list) {
        List<Token> newTokenList = new LinkedList<>();
//        2+2(10(3*4)+(-10))
        for (int i = 0; i < list.size(); i++) {
            try {
                newTokenList.add(list.get(i));
                if ((list.get(i).getType() == '#' || list.get(i).getType() == ')') && (list.get(i+1).getType() =='(' || list.get(i+1).getType() =='π') ){
//                    newTokenList.add(i,list.get(i));
                    newTokenList.add(new Token(0,'×'));
                }


            }catch (IndexOutOfBoundsException e){
                System.out.println("upGrateTokenListOnMultiply вийшов за межі масиву це норм");
            }

        }
        return newTokenList;
    }

    public static TwoListInOne slice(List<Token> list){
        List<Token> inner = new LinkedList<>();
        List<Integer> willDelete = new LinkedList<>();
        int volume = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == '('){
                volume++;
                i++;
                while (volume !=0){
                    if (list.get(i).getType() ==')'){
                        volume--;
                    }
                    if (list.get(i).getType() =='('){
                        volume++;
                    }
                    if (volume != 0) {
                        inner.add(list.get(i));
                        willDelete.add(i);
                    }
                    i++;
                }

                return new TwoListInOne(inner, willDelete);
            }

        }
        return null;
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

    public static class TwoListInOne {
        List<Token> inner;
        List<Integer> willDelete;
        public TwoListInOne(List<Token> inner, List<Integer> willDelete) {
            this.inner = inner;
            this.willDelete = willDelete;
        }

        public List<Token> getInner() {
            return inner;
        }

        public List<Integer> getWillDelete() {
            return willDelete;
        }
    }

    public static List<List> convertTokenListToStackList2(List<Token> list) {
        List<List> stack = new LinkedList<>();
        int part = 0;         //Stack<Character> part = new Stack<>();
        List<Token> mainList = new LinkedList<>();
        List<Token> interList = new LinkedList<>();

        TwoListInOne twoListInOne = slice(list);
        interList = twoListInOne.inner;
        mainList = list;
        for (int j = 0; j < twoListInOne.willDelete.size() - 1; j++) {
            mainList.remove(twoListInOne.getWillDelete().get(j));

        }
        mainList.add(twoListInOne.willDelete.get(0),new Token(0,'@'));


        System.out.println(mainList);
        return null;

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
            if (list.get(i).getType() !='#' && (list.get(i+1).getType() =='-' || list.get(i+1).getType() =='+') && list.get(i+2).getType() =='#' ){
                list.get(i+2).setValue(-list.get(i+1).getValue() );
                list.remove(i+1);
            }
        }
        return list;
    }
    public static List<Token> upGrateTokenListOnPi(List<Token> list) {
        //2π,π(,)π,π10
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
