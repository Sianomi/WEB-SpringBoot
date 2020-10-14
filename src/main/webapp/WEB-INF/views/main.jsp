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
    <script> 
        function setThumbnail(event) 
        { 
            var reader = new FileReader(); 
            reader.onload = function(event) 
            {
                $("#originalImageText").text("원본 이미지");
                $("#originalImage").attr("src", event.target.result);

                $("#inferImageText").text("");
                $("#inferImage").attr("src","");
            };            

            reader.readAsDataURL(event.target.files[0]); 

        } 
        </script>



</head>
<body>
    <script>
        $(document).ready(function() {
            $("#file").click(function () {
                var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
                var csrfHeader = $("meta[name='_csrf_header']").attr("content");
                var csrfToken = $("meta[name='_csrf']").attr("content");
                var headers= {};
                headers[csrfHeader] = csrfToken;

                var form = new FormData(document.getElementById('uploadForm'))
                $.ajax({
                    type: "POST",
                    url: '/infer',
                    headers:headers,
                    data: form,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    cache: false,
                }).done(function(result){
                    $("#inferImageText").text("추론 이미지");
                    $("#inferImage").attr("src", result);
                });
            });
        });
    </script>
    <h2>회원 전용 페이지</h2>
    ID : ${name} <br>
    소유 권한 : ${auth}</span> <br>
    <form id="logout" action="/logout" method="POST">
        <sec:csrfInput />
        <input type="submit" value="로그아웃"/>
    </form>
    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="fileId" name="file-data" accept="image/jpeg" onchange="setThumbnail(event);"/>
    </form>
            <input type="button" value="추론" id="file"/>
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
