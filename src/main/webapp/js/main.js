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
            url: '/infer',
            headers:headers,
            data: form,
            dataType: 'text',
            processData: false,
            contentType: false,
            cache: false,
        }).done(function(result){
            var result = JSON.parse(result)
            $("#inferImageText").text("추론 이미지");
            $("#inferImage").attr("src", result.sagemaker);
        });
    });
});