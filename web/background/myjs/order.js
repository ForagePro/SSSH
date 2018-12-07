var page=1;
var path = "/" + location.pathname.split("/")[1];

$(function () {
    $.post(path+"/order/getOrderAll.do",
        {
            "type":$("#o-l-type").val(),
            "keyword":$("#o-l-key").val(),
            "status":$("#o-l-status").val(),
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
        },"json")
});

$(function () {
    $("#o-l-submit").click(function () {
        $.post(path+"/order/getOrderAll.do",
            {
                "type":$("#o-l-type").val(),
                "keyword":$("#o-l-key").val(),
                "status":$("#o-l-status").val(),
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
            },"json")
    })
})

//动态添加
function addEle(data) {
    for (var i in data){
        var mode=data[i];
        var date=new Date(mode.ordertime.subTime);
        var time=formatDate(date);
        if (mode.ordertime.status==1){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">待付款</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        } else if(mode.ordertime.status==2){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">待发货</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>&nbsp;&nbsp;&nbsp;'+
                '<a style="text-decoration:none" href="logistics.html?id='+mode.ordertime.id+'" title="发货"><i class="Hui-iconfont">发货</i></a>'+
                '\t\t\t</tr>');
        }else if(mode.ordertime.status==3){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">待收货</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        }else if(mode.ordertime.status==4){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">交易成功</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        }else if(mode.ordertime.status==5){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">退款中</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-evrify.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="审核"><i class="Hui-iconfont">审核</i></a>&nbsp;&nbsp;&nbsp;'+
                '<a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        }else if(mode.ordertime.status==6){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">已退款</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        }else if(mode.ordertime.status==7){
            $("#tb").append('<tr class="text-c">\n' +
                '\t\t\t\t<td>'+ mode.oCode+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.name+'</td>\n' +
                '\t\t\t\t<td>'+ mode.receiveaddress.phone+'</td>\n' +
                '\t\t\t\t<td class="td-status"><span class="label label-success radius" id="span'+mode.ordertime.status+'">交易关闭</span></td>\n' +
                '\t\t\t\t<td>'+time+'</td>\n' +
                '\t\t\t\t<td class="td-manage"><a style="text-decoration:none" href="order-detail.html?id='+mode.id+'&status='+mode.ordertime.status+'" title="查看"><i class="Hui-iconfont">查看</i></a>'+
                '\t\t\t</tr>');
        }
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
    window.location.href="order-list.html";
});
//下一页
$("#next").click(function () {
    if (page==1){
        return;
    }
    page--;
    window.location.href="order-list.html";
});

