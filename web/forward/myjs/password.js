$(function () {
    $("#user-old-password").blur(function () {
        var path = "/" + location.pathname.split("/")[1];
        $.post(path+"/user/originCode.do",
            {"password":$("#user-old-password").val()},
            function (data) {
                if (data == "true") {
                    $("#msg").text("密码正确");
                    $("#msg").css("color","green");
                    $("#r-pwd").attr('disabled',"true");
                } else {
                    $("#msg").text("密码错误");
                    $("#msg").css("color","red");
                    $("#r-pwd").attr('disabled',"true");
                }
            }, "json");

    })

});

$(function () {
    $("#r-pwd").click(function () {
        var path = "/" + location.pathname.split("/")[1];
        $.post(path+"/user/updatePwd.do",
            {"password":$("#user-confirm-password").val()},
            function (data) {
                if (data == "true") {
                    $("#msg").text("更新成功");
                    $(".step-2 .u-stage-icon-inner .bg").css("opacity",1);
                    $("#msg").css("color","green");
                } else {
                    $("#msg").text("更新失败");
                    $("#msg").css("color","red");
                }
            }, "json");

    })

});

function uname() {
    if (/^[0-9a-zA-Z_]{6,15}$/.test($("#account").val())) {
        $("#span").html("用户名符合要求").css("color","green");
        return true;
    }else {
        $("#span").html("用户名不符合要求").css("color","red");
        return false;
    }
}
//密码验证
function pass() {
    if (/^[0-9a-zA-Z_]{6,15}$/.test($("#user-new-password").val())) {
        var pwd=$("#user-old-password").val();
        var rpwd=$("#user-new-password").val();
        if(pwd==rpwd){
            $("#msg1").html("密码与原密码一致").css("color","red");
            $("#r-pwd").attr('disabled',"true");
            return false;
        }
        $("#msg1").html("密码符合要求").css("color","green");
        $("#r-pwd").attr('disabled',"true");
        return true;
    }else {
        $("#msg1").html("密码不符合要求").css("color","red");
        $("#r-pwd").attr('disabled',"true");
        return false;
    }
}

function rpass() {
    if (/^[0-9a-zA-Z_]{6,15}$/.test($("#user-confirm-password").val())) {
        var rpwd=$("#user-new-password").val();
        var srpwd=$("#user-confirm-password").val();
        var pwd=$("#user-old-password").val();
        if(pwd==srpwd){
            $("#msg2").html("确认密码与原密码一致").css("color","red");
            $("#r-pwd").attr('disabled',"true");
            return false;
        }
        if(pwd==rpwd){
            $("#msg2").html("密码与原密码一致").css("color","red");
            $("#r-pwd").attr('disabled',"true");
            return false;
        }
        if(srpwd==rpwd){
            $("#msg2").html("新密码与确认密码一致").css("color","green");
            $("#r-pwd").removeAttr("disabled");
            return true;
        }
        $("#msg2").html("新密码与确认密码不一致").css("color","red");
        $("#r-pwd").attr('disabled',"true");
        return true;
    }else {
        $("#msg2").html("密码不符合要求").css("color","red");
        $("#r-pwd").attr('disabled',"true");
        return false;
    }
}
