package calculator;

public class AdvancedMath {
    public static int add(int a, char[] arr){
        return a + Lexer.callmethods(arr);
    }


    public static int sub(int a, char[] arr){
        return  a - Lexer.callmethods(arr);
    }
    public static long mul(long a, char[] arr){
        return 0;
    }
    public static long div(long a, char[] arr){
        return 0;
    }
    public static long pow(long a, char[] arr){
        return 0;
    }
    public static long root(long a, char[] arr){
        return 0;
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