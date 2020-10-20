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
<script src="/js/inference.js"></script>
<h2>추론 페이지</h2>
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
<form id="uploadForm" enctype="multipart/form-data">
    <input type="file" id="fileId" class="fileselect" name="filedata" accept="image/jpeg" onchange="setThumbnail(event);"/>
    <select id="solution" name="solution">
        <option value=1>SageMaker</option>
        <option value=2>Rekognition</option>
        <option value=3>Both</option>
    </select>
</form>
<input type="button" value="추론" id="file" class="inference-start"><br>
<h3>원본이미지</h3><h3>추론이미지</h3>
<div>
    <div id="image_container" style="display: inline;vertical-align: top;">
        <div class="attachment">
            <img src="" width="800" id="originalImage">
        </div>
    </div>
    <div id="image_infer_container" style="display: inline;">
        <div class="attachment">
            <img src="" id="inferImage">
        </div>
    </div>
</div>
</body>
</html>
