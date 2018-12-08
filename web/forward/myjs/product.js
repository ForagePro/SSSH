var path = "/" + location.pathname.split("/")[1];
$(function () {
    //展示商品
    var status=0;
    $.get(path+"/commodity/toShow",{"status":status},function (data) {
        for (var i in data){
            var mode=data[i];
            $(".am-thumbnails").append('<li>\n' +
                '\t\t\t\t\t\t\t\t<div class="list ">\n' +
                '\t\t\t\t\t\t\t\t\t<a href="introduction.html?id='+mode.id+'">\n' +
                '\t\t\t\t\t\t\t\t\t\t<img height="188" src="'+(path+mode.imgPath)+'" />\n' +
                '\t\t\t\t\t\t\t\t\t\t<div class="pro-title ">'+mode.name+'</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="e-price ">￥'+mode.price+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t</a>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t</li>');
        }
    },"json");

    //获取用户
    $.get(path+"/read/toGetUser",function (data) {
        if (data.username==undefined){
            $("#bbb").append('<a class="am-btn-warning btn" href="login.html">登录</a>\n' +
                '\t\t\t\t\t\t\t\t<a class="am-btn-warning btn" href="register.html">注册</a>');
        }else {
            $("#s-name").html(data.username);
            $("#uimg").attr("src",path+data.imgPath);
        }
    },"json")


});
