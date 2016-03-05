package com.assignment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by M on 2016-03-05.
 */
public class ExpressionServiceTest {

    private static ExpressionService service = new ExpressionService();

    @Test
    public void testArithmetic() {
        int value = service.calculateValues("add(1, 2)");
        assertEquals(value,3);

        value = service.calculateValues("add(1, mult(2, 3))");
        assertEquals(value,7);

        value = service.calculateValues("mult(add(2, 2), div(9, 3))");
        assertEquals(value,12);

        value = service.calculateValues("add(1, add(2, 3))");
        assertEquals(value,6);

        value = service.calculateValues("add(1, sub(5, 3))");
        assertEquals(value,3);
    }

}
