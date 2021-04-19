package com.example.demo;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CalculatorController {
    
    @GetMapping("/")
    public String home() {
        return "calculator";
    }

    @ResponseBody 
    @PostMapping("/calculate")
    public String calculator(String formula) {
        String[] formulaArr = formula.split(",");           // ','로 구분한 계산식을 배열로 담기
        long temp = 0;                                       // 순서대로 연산을 하기 위해 임시로 담는 값

        for (int i = 0; i < formulaArr.length; i++) {
            if (i % 2 == 0) {                               // 인덱스 값이 짝수일 때 숫자, 홀수일 때 부호
                if (i == 0) {
                    temp = Long.parseLong(formulaArr[i]);
                } else {
                    temp = compute(temp, Long.parseLong(formulaArr[i]), formulaArr[i - 1]);  // (첫번째 연산값, 두번째 값, 부호)
                }
            }
        }
        return String.valueOf(temp);
    }

    // 계산하기
   public long compute(long firstNumber, long nextNumber, String operator) {
       long result = 0;
        switch(operator) {
            case "+":  
                result = add(firstNumber, nextNumber); 
                break;
            case "-":  
                result = substract(firstNumber, nextNumber); 
                break;
            case "x":
                result = multiply(firstNumber, nextNumber);
                break;
            case "÷":  
                result = divide(firstNumber, nextNumber); 
                break;
        }
       return result;
   }

    // 더하기
    public long add(long firstNumber, long nextNumber) {
       return firstNumber + nextNumber;
    }

    // 빼기
    public long substract(long firstNumber, long nextNumber) {
        return firstNumber - nextNumber;
    }

    // 곱하기
   public long multiply(long firstNumber, long nextNumber) {
        return firstNumber * nextNumber;
    }
    
    // 나누기
    public long divide(long firstNumber, long nextNumber) {
        return firstNumber / nextNumber;
    }
}

    
