<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



    <!DOCTYPE html>
    <html>
    <head>
    <meta content="text/html; charset=UTF-8">
    <title>계산기</title>
    <link rel="stylesheet" href="../../css/style.css">
    </head>
    <body>
        <div class="calc">
            <h2>계산기</h2>
                <input type="text" id="show" value="0" readonly></input>
                <input type="hidden" id="send" value=""/>
                <input type="hidden" id="flag" value="0"/>
                <br>
                <div class="btn-box">
                    <input type="button" id="clear" value="ac"></input>
                    <input type="button" class="btnOperator" value="+/-"></input>
                    <input type="button" class="btnOperator" value="%"></input>
                    <input type="button" class="btnOperator" value="÷"></input>
                    <br>
                    <p></p>
                    <input type="button" class="btn" value="7"></input>
                    <input type="button" class="btn" value="8"></input>
                    <input type="button" class="btn" value="9"></input>
                    <input type="button" class="btnOperator" value="x"></input>
                    <br>
                    <p></p>
                    <input type="button" class="btn" value="4"></input>
                    <input type="button" class="btn" value="5"></input>
                    <input type="button" class="btn" value="6"></input>
                    <input type="button" class="btnOperator" value="-"></input>
                    <br>
                    <p></p>
                    <input type="button" id="btnOne" class="btn" value="1"></input>
                    <input type="button" class="btn" value="2"></input>
                    <input type="button" class="btn" value="3"></input>
                    <input type="button" class="btnOperator" value="+"></input>
                    <br>
                    <p></p>
                    <input type="button" id="btnZero" class="btn" value="0"></input>
                    <input type="button" class="btnOperator" value="."></input>
                    <input type="button" id="btnEqual" value="="></input>
                </div>
         </div>
         
         <script src="../../js/stack.js"></script>
         <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
         <script>
            //  window.onload = function () {
            //     // 버튼 클릭 시 숫자 누적
            //     let show = document.getElementById("show");
            //     let btnNumber = document.getElementsByClassName("btn");
            //     let btnOperator = document.getElementsByClassName("btnOperator");
            //     let stack = new Stack();
            //     var sum = 0;
                
            //     for (let i = 0; i < btnNumber.length; i++) {
            //         btnNumber[i].addEventListener("click", function addStack(){
            //             console.log(btnNumber[i]);
            //             stack.push(btnNumber[i].value);
            //             console.log("스택: " + stack.print());
            //             show.innerText += btnNumber[i].value;
            //         })
            //         for (let j = 0; j < stack.size(); j++) {
            //             console.log("stack.peek("+j+")");
            //             console.log("10제곱: "+Math.pow(10,j));
            //             sum += stack.peek(j) * Math.pow(10,j);
            //             console.log("sum :", sum);
            //         }
            //     }
            // }
             
         </script>
         <script>
            // flag가 '0'일 때는 최초의 상태 
             var flag = 0;
             $(function () {
                 // '=' 버튼 클릭시 
                 $('#btnEqual').on('click', function () {
                     var showResult = $('#show').val();
                     console.log("show: " + showResult);
                     console.log("send: " + $('#send').val());

                    //  if (showResult == ',=,') {
                    //     $('#show').val('');
                    //     $('#send').val(''); 
                    //  }

                     // ajax로 버튼으로 클릭된 값 보내기
                     $.ajax ({
                        url: '/calculate',
                        type: 'POST',
                        data: {
                            formula: $('#send').val()
                        },
                        dataType: 'text',
                        success: function (formula) {
                            console.log("formula: " + formula);
                            $('#show').val(formula);    // 계산한 결과 보여주기
                            $('#send').val(formula + ",");    // 계산한 결과값으로 초기화
                            $('#clear').val('ac');      // 취소 버튼 'ac'로 초기화
                            flag = 0;
                        },
                        error: function() {
                            $('#show').val('잘못된 값');
                            $('#clear').val('ac');
                            flag = 0;
                        }
                     });
                 });

                 // 숫자 버튼 클릭 시 
                 $('.btn').on('click', function () {
                    if (flag == 0) {
                        showInit();
                        flag = 1;
                    }
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    // 값 보여주기
                    var showNumber = $('#show').val();
                    $('#show').val(showNumber + $(this).val());

                    // 값 보내기
                    var sendNumber = $('#send').val();
                    $('#send').val(sendNumber + $(this).val());
                 })

                 // 부호 버튼 클릭 시
                 $('.btnOperator').on('click', function () {
                    if (flag == 0) {
                        showInit();
                        flag = 1;
                    }
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    // 부호 보여주기
                    var showOperator = $('#show').val();
                    var cutShowOperator = showOperator.substring(showOperator.length - 1, showOperator.length);
                    // 클릭된 부호로 변경해서 보여주기
                    if (cutShowOperator == '+' 
                        || cutShowOperator == '-' 
                        || cutShowOperator == '÷'  
                        || cutShowOperator == 'x') {
                        $('#show').val(showOperator.substring(0, showOperator.length - 1) + $(this).val());
                    } else {
                        $('#show').val(showOperator + $(this).val());    
                    }

                    // 부호 보내기
                    var sendOperator = $('#send').val();
                    var cutSendOperator = sendOperator.substring(sendOperator.length - 3, sendOperator.length);
                    // 클릭된 부호로 변경해서 보내기
                    if (cutSendOperator == ',+,' 
                        || cutSendOperator == ',-,' 
                        || cutSendOperator == ',÷,'  
                        || cutSendOperator == ',x,') {
                        $('#send').val(sendOperator.substring(0, sendOperator.length - 3) + "," + $(this).val() + ",");
                    } else {
                    $('#send').val(sendOperator + "," + $(this).val() + ",");
                    }
                })

                 // 'ac' 버튼 클릭시 
                 $('#clear').on('click', function () {
                    var show = $('#show').val();
                    var send = $('#send').val();
                    // 만약 계산 중 'c' 버튼을 눌렀을 때, 
                    // 보이는 부분 show와 계산식 send의 마지막 ',숫자 혹은 부호,'를 없애기
                    if ($('#clear').val() == 'c') {
                        console.log("c-clear")
                        $('#show').val(show.substring(0, send.length - 3));
                        $('#send').val(send.substring(0, send.length - 3));
                    } else {
                        console.log("ac-clear")
                        $('#show').val('0');
                        $('#send').val('');
                        flag = 0;
                    }
                 })
                 
                 //  보여지는 부분 show 초기화
                 function showInit() {
                     if (flag == 0) {
                        $('#show').val('');
                     }
                    // if ($('#flag').val() == '1') {
                    //     $('#show').val('');
                    //     $('#send').val('');                                
                    //     $('#flag').val('0');
                    //}
                 }
             } )
         </script>
    </body>
    </html>