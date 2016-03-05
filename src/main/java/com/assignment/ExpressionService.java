package com.assignment;

import org.apache.log4j.Logger;

/**
 * Created by M on 2016-03-04.
 */
public class ExpressionService {
    final static Logger logger = Logger.getLogger(ExpressionService.class);

    public Integer calculateValues (String command) {
        try{

            Expression expression = new Expression (command);
            if(expression.getFunction().equals(Constants.LET)){
                Integer valueThree = getInteger(expression.getExpressionThree());
            }else if (expression.getFunction() != null){
                Integer valueOne = getInteger(expression.getExpressionOne());
                Integer valueTwo = getInteger(expression.getExpressionTwo());

                if(valueOne == null){
                    expression.setExpressionOne(String.valueOf(calculateValues(expression.getExpressionOne())));
                }
                if(valueTwo == null){
                    expression.setExpressionTwo(String.valueOf(calculateValues(expression.getExpressionTwo())));
                }

                return getArithValue(expression);




            }

        }catch (Exception e){
            logger.error("Error while processing calculations", e);
        }
        return null;
    }

    public Integer getInteger(String s) {
        try {
            return Integer.parseInt(s);
        } catch(NumberFormatException e) {
            logger.debug("not integer", e);
            return null;
        } catch(Exception e) {
            logger.error("Error while processing calculations", e);
            return null;
        }
    }
    public Integer getArithValue(Expression expression) {
        Integer valueOne = getInteger(expression.getExpressionOne());
        Integer valueTwo = getInteger(expression.getExpressionTwo());
        if(valueOne == null || valueTwo == null){
            return null;
        }
        if(expression.getFunction().equals(Constants.ADD)){
            return valueOne+valueTwo;
        }else if(expression.getFunction().equals(Constants.SUB)){
            return valueOne-valueTwo;
        }else if(expression.getFunction().equals(Constants.MULT)){
            return valueOne*valueTwo;
        }else if(expression.getFunction().equals(Constants.DIV)){
            return valueOne/valueTwo;
        }else{
            return null;
        }
    }

}
