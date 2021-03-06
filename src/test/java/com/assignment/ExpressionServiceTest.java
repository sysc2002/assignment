package com.assignment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


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

        value = service.calculateValues("sub(5, 2)");
        assertEquals(value,3);

        value = service.calculateValues("mult(5, add(1,1))");
        assertEquals(value,10);

        value = service.calculateValues("div(mult(2,7), add(1,1))");
        assertEquals(value,7);
    }

    @Test
    public void testLet(){
        int value = service.calculateValues("let(a, 5, add(a, a))");
        assertEquals(value,10);

        value = service.calculateValues("let(a, 5, let(b, mult(a, 10), add(b, a)))");
        assertEquals(value,55);

        value = service.calculateValues("let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
        assertEquals(value,40);

        value = service.calculateValues("let(B, mult(5,10), add(B, B))");
        assertEquals(value,100);

        value = service.calculateValues("let(acb, mult(5,10), add(acb, acb))");
        assertEquals(value,100);
    }

    @Test
    public void testInvalidSyntax(){
        Integer value = service.calculateValues("let(a, 5, add(a, a)) 10");
        assertNull(value);

        value = service.calculateValues("0");
        assertNull(value);

        value = service.calculateValues("*");
        assertNull(value);

        value = service.calculateValues(null);
        assertNull(value);

        value = service.calculateValues("");
        assertNull(value);

        value = service.calculateValues(" ");
        assertNull(value);

        value = service.calculateValues("add32");
        assertNull(value);

        value = service.calculateValues("mult");
        assertNull(value);

        value = service.calculateValues("mult(5,b)");
        assertNull(value);

        value = service.calculateValues("add(1,1");
        assertNull(value);

        value = service.calculateValues("sub5-6");
        assertNull(value);
    }

    @Test
    public void testInvalidLet(){
        Integer value = service.calculateValues("let(a, 5, add(a, a),12)");
        assertNull(value);

        value = service.calculateValues("let(a, 5, add(a, a),");
        assertNull(value);

        value = service.calculateValues("let(a, 5, add(a, a)");
        assertNull(value);

        value = service.calculateValues("let(let(a,5,add(5,5), let(b, 10, add(b, b)), let(b, 20, add(a, b)))");
        assertNull(value);

        value = service.calculateValues("let(B, mult(5,10), add(B, A))");
        assertNull(value);

        value = service.calculateValues("let(-, mult(5,10), add(-, -))");
        assertNull(value);

        value = service.calculateValues("null");
        assertNull(value);
    }
}
