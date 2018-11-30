function GetQueryString(name)
{
    //(^|&)匹配^开始的位置匹配的第一个参数，&匹配的不是第一个参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)
        return  unescape(r[2]);
    return null;
}

$(function() {
    /*var username = GetQueryString("username");*/
    var path = "/" + location.pathname.split("/")[1];
    $.get(path + "/user/findUser.do", function (data) {
        if (data == "false") {
            $("#s-name").text("用户不存在");
        } else {
            $("#img-user").attr("src",  path+data[0].imgPath);
            $("#s-name").text(data[0].username);
        }
    }, "json");

    $(".m-pic").click(function () {
        window.location.href = "../person/information.html";
    })
})
