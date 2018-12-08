var page=1;
var path = "/" + location.pathname.split("/")[1];

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
    var id=GetQueryString("id");
    var status=GetQueryString("status");
    var way;
    if(status==1){
        way="待付款";
    }else if(status==2){
        way="待发货";
    }else if(status==3){
        way="待收货";
    }else if(status==4){
        way="交易成功";
    }else if(status==5){
        way="退款中";
    }else if(status==6){
        way="已退款";
    }else if(status==7){
        way="交易关闭";
    }
    $.get(path+"/order/getOrder.do",
        {"id":id},
        function (data) {
            if(data!="false"){
                $("#o-user").append('<span id="oCode">订单号：'+data.oCode+'</span><span class="label label-success radius" style="margin-left: 40px">'+way+'</span><br>'+
                '<span>用户账号：'+data.user.username+'</span>');
                $("#o-add").append('<span>收货人：'+data.receiveaddress.name+'</span><br><span>收货电话：'+data.receiveaddress.phone+'</span><br>'+
                '<span>收货地址：'+data.receiveaddress.address+'</span><br>');
                if(data.buymessage!=""){
                    $("#o-add").append('<span>买家留言：'+data.buymessage+'</span><br>');
                }
                if(data.invoicetitle!=""){
                    $("#o-add").append('<span>发票抬头：'+data.invoicetitle+'</span><br>');
                }
                var downprice=0;
                for (var i in data.set){
                    var mode=data.set[i];
                    downprice=mode.commodity.downprice*mode.num+downprice;
                    $("#tb").append('<tr class="text-c">\n' +
                        '\t\t\t\t<td>'+ mode.commodity.id+'</td>\n' +
                        '\t\t\t\t<td><img src="'+path+mode.commodity.imgPath+'" width="100px" height="70px"></td>\n'+
                        '\t\t\t\t<td>'+ mode.commodity.name+'</td>\n' +
                        '\t\t\t\t<td>'+ mode.commodity.price+'</td>\n' +
                        '\t\t\t\t<td>'+ mode.commodity.minsaleweight+mode.commodity.unit+'</td>\n' +
                        '\t\t\t\t<td>'+ mode.num+'</td>\n'
                    )
                }
                $("#o-breed").append('<span>直降金额：'+downprice+'元</span><br>');
                $("#o-breed").append('<span>配送费：郑州--->'+data.receiveaddress.address+'('+data.dId+'元)</span><br>');
                $("#o-breed").append('<span>实付款：'+data.sum+'元</span><br>');

                var date1=new Date(data.ordertime.subTime);
                var date2=new Date(data.ordertime.payTime);
                var date3=new Date(data.ordertime.sendTime);
                var date4=new Date(data.ordertime.recTime);
                var date5=new Date(data.ordertime.retTime);
                var date6=new Date(data.ordertime.sretTime);
                var date7=new Date(data.ordertime.closeTime);
                var time1=formatDate(date1);
                var time2=formatDate(date2);
                var time3=formatDate(date3);
                var time4=formatDate(date4);
                var time5=formatDate(date5);
                var time6=formatDate(date6);
                var time7=formatDate(date7);
                if(data.ordertime.status==1){
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span>');
                }else if(data.ordertime.status==2){
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                    '<span>付款时间：'+time2+'</span>');
                }else if(data.ordertime.status==3){
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                        '<span>付款时间：'+time2+'</span><br>'+
                    '<span>发货时间：'+time3+'</span>');
                }else if(data.ordertime.status==4){
                    $("#o-order").append('<span>物流名称：'+data.ordertime.name+'</span><br>'+
                        '<span id="code">物流单号：'+data.ordertime.code+'</span><br>');
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                        '<span>付款时间：'+time2+'</span><br>'+
                        '<span>发货时间：'+time3+'</span><br>'+
                    '<span>确认收货时间：'+time4+'</span>');
                }else if(data.ordertime.status==5){
                    $("#o-order").append('<span>物流名称：'+data.ordertime.name+'</span><br>'+
                        '<span id="code">物流单号：'+data.ordertime.code+'</span><br>');
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                        '<span>付款时间：'+time2+'</span><br>'+
                        '<span id="way">发货时间：'+time3+'</span><br>'+
                    '<span>申请退款时间：'+time5+'</span>');
                }else if(data.ordertime.status==6){
                    $("#o-order").append('<span>物流名称：'+data.ordertime.name+'</span><br>'+
                        '<span id="code">物流单号：'+data.ordertime.code+'</span><br>');
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                        '<span>付款时间：'+time2+'</span><br>'+
                        '<span>发货时间：'+time3+'</span><br>'+
                        '<span>申请退款时间：'+time5+'</span><br>'+
                    '<span>退款时间：'+time6+'</span>');
                }else if(data.ordertime.status==7){
                    $("#o-order").append('<span>物流名称：'+data.ordertime.name+'</span><br>'+
                        '<span id="code">物流单号：'+data.ordertime.code+'</span><br>');
                    $("#o-order").append('<span>提交订单时间：'+time1+'</span><br>'+
                        '<span>付款时间：'+time2+'</span><br>'+
                        '<span>发货时间：'+time3+'</span><br>'+
                        '<span>确认收货时间：'+time4+'</span><br>'+
                    '<span>交易关闭时间：'+time7+'</span>');
                }

            }
        },"json")
})

function formatDate(times){
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    var hour=times.getHours();
    var minute=times.getMinutes();
    var second=times.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}

$(function () {
    $("#o-submit").click(function () {
        var tmp=$('input[name="radio"]:checked').val();
        if(tmp==0){
            $.get(path+"/order/updateStatus.do",
                {"code":$("#oCode").text()},
                function (data) {
                    if(data=="true"){
                        window.location.href="order-list.html";
                    }
                },"json")
        }else {
            var way1=$("#way").text();
            var status;
            if(way1==null){
                status=2;
            }else {
                status=3;
            }
            $.get(path+"/order/updateStatusByCode.do",
                {"code":$("#oCode").text(),"status":status},
                function (data) {
                    if(data=="true"){
                        window.location.href="order-list.html";
                    }
                },"json")
        }

    })
})