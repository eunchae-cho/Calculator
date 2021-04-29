package com.example.demo.function;

import java.util.LinkedList;
import java.util.List;

public class Bracket {
    
    public List<String> calculate(List<String> list) {
        Arthmetic arthmetic = new Arthmetic();
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
                for (j = openIndex; j <= closeIndex; j++) {
                    bracketList.add(list.get(j));
                }
                arthmetic.calculate(bracketList, bracketList.size());
                resultString = bracketList.get(0);

                // formulaList의 해당 괄호식에 계산값 넣고 나머지 삭제
                list.add(openIndex, resultString);
                int count = 0;                          
                while(count != closeIndex - openIndex + 1) {    // 지워야할 괄호식의 갯수
                    list.remove(openIndex + 1);                 // 지워야할 연속된 자리
                    count++;
                }
                break;

            } else {
                continue;
            }
        }

        // 계산식에 괄호가 또 있는지 확인
        Check check = new Check();
        if (check.checkBracket(list)) {
            calculate(list);
        }
        
        // 괄호가 없다면 일반식 리턴
        return list;
    }
}
