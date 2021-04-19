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
                <div id="show"></div>
                <input type="hidden" id="send" value=""/>
                <input type="hidden" id="flag" value="0"/>
                <br>
                <button id="clear" class="btnOperator" data-type="ac">ac</button>
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
             $(function () {
                 // '=' 버튼 클릭시 
                 $('#btnEqual').on('click', function () {
                     var showResult = $('#show').text();
                     console.log("show: "+showResult);
                     console.log("send: "+ $('#send').val());

                     if (showResult == ',=,') {
                        $('#show').text('');
                        $('#send').val(''); 
                     }

                     // ajax로 버튼으로 클릭된 값 보내기
                     $.ajax ({
                         url: '/calculate',
                        type: 'POST',
                        data: {
                            formula: $('#send').val()
                        },
                        dataType: 'text',
                        success: function (formula) {
                            console.log("formula: "+formula);
                            $('#show').text(formula);
                            $('#flag').val('1');
                        },
                        error: function() {
                            $('#show').text("잘못된 값");
                            $('#flag').val('1');
                        }
                     });
                 });

                 // 숫자 버튼 클릭 시 
                 $('.btn').on('click', function () {
                    init();
                    // 값 보여주기
                    var showNumber = $('#show').text();
                    $('#show').text(showNumber + $(this).val());

                    // 값 보내기
                    var sendNumber = $('#send').val();
                    $('#send').val(sendNumber + $(this).val());
                 })

                 // 부호 버튼 클릭 시
                 $('.btnOperator').on('click', function () {
                    init();
                    // 부호 보여주기
                    var showOperator = $('#show').text();
                    var cutShowOperator = showOperator.substring(showOperator.length-1, showOperator.length);
                    // 클릭된 부호로 변경해서 보여주기
                    if (cutShowOperator == '+' 
                        || cutShowOperator == '-' 
                        || cutShowOperator == '÷'  
                        || cutShowOperator == 'x') {
                        $('#show').text(showOperator.substring(0, showOperator.length-1) + $(this).val());
                    } else {
                        $('#show').text(showOperator + $(this).val());    
                    }

                    // 부호 보내기
                    var sendOperator = $('#send').val();
                    var cutSendOperator = sendOperator.substring(sendOperator.length-3, sendOperator.length);
                    // 클릭된 부호로 변경해서 보내기
                    if (cutSendOperator == ',+,' 
                        || cutSendOperator == ',-,' 
                        || cutSendOperator == ',÷,'  
                        || cutSendOperator == ',x,') {
                        $('#send').val(sendOperator.substring(0, sendOperator.length-3) + "," + $(this).val() + ",");
                    } else {
                    $('#send').val(sendOperator + "," + $(this).val()+",");
                    }
                })

                 // 'ac' 버튼 클릭시 
                 $('#clear').on('click', function () {
                    $('#show').text('');
                    $('#send').val('');
                 })
                 
                 // 계산식 초기화
                 function init() {
                    if ($('#flag').val() == '1') {
                        $('#show').text('');
                        $('#send').val('');                                
                        $('#flag').val('0');
                    }
                 }
             } )
         </script>
    </body>
    </html>