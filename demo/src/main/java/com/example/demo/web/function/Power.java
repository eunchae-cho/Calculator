package com.example.demo.web.function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Power {

    @Autowired Bracket bracket;
    @Autowired Check check;
    
    public List<String> calculate(List<String> list) {
        int size = list.size();
        int openIndex = 0;
        int closeIndex = 0;
        int openCount = 0;
        int closeCount = 0;
        String resultString = null;

        for (int i = 0; i < size; i++) {
            // 거듭제곱 해당 괄호 찾기  예) (2+3)², (4)²
            if (list.get(i).equals(")") && list.get(i + 1).equals("²")) {
                closeIndex = i;
                for (int j = closeIndex; j >= 0; j--) {
                    if(list.get(j).equals(")")) {
                        closeCount++;
                    }
                    if(list.get(j).equals("(")) {
                        openCount++;
                        if (openCount == closeCount) {
                            openIndex = j;
                            // 거듭제곱의 괄호식 bracketList 따로 생성
                            List<String> bracketList = new LinkedList<>();
                            for (int k = openIndex; k <= closeIndex; k++) {
                                bracketList.add(list.get(k));
                            }

                            // list에 계산된 값 추가
                            bracket.calculate(bracketList);
                            resultString = String.valueOf(Math.pow(Double.parseDouble(bracketList.get(0)), 2));
                            list.add(openIndex, resultString);
                            
                            // 기존 list에서 해당 괄호식 삭제
                            int count = 0;                          
                            // '²' 포함 지워야할 괄호식의 갯수 => '( )²'
                            while(count != closeIndex - openIndex + 2) {    
                                // 지워야할 연속된 자리
                                list.remove(openIndex + 1);                 
                                System.out.print("remove list: ");
                                printList(list);
                                count++;
                            }
                            size = list.size();
                            break;
                        }
                    }
                }
            // 일반 상수 거듭제곱 계산  예) 2²
            } else if (list.get(i).equals("²")) {
                resultString = String.valueOf(Math.pow(Double.parseDouble(list.get(i - 1)), 2));
                list.set(i - 1, resultString);
                list.remove(i);
                size = list.size();

                System.out.print("power list: ");
                printList(list);
                break;
            }
        }
        // 계산된 식에 또 거듭제곱이 있는지 검사
        if (check.checkPower(list)) {
            calculate(list);
        }
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
    
}
