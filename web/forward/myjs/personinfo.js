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
    /*console.log(username);*/
    var path = "/" + location.pathname.split("/")[1];
    $.get( path+"/user/findUser.do", function (data) {
        console.log(data);
        if (data == "false") {
            $("#s-name").text("用户不存在");
        } else {
            $("#u-id").attr("value",data[0].id);
            $(".am-circle").attr("src", path+data[0].imgPath);
            $("#s-name").text(data[0].username);
            $("#user-name2").attr("value", data[0].name);
            $("#man:checked").val(data[0].sex);
            if (data[0].sex == 0) {
                $("#man").attr("checked", "checked");
            } else if (data[0].sex == 1) {
                $("#women").attr("checked", "checked");
            }

            var date=new Date(data[0].birthday);
            var time=formatDate1(date);
            $("#u-birthday").val(time);
            console.log(time);
            $("#user-email").attr("value", data[0].email);
        }
    }, "json");
});
function formatDate1(times) {
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    if(date.valueOf()>0&&date.valueOf()<10){
        date="0"+date;
    }
    return year+"-"+month+"-"+date;
}

$(function(){
    $("#imgPath").on("change",function(){
        var path = "/" + location.pathname.split("/")[1];
        var username=$("#s-name").text();
        var data=new FormData($("#imgfile")[0]);
        $.ajax({
            type:"POST",
            url:path+"/user/updateUserImg.do",
            data:data,
            dataType:"json",
            cache:false,
            processData:false,
            contentType:false,
            success:function(data){
                console.log(data);
                window.location.href = "index.html?username="+username;
            },
            error:function(){

            }
        });
    });
});

$(function () {
    $("#am-updatuser").click(function () {
        var path = "/" + location.pathname.split("/")[1];
        var username = $("#s-name").text();
        $.post(path+"/user/updateUser.do",
            {"id": $("#u-id").val(), "name": $("#user-name2").val(), "sex": $('input[name="radio10"]:checked').val(), "birthday": $("#u-birthday").val(), "email": $("#user-email").val()},
            function (data) {
                if (data == "OK") {
                    window.location.href = "index.html?username=" + username;
                } else {
                    alert("添加失败");
                }
            }, "json");
    });
});
