package com.example.demo;



import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
//         float temp = 0;                             // 순서대로 연산을 하기 위해 임시로 담는 값
        List<String> numberList = new LinkedList<>();
        List<String> operatorList = new LinkedList<>();

        checkSecondIndexIsBlank(formulaArr);
        checkSecondIndexIsOperator(formulaArr);

        // formulaArr의 인덱스가 짝수일 때 숫자, 홀수일 때 부호을 구분하고
        // 각각의 리스트를 생성
        for (int i = 0; i < formulaArr.length; i++) {
            if (i % 2 == 0) {
                numberList.add(formulaArr[i]);
            } else {
                operatorList.add(formulaArr[i]);
                // for(int j = 0; j < operatorList.size(); j++) {
                //     if (operatorList.get(j).equals(null)) {
                //         operatorList.remove(j);
                //     }
                // }
            }
        }

        System.out.println();
        Iterator<String> iterator = numberList.iterator();
        System.out.println("numberList");
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ " ");
        }
        System.out.println();
        Iterator<String> iterator2 = operatorList.iterator();
        System.out.println("operatorList");
        while (iterator2.hasNext()) {
            System.out.print(iterator2.next()+ " ");
        }


        float result = 0;
        for (int i = 0; i < operatorList.size(); i++) {
            if (operatorList.get(i).equals("x")) {
                result = Float.parseFloat(numberList.get(i)) * Float.parseFloat(numberList.get(i + 1));
                numberList.add(i, String.valueOf(result));
                numberList.remove(i + 1);
                operatorList.remove(i);
            } else if (operatorList.get(i).equals("÷")) {
                result = Float.parseFloat(numberList.get(i)) / Float.parseFloat(numberList.get(i + 1));
                numberList.add(i, String.valueOf(result));
                numberList.remove(i + 1);
                operatorList.remove(i);
            } else if (operatorList.get(i).equals("+")) {
                result = Float.parseFloat(numberList.get(i)) + Float.parseFloat(numberList.get(i + 1));
                numberList.add(i, String.valueOf(result));
                numberList.remove(i + 1);
                operatorList.remove(i);
            } else if (operatorList.get(i).equals("-")) {
                result = Float.parseFloat(numberList.get(i)) - Float.parseFloat(numberList.get(i + 1));
                numberList.add(i, String.valueOf(result));
                numberList.remove(i + 1);
                operatorList.remove(i);
            }
        }
        return numberList.get(0);

        // for (int i = 0; i < formulaArr.length; i++) {
        //     if (i % 2 == 0) {                               // 인덱스 값이 짝수일 때 숫자, 홀수일 때 부호
        //         if (i == 0) {
        //             temp = Float.parseFloat(formulaArr[i]);
        //         } else {
        //             temp = compute(temp, Float.parseFloat(formulaArr[i]), formulaArr[i - 1]);  // (첫번째 연산값, 두번째 값, 부호)
        //         }
        //     }
        // }
        // return String.valueOf(temp);
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

    // 누적 계산 시 두번째 인덱스가 "" 공백으로 넘어옴 => [전연산결과][][부호][숫자]
    // 세번째 인덱스부터 한 칸씩 앞으로 이동한다 => [전연산결과][부호][숫자][null]
    public String[] checkSecondIndexIsBlank(String[] formulaArr) {
        if(formulaArr[1].equals("")) {
            for (int i = 1; i < formulaArr.length - 1; i++) {
                formulaArr[i] = formulaArr[i + 1];
            }
            //formulaArr[formulaArr.length - 1] = null;   // 맨 마지막 인덱스 값 null
            return Arrays.copyOf(formulaArr, formulaArr.length - 1);
        } else {
            return formulaArr;
        }
    }

    // formulaArr의 두번째 인덱스가 부호가 아닐 때 한 칸씩 앞으로 자리 이동
    // 이럴 경우 처음 인덱스 값이 전의 계산된 결과값이기 때문 
    // [전연산결과][입력숫자1][부호][입력숫자2] => [입력숫자1][부호][입력숫자2]
    public String[] checkSecondIndexIsOperator(String[] formulaArr) {
         if (!isOperator(formulaArr[1])) {
            for(int i = 0; i < formulaArr.length - 1; i++) {
                formulaArr[i] = formulaArr[i + 1];
            }
            //formulaArr[formulaArr.length - 1] = null;
            return Arrays.copyOf(formulaArr, formulaArr.length - 1);
        }
        return formulaArr;

    }
}

    
