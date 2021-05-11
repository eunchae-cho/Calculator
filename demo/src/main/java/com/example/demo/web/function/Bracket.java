package com.example.demo.web.function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Bracket {
    
    @Autowired Arthmetic arthmetic;
    @Autowired Check check;
    @Autowired Root root;
    @Autowired Power power; 

    public List<String> calculate(List<String> list) {
        int openIndex = 0;
        int closeIndex = 0;
        int size = list.size();
        String resultString;
        
        for(int i = 0; i < size; i++) {

            // 입력값이 '숫자('일 때 '숫자 x ('로 변환
            // 예) 2(1+3) -> 2x(1+3)
            if (list.get(i).contains("(") && !list.get(i).equals("(")) {
                char[] charArray = list.get(i).toCharArray();
                list.remove(i);
                list.add(i, String.valueOf(charArray[0]));
                list.add(i + 1, "x");
                list.add(i + 2, String.valueOf(charArray[1]));
                size = list.size();
            }

            // 나중의 '('index 수색
            if (list.get(i).equals("(")) {
                openIndex = i;
                
              // 처음의 ')' index 수색  
            } else if (list.get(i).equals(")")) {
                closeIndex = i;

                // 괄호식을 따로 리스트를 만들어 계산
                int j = 0;
                List<String> bracketList = new LinkedList<>();
                for (j = openIndex + 1; j < closeIndex; j++) {
                    bracketList.add(list.get(j));
                }
                System.out.print("bracketList: ");
                printList(bracketList);

                // 루트 혹은 루트식이 있는지 확인
                if (check.checkRoot(bracketList)) {
                    bracketList = root.calculate(bracketList);
                }
                // 거듭제곱식이 있는지 확인
                if (check.checkPower(bracketList)) {
                    bracketList = power.calculate(bracketList);
                }
                arthmetic.calculate(bracketList);
                resultString = bracketList.get(0);

                // formulaList의 해당 괄호식에 계산값 넣고 나머지 삭제
                list.add(openIndex, resultString);
                int count = 0;                          
                while(count != closeIndex - openIndex + 1) {    // 지워야할 괄호식의 갯수
                    list.remove(openIndex + 1);                 // 지워야할 연속된 자리
                    System.out.print("remove list: ");
                    printList(list);
                    count++;
                }
                break;  // 괄호식 하나가 계산되면 탈출
            } else {
                continue;
            }
        }
        
        // 계산식에 잘못된 괄호 입력이 있는지 확인 
        if (check.checkWrongBracket(list)) {
            List<String> errorList = new LinkedList<>();
            errorList.add("?1");
            return errorList;       // 잘못된 괄호식이 있다면 '?1' 보냄
        } else {
            // 계산식에 괄호가 또 있는지 확인
            if (check.checkBracket(list)) {
                calculate(list);
            }
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
