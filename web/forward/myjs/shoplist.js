var path = "/" + location.pathname.split("/")[1];
$(function () {
    var id=showWindowHref();
    $.get(path+"/commodity/toFind",{"id":id},function (data) {
        $("#img").attr("src",path+data.imgPath);
        $("#img").attr("mid",path+data.imgPath);
        $("#img").attr("big",path+data.imgPath);
        $("#toShow").attr("src",path+data.imgPath);
        $("#toShow").attr("rel",path+data.imgPath);
        $("#pname").html(data.name);
        $(".tb-detail-price").html('<li class="price iteminfo_price">\n' +
            '\t\t\t\t\t\t\t\t\t<dt>促销价</dt>\n' +
            '\t\t\t\t\t\t\t\t\t<dd><em>¥</em><b class="sys_item_price">'+(data.price-data.downprice)+'</b>  </dd>                                 \n' +
            '\t\t\t\t\t\t\t\t</li>\n' +
            '\t\t\t\t\t\t\t\t<li class="price iteminfo_mktprice">\n' +
            '\t\t\t\t\t\t\t\t\t<dt>原价</dt>\n' +
            '\t\t\t\t\t\t\t\t\t<dd><em>¥</em><b class="sys_item_mktprice">'+data.price+'</b></dd>\t\t\t\t\t\t\t\t\t\n' +
            '\t\t\t\t\t\t\t\t</li>');
        $(".stock").text(data.stockbalance);
    },"json");
    $("#LikBuy").attr("onclick","toBuy("+id+")");
    $("#LikBasket").attr("onclick","addCart("+id+")");
});
//商品详情
function showWindowHref() {
    var sHref = window.location.href;
    var args = sHref.split('?');
    if (args[0] == sHref) {
        return "";
    }
    var arr = args[1].split('=');
    //console.log(arr[1]);
    return arr[1];
}

//加入购物车
 function  addCart(id){
    var num=$("#text_box").val();
    $.post(path+"/shopcart/toAdd",{"cId":id,"num":num},function (data) {
        alert(data);
    },"json");
}
//立即购买
function toBuy(id) {
    var flag=confirm("确定购买？");
    if (flag) {
        var num = $("#text_box").val();
        $.post(path + "/orderdetails/toAddOne", {"num": num, "cId": id}, function (data) {
            window.location.href = "pay.html?code=" + data;
        },"json");
    }
}