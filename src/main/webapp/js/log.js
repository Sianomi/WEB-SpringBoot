$(document).ready(function() {
    $('#logtable').DataTable(
        {
            "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
            "iDisplayLength": 5
        }
    );

    $('#logtable').on('click','a',function(event){
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers= {};
        headers[csrfHeader] = csrfToken;

        $("#inputs3path").val(event.target.getAttribute('s3path'));
        var form = new FormData(document.getElementById('logForm'))

        $.ajax({
            type: "POST",
            url: '/logs3image',
            headers:headers,
            data:form,
            processData: false,
            contentType: false,
            cache: false,
            beforeSend:function (){
                LogInit();
            }
        }).done(function (result){
            ShowLogImage(result);
        });
    })
});

function LogInit(){
    if($("#ModalSize").hasClass("modal-xl")) {
        $("#ModalSize").removeClass("modal-xl");
    }
    $("#ModalLogTitle").text("Download From S3..");
    $("#ModalLogText").text("이미지를 받아오는 중입니다..");
    $("#LogImage").attr("src", "");
    $("#ModalLog").modal("show");
}

function ShowLogImage(result){
    var result = JSON.parse(result);
    $("#ModalLogTitle").text("Result");
    $("#ModalLogText").text("");
    $("#ModalSize").addClass("modal-xl");
    $("#LogImage").attr("src", result.result);
}