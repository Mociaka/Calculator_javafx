package calculator;

import calculator.exceptions.DivByZeroException;
import calculator.exceptions.VolumeOfParenthesesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.zip.DataFormatException;


public class AdvancedMathTest {

    @Test
    void expresion() throws VolumeOfParenthesesException, DivByZeroException {
        String s = "2(10(100*3))+(2*2)"; ////6004
        double n = AdvancedMath.expresion(s);
        Assertions.assertEquals(n,6004);
    }
}
