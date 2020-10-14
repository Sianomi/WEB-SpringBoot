<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html> 
<head> 
    <meta charset="UTF-8" /> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Inference</title> 
    <script> 
        function setThumbnail(event) 
        { 
            var reader = new FileReader(); 
            reader.onload = function(event) 
            {
                document.getElementById("originalImageText").innerHTML="원본 이미지";
                document.querySelector("#originalImage").setAttribute("src", event.target.result)

                document.getElementById("inferImageText").innerHTML="추론 이미지";
                document.querySelector("#inferImage").setAttribute("src", event.target.result)
            };            

            reader.readAsDataURL(event.target.files[0]); 

        } 
        </script>
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>


</head>
<body>
    <script>
        $(document).ready(function() {
            $("#file").click(function () {
                var form = new FormData(document.getElementById('uploadForm'))
                $.ajax({
                    type: "POST",
                    url: '/upload',
                    data: form,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function (result) {
                    },
                    error: function (e) {
                    }
                });
            });
        });
    </script>
    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="fileId" name="file-data" accept="image/jpeg" onchange="setThumbnail(event);"/>
    </form>
    <input type="button" value="업로드" id="file"/>
    <div>
        <div id="image_container" style="display: inline-block;vertical-align: top;">
            <h3 id="originalImageText"></h3>
            <img src="" width="800" id="originalImage">
        </div>
        <div id="image_infer_container" style="display: inline-block">
            <h3 id="inferImageText"></h3>
            <img src="" width="800" id="inferImage">
        </div>
    </div>
</body> 
</html>
