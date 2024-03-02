package calculator;

import calculator.exceptions.VolumeOfParenthesesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

class LexerTests {
    @Test
     void makeTokenList(){
        String s = "2(10(100*3))+(2*2)";
        List<Token> myListTokens  = new LinkedList<>();
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(10, '#'));
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(100, '#'));
        myListTokens.add(new Token(0, '×'));
        myListTokens.add(new Token(3, '#'));
        myListTokens.add(new Token(0, ')'));
        myListTokens.add(new Token(0, ')'));
        myListTokens.add(new Token(0, '+'));
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, '×'));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, ')'));

        List<Token> list  = Lexer.makeTokenList(s.toCharArray());
//       System.out.println(list);
//       System.out.println(myListTokens);
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getType(),myListTokens.get(i).getType());
           Assertions.assertEquals(list.get(i).getValue(),myListTokens.get(i).getValue());
        }

     }
    @Test
    void makeTokenList2() throws VolumeOfParenthesesException {
        String s = "(2+2) (2+2)";
        List<Token> myListTokens  = new LinkedList<>();
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, '+'));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, ')'));
        myListTokens.add(new Token(0, '×'));
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, '+'));
        myListTokens.add(new Token(2, '#'));
        myListTokens.add(new Token(0, ')'));

        List<Token> list  = Lexer.upGrateTokenListOnMultiply(Lexer.upGrateTokenListNegativeNumbers(Lexer.makeTokenList(s.toCharArray())));

       System.out.println(list);
       System.out.println(myListTokens + "Expected");
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getType(),myListTokens.get(i).getType());
            Assertions.assertEquals(list.get(i).getValue(),myListTokens.get(i).getValue());
        }

    }
   @Test
   void makeTokenListOnAllSign(){
      String s = "+-×÷*/()^π";
      List<Token> myListTokens  = new LinkedList<>();
      List<Token> list  = Lexer.makeTokenList(s.toCharArray());

      myListTokens.add(new Token(0,'+'));
      myListTokens.add(new Token(0,'-'));
      myListTokens.add(new Token(0,'×'));
      myListTokens.add(new Token(0,'÷'));
      myListTokens.add(new Token(0,'×'));
      myListTokens.add(new Token(0,'÷'));

      myListTokens.add(new Token(0,'('));
      myListTokens.add(new Token(0,')'));
      myListTokens.add(new Token(0,'^'));
      myListTokens.add(new Token(0,'π'));


      for (int i = 0; i < list.size(); i++) {
         Assertions.assertEquals(list.get(i).getType(),myListTokens.get(i).getType());
         Assertions.assertEquals(list.get(i).getValue(),myListTokens.get(i).getValue());
      }
   }

      @Test
     void divedByZero(){
       Assertions.assertThrows(ArithmeticException.class, () -> Math.floorDiv(10,0));
     }
     @Test
      void upGrateTokenListNegativeNumbers() throws VolumeOfParenthesesException {
        String s ="-2(-1)+-1";//в началі, в дужках, після знака
         List<Token> list = Lexer.upGrateTokenListNegativeNumbers(Lexer.makeTokenList(s.toCharArray()));
        List<Token> myListTokens  = new LinkedList<>();
        myListTokens.add(new Token(-2,'#'));
        myListTokens.add(new Token(0,'('));
        myListTokens.add(new Token(-1,'#'));
        myListTokens.add(new Token(0,')'));
        myListTokens.add(new Token(0,'+'));
        myListTokens.add(new Token(-1,'#'));


        for (int i = 0; i < list.size(); i++) {
           Assertions.assertEquals(list.get(i).getValue(),myListTokens.get(i).getValue());
           Assertions.assertEquals(list.get(i).getType(),myListTokens.get(i).getType());
        }
     }
    @Test
    void cut(){
        String s = "2(10(100*3))+(2*2)";
        List<Token> myListTokens  = new LinkedList<>();


        myListTokens.add(new Token(10, '#'));
        myListTokens.add(new Token(0, '('));
        myListTokens.add(new Token(100, '#'));
        myListTokens.add(new Token(0, '×'));
        myListTokens.add(new Token(3, '#'));
        myListTokens.add(new Token(0, ')'));



        List<Token> list  = Lexer.cut(Lexer.makeTokenList(s.toCharArray()));
       System.out.println(list);
       System.out.println(myListTokens);
        for (int i = 0; i < list.size(); i++) {
            Assertions.assertEquals(list.get(i).getType(),myListTokens.get(i).getType());
            Assertions.assertEquals(list.get(i).getValue(),myListTokens.get(i).getValue());
        }

    }

}
