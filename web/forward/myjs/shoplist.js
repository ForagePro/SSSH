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

    var cId=showWindowHref();
    //显示评价
    $.get(path+"/evaluate/toShow",{"cId":cId},function (data) {

        for (var i in data){
            var mode=data[i];
            console.log(mode);
            var time=new Date(mode.createTime);
            $("#comm").append('<li class="am-comment">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t<!-- 评论容器 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t<a href="">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<img class="am-comment-avatar" src="'+(path+mode.user.imgPath)+'" />\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<!-- 评论者头像 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t</a>\n' +
                '\n' +
                '\t\t\t\t\t\t\t\t\t\t\t<div class="am-comment-main">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<!-- 评论内容容器 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<header class="am-comment-hd">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t<!--<h3 class="am-comment-title">评论标题</h3>-->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t<div class="am-comment-meta">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- 评论元数据 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<a href="#link-to-user" class="am-comment-author">用户：'+mode.user.username+'</a>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<!-- 评论者 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t评论于\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<time datetime="">'+formatDate(time)+'</time>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t</header>\n' +
                '\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<div class="am-comment-bd">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t<div class="tb-rev-item " data-id="255776406962">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class="J_TbcRate_ReviewContent tb-tbcr-content ">\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t'+mode.context+'\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t<div class="tb-r-act-bar">\n' +
                // '\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t颜色分类：柠檬黄&nbsp;&nbsp;尺码：S\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t\t\t<!-- 评论内容 -->\n' +
                '\t\t\t\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t\t\t</li>');
        }
    },"json");

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

function formatDate(times) {
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    var hour=times.getHours();
    var minute=times.getMinutes();
    var second=times.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}