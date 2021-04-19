<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style>
fieldset {
	width: 400px;
}
table {
	width: 200px;
	margin: 0px auto;
}
</style>
</head>
<body>
	<%
		request.setCharacterEncoding("EUC-KR");
		String name = request.getParameter("name");
		String java = request.getParameter("java");
		int mdJava = Integer.parseInt(java);
		String web = request.getParameter("web");
		int mdWeb = Integer.parseInt(web);
		String iot = request.getParameter("iot");
		int mdIot = Integer.parseInt(iot);
		String android = request.getParameter("android");
		int mdAndroid = Integer.parseInt(android);
		double avg = 0;
		avg = ((int) ((mdJava + mdWeb + mdIot + mdAndroid) / 4) * 100) / 100;
		String score = "";
		if (avg <= 100 && avg >= 95) {
			score = "A+";
		} else if (avg >= 85) {
			score = "A";
		} else if (avg >= 80) {
			score = "B+";
		} else if (avg >= 70) {
			score = "C";
		} else {
			score = "F";
		}
	%>

	<fieldset>
		<legend>학점확인프로그램</legend>
		<table>
			<tr>
				<td>이름</td>
				<td><%=name%></td>
			</tr>
			<tr>
				<td>JAVA점수</td>
				<td><%=java%></td>
			</tr>
			<tr>
				<td>WEB점수</td>
				<td><%=web%></td>
			</tr>
			<tr>
				<td>IOT점수</td>
				<td><%=iot%></td>
			</tr>
			<tr>
				<td>ANDROID점수</td>
				<td><%=android%></td>
			</tr>
			<tr>
				<td>평균</td>
				<td><%=avg%></td>
			</tr>
			<tr>
				<td>학점</td>
				<td><%=score%></td>
			</tr>
		</table>
	</fieldset>
</body>
</html>