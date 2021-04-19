// 자바스크립트로 스택 구현하기

class Stack {
   constructor() {
     this.items = []
     this.count = 0
   }

   // 스택의 맨 위 값 넣기
   push(element) {
     this.items[this.count++] = element
   }

   // 스택의 맨 위의 값을 리턴하고 삭제
   // 만약 값이 없다면 "undefined"를 리턴
   pop() {
      if(this.count == 0) return undefined 
      return this.items[--this.count]
   }

   // 스택의 맨 위의 값 알기
   peek(element) {
    return this.items[this.count - 1]
   }

   // 스택이 비었는지 확인
   isEmpty() {
     return this.count == 0;
   }

   // 스택의 길이
   size() {
     return this.count
   }

   // 스택의 요소(값)들을 출력
   print() {
     let str = ''
     for (let i = 0; i < this.count; i++) {
        str += this.items[i] + ' '
     }
     return str
   }
  }

 