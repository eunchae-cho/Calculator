package com.example.demo.function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Bracket {
    
    @Autowired Arthmetic arthmetic;
    @Autowired Check check;

    public List<String> calculate(List<String> list) {
        int openIndex = 0;
        int closeIndex = 0;
        int size = list.size();
        String resultString;
        
        for(int i = 0; i < size; i++) {
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
                arthmetic.calculate(bracketList);
                resultString = bracketList.get(0);

                // formulaList의 해당 괄호식에 계산값 넣고 나머지 삭제
                list.add(openIndex, resultString);
                int count = 0;                          
                while(count != closeIndex - openIndex + 1) {    // 지워야할 괄호식의 갯수
                    list.remove(openIndex + 1);                 // 지워야할 연속된 자리
                    System.out.print("remove list: ");
                    printList(list);
                    System.out.println("count " + count);
                    count++;
                }
                break;

            } else {
                continue;
            }
        }
        
        // 계산식에 잘못된 괄호 입력이 있는지 확인 
        if (check.checkWrongBracket(list)) {
            List<String> errorList = new LinkedList<>();
            errorList.add("?1");
            System.out.println("a");
            return errorList;       // 잘못된 괄호식이 있다면 '?1' 보냄
        } else {
            // 계산식에 괄호가 또 있는지 확인
            if (check.checkBracket(list)) {
                System.out.println("b");
                calculate(list);
            }
        }
        System.out.println("c");
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
