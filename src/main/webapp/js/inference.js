function setThumbnail(event)
{
    var reader = new FileReader();
    reader.onload = function(event)
    {
        $("#Oritext").text("원본이미지");
        $("#originalImage").attr("src", event.target.result);

        $("#inferImageText").text("");
        $("#inferImage").attr("src","");
    };

    reader.readAsDataURL(event.target.files[0]);

}
$(document).ready(function() {
    $("#file").click(function () {
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers= {};
        headers[csrfHeader] = csrfToken;

        var form = new FormData(document.getElementById('uploadForm'));

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: '/originals3',
            headers:headers,
            data: form,
            dataType: 'text',
            processData: false,
            contentType: false,
            cache: false,
        }).done(function (result) {
            if(result){
                inference()
            } else {
                alert("추론에 실패했습니다.\n다시 시도해주십시오.")
            }

        });
    });
});

// var result = JSON.parse(result)
// $("#inferImageText").text("추론 이미지");
// $("#inferImage").attr("src", result.sagemaker);

function inference() {
    var solution = $("#solution").val();
    switch (solution) {
        case "1":
            sagemaker();
            break;
        case "2":
            rekognition();
            break;
        case "3":
            sagemaker();
            rekognition();
            break;
    }
}
function sagemaker(){
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers= {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: '/sagemaker',
        headers:headers,
        dataType: 'text',
        processData: false,
        contentType: false,
        cache: false,
    }).done(
    );
}

function rekognition(){
    var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var headers= {};
    headers[csrfHeader] = csrfToken;

    $.ajax({
        type: "POST",
        url: '/rekognition',
        headers:headers,
        dataType: 'text',
        processData: false,
        contentType: false,
        cache: false,
    }).done(
    );
}