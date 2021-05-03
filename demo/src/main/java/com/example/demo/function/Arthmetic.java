package com.example.demo.function;

import java.util.Iterator;
import java.util.List;

// 사칙연산
public class Arthmetic {

    public List<String> calculate(List<String> list) {
        float resultFloat = 0;
        int size = list.size();
        //int count = 0;

        while(size != 1) {

            // '(', ')' 삭제
            // System.out.print("list :");
            // printList(list);
            // for (int i = 0; i < size; i++) {
            //     System.out.println("a");
            //     if (list.get(i).equals("(") || list.get(i).equals(")")) {
            //         System.out.println("b");
            //         list.remove(i);
            //         size = list.size();
            //         } else {
            //         continue;
            //     }
            // }
            
            // 곱하기
            for (int i = 0; i < size; i++) {
                if (list.get(i).equals("x")) {
                    resultFloat = Float.parseFloat(list.get(i - 1)) * Float.parseFloat(list.get(i + 1));   // ..[숫자1][x][숫자2].. 양쪽의 숫자 연산
                    calculateBothOfIndex(list, resultFloat, i);
                    size = list.size();
                } else {
                    continue;
                }
            }

            // 나누기
            for (int i = 0; i < size; i++) {
                if (list.get(i).equals("÷")) {
                    resultFloat = Float.parseFloat(list.get(i - 1)) / Float.parseFloat(list.get(i + 1)); 
                    calculateBothOfIndex(list, resultFloat,i);
                    size = list.size();
                } else {
                    continue;
                }
            }

            // 더하기
            for (int i = 0; i < size; i++) {
                if (list.get(i).equals("+")) {
                    resultFloat = Float.parseFloat(list.get(i - 1)) + Float.parseFloat(list.get(i + 1)); 
                    calculateBothOfIndex(list, resultFloat, i);
                    size = list.size();
                } else {
                    continue;
                }
            }

            // 빼기
            for (int i = 0; i < size; i++) {
                if (list.get(i).equals("-")) {
                    resultFloat = Float.parseFloat(list.get(i - 1)) - Float.parseFloat(list.get(i + 1)); 
                    calculateBothOfIndex(list, resultFloat, i);
                    size = list.size();
                } else {
                    continue;
                }
            }
        }
        return list;
    }

    // 부호에 따라 연산되는 인덱스와 부호를 정리
    // 1. 부호에 따라 연산                  ...[숫자][부호][숫자]...
    // 2. 가운데 부호를 연산 결과 값으로 바꿈   ...[숫자][연산결과][숫자]...
    // 3. 앞의 숫자 삭제                   ...[연산결과][숫자]...
    // 4. 뒤의 숫자 삭제                   ...[연산결과]...
    public List<String> calculateBothOfIndex(List<String> list, float resultFloat, int i) {
        list.set(i, String.valueOf(resultFloat));
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
}
