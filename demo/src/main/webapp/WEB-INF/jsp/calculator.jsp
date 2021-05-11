<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
    <meta content="text/html; charset=UTF-8">
    <title>계산기</title>
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link rel="stylesheet" href="../../css/style.css">
    </head>
    <body>
        <div class="calc">

            <h2>계산기</h2>
            
            <div class="show-container">
                <textarea id="show" cols="19" rows="2" readonly></textarea>
                <div id="input">0</div>
                <input type="hidden" id="send" value=""/>
            </div>

            <div class="btn-container">
                <div class="container">
                    <input type="button" id="root" value="√"></input>
                    <input type="button" id="clear" value="ac"></input>
                    <input type="button" id="bracketOpen" value="("></input>
                    <input type="button" id="bracketClose" value=")"></input>
                    <input type="button" class="btnOperator" value="÷"></input>

                    <input type="button" id="power" value="²"></input>
                    <input type="button" class="btn" value="7"></input>
                    <input type="button" class="btn" value="8"></input>
                    <input type="button" class="btn" value="9"></input>
                    <input type="button" class="btnOperator" value="x"></input>
                    
                    <input type="button" class="btnOperator" value=""></input>
                    <input type="button" class="btn" value="4"></input>
                    <input type="button" class="btn" value="5"></input>
                    <input type="button" class="btn" value="6"></input>
                    <input type="button" class="btnOperator" value="-"></input>
                    
                    <input type="button" class="btnOperator" value=""></input>
                    <input type="button" id="btnOne" class="btn" value="1"></input>
                    <input type="button" class="btn" value="2"></input>
                    <input type="button" class="btn" value="3"></input>
                    <input type="button" class="btnOperator" value="+"></input>
                    
                    <div class="zero-container">
                        <input type="button" id="btnZero" class="btn" value="0"></input>
                        <input type="button" class="btnOperator" value="."></input>
                        <input type="button" id="btnEqual" value="="></input>
                    </div>
                </div>
            </div>

            <div class="save-btn">
                <button onclick="window.open('/list', 'window_name','width=500, height=500, location=no, status=no, scrollbars=yes');">이전 결과 조회</button>
            </div>

             <div class="save-container">
                <div class="save-box">
                    <div class="top-box">
                        <p style="font-size: 12px; margin-block: 5px;">날짜</p>
                        <a href="#" style="color: black;"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <h4 class="content">asdfasdfasdfasdfdfasdfasdfasdfasdfadsfsafasdfasdfa</h4>
                    <h4 style="text-align: right; margin-block: 10px;">결과</h4>
                </div>
                <div class="save-box">
                    <div class="top-box">
                        <p style="font-size: 12px; margin-block: 5px;">날짜</p>
                        <a href="#" style="color: black;"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <h4 style="margin-block: 10px;">계산식</h4>
                    <h4 style="text-align: right; margin-block: 10px;">결과</h4>
                </div>
                <div class="save-box">
                    <div class="top-box">
                        <p style="font-size: 12px; margin-block: 5px;">날짜</p>
                        <a href="#" style="color: black;"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <h4 style="margin-block: 10px;">계산식</h4>
                     <h4 style="text-align: right; margin-block: 10px;">결과</h4>
                </div>
            </div>

        </div>
         
        <script src="../../js/stack.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script>
            //  window.onload = function () {
            //     // 버튼 클릭 시 숫자 누적
            //     let input = document.getElementById("input");
            //     let btnNumber = document.getElementsByClassName("btn");
            //     let btnOperator = document.getElementsByClassName("btnOperator");
            //     let stack = new Stack();
            //     var sum = 0;
                
            //     for (let i = 0; i < btnNumber.length; i++) {
            //         btnNumber[i].addEventListener("click", function addStack(){
            //             console.log(btnNumber[i]);
            //             stack.push(btnNumber[i].value);
            //             console.log("스택: " + stack.print());
            //             input.innerText += btnNumber[i].value;
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
            // inputFlag가 '0'일 때는 최초의 상태 
             var inputFlag = 0;
             var showFlag = 0;
             var sendFlag = 0;
             var result= '';

             $(function () {
                 // '=' 버튼 클릭시 
                 $('#btnEqual').on('click', function () {
                     var show = $('#show').text();
                     console.log("show: " + show);
                     console.log("send: " + $('#send').val());

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
                            $('#input').text(formula);    // 계산한 결과 보여주기
                            $('#show').text(show + "=" + formula);
                            $('#send').val(formula + ",");    // 계산한 결과값으로 초기화
                            $('#clear').val('ac');      // 취소 버튼 'ac'로 초기화
                            result = formula;
                            inputFlag = 0;
                            showFlag = 0;
                            sendFlag = 0;
                        },
                        error: function() {
                            $('#input').text('0');
                            $('#show').text('잘못된 값');
                            $('#clear').val('ac');
                            inputFlag = 0;
                            showFlag = 0;
                            sendFlag = 0;
                        }
                     });
                 });



                 // 숫자 버튼 클릭 시 
                 $('.btn').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    showInit();
                    showFlag = 1;
                    sendInit();
                    sendFlag = 1;

                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    
                    // input 값 보여주기
                    $('#input').text($(this).val());
                    // show 값 보여주기
                    var showNumber = $('#show').text();
                    $('#show').text(showNumber + $(this).val());
                    // 값 보내기
                    var sendNumber = $('#send').val();
                    $('#send').val(sendNumber + $(this).val());
                 })



                 // 부호 버튼 클릭 시
                 $('.btnOperator').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    sendInit();
                    sendFlag = 1;
                    if (showFlag == 0) {
                        showInit();
                        $('#show').text(result);
                        $('#send').val(result);
                        showFlag = 1;
                    }
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    
                    // input 부호 보여주기
                    $('#input').text($(this).val());   

                    // show 부호 보여주기
                    var showOperator = $('#show').text();
                    var cutShowOperator = showOperator.substring(showOperator.length - 1, showOperator.length);
                    // 클릭된 부호로 변경해서 보여주기
                    if (cutShowOperator == '+' 
                        || cutShowOperator == '-' 
                        || cutShowOperator == '÷'  
                        || cutShowOperator == 'x') {
                        $('#show').text(showOperator.substring(0, showOperator.length - 1) + $(this).val());
                    } else {
                        $('#show').text(showOperator + $(this).val());    
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



                 // 취소 버튼 클릭 시 (기본값 : ac)
                 $('#clear').on('click', function () {
                    var input = $('#input').text();
                    var show = $('#show').text();
                    var send = $('#send').val();
                    // 만약 계산 중 'c' 버튼을 눌렀을 때, 
                    // 보이는 부분 input과 계산식 send의 마지막 ',숫자 혹은 부호,'를 없애기
                    if ($('#clear').val() == 'c') {
                        console.log("c-clear");
                        show = show.substring(0, show.length - 1);
                        $('#show').text(show);
                        $('#input').text('0');
                        if (show == '') {
                            $('#clear').val('ac');  // 더 이상 지울게 없을 때 다시 'ac'로 초기화
                            // $('#input').text('0');
                            // $('#show').text('');     // '0'보이게 초기화
                            // $('#send').val('');     // 값 넘겨주는 곳도 초기화
                            // inputFlag = 0;
                            // showFlag = 0;
                            // sendFlag = 0;
                        }
                        var num = $('.btn').val();
                        send = send.substring(0, send.length - 2);
                        console.log("잘라낸 send: " + send);
                        var cutLast = send.substring(send.length - 2, send.length);
                        console.log("cutLast: " + cutLast);

                        // 마지막 부분이 '숫자,'인 경우 ',' 자르기
                        if (cutLast == num + ","
                            || cutLast == "),") {
                            send = send.substring(0, send.length - 1);
                            $('#send').val(send);
                        // 마지막 부분이 ',부호'인 경우 ',' 붙이기
                        } else if (cutLast == ",+" 
                                    || cutLast == ",-"
                                    || cutLast == ",x"
                                    || cutLast == ",÷") {
                            $('#send').val(send + ",");
                        } else if (cutLast == ",(" 
                                    || cutLast == ",√"
                                    || cutLast == ",²") {
                            $('#send').val(send + ",");
                        } else {
                            $('#send').val(send);
                        }
                    } else {
                        console.log("ac-clear");
                        $('#input').text('0');    // '0' 보이게 초기화
                        $('#show').text('');
                        $('#send').val('');
                        inputFlag = 0;
                        showFlag = 0;
                        sendFlag = 0;
                    }
                 });

                 // 취소버튼 더블클릭 시 초기화
                //  $('#clear').dblclick(function () {
                //     console.log("ac-clear");
                //     $('#clear').val('ac');
                //     $('#input').val('0');
                //     $('#send').val('');
                //     inputFlag = 0;
                //     sendFlag = 0;
                //  });



                // '(' 버튼 클릭 시
                $('#bracketOpen').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    showInit();
                    showFlag = 1;
                    sendInit();
                    sendFlag = 1;
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    // input 값 보여주기
                    $('#input').text($(this).val());
                     // show 값 보여주기
                     var showBracket = $('#show').text();
                    $('#show').text(showBracket + $(this).val());
                    // 값 보내기
                    var sendBracket = $('#send').val();
                    $('#send').val(sendBracket + $(this).val() + ",");
                });



                // ')' 버튼 클릭 시
                $('#bracketClose').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                     // input 값 보여주기
                    $('#input').text($(this).val());
                     // show 값 보여주기
                     var showBracket = $('#show').text();
                    $('#show').text(showBracket + $(this).val());
                    // 값 보내기
                    var sendBracket = $('#send').val();
                    $('#send').val(sendBracket + "," + $(this).val());
                });

                $('#root').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    showInit();
                    showFlag = 1;
                    sendInit();
                    sendFlag = 1;
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    // input 값 보여주기
                    $('#input').text($(this).val());
                    // show 값 보여주기
                    var showRoot = $('#show').text();
                    $('#show').text(showRoot + $(this).val());
                    // 값 보내기
                    var sendRoot = $('#send').val();
                    $('#send').val(sendRoot + $(this).val() + ",");
                });

                $('#power').on('click', function () {
                    inputInit();
                    inputFlag = 1;
                    showInit();
                    showFlag = 1;
                    // 취소 버튼 'ac'를 'c'로 바꾸기
                    $('#clear').val('c');
                    // input 값 보여주기
                    $('#input').text($(this).val());
                    // show 값 보여주기
                    var showPower = $('#show').text();
                    $('#show').text(showPower + $(this).val());
                    // 값 보내기
                    var sendPower = $('#send').val();
                    $('#send').val(sendPower + "," + $(this).val());
                });
                 


                 //  보여지는 부분 input 초기화
                 function inputInit() {
                     if (inputFlag == 0) {
                        $('#input').text('');
                     }
                 }

                //  보여지는 부분 show 초기화
                function showInit() {
                    if (showFlag == 0) {
                        $('#show').text('');
                     }
                 }

                 //  보내는 부분 send 초기화
                 function sendInit() {
                     if (sendFlag == 0) {
                        $('#send').val('');
                     }
                 }
             } )
         </script>
    </body>
    </html>