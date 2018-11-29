
$(function () {
    //用户登录
    var path="/"+location.pathname.split("/")[1];
    $("#btn").click(function () {
        var tologin = $.get("../../user/toLogin", {"username": $("#user").val(), "password": $("#password").val()}, function (data) {
            //var tip = $("#error");
            for (var key in data[0]){
                console.log(key);
                console.log(data[0]);
                if (key=="1000") {
                    alert(data[0][key]);
                } else if (key == "1001") {
                    alert(data[0][key]);
                } else if (key == "1002") {
                    //用户名密码正确，跳转页面
                    window.location.href = "../person/index.html";
                }else if (key == "1003") {
                    alert(data[0][key]);
                }else if (key == "1004") {
                    alert(data[0][key]);
                }
            }
        },"json");
    });



    //注册
    $("#rbtn").click(function () {
        if (uname()==true&&pass()==true&&repass()==true){
            var toRegister = $.get("../../user/toRegister",{"username":$("#user").val(),"password":$("#password").val()},function (data) {
                alert(data);
                // window.location.href="register.html";
            },"json");
        }else {
            alert("请输入正确的信息");
        }

    });

});
//用户注册
//手机号验证
// function pho() {
//     if ($("#phone").val() == "") {
//         //alert("手机号码不能为空！");
//         $("#span").html("手机号码不能为空").css("color","red");
//         return false;
//     }
//     if (!$("#phone").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
//         $("#span").html("手机号码不正确").css("color","red");
//         return false;
//     }
//     $("#span").html("手机号码正确").css("color","green");
//     return true;
// }
//用户名验证
function uname() {
    if (/^[0-9a-zA-Z_]{6,15}$/.test($("#user").val())) {
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
};
//确认密码
function repass() {
    if (($("#password").val())==($("#passwordRepeat").val())) {
        $("#span").html("密码相同").css("color","green");
        return true;
    }else {
        $("#span").html("密码不相同").css("color","red");
        return false;
    }
};