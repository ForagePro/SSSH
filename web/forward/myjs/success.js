var path = "/" + location.pathname.split("/")[1];
var code=showWindowHref();
$(function () {
    $.post(path+"/orderdetails/toShowOne",{"code":code},function (data){
        $("#success").append('<li>付款金额<em>¥'+(data.sum+data.dId)+'</em></li>\n' +
            '       <div class="user-info">\n' +
            '         <p>收货人：'+data.receiveaddress.name+'</p>\n' +
            '         <p>联系电话：'+data.receiveaddress.phone+'</p>\n' +
            '         <p>收货地址：'+(data.receiveaddress.province+data.receiveaddress.city+data.receiveaddress.county+data.receiveaddress.address)+'</p>\n' +
            '       </div>');
    },"json");
});


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