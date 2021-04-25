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

        // ','로 구분한 계산식을 배열로 담기
        String[] formulaArr = formula.split(",");
        
        // formulaArr 배열을 리스트로 담기
        List<String> formulaList = new LinkedList<>();
        for (int i = 0; i < formulaArr.length; i++) {
            formulaList.add(formulaArr[i]);
        }

        formulaArr = checkSecondIndexIsBlank(formulaArr);
        formulaArr = checkSecondIndexIsOperator(formulaArr);

        // 확인용
        System.out.print("formulaList: ");
        printList(formulaList);
        
        int size = formulaList.size();
        float result = 0;
    
        // formulaList에 연산값으로 하나만 저장될 때까지
        while(formulaList.size() != 1) {

            // 곱하기
            for (int i = 0; i < size; i++) {
                if (formulaList.get(i).equals("x")) {
                    result = Float.parseFloat(formulaList.get(i - 1)) * Float.parseFloat(formulaList.get(i + 1));   // ..[숫자1][x][숫자2].. 양쪽의 숫자 연산
                    calculateBothOfIndex(formulaList, result, i);
                    size = formulaList.size();
                } else {
                    continue;
                }
            }

            // 나누기
            for (int i = 0; i < size; i++) {
                if (formulaList.get(i).equals("÷")) {
                    result = Float.parseFloat(formulaList.get(i - 1)) / Float.parseFloat(formulaList.get(i + 1)); 
                    calculateBothOfIndex(formulaList, result,i);
                    size = formulaList.size();
                } else {
                    continue;
                }
            }
    
            // 더하기
            for (int i = 0; i < size; i++) {
                if (formulaList.get(i).equals("+")) {
                    result = Float.parseFloat(formulaList.get(i - 1)) + Float.parseFloat(formulaList.get(i + 1)); 
                    calculateBothOfIndex(formulaList, result, i);
                    size = formulaList.size();
                } else {
                    continue;
                }
            }

            // 빼기
            for (int i = 0; i < size; i++) {
                if (formulaList.get(i).equals("-")) {
                    result = Float.parseFloat(formulaList.get(i - 1)) - Float.parseFloat(formulaList.get(i + 1)); 
                    calculateBothOfIndex(formulaList, result, i);
                    size = formulaList.size();
                } else {
                    continue;
                }
            }
        }
        return formulaList.get(0);
    }


    // 부호에 따라 연산되는 인덱스와 부호를 정리
    // 1. 부호에 따라 연산                  ...[숫자][부호][숫자]...
    // 2. 가운데 부호를 연산 결과 값으로 바꿈   ...[숫자][연산결과][숫자]...
    // 3. 앞의 숫자 삭제                   ...[연산결과][숫자]...
    // 4. 뒤의 숫자 삭제                   ...[연산결과]...
    private List<String> calculateBothOfIndex(List<String> list, float result, int i) {
        list.set(i, String.valueOf(result));
        list.remove(i - 1);
        list.remove(i);
        return list;
    }

    // 확인용 LinkedList 값 출력
    public void printList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ ", ");
        }
        System.out.println();
    }

    // 문자열이 부호인지 아닌지 판단 
    public boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-")
            || str.equals("x") || str.equals("÷")) {
                return true;
        }
        return false;
    }

    // 누적 계산 시 두번째 인덱스가 "" 공백으로 넘어옴
    // 세번째 인덱스부터 한 칸씩 앞으로 이동한다
    // [전연산결과][][부호][숫자] => [전연산결과][부호][숫자]
    public String[] checkSecondIndexIsBlank(String[] formulaArr) {
        if(formulaArr[1].equals("")) {
            for (int i = 1; i < formulaArr.length - 1; i++) {
                formulaArr[i] = formulaArr[i + 1];
            }
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
            String[] cutLastFormulaArr = Arrays.copyOf(formulaArr, formulaArr.length - 1);
            System.out.println("cutLastFormulaArr: " + Arrays.toString(cutLastFormulaArr));
            return cutLastFormulaArr;
        } else {
            return formulaArr;
        }

    }
}

    
