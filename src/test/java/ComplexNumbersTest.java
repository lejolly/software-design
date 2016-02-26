import org.junit.Test;
import tools.ComplexNumber;

import static junit.framework.TestCase.assertEquals;

public class ComplexNumbersTest {

    @Test
    public void addition() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.add(b), new ComplexNumber(4, 6));
    }

    @Test
    public void subtraction() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.subtract(b), new ComplexNumber(-2, -2));
    }

    @Test
    public void multiplication() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.multiply(b), new ComplexNumber(-5, 10));
    }

    @Test
    public void division() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.divide(b), new ComplexNumber(0.44, 0.08));
    }

    @Test
    public void absolute() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.getAbsolute(), Math.sqrt(5));
        assertEquals(b.getAbsolute(), (double) 5);
    }

    @Test
    public void argument() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.getArgument(), Math.atan(2));
        assertEquals(b.getArgument(), Math.atan((double) 4/3));
    }

    @Test
    public void square() {
        ComplexNumber a = new ComplexNumber(1, 2);
        ComplexNumber b = new ComplexNumber(3, 4);
        assertEquals(a.getSquare(), new ComplexNumber(-3, 4));
        assertEquals(b.getSquare(), new ComplexNumber(-7, 24));
    }

}
