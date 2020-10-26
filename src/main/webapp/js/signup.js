$(document).ready(function() {
    $("#signup-button").click(function () {
        var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers= {};
        headers[csrfHeader] = csrfToken;

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if (!reg_email.test(document.signup.eID.value)) {
            document.formsignin.eID.focus();
            return;
        }
        else if(document.signup.password.value==""){
            document.formsignin.password.focus();
            return;
        } else if(document.signup.name.value==""){
            document.formsignin.name.focus();
            return;
        } else if(document.signup.phonenumber.value==""){
            document.formsignin.phonenumber.focus();
            return;
        }
        
        var regexp= /^\d{10,11}$/;
        v = document.signup.phonenumber.value;

        if (!regexp.test(v)) {
            alert("올바른 핸드폰번호를 입력해주세요.");
            document.formsignin.phonenumber.focus();
            return;
        }

        var form = new FormData(document.getElementById('signup'));

        $.ajax({
            type: "POST",
            url: '/signup',
            headers:headers,
            data: form,
            dataType: 'text',
            processData: false,
            contentType: false,
            cache: false,
        }).done(function(result){
            var result = JSON.parse(result)
            alert(result.msg);
            window.location.href = result.url;
        });
    });
});

