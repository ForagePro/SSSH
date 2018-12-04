var page=1;
var path = "/" + location.pathname.split("/")[1];
$(function () {
    $.get(path+"/threeLevelLinkage/getProvinces.do",
        function (data) {
            if(data!="false"){
                for(var i=0;i<data.length;i++){
                    $("#d-a-pro").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
                }
            }
        },"json")
});

$(function () {
    $("#d-a-pro").on("change",function () {
        $.get(path+"/threeLevelLinkage/getCity.do",
            {"provinceid":$("#d-a-pro").val()},
            function (data) {
                $("#d-a-city").empty();
                $("#d-a-city").append("<option value='选择市'>选择市</option>");
                $("#d-a-area").empty();
                $("#d-a-area").append("<option value='选择县区'>选择县区</option>");
                if(data!="false"){
                    for(var i=0;i<data.length;i++){
                        $("#d-a-city").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>")
                    }
                }
            },"json")
    });
});

$(function () {
    $("#d-a-city").on("change",function () {
        $.get(path+"/threeLevelLinkage/getArea.do",
            {"cityid":$("#d-a-city").val()},
            function (data) {
                $("#d-a-area").empty();
                $("#d-a-area").append("<option value='选择县区'>选择县区</option>");
                if(data!="false"){
                    for(var i=0;i<data.length;i++){
                        $("#d-a-area").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>")
                    }
                }
            },"json")
    });
});

$(function () {
    $("#d-a-area").on("change",function () {
        var province=$("#d-a-pro").find("option:selected").text();
        var city=$("#d-a-city").find("option:selected").text();
        var area=$("#d-a-area").find("option:selected").text();
            $.post(path+"/dispatch/testDest.do",
                {"destination":province+"-"+city+"-"+area},
                function (data) {
                    if(data=="true"){
                        $("#msg").text("地址已存在，不可以添加");
                        $("#msg").css("color","red");
                        $("#c-a-submit").addClass("disabled");
                        return;
                    }else {
                        $("#msg").text("地址不存在，可以添加");
                        $("#msg").css("color","green");
                        $("#c-a-submit").removeClass("disabled");
                        return;
                    }
                },"json")
    });
});

function addDispatch() {
    var dest=$("#d-a-pro").find("option:selected").text()+"-"+$("#d-a-city").find("option:selected").text()+"-"+$("#d-a-area").find("option:selected").text();
    $.post(path+"/dispatch/addDispatch.do",
        {"way":$('input[name="way"]:checked').val(),"destination":dest,"cost":$("#d-a-cost").val(),"content":$("#d-a-content").val()},
        function (data) {
            if(data=="true"){
                window.location.href="dispatch-list.html";
            }else {
                $("#msg3").text("添加失败！");
                $("#msg3").css("color","red");
            }
        },"json");
}

$(function () {
    var province=$("#d-a-pro").find("option:selected").text();
    var city=$("#d-a-city").find("option:selected").text();
    var area=$("#d-a-area").find("option:selected").text();
    $.post(path+"/dispatch/getList.do",
        {
            "province":province,
            "city":city,
            "area":area,
            "way":$("#d-l-status").find("option:selected").text(),
            "page":page,
            "num":$(".select").find("option:selected").text()
        },
        function (data) {
            $("#num").html(data.length);
            if (data.length<($(".select").val())){
                $("#next").attr("disabled",true);
            }else {
                $("#next").attr("disabled",false);
            }
            $("#tb").empty();
            addEle(data);
        },"json");
});

$(function () {
    $("#d-l-submit").click(function getList(){
        var province=$("#d-a-pro").find("option:selected").text();
        console.log(province);
        var city=$("#d-a-city").find("option:selected").text();
        var area=$("#d-a-area").find("option:selected").text();
        $.post(path + "/dispatch/getList.do",
            {
                "province": province,
                "city": city,
                "area": area,
                "way":$("#d-l-status").find("option:selected").text(),
                "page": page,
                "num": $(".select").find("option:selected").text()
            },
            function (data) {
                $("#num").html(data.length);
                if (data.length < ($(".select").val())) {
                    $("#next").attr("disabled", true);
                } else {
                    $("#next").attr("disabled", false);
                }
                $("#tb").empty();
                addEle(data);
            }, "json");
    });
});
//动态添加
function addEle(data) {
    for (var i in data){
        var mode=data[i];
        var date=new Date(mode.createTime);
        var time=formatDate(date);
        $("#tb").append('<tr class="text-c">\n' +
            '\t\t\t\t<td><input type="checkbox" value="1" name=""></td>\n' +
            '\t\t\t\t<td>'+ mode.id+'</td>\n' +
            '\t\t\t\t<td>'+ mode.destination+'</td>\n' +
            '\t\t\t\t<td>'+ mode.cost+'</td>\n' +
            '\t\t\t\t<td>'+ mode.way+'</td>\n' +
            '\t\t\t\t<td>'+ mode.content+'</td>\n' +
            '\t\t\t\t<td>'+time+'</td>\n' +
            '\t\t\t\t<td class="td-manage"><a title="修改" href="'+path+'/background/dispatch-edit.html?id='+mode.id+'" className="ml-5" style="text-decoration:none"><i className="Hui-iconfont">&#xe6df;</i></a>&nbsp;'+
            '\t\t\t</tr>');
    }
}

function formatDate(times){
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    var hour=times.getHours();
    var minute=times.getMinutes();
    var second=times.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}

//上一页
$("#previous").click(function () {
    page++;
    window.location.href="category-list.html";
});
//下一页
$("#next").click(function () {
    if (page==1){
        return;
    }
    page--;
    window.location.href="category-list.html";
});

function GetQueryString(name)
{
    //(^|&)匹配^开始的位置匹配的第一个参数，&匹配的不是第一个参数
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);//search,查询？后面的参数，并匹配正则
    if(r!=null)
        return  unescape(r[2]);
    return null;
}

$(function () {
    var id = GetQueryString("id");
    $.post(path+"/dispatch/editDispatch.do",
        {"id":id},
        function (data) {
            if(data!="false"){
                $("#d-id").val(data.id);
                if (data.way == "物流") {
                    $("#e-logistics").attr("checked", "checked");
                } else{
                    $("#e-delivery").attr("checked", "checked");
                }
                $("#d-e-dest").text(data.destination);

                $("#d-e-cost").val(data.cost);
                $("#d-e-content").val(data.content);
            }else {
                alert("用户不存在！");
            }
        },"json");
});

/*function split(tmp){
    var arr=tmp.split("-");
    return arr;
}*/
$(function () {
    $("#d-e-submit").click(function () {
        $.post(path+"/dispatch/update.do",
            {
                "id":$("#d-id").val(),
                "way":$('input[name="way"]:checked').val(),
                "cost":$("#d-e-cost").val(),
                "content":$("#d-e-content").val()
            },
            function (data) {
                if(data=="true"){
                    window.location.href="dispatch-list.html";
                }else {
                    $("#msg3").text("修改失败！");
                    $("#msg3").css("color","red");
                }
            },"json");
    });
});

