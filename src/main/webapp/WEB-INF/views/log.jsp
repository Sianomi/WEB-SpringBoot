<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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
        <th>Number</th>
        <th>Bucket Name</th>
        <th>E-mail ID</th>
        <th>Inference Image Path</th>
        <th>Original Image Path</th>
        <th>Used Service</th>
        <th>Result</th>
        <tr><!-- 첫번째 줄 시작 -->
            <td>1</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 첫번째 줄 끝 -->
        <tr><!-- 두번째 줄 시작 -->
            <td>2</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 두번째 줄 끝 -->
        <tr><!-- 세번째 줄 시작 -->
            <td>3</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 세번째 줄 끝 -->
        <tr><!-- 네번째 줄 시작 -->
            <td>4</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 네번째 줄 끝 -->
        <tr><!-- 다섯번째 줄 시작 -->
            <td>5</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 다섯번째 줄 끝 -->
        <tr><!-- 여섯번째 줄 시작 -->
            <td>6</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 여섯번째 줄 끝 -->
        <tr><!-- 일곱번째 줄 시작 -->
            <td>7</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 일곱번째 줄 끝 -->
        <tr><!-- 여덟번째 줄 시작 -->
            <td>8</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 여덟번째 줄 끝 -->
        <tr><!-- 아홉번째 줄 시작 -->
            <td>9</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 아홉번째 줄 끝 -->
        <tr><!-- 열번째 줄 시작 -->
            <td>10</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr><!-- 열번째 줄 끝 -->

    </table>
</body>
</html>
