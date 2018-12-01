var page=1;
var path = "/" + location.pathname.split("/")[1];
//验证名称是否存在
function testName() {
    $.post(path+"/breed/testBreedName.do",
        {"name":$("#c-name").val()},
        function (data) {
        if(data=="true"){
            $("#meg").text("该品类已添加");
            $("#meg").css("color","red");
        }else {
            $("#meg").text("该品类未添加");
            $("#meg").css("color","green");
        }
        },"json");
}
//添加品类
function addBreed() {
    $.post(path+"/breed/addBreed.do",
        {"sort":$("#c-sort").val(),"name":$("#c-name").val(),"content":$("#c-content").val()},
        function (data) {
            if(data=="true"){
                window.location.href=path+"/background/category-list.html";
            }else {
                $("#msg3").text("添加失败");
            }
        },"json");
};

//获取名称
$.get(path+"/breed/getBreedName.do",
    function (data) {
        for(var i=0;i<data.length;i++){
            $("#c-l-name").append('<option>'+data[i].name+'</option>');
        }
    }, "json");

$(function () {
    $.post(path+"/breed/select.do",
        {
            "name":$("#c-l-name").val(),
            "status":$("#c-l-status").val(),
            "keyword":$("#c-l-content").val(),
            "num":$(".select").val(),
            "page":page
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
    $("#c-l-submit").click(function find() {
         $.post(path+"/breed/select.do",
            {
                "name":$("#c-l-name").val(),
                "status":$("#c-l-status").val(),
                "keyword":$("#c-l-content").val(),
                "num":$(".select").val(),
                "page":page
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
});

//动态添加
function addEle(data) {
    for (var i in data){
        var mode=data[i];
        var status;
        var title;
        if (mode.status==0){
            status='已上架';
            title="下架"
            lab="success"
        } else{
            status='已下架';
            title="上架";
            lab="defaunt"
        }
        var date=new Date(mode.createTime);
        var time=formatDate(date);
        $("#tb").append('<tr class="text-c">\n' +
            '\t\t\t\t<td><input type="checkbox" value="1" name=""></td>\n' +
            '\t\t\t\t<td>'+ mode.id+'</td>\n' +
            '\t\t\t\t<td>'+ mode.sort+'</td>\n' +
            '\t\t\t\t<td>'+ mode.name+'</td>\n' +
            '\t\t\t\t<td>'+ mode.content+'</td>\n' +
            '\t\t\t\t<td>'+time+'</td>\n' +
            '\t\t\t\t<td class="td-status"><span class="label label-'+lab+' radius" id="span'+mode.id+'">'+status+'</span></td>\n' +
            '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" onClick="member_update('+mode.id+','+mode.status+')" href="javascript:;" title="'+title+'"><i class="Hui-iconfont">&#xe631;</i></a>&nbsp;&nbsp;&nbsp;'+
                '<a title="修改" href="'+path+'/background/category-edit.html?id='+mode.id+'" className="ml-5" style="text-decoration:none"><i className="Hui-iconfont">&#xe6df;</i></a>&nbsp;'+
                '<a title="删除" href="javascript:;" onclick="member_dell('+mode.id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
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

function member_update(id,status) {
    var flag;
    if (status==0){
        status=1;
        flag=confirm("确定下架");
    }else{
        status=0;
        flag=confirm("确定上架");
    }

    if (flag){
        $.get(path+"/breed/updateStatus.do",{"status":status,"id":id},function () {
            window.location.href="category-list.html";
        });
    }else {
        alert("未修改！")
    }
}

function member_dell(id) {
    var flag;
    flag=confirm("确定删除");
    if (flag){
        var del=$.get(path+"/breed/delete.do",{"id":id},function () {
                window.location.href="category-list.html";
                return true;
        });
    }else {
        alert("未删除！")
    }
}