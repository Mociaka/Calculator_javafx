package calculator;

import java.util.List;
import java.util.Stack;


public class AdvancedMath {

    public static long expresion(String s){
       return AdvancedMath.cul(Lexer.upGrateTokenListOnMultiply(Lexer.upGrateTokenListNegativeNumbers(Lexer.makeTokenList(s.toCharArray()))));

    }
    public static long cul(List<Token> list){
        List<Token> internalList = Lexer.cut(list);

        if (internalList !=null){

            ////////////////////////////////////////////
            for (int i = 0,nParenteses = 0; i < list.size(); i++) {
                if (list.get(i).getType()=='('){
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

    public static long add(long a, long b){
        return a + b;
    }


    public static long sub(long a, long b){
        return  a - b;
    }
    public static long mul(long a, long b){
        return a*b;
    }
    public static long div(long a, long b){
        return a/b;
    }
    public static long pow(long a, long b){
        return (long) Math.pow(a,b);
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