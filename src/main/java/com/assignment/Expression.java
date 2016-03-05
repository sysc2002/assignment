package com.assignment;

/**
 * Created by M on 2016-03-04.
 */
public class Expression {

    private String function;
    private String expressionOne;
    private String expressionTwo;
    private String expressionThree;


    public Expression(String command) {
        command = command.trim();
        String function = command.substring(0,4);
        if (function.equals("let(")){
            this.function = Constants.LET;
        }else if (function.equals("add(")){
            this.function = Constants.ADD;
        }else if (function.equals("sub(")){
            this.function =Constants.SUB;
        }else if (function.equals("mult")){
            this.function =Constants.MULT;
        }else if (function.equals("div(")) {
            this.function =Constants.DIV;
        }

        if(this.function != null) {
            setExpressions(command);
        }

    }

    private void setExpressions (String command){
        int size = command.length();
        if (function.equals(Constants.MULT)){
            command = command.substring(4,size);
        }else if (function.equals(Constants.LET)) {
            command = command.substring(3,size);
        }else if (function != null){
            command = command.substring(3,size);
        }

        //validate
        if(!isValid(command)){
            this.function = null;
            return;
        }

        //remove brackets
        size = command.length();
        command = command.substring(1,size-1);

        String[] expressions = command.split(",(?![^(]*\\))");
        this.expressionOne = expressions[0].trim();
        this.expressionTwo = expressions[1].trim();

        if(function.equals(Constants.LET)){
            this.expressionThree = expressions[2].trim();
        }

    }

    private boolean isValid (String command){
        // validating expressions within ()
        int size = command.length();
        String end = command.substring(size-1);
        if(this.function == null){
            return false;
        }
        if (!end.equals(")")){
            return false;
        }

        if (command.charAt(0) != '('){
            return false;
        }

        if(!command.contains(",")){
            return false;
        }

        command = command.substring(1,size-1);
        String[] expressions = command.split(",(?![^(]*\\))");
        if (expressions.length > 3 || expressions.length < 2 || (expressions.length == 2 && this.function.equals(Constants.LET))){
            return false;
        }

        if(expressions.length == 3 && !this.function.equals(Constants.LET)){
            return false;
        }


        return true;
    }


    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getExpressionOne() {
        return expressionOne;
    }

    public void setExpressionOne(String expressionOne) {
        this.expressionOne = expressionOne;
    }

    public String getExpressionTwo() {
        return expressionTwo;
    }

    public void setExpressionTwo(String expressionTwo) {
        this.expressionTwo = expressionTwo;
    }

    public String getExpressionThree() {
        return expressionThree;
    }

    public void setExpressionThree(String expressionThree) {
        this.expressionThree = expressionThree;
    }
}
