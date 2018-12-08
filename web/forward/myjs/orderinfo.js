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
    var code=GetQueryString("code");
    $.get(path+"/order/getOrderByCode.do",
        {"code":code},
        function (data) {
            if(data!="false"){
                addEle(data);
            }
        },"json");
});

function addEle(data) {
    if(data.status==1){
        $("#bg1").css("opacity",1);
    }else if(data.status==2){
        $("#bg1").css("opacity",1);
        $("#bg2").css("opacity",1);
    }else if(data.status==3){
        $("#bg1").css("opacity",1);
        $("#bg2").css("opacity",1);
        $("#bg3").css("opacity",1);
    }else if(data.status==4){
        $("#bg1").css("opacity",1);
        $("#bg2").css("opacity",1);
        $("#bg3").css("opacity",1);
        $("#bg4").css("opacity",1);
    }else if(data.status==5){
        $("#bg1").css("opacity",1);
    }else if(data.status==7){
        $("#bg1").css("opacity",1);
        $("#bg2").css("opacity",1);
        $("#bg3").css("opacity",1);
        $("#bg4").css("opacity",1);
    }
    $("#order-name").text(data.ordertime.name);
    $(".package-number").text(data.ordertime.code);
    $(".new-txt").text(data.receiveaddress.name);
    $(".new-txt-rd2").text(data.receiveaddress.phone);
    $(".province").text(data.receiveaddress.province);
    $(".city").text(data.receiveaddress.city);
    $(".dist").text(data.receiveaddress.county);
    $(".street").text(data.receiveaddress.address);

    var date1=new Date(data.ordertime.subTime);
    var time=formatDate(date1);
    $("#order-main").empty();
    $("#order-main").append('<div className="order-status1">'+
        '<div className="order-title">'+
            '<div className="dd-num">订单编号：<a href="javascript:;">'+data.oCode+'</a>'+
                '<span style="margin-left: 100px;line-height: 3">成交时间：'+time+'</span>'+
            '</div>'+
        '</div>'+
        '<div id="order-content" className="order-content">'+
            '<div className="order-left" id="pro">'+
            '</div>'+
            '<div id="order-right" className="order-right">'+
                '<li id="td-amount" className="td td-amount">'+
                    '<div id="item-amount" className="item-amount">合计：'+data.sum+
                        '<p>含运费：<span>'+data.dId+'</span></p>'+
                    '</div>'+
                '</li>'+
                '<div id="move-right" className="move-right">'+
                    '<li id="td-status" className="td td-status">'+
                        '<div id="item-status" className="item-status">'+

                        '</div>'+
                    '</li>'+
                    '<li id="td-change" className="td td-change">'+

                    '</li>'+
                '</div>'+
            '</div>'+
        '</div>'+
    '</div>');
    $("#order-content").addClass("order-content");
    $("#pro").addClass("order-left");
    $("#order-right").addClass("order-right");
    $("#td-amount").addClass("td td-amount");
    $("#item-amount").addClass("item-amount");
    $("#move-right").addClass("move-right");
    $("#td-status").addClass("td td-status");
    $("#item-status").addClass("item-status");
    $("#Mystatus").addClass("Mystatus");
    $("#td-change").addClass("td td-change");

    for(var j in data.set){
        var item=data.set[j];
        $("#pro").append('<ul id="item-list" className="item-list">'+
            '<li id="td-item'+j+'" className="td td-item">'+
                '<div id="item-pic'+j+'" className="item-pic">'+
                    '<a id="J_MakePoint'+j+'" href="#" className="J_MakePoint">'+
                        '<img id="J_ItemImg'+j+'" src="'+path+item.commodity.imgPath+'" className="itempic J_ItemImg">'+
                    '</a>'+
                '</div>'+
                '<div id="item-info'+j+'" className="item-info">'+
                    '<div id="item-basic-info'+j+'" className="item-basic-info">'+
                        '<a href="#">'+
                            '<p>'+item.commodity.name+'</p>'+
                        '</a>'+
                    '</div>'+
                '</div>'+
            '</li>'+
            '<li id="td-price'+j+'" className="td td-price">'+
                '<div id="item-price'+j+'" className="item-price">'+item.commodity.price+'/'+item.commodity.unit+'</div>'+
            '</li>'+
            '<li id="td-number'+j+'" className="td td-number">'+
                '<div id="item-number'+j+'" className="item-number">'+
                    '<span>×</span>'+item.num+
                '</div>'+
            '</li>'+
            '<li id="td-operation'+j+'" className="td td-operation">'+
                '<div id="item-operation'+j+'" className="item-operation">'+
            '</div>'+
            '</li>'+
            '</ul>');

        $("#item-list"+j).addClass("item-list");
        $("#td-item"+j).addClass("td td-item");
        $("#item-pic"+j).addClass("item-pic");
        $("#J_MakePoint"+j).addClass("J_MakePoint");
        $("#J_ItemImg"+j).addClass("itempic J_ItemImg");
        $("#item-info"+j).addClass("item-info");
        $("#item-basic-info"+j).addClass("item-basic-info");
        $("#td-price"+j).addClass("td td-price");
        $("#item-price"+j).addClass("item-price");
        $("#td-number"+j).addClass("td td-number");
        $("#item-number"+j).addClass("item-number");
        $("#td-operation"+j).addClass("td td-operation");
        $("#item-operation"+j).addClass("item-operation");
       if(data.status==2){
            $("#item-operation"+j).append('<a style="color: #000" onclick="drawback('+data.oCode+')">退款</a>');
        }else if(data.status==3){
            $("#item-operation"+j).append('<a style="color: #000" onclick="drawback('+data.oCode+')">退款/退货</a>');
        }else if(data.status==4){
            $("#item-operation"+j).append('<a style="color: #000" onclick="drawback('+data.oCode+')">退款/退货</a>');
        }
    }
    if(data.status==2){
        $("#item-status").append('<p id="Mystatus-l2" className="Mystatus">买家已付款</p>');
        $("#Mystatus-l2").addClass("Mystatus");
        $("#td-change").append('<div id="am-btn-danger-l2" className="am-btn am-btn-danger anniu">提醒发货</div>');
        $("#am-btn-danger-l2").addClass("am-btn am-btn-danger anniu");
    }else if(data.status==3){
        $("#item-status").append('<p id="Mystatus-l3" className="Mystatus">卖家已发货</p>'+
            '<p id="order-info-l3-2" className="order-info"><a href="">查看物流</a></p>'+
            '<p id="order-info-l3-3" className="order-info"><a href="">延长收货</a></p>');
        $("#Mystatus-l3").addClass("Mystatus");
        $("#order-info-l3-2").addClass("order-info");
        $("#order-info-l3-3").addClass("order-info");
        $("#td-change").append('<div id="am-btn-danger-l3" className="am-btn am-btn-danger anniu"><a style="color: #fff" onclick="receiptStatus(this,'+data.oCode+')">确认收货</a></div>');
        $("#am-btn-danger-l3").addClass("am-btn am-btn-danger anniu");
    }else if(data.status==4){
        $("#item-status").append('<p id="Mystatus-l4" className="Mystatus">交易成功</p>'+
            '<p id="order-info-l4-2" className="order-info"><a href="">查看物流</a></p>');
        $("#Mystatus-l4").addClass("Mystatus");
        $("#order-info-l4-2").addClass("order-info");
        $("#td-change").append('<div id="am-btn-danger-l4" className="am-btn am-btn-danger anniu"><a style="color: #fff" href="commentlist.html?code='+data.oCode+'">评价商品</a></div>');
        $("#am-btn-danger-l4").addClass("am-btn am-btn-danger anniu");
    }else if(data.status==5){
        $("#item-status").append('<p id="Mystatus-l5" className="Mystatus">退款中</p>');
        $("#Mystatus-l5").addClass("Mystatus");
        $("#td-change").append('<div id="am-btn-danger-l5" className="am-btn am-btn-danger anniu">');
       /* $("#am-btn-danger-l5").addClass("am-btn am-btn-danger anniu");*/
    }else if(data.status==7){
        $("#item-status").append('<p id="Mystatus-l7" className="Mystatus">交易关闭</p>');
        $("#Mystatus-l7").addClass("Mystatus");
        $("#td-change").append('<div id="am-btn-danger-l7" className="am-btn am-btn-danger anniu"></div>');
        /*$("#am-btn-danger-l7").addClass("am-btn am-btn-danger anniu");*/
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

function receiptStatus(tmp,code) {
    $.get(path + "/order/receiptStatus.do",
        {"code": code},
        function (data) {
            if(data=="true"){
                window.location.href="order.html";
            }
        },"json");
}

function drawback(code) {
    $.get(path+"/order/closeOrder.do",
        {"code":code,"status":5},
        function (data) {
            if(data=="true"){
                window.location.href="order.html";
            }
        },"json");
}