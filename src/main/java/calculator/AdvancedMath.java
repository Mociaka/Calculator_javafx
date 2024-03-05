package calculator;


import calculator.exceptions.VolumeOfParenthesesException;

import java.util.List;


public class AdvancedMath {

    public static double expresion(String s) throws VolumeOfParenthesesException {
        return AdvancedMath.cul(
                Lexer.upGrateTokenListOnMultiply(
                        Lexer.rePlacePiOnValue(
                                Lexer.upGrateTokenListOnPi(
                                    Lexer.upGrateTokenListNegativeNumbers(
                                           Lexer.makeTokenList(s.toCharArray()))))));

    }
    public static String expresionToString(String s) {
        double value = 0;
        try {
            value = expresion(s);
        } catch (VolumeOfParenthesesException e) {
            return "Перевірьте кількість дужок";
        }
        catch (Exception e) {
            return "Exception";
        }


        return String.valueOf(value);
    }
    public static double cul(List<Token> list){


        if (Lexer.cut(list) !=null){

            for (int i = 0,nParenteses = 0; i < list.size(); i++) {
                if (list.get(i).getType()=='('){
                    List<Token> internalList =Lexer.cut(list) ;
                    nParenteses++;
                    while (nParenteses!=0){
                        list.remove(i);

                        if (list.get(i).getType()=='(')nParenteses++;
                        if (list.get(i).getType()==')')nParenteses--;
                    }
                    list.set(i,new Token(cul(internalList),'#'));
                }
            }

        }


        for (int i = 0; i < list.size()-2; i++) {//пошук степення
            if (list.get(i + 1).getType()=='^'){
                list.get(i+2).setValue(pow(list.get(i).getValue(),list.get(i+2).getValue()));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        for (int i = 0; i < list.size()-2; i++) {//пошук множення
            if (list.get(i + 1).getType()=='×'){
                list.get(i+2).setValue(mul(list.get(i).getValue(),list.get(i+2).getValue()));
                list.remove(i);
                list.remove(i);
                i--;
            }
            if (list.get(i + 1).getType()=='÷'){//пошук ділення

                list.get(i+2).setValue(div(list.get(i).getValue(),list.get(i+2).getValue()));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        for (int i = 0; i < list.size()-2; i++) {//пошук додавання
            if (list.get(i + 1).getType()=='+'){
                list.get(i+2).setValue(add(list.get(i).getValue(),list.get(i+2).getValue()));
                list.remove(i);
                list.remove(i);
                i--;
            }
            if (list.get(i + 1).getType()=='-'){
                list.get(i+2).setValue(sub(list.get(i).getValue(),list.get(i+2).getValue()));
                list.remove(i);
                list.remove(i);
                i--;
            }
        }
        return list.get(0).getValue();
    }

    public static double add(double a, double b){
        return a + b;
    }
    public static double sub(double a, double b){
        return  a - b;
    }
    public static double mul(double a, double b){
        return a * b;
    }
    public static double div(double a, double b){
        return a / b;
    }
    public static double pow(double a, double b){
        return Math.pow(a,b);
    }

}
