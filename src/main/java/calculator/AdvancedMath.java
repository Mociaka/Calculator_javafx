package calculator;

import calculator.exceptions.DivByZeroException;
import calculator.exceptions.VolumeOfParenthesesException;

import java.util.List;


public class AdvancedMath {

    public static double expresion(String s) throws VolumeOfParenthesesException, DivByZeroException {
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
        }catch (DivByZeroException e){
            return "Ділення на 0";
        }
//        catch (Exception e) {
//            return "Exception";
//        }


        return String.valueOf(value);
    }
    public static double cul(List<Token> list) throws DivByZeroException{


        if (Lexer.cut(list) !=null){

            ////////////////////////////////////////////
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

            ////////////////////////////////////////////
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
            if (list.get(i + 1).getType()=='÷'){
//                if (list.get(i).getValue() == 0.0 || list.get(i+1).getValue() == 0){
//                    throw new DivByZeroException();
//                }
                list.get(i+2).setValue(div(list.get(i).getValue(),list.get(i+2).getValue()));//кинуть ошика ділення на 0
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

//перетворити input но 2 прості компоненти
//рекурсія яка все це визиває

//лексер який все це виконує | lexer принімає перше чилос до знака за яким знаком визиває метод з продвинутиої математики
//і предає в нього перше число і остальний масів чаров і в середині методу по нужді визивається lexer
/*1)очистка коду від літер
розуміння чисел з плавоючою точкою.
* якщо дужка відкрита то вона діє до самого кінця 12*(11+3+7 => mul(12, add(11,add(3+7)))
* */
//переда все через масів(array, arraylist...) або в об'єкті lexer


//рекурсія на додавання, віднімання, множення(пусте множення на π), ділення(!на 0), степінь, корінь, дужки