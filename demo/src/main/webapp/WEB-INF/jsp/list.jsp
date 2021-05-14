<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!DOCTYPE html>
    <html>
    <head>
    <meta content="text/html; charset=UTF-8">
    <title>계산기</title>
    <link rel="stylesheet" href="../../css/style.css">
    </head>
    <body>
        <br>
        <table width="100%" align="center">
            <thead align="center">
                <tr>
                    <th scope="cols" width="100px">날짜</th>
                    <th scope="cols" width="200px">식</th>
                    <th scope="cols">결과</th>
                    <th scope="cols"></th>
                </tr>
            </thead>
            <tbody align="center">
                    <c:forEach items="${list}" var="list">
                        <c:if test="${empty list}">
                            <p style="text-align: center;">없음</p>
                        </c:if>
                    <tr>
                        <td>${list.date}</td>
                        <td width="200px"><a href="javascript:void(0);" onclick="callParent('${list.formula}','${list.send}');">${list.formula}</a></td>
                        <td width="110px">${list.result}</td>
                        <td><a href="list/delete?no=${list.no}">삭제</a></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
    <script>
        // calculator.jsp에 저장된 리스트 중 
        // 클릭된 하나의 값 보내서 식과 결과값 보여주기
        function callParent(formula, send) {
            opener.callChild(formula, send);
            window.open('','_self').close();
        } 
    </script>
    </body>
    </html>