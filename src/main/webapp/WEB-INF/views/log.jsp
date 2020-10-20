<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>Log</title>
    <sec:csrfMetaTags />
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <h2>사용기록 확인 페이지</h2>
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
    <table>
        <th>Bucket Name</th>
        <th>E-mail ID</th>
        <th>Inference Image Path</th>
        <th>Original Image Path</th>
        <th>Used Service</th>
        <th>Result</th>
        <th>Used Time</th>
        <c:forEach var="log" items="${logList}">
            <tr>
                <td>${log.getBucketname()}</td>
                <td>${log.getEID()}</td>
                <td>${log.inferimagepath}</td>
                <td>${log.originalimagepath}</td>
                <td>${log.usedservice}</td>
                <td>${log.result}</td>
                <td>${log.timestamp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
