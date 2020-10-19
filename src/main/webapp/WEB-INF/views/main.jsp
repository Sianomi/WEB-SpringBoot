<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html> 
<head> 
    <meta charset="UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Inference</title>
    <sec:csrfMetaTags />
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <script src="/js/main.js"></script>
    <h2 style="width: 220px; margin: 0;">회원 전용 페이지</h2>
    ID : ${name}
    소유 권한 : ${auth}
    <form id="logout" action="/logout" method="POST">
        <sec:csrfInput />
        <input type="submit" value="로그아웃"/>
    </form>
    <ul class="top-menu">
        <li><button type="button" id="Home" class="top-entry-container" onClick="location.href='/'">Home</button></li>
        <li><button type="button" id="Inference" class="top-entry-container" onClick="location.href='/infer'">Inference</button></li>
        <li><button type="button" id="Log" class="top-entry-container" onClick="location.href='/log'">Log</button></li>
    </ul>

</body> 
</html>
