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

var checkSagemaker = 0;
var checkRekognition = 0;

var sageResult = "";
var rekogResult = "";

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
            beforeSend: function () {
                $("#ModalUpload").modal('show');
                Init();
            }
        }).done(function (result) {
            if(result){
                inference();
            } else {
                failUpload();
            }
        }).fail(function (result){
            failUpload();
        })
    });
});

function Init() {
    sageResult = "";
    rekogResult = "";
    checkSagemaker=0;
    checkRekognition=0;
    $("#sageText").text("");
    $("#sageImage").attr("src", "");
    $("#rekogText").text("");
    $("#rekogImage").attr("src", "");

    if($("#ModalSize").hasClass("modal-lg")) {
        $("#ModalSize").removeClass("modal-lg");
    }
    if($("#ModalSize").hasClass("modal-xl")) {
        $("#ModalSize").removeClass("modal-xl");
    }
}

function failUpload(){
    if(!alert("업로드에 실패했습니다.\n다시 시도해주십시오.")){
        $("ModalUpload").modal('hide');
    }
}

function inference() {
    var solution = $("#solution").val();
    switch (solution) {
        case "1":
            sagemaker();
            checkRekognition=1;
            break;
        case "2":
            checkSagemaker=1;
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
    }).done(function (result){
        checkSagemaker=1;
        sageResult = result;
        showResult();
    }).fail(function(){
        failInference();
    });
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
    }).done(function (result) {
        checkRekognition=1;
        rekogResult = result;
        showResult();
    }).fail(function(){
        failInference();
    });
}

function showResult(){
    if(checkSagemaker && checkRekognition){
        $("#ModalUpload").modal("hide");

        var twiceCheck = 0;

        if(this.sageResult) {
            var sageResult = JSON.parse(this.sageResult);
            $("#sageText").text("SageMaker");
            $("#sageImage").attr("src", sageResult.result);
            twiceCheck++;
        }

        if(this.rekogResult) {
            var rekogResult = JSON.parse(this.rekogResult);
            $("#rekogText").text("Rekognition");
            $("#rekogImage").attr("src", rekogResult.result);
            twiceCheck++;
        }

        if(twiceCheck>=2){
            $("#ModalSize").addClass("modal-lg");
        } else {
            $("#ModalSize").addClass("modal-xl");
        }

        checkSagemaker=0;
        checkRekognition=0;

        $("#ModalInference").modal("show");
    }
}

function failInference(){
    if(!alert("추론에 실패했습니다.\n다시 시도해주십시오.")){
        $("#ModalUpload").modal('hide');
    }
}