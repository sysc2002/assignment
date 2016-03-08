package com.assignment;

import org.apache.log4j.Logger;

/**
 * Created by M on 2016-03-04.
 */
public class ExpressionService {
    final static Logger logger = Logger.getLogger(ExpressionService.class);

    public Integer calculateValues (String command) {
        try{
            command = command.replaceAll("\\s+","");
            Expression expression = new Expression (command);
            if(expression.getFunction().equals(Constants.LET)){
                logger.debug("LET function used \n");
                // check if first field is within A-Za-z
                if(expression.getExpressionOne().matches("[A-Za-z]+$")){
                    // check if second expression is an integer otherwise perform function given
                    Integer valueOne = getInteger(expression.getExpressionTwo());
                    if(valueOne == null){
                        valueOne = calculateValues(expression.getExpressionTwo());
                    }
                    logger.debug("expression '"+expression.getExpressionOne()+"' was replaced by valueOne= "+valueOne+"\n");

                    // String manipulation to convert the attributes to there values
                    command = expression.getExpressionThree().substring(0,3)+ (expression.getExpressionThree().replaceAll(expression.getExpressionOne()+",+",String.valueOf(valueOne)+",")).substring(3);
                    command = command.replaceAll(expression.getExpressionOne()+"\\)",String.valueOf(valueOne)+")");

                    return calculateValues(command);
                }


            }else if (expression.getFunction() != null){
                logger.debug(expression.getFunction()+" function used \n");

                // check if first and second expressions are an integer otherwise perform function given
                Integer valueOne = getInteger(expression.getExpressionOne());
                Integer valueTwo = getInteger(expression.getExpressionTwo());
                logger.debug("ValueOne= "+valueOne+"\n");
                logger.debug("valueTwo= "+valueTwo+"\n");


                if(valueOne == null){
                    expression.setExpressionOne(String.valueOf(calculateValues(expression.getExpressionOne())));
                }
                if(valueTwo == null){
                    expression.setExpressionTwo(String.valueOf(calculateValues(expression.getExpressionTwo())));
                }

                return getValue(expression);
            }

        }catch (Exception e){
            logger.debug("Invalid syntax ", e);
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
    public Integer getValue(Expression expression) {
        logger.info("expression: "+ expression.toString()+"\n");
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
