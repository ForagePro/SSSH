var pageNo=1;
$(function () {

    var toshow=$.get("../user/toShowAll",{"pageNo":pageNo,"pageSize":$(".select").val()},function (data) {
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
function toFind() {
    var datemin=$("#datemin").val();
    var datemax=$("#datemax").val();
    if (datemin.length==0)
        datemin="1900-00-00";
    if (datemax.length==0){
        var date=new Date();
        datemax=formatDate1(date)
    }else {
        datemax=$("#datemax").val();
    }
    $.get("../user/toFind",{"datemin":datemin,"datemax":datemax,"pageNo":pageNo,"pageSize":$(".select").val()},function (data) {
        if (data.length<($(".select").val())){
            $("#next").attr("disabled",true);
        }else {
            $("#next").attr("disabled",false);
        }
        $("#tb").empty();
        // console.log(data);
        addEle(data);
    },"json");
}
//查询
// $("#datemin").change(function () {
//     console.log( $("#datemin").length);
//     alert("说的是");
//     toFind();
// });
// $("#datemax").change(function () {
//     toFind();
// });

//动态添加
function addEle(data) {
    for (var i in data){
        var mode=data[i];
        var sex;
        if (mode.sex==0){
            sex='男';
        } else if (mode.sex==1){
            sex='女';
        }
        var status;
        var title;
        if (mode.status==0){
            status='已启用';
            title="停用"
            lab="success"
        } else if (mode.status==1){
            status='已停用';
            title="启用";
            lab="defaunt"
        }else if (mode.status==2){
            status='已删除';
            title="恢复";
            lab="danger"
        }
        var date=new Date(mode.registerTime);
        var time=formatDate(date);
        var date1=new Date(mode.birthday);
        var time1=formatDate1(date1);
        $("#tb").append('<tr class="text-c">\n' +
            '\t\t\t\t<td><input type="checkbox" value="1" name=""></td>\n' +
            '\t\t\t\t<td>'+ mode.id+'</td>\n' +
            '\t\t\t\t<td><img src="'+mode.imgPath+'"></td>\n' +
            '\t\t\t\t<td><u style="cursor:pointer" class="text-primary" onclick="member_show('+mode.username+',\'member-show.html\',\'10001\',\'360\',\'400\')">'+mode.username+'</u></td>\n' +
            '\t\t\t\t<td >'+sex+'</td>\n' +
            '\t\t\t\t<td>'+mode.phone+'</td>\n' +
            '\t\t\t\t<td>'+mode.email+'</td>\n' +
            '\t\t\t\t<td class="text-l">'+time1+'</td>\n' +
            '\t\t\t\t<td>'+time+'</td>\n' +
            '\t\t\t\t<td class="td-status"><span class="label label-'+lab+' radius" id="span'+mode.id+'">'+status+'</span></td>\n' +
            '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" onClick="member_update('+mode.id+','+mode.status+')" href="javascript:;" title="'+title+'"><i class="Hui-iconfont">&#xe631;</a> <a title="删除" href="javascript:;" onclick="member_dell('+mode.id+','+mode.status+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>\n' +
            '\t\t\t</tr>');
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
function formatDate1(times) {
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    return year+"-"+month+"-"+date;
}

function member_update(id,status) {
    var flag;
    if (status==0){
        status=1;
        flag=confirm("确定停用");
    }else if (status==1){
        status=0;
        flag=confirm("确定启用");
    }else if (status==2) {
        status=0;
        flag=confirm("确定恢复");
    }

    if (flag){
        $.get("../user/toUpdateStatus",{"status":status,"id":id},function () {
            window.location.href="member-list.html";
        });
    }else {
        alert("未修改！")
    }
}
function member_dell(id,status) {
    var flag;
    if (status!=2){
        flag=confirm("确定删除");
        status=2;
        if (flag){
            $.get("../user/toUpdateStatus",{"status":status,"id":id},function () {
                window.location.href="member-list.html";
            });
        }else {
            alert("未修改！")
        }
    } else {
        flag=confirm("确定彻底删除");
        if (flag){
            $.get("../user/toDelete",{"id":id},function () {
                window.location.href="member-list.html";
            });
        }else {
            alert("未删除！")
        }
    }
}

//上一页
$("#previous").click(function () {
    pageNo++;
    window.location.href="member-list.html";
});
//下一页
$("#next").click(function () {
    if (pageNo==1){
        return;
    }
    pageNo--;
    window.location.href="member-list.html";
});
//搜索
$("#btn").click(function () {
    $.get("../user/toFindUser",{"keywords":$("#keywords").val(),"pageNo":pageNo,"pageSize":$(".select").val()},function (data) {
        $("#tb").empty();
        if (data.length==0){
            alert("用户不存在");
        } else {
            addEle(data);
        }
    },"json");
});