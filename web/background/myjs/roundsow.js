var path = "/" + location.pathname.split("/")[1];
var pageNo=1;
//显示
function toShow(){
    $.get(path+"/round/toShow",{"pageNo":pageNo,"pageSize":$(".select").val()},function (data) {
        $("#num").html(data.length);
        if (data.length<($(".select").val())){
            $("#next").attr("disabled",true);
        }else {
            $("#next").attr("disabled",false);
        }
        $("#tb").empty();
        addEle(data);
    },"json");
}

$(function () {
    toShow();
});
$(".select").change(function () {
    toShow();
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
        } else if (mode.status==1){
            status='已下架';
            title="上架";
            lab="defaunt"
        }else if (mode.status==2){
            status='已删除';
            title="恢复";
            lab="danger"
        }
        var date=new Date(mode.createTime);
        var time=formatDate(date);
        $("#tb").append('<tr class="text-c">\n' +
            '\t\t\t\t\t<td><input name="" type="checkbox" value=""></td>\n' +
            '\t\t\t\t\t<td>'+mode.id+'</td>\n' +
            '\t\t\t\t\t<td>'+mode.type+'</td>\n' +
            '\t\t\t\t\t<td><a href="javascript:;" onClick="picture_edit(\'图库编辑\',\'picture-show.html\',\'10001\')"><img width="210" class="picture-thumb" src="'+(path+mode.imgPath)+'"></a></td>\n' +
            '\t\t\t\t\t<td>'+mode.sort+'</td>\n' +
            '\t\t\t\t\t<td>'+time+'</td>\n' +
            '\t\t\t\t\t<td class="td-status"><span class="label label-'+lab+' radius">'+status+'</span></td>\n' +
            '\t\t\t\t\t<td class="td-manage"><a style="text-decoration:none" onClick="picture_stop1('+mode.id+','+mode.status+')" href="javascript:;" title="'+title+'"><i class="Hui-iconfont">&#xe6de;</i></a> <a style="text-decoration:none" class="ml-5" onClick="picture_del1('+mode.id+','+mode.status+')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
            '\t\t\t\t</tr>');
    }
}
function formatDate(times) {
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    var hour=times.getHours();
    var minute=times.getMinutes();
    var second=times.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}

//下一页
$("#next").click(function () {
    pageNo++;
    toShow();
});
//上一页
$("#previous").click(function () {
    if (pageNo==1){
        return;
    }
    pageNo--;
    toShow()
});

function picture_stop1(id,status) {
    var flag;
    if (status==0){
        status=1;
        flag=confirm("确定下架");
    }else if (status==1){
        status=0;
        flag=confirm("确定上架");
    }else if (status==2) {
        status=0;
        flag=confirm("确定恢复");
    }

    if (flag){
        $.get(path+"/round/toUpdateStatus",{"status":status,"id":id},function (data) {
            alert(data);
            window.location.href="picture-list.html";
        },"json");
    }else {
        alert("未修改！")
    }
}
function picture_del1(id,status) {
    var flag;
    if (status!=2){
        flag=confirm("确定删除");
        status=2;
        if (flag){
            $.get(path+"/round/toUpdateStatus",{"status":status,"id":id},function () {
                window.location.href="picture-list.html";
            });
        }else {
            alert("未修改！")
        }
    } else {
        flag=confirm("确定彻底删除");
        if (flag){
            $.get("../round/toDelete",{"id":id},function () {
                window.location.href="picture-list.html";
            });
        }else {
            alert("未删除！")
        }
    }
}

