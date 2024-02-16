package calculator;

public class Lexer {

    public static int callmethods(char[] arr){
        int x  = 0;
        int i =0;
        String xOfChar ="";
        

        for (; i < arr.length ; i++) {
            if (arr[i] != '+' && arr[i] != '-' && arr[i] != '*' && arr[i] != '/') {
                xOfChar += arr[i];
            }
            else {
                if (arr[i] == '+'){
                    char[] temp = cutArray(i,arr); //for debuging
                    return AdvancedMath.add(Integer.valueOf(xOfChar),temp  );
                }
                if (arr[i] == '-'){
                    return AdvancedMath.sub(Integer.valueOf(xOfChar), cutArray(i,arr) );
                }




            }



        }
        return Integer.valueOf(xOfChar);



    }

    public static char[] cutArray(int i, char[] arr){
            int lenght = (arr.length) - ++i;
            if (lenght <=1)return new char[arr[0]];
            char[] newArr = new char[lenght] ;

            for (int j = 0; j < newArr.length; j++) {
                newArr[j] = arr[i];
                i++;
            }
            return newArr;


//        }catch (IndexOutOfBoundsException e ){
//            return new char[arr[0]];
//        }



    }

}
