<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html> 
<head> 
    <meta charset="UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Home</title>
    <sec:csrfMetaTags />
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <script src="/js/main.js"></script>
    <h2>회원 전용 페이지</h2>
    ${name}님, 안녕하십니까. 오늘도 좋은 하루 되세요!
    <form id="logout" action="/logout" method="POST">
        <sec:csrfInput />
        <input type="submit" value="로그아웃"/>
    </form>
    <ul class="top-menu">
        <li><button type="button" id="Home" class="top-entry-container" onClick="location.href='/'">Home</button></li>
        <li><button type="button" id="Inference" class="top-entry-container" onClick="location.href='/infer'">Inference</button></li>
        <li><button type="button" id="Log" class="top-entry-container" onClick="location.href='/log'">Log</button></li>
    </ul>

    <p>&#187; AWS 클라우드 기반의 Object-Detection AI 서비스</p>
    <p>&#187; Object-Detection: 객체 탐지는 이미지 또는 비디오에서 객체의 클래스와 위치를 찾는 컴퓨터 비전 기술과 이미지 처리에 관련된 컴퓨터 기술</p>
    <p>&nbsp;&nbsp; Object-Detection: 여러가지 물체에 대한 <em>Classification</em> + 물체의 위치 정보를 파악하는 <em>Localization<em></p>
    <img src="../../bootstrap/brand/example.svg">
</body> 
</html>
