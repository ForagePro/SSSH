var path = "/" + location.pathname.split("/")[1];

$(function () {
    $.get(path+"/order/getOrderByUId.do",
        function (data) {
            if(data.length>0){
                $("#order-list0").empty();
                addEle(data,1,0);
            }else {

            }
        },"json")
});

$(function () {
    $("#tab-1").click(function () {
        $.get(path+"/order/getOrderByUId.do",
            function (data) {
                if(data.length>0){
                    $("#order-list0").empty();
                    addEle(data,1,0);
                }else {

                }
            },"json")
    })
})

$(function () {
    $("#tab-2").click(function () {
        $.get(path+"/order/getOrderByUser.do",
            {"status":1},
            function (data) {
                if(data.length>0){
                    $("#order-list1").empty();
                    addEle(data,2,1);
                }else {

                }
            },"json")
    })
})

$(function () {
    $("#tab-3").click(function () {
        $.get(path+"/order/getOrderByUser.do",
            {"status":2},
            function (data) {
                if(data.length>0){
                    $("#order-list2").empty();
                    addEle(data,3,2);
                }else {

                }
            },"json")
    })
})
$(function () {
    $("#tab-4").click(function () {
        $.get(path+"/order/getOrderByUser.do",
            {"status":3},
            function (data) {
                if(data.length>0){
                    $("#order-list3").empty();
                    addEle(data,4,3);
                }else {

                }
            },"json")
    })
})

$(function () {
    $("#tab-5").click(function () {
        $.get(path+"/order/getOrderByUser.do",
            {"status":4},
            function (data) {
                if(data.length>0){
                    $("#order-list4").empty();
                    addEle(data,5,4);
                }else {

                }
            },"json")
    })
})

