package com.example.demo.controller;



import java.util.LinkedList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.example.demo.function.Arthmetic;

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
        
        // formulaArr 배열 순서가 올바른지 검사
        formulaArr = checkSecondIndexIsBlank(formulaArr);
        formulaArr = checkSecondIndexIsOperator(formulaArr);
        
        // formulaArr 배열을 리스트로 담기
        List<String> formulaList = new LinkedList<>();
        for (int i = 0; i < formulaArr.length; i++) {
            formulaList.add(formulaArr[i]);
        }

        // 확인용
        System.out.print("formulaList: ");
        printList(formulaList);

        Arthmetic arthmetic = new Arthmetic();
        // arthmetic.calculate(formulaList, formulaList.size());

        int countOpenBracket = 0, countCloseBracket= 0;
        int openIndex = 0, closeIndex = 0;
        String resultString;
        List<String> bracketList = new LinkedList<>();
        int size = formulaList.size();

        while(size!= 1) {
            // 괄호식 찾기
            // '('의 카운트(countOpenBracket)과 ')'의 카운트(countCloseBracket)이 같으면
            // 제일 안쪽의 있는 괄호식 
            for(int i = 0; i < size; i++) {
                System.out.println(size);
                if (formulaList.get(i).equals("(")) {
                    countOpenBracket++;
                    openIndex = i;
                } else if (formulaList.get(i).equals(")")) {
                    countCloseBracket++;
                    if (countCloseBracket == 1) {
                        closeIndex = i;
                    }
                } else {
                    resultString = arthmetic.calculate(formulaList, size);
                }
                
                // 괄호식 계산하기
                if (countOpenBracket > 0 && countOpenBracket == countCloseBracket) {
                    for (int j = openIndex; j <= closeIndex; j++) {
                        bracketList.add(formulaArr[j]);
                        formulaList.remove(j);
                    }
                    resultString = arthmetic.calculate(bracketList, bracketList.size());
                    formulaList.add(openIndex - 1, resultString);
                    countOpenBracket = 0;
                    countCloseBracket = 0;
                }
            }
        }
        return transferResult(Float.parseFloat(formulaList.get(0)), (int) Float.parseFloat(formulaList.get(0)));
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

    // 문자열이 부호인지 아닌지 판단 
    public boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-")
            || str.equals("x") || str.equals("÷")) {
                return true;
        }
        return false;
    }

    // 확인용 LinkedList 값 출력
    public void printList(List<String> list) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ " ");
        }
        System.out.println();
    }

    // 결과 값이 쓸데 없이 소수점으로 나오지 않도록 
    // 결과가 만약에 정수로 나왔다면 정수로, 실수로 나왔다면 실수로
    public String transferResult(float floatFomularResult, int integerFormulaResult) {
        if (floatFomularResult - integerFormulaResult == 0) {
            return Integer.toString(integerFormulaResult);
        }
        return Float.toString(floatFomularResult);
    }

    // 괄호안의 식 찾기
    public List<String> searchBracketFormula(List<String> list) {
        List<String> bracketList = new LinkedList<>();
            bracketList = list;
            int bracketSize = list.size();

            // 바깥쪽 왼쪽 괄호
            for(int i = 0; i < bracketSize; i++) {
                if(bracketList.get(i).equals("(")) {
                    // 바깥쪽 오른쪽 괄호
                    for(int j = bracketSize; j>= 0; i--) {
                        if(bracketList.get(i).equals(")")) {
                        return bracketList;
                        } else {
                            bracketList.remove(j);
                            bracketSize = bracketList.size();
                        }
                    }
                } else {
                    bracketList.remove(i);
                    bracketSize = bracketList.size();
                }
            }
            return searchBracketFormula(bracketList);

    }
}

    
