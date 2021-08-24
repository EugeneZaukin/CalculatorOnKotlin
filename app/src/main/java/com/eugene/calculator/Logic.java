package com.eugene.calculator;

public class Logic {
    private StringBuilder number = new StringBuilder();
    private String SIGN;

    //Заполняем число
    public void buttonNumber(String num) {
        number.append(num);
    }

    //Получаем число
    public StringBuilder getNumber() {
        return number;
    }
    public String getSIGN() {
        return SIGN;
    }

    //Определение знака
    public void buttonSign(String textSign) {
        if (SIGN != null && number.length() >= 1) {
            if ((number.length()-1) == number.lastIndexOf(SIGN)) {
                number.deleteCharAt(number.lastIndexOf(SIGN));
            } else {
                number.append(buttonSwitch());
            }
        }
        if (number.length() >= 1) {
            SIGN = textSign;
            number.append(SIGN);
        }
    }

    //Выбираем действие по знаку
    private double buttonSwitch() {
        double firstNum = 0;
        double secondNum;
        switch (SIGN){
            case "+":
                firstNum = Double.parseDouble(number.substring(0, number.indexOf(SIGN)));
                secondNum = Double.parseDouble(number.substring(number.indexOf(SIGN)+1));
                number.delete(0, number.length());
                firstNum += secondNum;
                break;
            case "-":
                firstNum = Double.parseDouble(number.substring(0, number.indexOf(SIGN)));
                secondNum = Double.parseDouble(number.substring(number.indexOf(SIGN)+1));
                number.delete(0, number.length());
                firstNum -= secondNum;
                break;
            case "*":
                firstNum = Double.parseDouble(number.substring(0, number.indexOf(SIGN)));
                secondNum = Double.parseDouble(number.substring(number.indexOf(SIGN)+1));
                number.delete(0, number.length());
                firstNum *= secondNum;
                break;
            case "/":
                firstNum = Double.parseDouble(number.substring(0, number.indexOf(SIGN)));
                secondNum = Double.parseDouble(number.substring(number.indexOf(SIGN)+1));
                number.delete(0, number.length());
                firstNum /= secondNum;
                break;
        }
        return firstNum;
    }

    //Равно
    public void buttonEquals() {
        //Если знак не null и строка не заканчивается на знак
        if (SIGN != null && !number.toString().endsWith(SIGN)) {
            number.append(buttonSwitch());
            SIGN = null;
        }
    }

    //Удалить все
    public void buttonAC() {
        number.delete(0, number.length());
        SIGN = null;
    }

    //Удаление элемента
    public void buttonDelete() {
        if (number.length() > 0) {
            //Если знак не null и строка заканчивается на знак
            if (SIGN != null && number.toString().endsWith(SIGN)) {
                number.deleteCharAt(number.length() - 1);
                SIGN = null;
            } else {
                number.deleteCharAt(number.length() - 1);
            }
        }
    }

    //Процент
    public void buttonPercent() {
            double firstNum;
            double secondNum;
        //Если знак не null и строка не заканчивается на знак
            if (SIGN != null && !number.toString().endsWith(SIGN)) {
                firstNum = Double.parseDouble(number.substring(0, number.indexOf(SIGN)));
                secondNum = Double.parseDouble(number.substring(number.indexOf(SIGN) + 1));
                firstNum = firstNum / 100 * secondNum;
                number.delete(number.indexOf(SIGN) + 1, number.length());
                number.append(firstNum);
            }
        }

    //.
    public void buttonPoint(String num) {
        boolean symbolExits = false;
        if (number.toString().contains(num)) {
            symbolExits = true;
        }

        if (!symbolExits) {
            if (number.length() == 0) {
                buttonNumber(String.format("%s%s", 0, num));
            } else {
                buttonNumber(num);
            }
        }
    }
}
