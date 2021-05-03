package com.example.demo.controller;



import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.function.Arthmetic;
import com.example.demo.function.Bracket;
import com.example.demo.function.Check;
import com.example.demo.function.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CalculatorController {

    @Autowired Arthmetic arthmetic;
    @Autowired Bracket bracket;
    @Autowired Check check;
    @Autowired Error error;
    
    @GetMapping("/")
    public String home() {
        return "calculator";
    }

    @ResponseBody 
    @PostMapping("/calculate")
    public String calculator(String formula) {
        String resultString = null;
        
        // ','로 구분한 계산식을 배열로 담기
        String[] formulaArr = formula.split(",");
        
        // formulaArr 배열을 리스트로 담기
        List<String> formulaList = new LinkedList<>();
        for (int i = 0; i < formulaArr.length; i++) {
            formulaList.add(formulaArr[i]);
        }

        // 확인용
        System.out.print("formulaList: ");
        printList(formulaList);

        // 괄호식이 있는지 없는지 확인
        if (check.checkBracket(formulaList)) {
            // 괄호식 계산 
            formulaList = bracket.calculate(formulaList);
            // 만약 괄호식이 잘못되었다면 에러메세지 리턴
            if ("?1".equals(formulaList.get(0))) {
                formulaList = error.wrongBracket(formulaList);
                resultString = formulaList.get(0);
                return resultString;
            }
        }

        // 괄호식이 없다면 일반식 계산
        arthmetic.calculate(formulaList);
        resultString = formulaList.get(0);

        return check.convertResultType(Float.parseFloat(resultString), (int) Float.parseFloat(resultString));
    }
    
  
    // 확인용 LinkedList 값 출력
    public void printList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ " ");
        }
        System.out.println();
    }
}

    
