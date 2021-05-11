package com.example.demo.web;



import java.util.LinkedList;
import java.security.Provider.Service;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.example.demo.service.CalcService;
import com.example.demo.web.function.Arthmetic;
import com.example.demo.web.function.Bracket;
import com.example.demo.web.function.Check;
import com.example.demo.web.function.Error;
import com.example.demo.web.function.Power;
import com.example.demo.web.function.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CalculatorController {

    @Autowired Arthmetic arthmetic;
    @Autowired Bracket bracket;
    @Autowired Check check;
    @Autowired Error error;
    @Autowired Root root;
    @Autowired Power power;

    @Autowired CalcService calcService;
    
    @GetMapping("/")
    public String home() throws Exception{
        return "calculator";
    }

    @ResponseBody 
    @PostMapping("/calculate")
    public String calculator(String formula) throws Exception{
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

        // 거듭제곱이 있다면 거듭제곱 계산
        if (check.checkPower(formulaList)) {
            formulaList = power.calculate(formulaList);
            // 만약 거듭제곱식이 잘못되었다면 에러메세지 리턴
            // if ("?2".equals(formulaList.get(0)));
            //     formulaList = error.wrongPower(formulaList);
            //     resultString = formulaList.get(0);
            //     return resultString;
        }

        // 괄호식이 있다면 괄호식 계산
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

        // 루트가 있다면 루트 계산
        if (check.checkRoot(formulaList)) {
            root.calculate(formulaList);
            System.out.print("root list:");
            printList(formulaList);
        }

        // 일반식 계산
        arthmetic.calculate(formulaList);
        resultString = formulaList.get(0);

        return check.convertResultType(Double.parseDouble(resultString), (int) Double.parseDouble(resultString));
    }
    
  
    // 확인용 LinkedList 값 출력
    public void printList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ " ");
        }
        System.out.println();
    }

    @GetMapping("/list")
    public void list(Model model) throws Exception {
        List<Map<String, String>> list = calcService.list();
    }

    // @PostMapping("/add")
    // public String add() throws Exception{
    //     return "../calculator";
    // }
}

    
