var path = "/" + location.pathname.split("/")[1];
var code=showWindowHref();
$(function () {
    //显示地址

    console.log(code);
    $.get(path+"/read/toShow",function (data) {
        //console.log(data);
        for (var i in data){
            var mode=data[i];
            var adds;
            var cla;
            if (mode.status==0){
                adds="";
                cla="user-addresslist";
            } else {
                adds="默认地址";
                cla="user-addresslist defaultAddr";
            }
            $("#address1").append('<div class="per-border"></div>\n' +
                '\t\t\t\t<li class="'+cla+'">\n' +
                '<input type="radio" name="address" onclick="selAddress('+mode.id+','+code+')">\n' +
                '\t\t\t\t\t<div class="address-left">\n' +
                '\t\t\t\t\t\t<div class="user DefaultAddr">\n' +
                '\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="buy-address-detail">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t<span class="buy-user">'+mode.name+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t<span class="buy-phone">'+mode.phone+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t</span>\n' +
                '\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t<div class="default-address DefaultAddr">\n' +
                '\t\t\t\t\t\t\t<span class="buy-line-title buy-line-title-type">收货地址：</span>\n' +
                '\t\t\t\t\t\t\t<span class="buy--address-detail">\n' +
                '\t\t\t\t\t\t\t\t   <span class="province">'+mode.province+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="city">'+mode.city+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="dist">'+mode.county+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="street">'+mode.address+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t</span>\n' +
                '\t\t\t\t\t\t\t</span>\n' +
                '\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t<ins class="deftip">'+adds+'</ins>\n' +
                '\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t<div class="address-right">\n' +
                '\t\t\t\t\t\t<a href="../person/address.html">\n' +
                '\t\t\t\t\t\t\t<span class="am-icon-angle-right am-icon-lg"></span></a>\n' +
                '\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t<div class="clear"></div>\n' +
                '\n' +
                '\t\t\t\t\t<div class="new-addr-btn">\n' +
                '\t\t\t\t\t\t<a href="#" class="hidden">设为默认</a>\n' +
                '\t\t\t\t\t\t<span class="new-addr-bar hidden">|</span>\n' +
                '\t\t\t\t\t\t<a href="../person/address.html">编辑</a>\n' +
                '\t\t\t\t\t\t<span class="new-addr-bar">|</span>\n' +
                '\t\t\t\t\t\t<a href="javascript:void(0);" onclick="delClick('+mode.id+');">删除</a>\n' +
                '\t\t\t\t\t</div>');
        }
    },"json");

    // console.log(code);
    $.post(path+"/orderdetails/toShowOne",{"code":code},function (data) {
        // console.log(data);
        var sum=0;
        var shoplist=data.set;
        for(var i in shoplist){
            var mode=shoplist[i];
            var commodity=mode.commodity;
            sum+=parseFloat(((commodity.price-commodity.downprice)*mode.num).toFixed(1));

            $(".cart-table-th").append('<ul class="item-content clearfix">\n' +
                '\t\t\t\t\t\t<li class="td td-chk">\n' +
                '\t\t\t\t\t\t\t<div class="cart-checkbox ">\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '\t\t\t\t\t\t<li class="td td-item">\n' +
                '\t\t\t\t\t\t\t<div class="item-pic">\n' +
                '\t\t\t\t\t\t\t\t<a href="#" target="_blank" class="J_MakePoint" data-point="tbcart.8.12">\n' +
                '\t\t\t\t\t\t\t\t\t<img src="'+(path+commodity.imgPath)+'" class="itempic J_ItemImg"></a>\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t<div class="item-info">\n' +
                '\t\t\t\t\t\t\t\t<div class="item-basic-info">\n' +
                '\t\t\t\t\t\t\t\t\t<a href="" target="_blank" class="item-title J_MakePoint" data-point="tbcart.8.11">'+commodity.name+'</a>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '\t\t\t\t\t\t<li class="td td-info">\n' +
                '\t\t\t\t\t\t\t<div class="item-props item-props-can"></div>\n' +
                '\t\t\t\t<span class="sku-line">'+commodity.details+'</span>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '\t\t\t\t\t\t<li class="td td-price">\n' +
                '\t\t\t\t\t\t\t<div class="item-price price-promo-promo">\n' +
                '\t\t\t\t\t\t\t\t<div class="price-content">\n' +
                '\t\t\t\t\t\t\t\t\t<div class="price-line">\n' +
                // '\t\t\t\t\t\t\t\t\t\t<em class="price-original">￥'+commodity.price+'</em>\n' +
                '\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t<div class="price-line">\n' +
                '\t\t\t\t\t\t\t\t\t\t<em class="J_Price price-now" tabindex="0">￥'+(commodity.price-commodity.downprice)+'</em>\n' +
                '\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '\t\t\t\t\t\t<li class="td td-amount">\n' +
                '\t\t\t\t\t\t\t<div class="amount-wrapper ">\n' +
                '\t\t\t\t\t\t\t\t<div class="item-amount ">\n' +
                '\t\t\t\t\t\t\t\t\t<div class="sl">\n' +
                '\t\t\t\t\t\t\t\t\t\t<input class="min am-btn" name="" type="button" onclick="toSubNum('+mode.id+')" value="-" />\n' +
                '\t\t\t\t\t\t\t\t\t\t<input class="text_box" id="num'+mode.id+'" name="" type="text" value="'+mode.num+'" style="width:30px;" onchange="toUpdateNum('+mode.id+')" />\n' +
                '\t\t\t\t\t\t\t\t\t\t<input class="add am-btn" name="" type="button" onclick="toAddNum('+mode.id+')" value="+" />\n' +
                '\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '\t\t\t\t\t\t<li class="td td-sum">\n' +
                '\t\t\t\t\t\t\t<div class="td-inner">\n' +
                '\t\t\t\t\t\t\t\t<em tabindex="0" class="J_ItemSum number">'+((commodity.price-commodity.downprice)*mode.num).toFixed(1)+'</em>\n' +
                '\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t</li>\n' +
                '<li class="td td-oplist">\n' +
                '\t\t\t\t<div class="td-inner">\n' +
                // '\t\t\t\t<span class="phone-title">配送方式</span>\n' +
                // '\t\t\t\t<div class="pay-logis">'+$("#way").html()+'</div>\n' +
                '\t\t\t\t</div>\n' +
                '\t\t\t\t\t</ul>');

        }
        $(".pay-sum").html(parseFloat(sum).toFixed(1));
    },"json");

});
function delClick(id) {
    var flag=confirm("确认删除？")
    if (flag){
        $.post(path+"/read/toDelete",{"id":id},function () {
            window.location.href="pay.html?code="+code;
        });
    }
}
//添加地址
$("#toSave").click(function () {
    var name=$("#user-name").val();
    var phone=$("#user-phone").val();
    var code=$("#user-code").val();
    var province=$("#s_province").val();
    var city=$("#s_city").val();
    var county=$("#s_county").val();
    var address=$("#user-intro").val();
    if (pho()&&name.length!=0&&province!='省份'&&city!='地级市'&&county!='市、县级市'&&address.length!=0){
        $.post(path+"/read/toAdd",{"name":name,"phone":phone,"code":code,"province":province,"city":city,"county":county,"address":address},function () {
            window.location.href="pay.html?code="+code;
        });
    }
    else {
        alert("请填写完整的信息!");
    }
});
//手机号验证
function pho() {
    if ($("#user-phone").val() == "") {
        //alert("手机号码不能为空！");
        return false;
    }
    if (!$("#user-phone").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
        //alert("手机号码不正确");
        return false;
    }
    // alert("手机号码正确");
    return true;
}
//邮编验证
function postcode(value)
{
    var reg=/^[1-9][0-9]{5}$/;
    var re = new RegExp(reg);
    if (re.test(value))
    {
        return true;
    }
    else
    {
        return false;
    }
}
function selAddress(id) {
    return id;
}
//确认消息
function toSubNum(id) {
    //console.log($("#num"+id).val());
    var num=$("#num"+id).val();
    if (num==1){
        num=1;
        $(".min").attr("disabled",true);
        return;
    }
    $(".min").attr("disabled",false);
    $("#num"+id).val(--num);
    $.get(path+"/shoplist/toUpdate",{"id":id,"num":num},function () {
        window.location.href="pay.html?code="+code;
    });
}
function toAddNum(id) {
    var num=$("#num"+id).val();
    $("#num"+id).val(++num);
    $.get(path+"/shoplist/toUpdate",{"id":id,"num":num},function () {
        window.location.href="pay.html?code="+code;
    });
}

