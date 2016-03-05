package com.assignment;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Created by M on 2016-03-05.
 */
public class test {

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

    public static void main(String [ ] args)
    {


        int value = service.calculateValues("add(1, mult(2, 3))");

        String m = "";








        /*
        Expression a2 = new Expression ("mult(1,2)");
        Expression a3 = new Expression ("sub(1,2)");
        Expression a4 = new Expression ("div(1,2)");
        Expression a5 = new Expression ("let(1,2,b)");



        Expression e1 = new Expression ("add(1,,2)");
        Expression e2 = new Expression ("mult(1,2) ");
        Expression e3 = new Expression ("subu(1,2)");
        Expression e4 = new Expression ("divl(1,2)");
        Expression e5 = new Expression ("let(1,2,b,9)");
        */

    }
}
