package com.example.demo.function;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Root {

    @Autowired Bracket bracket;
    @Autowired Check check;
    
    public List<String> calculate(List<String> list) {
        int size = list.size();
        int closeIndex = 0;
        int openCount = 0;
        int closeCount = 0;
        String resultString = null;

        System.out.print("root list: ");
        printList(list);

        for (int i = 0; i < size; i++) {
            if (list.get(i).equals("√")) {
                list.remove(i);     // 루트 기호 삭제
                size = list.size();
                
                System.out.print("root list (remove): ");
                printList(list);

                // 루트 안에 괄호식이 있다면  예) √(2+4)
                if (list.get(i).equals("(")) {
                    
                    int j = 0;
                    for (j = i; j < size; j++) {
                        if (list.get(j).equals("(")) {
                            openCount++;
                        } else if (list.get(j).equals(")")) {
                            closeCount++;
                            if (openCount == closeCount) {
                                closeIndex = j;
                                break;
                            }
                        }
                    }
                    // 루트 안 괄호식 bracketList 따로 생성
                    // list 괄호식 삭제
                    List<String> bracketList = new LinkedList<>();
                    for (int k = i + 1; k <= closeIndex; k++) {
                        bracketList.add(k, list.get(i));
                        list.remove(i);
                        size = list.size();
                    }
                    // 루트 안 괄호식 계산
                    bracket.calculate(bracketList);
                    resultString = String.valueOf(Math.sqrt(Double.parseDouble(bracketList.get(i))));
                    list.set(i, resultString);
                    System.out.print("root list (resultA): ");
                    printList(list);
                // 그 외 일반 루트 연산이라면  예) √6
                } else {
                    resultString = String.valueOf(Math.sqrt(Double.parseDouble(list.get(i))));
                    list.set(i, resultString);
                    System.out.print("root list (resultB): ");
                    printList(list);
                }
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