function toUpdateNum(id) {
    var num=$("#num"+id).val();
    console.log(num);
    $.get(path+"/shopcart/toUpdate",{"id":id,"num":num},function () {
        window.location.href="shopcart.html";
    });
}
//获取参数
function showWindowHref() {
    var sHref = window.location.href;
    var args = sHref.split('?');
    if (args[0] == sHref) {
        return "";
    }
    var arr = args[1].split('=');
    return arr[1];
}

//选择地址

function selAddress(id,code) {
    // var code=showWindowHref();
    $.get(path+"/dispatch/toPay",{"id":id,"code":code},function (data) {
        // console.log(data);
        $("#way").html(data.way);
        $("#freight").html(data.cost);
    },"json");
}

$("#J_Go").click(function () {
    // var code=showWindowHref();
    var buymassage=$("#buymassage").val();
    var freight=$("#freight").text()
    var pay=$(".pay-sum").text();
    var sum=parseFloat(freight)+parseFloat(pay);
    var flag=confirm("确定支付人民币"+parseFloat(sum).toFixed(1)+"元？");
    if (flag){
        $.post(path+"/orderdetails/toUpdate",{"buymessage":buymassage,"sum":pay,"oCode":code},function () {
            window.location.href="success.html?code="+code;
        })
    }
});
