
$(function () {
    //用户登录
    var path="/"+location.pathname.split("/")[1];
    $("#btn").click(function () {
        var tologin = $.post(path+"/user/toLogin", {"phone": $("#phone").val(), "password": $("#password").val()}, function (data) {
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
                }
            }
        },"json");
    });
})