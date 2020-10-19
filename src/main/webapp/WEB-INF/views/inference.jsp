<%--
  Created by IntelliJ IDEA.
  User: jin
  Date: 2020-10-19
  Time: 오후 2:00
  To change this template use File | Settings | File Templates.
--%>
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
    <h2 style="width: 220px; margin: 0;">추론 페이지</h2>
    ID : ${name}
    소유 권한 : ${auth}
    <form id="logout" action="/logout" method="POST">
        <sec:csrfInput />
        <input type="submit" value="로그아웃"/>
    </form>
    <ul class="top-menu">
        <li><button type="button" id="Home" class="top-entry-container" onClick="location.href='/main.jsp'">Home</button></li>
        <li><button type="button" id="Inference" class="top-entry-container" onClick="location.href='/inference.jsp'">Inference</button></li>
        <li><button type="button" id="Log" class="top-entry-container" onClick="location.href='/log.jsp'">Log</button></li>
    </ul>
    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="fileId" name="filedata" accept="image/jpeg" onchange="setThumbnail(event);"/>
        <select id="solution" name="solution">
            <option value=1>SageMaker</option>
            <option value=2>Rekognition</option>
            <option value=3>Both</option>
        </select>
    </form>
    <input type="button" value="추론" id="file"  class="inference-start"//>
    <div>
        <div id="image_container" style="display: inline-block;vertical-align: top;">
            <h3 id="originalImageText"></h3>
            <img src="" width="800" id="originalImage">
        </div>
        <div id="image_infer_container" style="display: inline-block">
            <h3 id="inferImageText"></h3>
            <img src="" id="inferImage">
        </div>
    </div>
    </body>
</html>
