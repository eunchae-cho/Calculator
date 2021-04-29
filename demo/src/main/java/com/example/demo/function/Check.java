package com.example.demo.function;

import java.util.List;

// 검사
public class Check {

    // 괄호식이 있는지 없는지 검사
    // 존재하면 true, 없으면 false
    public boolean checkBracket(List<String> list) {
        int size = list.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (list.get(i).equals("(")) {
                count++;
            } 
        }
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}