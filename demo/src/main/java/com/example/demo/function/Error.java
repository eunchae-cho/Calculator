package com.example.demo.function;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Error {
    
    @Autowired Check check;

    List<String> errorList = new LinkedList<>();

    // '?1' 문자열이 있을 때 리턴
    public List<String> wrongBracket(List<String> list) {
        if (list.get(0).equals("?1")) {
            errorList.add(0, "잘못된 괄호");
            return errorList;
        }
        return list;
    }

    public List<String> wrongPower(List<String> list) {
        if (list.get(0).equals("?2")) {
            errorList.add(0, "잘못된 제곱");
            return errorList;
        }
        return list;
    }
}
