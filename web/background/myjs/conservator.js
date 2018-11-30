var path = "/" + location.pathname.split("/")[1];
$(function () {
//管理员登录

    $("#btn").click(function () {
        if (uname()==true&&pass()==true&&code1()==true) {
            var tologin = $.get(path+"/conser/toLogin", {
                "account": $("#account").val(),
                "password": $("#password").val(),
                "code": $("#codes").val()
            }, function (data) {
                for (var key in data[0]) {
                    console.log(key);

                    if (key == "1000") {
                        alert(data[0][key]);
                    } else if (key == "1001") {
                        alert(data[0][key]);
                    } else if (key == "1002") {
                        //用户名密码正确，跳转页面
                        window.location.href = "index.html";
                    } else if (key == "1003") {
                        alert(data[0][key]);
                    }
                }
            }, "json");
        } else {
            $("#span").html("请输入正确的信息").css("color","red");
        }
    });

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
    if (/^[0-9a-zA-Z_]{6,15}$/.test($("#password").val())) {
        $("#span").html("密码符合要求").css("color","green");
        return true;
    }else {
        $("#span").html("密码不符合要求").css("color","red");
        return false;
    }
}
// 验证码验证
function code1() {
    if (/^[0-9a-zA-Z_]{4}$/.test($("#codes").val())) {
        $("#span").html("验证码符合要求").css("color","green");
        return true;
    }else {
        $("#span").html("验证码不符合要求").css("color","red");
        return false;
    }
}