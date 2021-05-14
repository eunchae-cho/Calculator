package com.example.demo.web.function;

import java.util.List;

// 검사
public class Check {

    // 거듭제곱이 있는지 검사
    public boolean checkPower(List<String> list) {
        int size = list.size();
        for(int i = 0; i < size; i++) {
            if (list.get(i).contains("²")) {
                return true;
            }
        }
        return false;
    }

    // 루트가 있는지 겁사
    public boolean checkRoot(List<String> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals("√")) {
                return true;
            }
        }
        return false;
    }

    // 괄호식이 있는지 검사
    // 존재하면 true, 없으면 false
    public boolean checkBracket(List<String> list) {
        int size = list.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals("(") || list.get(i).equals(")")) {
                count++;
            } 
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 잘못된 괄호식이 있는지 검사
    // 예) ((2 + 2)
    public boolean checkWrongBracket(List<String> list) {
        int size = list.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals("(") || list.get(i).equals(")")) {
                count++;
            } 
        }
        // 괄호 '(' 혹은 ')'의 갯수가 홀수일 때
        if (count % 2 != 0) {
            return true;
        } else {
            return false;
        }
    }

    // 결과 값이 쓸데 없이 소수점으로 나오지 않도록 
    // 결과가 만약에 정수로 나왔다면 정수로, 실수로 나왔다면 실수로
    public String convertResultType(double doubleFomularResult, int integerFormulaResult) {
        if (doubleFomularResult - integerFormulaResult == 0) {
            return Integer.toString(integerFormulaResult);
        }
        return Double.toString(doubleFomularResult);
    }
}