//动态添加
function addEle(data,tmp1,tmp) {
    for (var i in data){
        var mode=data[i];
        var subTime=mode.ordertime.subTime;
        var date=new Date(subTime);
        var time=formatDate(date);
        $("#order-list"+tmp).append('<div className="order-status1">'+
            '<div className="order-title">'+
                '<div className="dd-num">订单编号：<a id="order-code'+i+'" href="javascript:;">'+mode.oCode+'</a>'+
                    '<span style="margin-left: 100px;line-height: 3">成交时间：'+time+'</span>'+
                '</div>'+
            '</div>'+
            '<div id="order-content'+tmp1+i+'" className="order-content">'+
                '<div className="order-left" id="pro'+tmp1+i+'">'+
                '</div>'+
                '<div id="order-right'+tmp1+i+'" className="order-right">'+
                    '<li id="td-amount'+tmp1+i+'" className="td td-amount">'+
                        '<div id="item-amount'+tmp1+i+'" className="item-amount">合计：'+mode.sum+
                            '<p>含运费：<span>'+mode.dId+'</span></p>'+
                        '</div>'+
                    '</li>'+
                    '<div id="move-right'+tmp1+i+'" className="move-right">'+
                        '<li id="td-status'+tmp1+i+'" className="td td-status">'+
                            '<div id="item-status'+tmp1+i+'" className="item-status">'+

                            '</div>'+
                        '</li>'+
                        '<li id="td-change'+tmp1+i+'" className="td td-change">'+

                        '</li>'+
                    '</div>'+
                '</div>'+
            '</div>'+
        '</div>');
        $("#order-content"+tmp1+i).addClass("order-content");
        $("#pro"+tmp1+i).addClass("order-left");
        $("#order-right"+tmp1+i).addClass("order-right");
        $("#td-amount"+tmp1+i).addClass("td td-amount");
        $("#item-amount"+tmp1+i).addClass("item-amount");
        $("#move-right"+tmp1+i).addClass("move-right");
        $("#td-status"+tmp1+i).addClass("td td-status");
        $("#item-status"+tmp1+i).addClass("item-status");
        $("#Mystatus"+tmp1+i).addClass("Mystatus");
        $("#td-change"+tmp1+i).addClass("td td-change");



        for(var j in mode.set){
            var item=mode.set[j];
            $("#pro"+tmp1+i).append('<ul id="item-list" className="item-list">'+
                '<li id="td-item'+tmp1+i+j+'" className="td td-item">'+
                    '<div id="item-pic'+tmp1+i+j+'" className="item-pic">'+
                        '<a id="J_MakePoint'+tmp1+i+j+'" href="#" className="J_MakePoint">'+
                            '<img id="J_ItemImg'+tmp1+i+j+'" src="'+path+item.commodity.imgPath+'" className="itempic J_ItemImg">'+
                        '</a>'+
                    '</div>'+
                    '<div id="item-info'+tmp1+i+j+'" className="item-info">'+
                        '<div id="item-basic-info'+tmp1+i+j+'" className="item-basic-info">'+
                            '<a href="#">'+
                                '<p>'+item.commodity.name+'</p>'+
                            '</a>'+
                        '</div>'+
                    '</div>'+
                '</li>'+
                '<li id="td-price'+tmp1+i+j+'" className="td td-price">'+
                    '<div id="item-price'+tmp1+i+j+'" className="item-price">'+item.commodity.price+'/'+item.commodity.unit+'</div>'+
                '</li>'+
                '<li id="td-number'+tmp1+i+j+'" className="td td-number">'+
                    '<div id="item-number'+tmp1+i+j+'" className="item-number">'+
                        '<span>×</span>'+item.num+
                    '</div>'+
                '</li>'+
                '<li id="td-operation'+tmp1+i+j+'" className="td td-operation">'+
                    '<div id="item-operation'+tmp1+i+j+'" className="item-operation">'+
                    '</div>'+
                '</li>'+
            '</ul>');

            $("#item-list"+tmp1+i+j).addClass("item-list");
            $("#td-item"+tmp1+i+j).addClass("td td-item");
            $("#item-pic"+tmp1+i+j).addClass("item-pic");
            $("#J_MakePoint"+tmp1+i+j).addClass("J_MakePoint");
            $("#J_ItemImg"+tmp1+i+j).addClass("itempic J_ItemImg");
            $("#item-info"+tmp1+i+j).addClass("item-info");
            $("#item-basic-info"+tmp1+i+j).addClass("item-basic-info");
            $("#td-price"+tmp1+i+j).addClass("td td-price");
            $("#item-price"+tmp1+i+j).addClass("item-price");
            $("#td-number"+tmp1+i+j).addClass("td td-number");
            $("#item-number"+tmp1+i+j).addClass("item-number");
            $("#td-operation"+tmp1+i+j).addClass("td td-operation");
            $("#item-operation"+tmp1+i+j).addClass("item-operation");
            if(mode.status==1){

            }else if(mode.status==2){
                $("#item-operation"+tmp1+i+j).append('<a style="color: #000" onclick="drawback('+mode.oCode+')">退款</a>');
            }else if(mode.status==3){
                $("#item-operation"+tmp1+i+j).append('<a style="color: #000" onclick="drawback('+mode.oCode+')">退款/退货</a>');
            }else if(mode.status==4){
                $("#item-operation"+tmp1+i+j).append('<a style="color: #000" onclick="drawback('+mode.oCode+')">退款/退货</a>');
            }else if(mode.status==5){

            }else if(mode.status==6){

            }else if(mode.status==7){

            }
        }
        if(mode.status==1){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l1'+tmp1+i+'" className="Mystatus">等待买家付款</p>'+
                '<p id="order-info-l1'+tmp1+i+'" className="order-info"><a style="color: #000" onclick="closeOrder('+mode.oCode+')">取消订单</a></p>');
            $("#Mystatus-l1"+tmp1+i).addClass("Mystatus");
            $("#order-info-l1"+tmp1+i).addClass("order-info");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l1'+tmp1+i+'" className="am-btn am-btn-danger anniu"><a style="color:#fff" href="../home/pay.html?code='+mode.oCode+'">一键付款</a></div>');
            $("#am-btn-danger-l1"+tmp1+i).addClass("am-btn am-btn-danger anniu");
        }else if(mode.status==2){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l2'+tmp1+i+'" className="Mystatus">买家已付款</p>'+
                '<p id="order-info-l2'+tmp1+i+'" className="order-info"><a href="orderinfo.html?code='+mode.oCode+'">订单详情</a></p>');
            $("#Mystatus-l2"+tmp1+i).addClass("Mystatus");
            $("#order-info-l2"+tmp1+i).addClass("order-info");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l2'+tmp1+i+'" className="am-btn am-btn-danger anniu">提醒发货</div>');
            $("#am-btn-danger-l2"+tmp1+i).addClass("am-btn am-btn-danger anniu");
        }else if(mode.status==3){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l3'+tmp1+i+'" className="Mystatus">卖家已发货</p>'+
                '<p id="order-info-l3-1'+tmp1+i+'" className="order-info"><a href="orderinfo.html?code='+mode.oCode+'">订单详情</a></p>'+
                '<p id="order-info-l3-2'+tmp1+i+'" className="order-info"><a href="">查看物流</a></p>'+
                '<p id="order-info-l3-3'+tmp1+i+'" className="order-info"><a href="">延长收货</a></p>');
            $("#Mystatus-l3"+tmp1+i).addClass("Mystatus");
            $("#order-info-l3-1"+tmp1+i).addClass("order-info");
            $("#order-info-l3-2"+tmp1+i).addClass("order-info");
            $("#order-info-l3-3"+tmp1+i).addClass("order-info");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l3'+tmp1+i+'" className="am-btn am-btn-danger anniu"><a style="color:#fff" onclick="receiptStatus(this,'+mode.oCode+')">确认收货</a></div>');
            $("#am-btn-danger-l3"+tmp1+i).addClass("am-btn am-btn-danger anniu");
        }else if(mode.status==4){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l4'+tmp1+i+'" className="Mystatus">交易成功</p>'+
                '<p id="order-info-l4-1'+tmp1+i+'" className="order-info"><a href="orderinfo.html?code='+mode.oCode+'">订单详情</a></p>'+
                '<p id="order-info-l4-2'+tmp1+i+'" className="order-info"><a href="">查看物流</a></p>');
            $("#Mystatus-l4"+tmp1+i).addClass("Mystatus");
            $("#order-info-l4-1"+tmp1+i).addClass("order-info");
            $("#order-info-l4-2"+tmp1+i).addClass("order-info");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l4'+tmp1+i+'" className="am-btn am-btn-danger anniu"><a style="color: #fff" onclick="closeOrder('+mode.oCode+')" href="commentlist.html?code='+mode.oCode+'">评价商品</a></div>');
            $("#am-btn-danger-l4"+tmp1+i).addClass("am-btn am-btn-danger anniu");
        }else if(mode.status==5){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l5'+tmp1+i+'" className="Mystatus">退款中</p>'+
                '<p id="order-info-l5-1'+tmp1+i+'" className="order-info"><a href="orderinfo.html?code='+mode.oCode+'">订单详情</a></p>');
            $("#Mystatus-l5"+tmp1+i).addClass("Mystatus");
            $("#order-info-l5-1"+tmp1+i).addClass("order-info");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l5'+tmp1+i+'" className="am-btn am-btn-danger anniu"></div>');
            /*$("#am-btn-danger-l5"+tmp1).addClass("am-btn am-btn-danger anniu");*/
        }else if(mode.status==6){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l6'+tmp1+i+'" className="Mystatus">退款成功</p>');
            $("#Mystatus-l6"+tmp+i).addClass("Mystatus");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l6'+tmp1+i+'" className="am-btn am-btn-danger anniu"></div>');
            /*$("#am-btn-danger-l6"+tmp1).addClass("am-btn am-btn-danger anniu");*/
        }else if(mode.status==7){
            $("#item-status"+tmp1+i).append('<p id="Mystatus-l7'+tmp1+i+'" className="Mystatus">交易关闭</p>');
            $("#Mystatus-l7"+tmp1+i).addClass("Mystatus");
            $("#td-change"+tmp1+i).append('<div id="am-btn-danger-l7'+tmp1+i+'" className="am-btn am-btn-danger anniu"></div>');
            /*$("#am-btn-danger-l7"+tmp1).addClass("am-btn am-btn-danger anniu");*/
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

function receiptStatus(tmp,code) {
    $.get(path + "/order/receiptStatus.do",
        {"code": code},
        function (data) {
            if(data=="true"){
                window.location.href="order.html";
            }
        },"json");
}

function closeOrder(code) {
    $.get(path+"/order/closeOrder.do",
        {"code":code,"status":7},
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