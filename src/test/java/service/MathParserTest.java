package service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathParserTest {

    /**
     * @see MathParser#numberOfNumbers(String)
     */
    @Test
    void numberOfNumbers() {
        assertEquals(7, MathParser.numberOfNumbers("(12,01*5478 - (-2*3) -3.21 - 6/2)"));
        assertEquals(0, MathParser.numberOfNumbers(null));
        assertEquals(0, MathParser.numberOfNumbers(" +--+ "));
    }

    /**
     * @see MathParser#equationIsCorrect(String)
     */
    @Test
    void equationIsCorrect() {
        assertTrue(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertTrue(MathParser.equationIsCorrect("-(12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertTrue(MathParser.equationIsCorrect("+12,01*5478 - (-2*3) -3.21 - 6/-2"));
        assertTrue(MathParser.equationIsCorrect("(+12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertTrue(MathParser.equationIsCorrect("(12,01*5478 -+ (-2*3) -3.21 - 6/-2)"));
        assertTrue(MathParser.equationIsCorrect("(++++++++12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertTrue(MathParser.equationIsCorrect("(12,01*5478 - (-2*3)( -3.21 - 6)/-2)"));
        assertTrue(MathParser.equationIsCorrect("(12,01*5478 - 2*3( -3.21 - 6)/-2)"));

        assertFalse(MathParser.equationIsCorrect(" +--+ "));
        assertFalse(MathParser.equationIsCorrect(null));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3-) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*/3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*/3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478) - (-2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("((12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12(,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,)01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,.01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2.*/3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) -3.2.1 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) -3.21 - .6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) -3Y.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 -- (2*3) -3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) ---3.21 - 6/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - (-2*3) -3.21 - 6/0)"));
        assertFalse(MathParser.equationIsCorrect("(12,01*5478 - 2*3.( -3.21 - 6)/-2)"));
        assertFalse(MathParser.equationIsCorrect("(12,*5478 - (-2*3) -3.21 - 6/-2)"));
    }

    /**
     * @see MathParser#calculate(String)
     */
    @Test
    void calculate() {
        assertEquals("65796.56999999999",MathParser.calculate("(12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertEquals("-65796.56999999999",MathParser.calculate("-(12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertEquals("65796.56999999999", MathParser.calculate("+12,01*5478 -+ (-2*3) -3.21 - 6/-2"));
        assertEquals("65796.56999999999",MathParser.calculate("(+12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertEquals("65796.56999999999",MathParser.calculate("(12,01*5478 -+ (-2*3) -3.21 - 6/-2)"));
        assertEquals("65796.56999999999",MathParser.calculate("(++++++++12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertEquals("65818.41",MathParser.calculate("(12,01*5478 - (-2*3)( -3.21 - 6)/-2)"));
        assertEquals("65763.15",MathParser.calculate("(12,01*5478 - 2*3( -3.21 - 6)/-2)"));

        assertNull(MathParser.calculate(" +--+ "));
        assertNull(MathParser.calculate(null));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3-) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*/3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*/3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478) - (-2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("((12,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12(,01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,)01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,.01*5478 - (-2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2.*/3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3) -3.2.1 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3) -3.21 - .6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3) -3Y.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 -- (2*3) -3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3) ---3.21 - 6/-2)"));
        assertNull(MathParser.calculate("(12,01*5478 - (-2*3) -3.21 - 6/0)"));
        assertNull(MathParser.calculate("(12,01*5478 - 2*3.( -3.21 - 6)/-2)"));
        assertNull(MathParser.calculate("(12,*5478 - (-2*3) -3.21 - 6/-2)"));
    }
}