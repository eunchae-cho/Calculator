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
        String[] formulaArr = formula.split(",");   // ','로 구분한 계산식을 배열로 담기
        float temp = 0;                             // 순서대로 연산을 하기 위해 임시로 담는 값

        // formulaArr의 두번째 인덱스가 부호가 아닐 때 한 칸씩 앞으로 자리 이동
        // 이럴 경우 처음 인덱스 값이 전의 계산된 결과값이기 때문 
        // [연산숫자][입력숫자1][부호][입력숫자2] => [입력숫자1][부호][입력숫자2]
        if (!isOperator(formulaArr[1])) {
            for(int i = 0; i < formulaArr.length; i++) {
                formulaArr[i] = formulaArr[i+1];
            }
            formulaArr[formulaArr.length] = null;
        }

        for (int i = 0; i < formulaArr.length; i++) {
            if (i % 2 == 0) {                               // 인덱스 값이 짝수일 때 숫자, 홀수일 때 부호
                if (i == 0) {
                    temp = Float.parseFloat(formulaArr[i]);
                } else {
                    temp = compute(temp, Float.parseFloat(formulaArr[i]), formulaArr[i - 1]);  // (첫번째 연산값, 두번째 값, 부호)
                }
            }
        }
        return String.valueOf(temp);
    }

    // 계산하기
   public float compute(float firstNumber, float nextNumber, String operator) {
       float result = 0;
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
    public float add(float firstNumber, float nextNumber) {
       return firstNumber + nextNumber;
    }

    // 빼기
    public float substract(float firstNumber, float nextNumber) {
        return firstNumber - nextNumber;
    }

    // 곱하기
   public float multiply(float firstNumber, float nextNumber) {
        return firstNumber * nextNumber;
    }
    
    // 나누기
    public float divide(float firstNumber, float nextNumber) {
        return firstNumber / nextNumber;
    }

    // 문자열이 부호인지 아닌지 판단 
    public boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-")
            || str.equals("x") || str.equals("÷")) {
                return true;
        }
        return false;
    }
}


// 계산식의 숫자와 부호를 구분하기 위해 타입을 구분해주는 클래스
class TypeTest {
    
    public String type(String str) {
        return "typeString";
    }

    public String type(float f) {
        return "typeFloat";
    }
}

    
