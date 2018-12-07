var path = "/" + location.pathname.split("/")[1];

$(function () {
    $.get(path+"/order/getOrderByUId.do",
        function (data) {
            if(data.length>0){
                addEle(data);
            }else {

            }
        },"json")
})

//动态添加
function addEle(data) {
    for (var i in data){
        var mode=data[i];
        var date=new Date(mode.ordertime.subTime);
        var time=formatDate(date);
        $("#order-list").append('<div className="order-status5">'+
            '<div className="order-title">'+
                '<div className="dd-num">订单编号：<a href="javascript:;">'+mode.oCode+'</a>'+
                    '<span style="margin-left: 100px;line-height:3">成交时间：'+time+'</span>'+
                '</div>'+
            '</div>'+
            '<div className="order-content">'+
                '<div className="order-left" style="border: 1px #cccccc solid" id="pro'+i+'">'+
                '</div>'+
            '</div>'+
        '</div>');
        for(var j in mode.set){
            var item=mode.set[j];
            $("#pro"+i).append('<ul className="item-list">'+
                '<li className="td td-item" style="margin-top: 25px">'+
                    '<div className="item-pic">'+
                        '<a href="#" className="J_MakePoint">'+
                            '<img src="'+path+item.commodity.imgPath+'" style="width: 90px;height: 80px" className="itempic J_ItemImg">'+
                        '</a>'+
                    '</div>'+
                    '<div className="item-info">'+
                        '<div className="item-basic-info">'+
                            '<a href="#">'+
                                '<p>'+item.commodity.name+'</p>'+
                            '</a>'+
                        '</div>'+
                    '</div>'+
                '</li>'+
                '<li className="td td-price">'+
                    '<div className="item-price">'+item.commodity.price+'/'+item.commodity.unit+'</div>'+
                '</li>'+
                '<li className="td td-number">'+
                    '<div className="item-number">'+
                        '<span>×</span>'+item.num+
                    '</div>'+
                '</li>'+
                '<li className="td td-operation">'+
                    '<div className="item-operation">'


            )
        }



    }
}

{/*
<div className="order-status5">


                <li className="td td-operation">
                    <div className="item-operation">

                    </div>
                </li>
            </ul>

            <ul className="item-list">
                <li className="td td-item">
                    <div className="item-pic">
                        <a href="#" className="J_MakePoint">
                            <img src="../images/62988.jpg_80x80.jpg" className="itempic J_ItemImg">
                        </a>
                    </div>
                    <div className="item-info">
                        <div className="item-basic-info">
                            <a href="#">
                                <p>礼盒袜子女秋冬 纯棉袜加厚 韩国可爱 </p>
                                <p className="info-little">颜色分类：李清照
                                    <br/>尺码：均码</p>
                            </a>
                        </div>
                    </div>
                </li>
                <li className="td td-price">
                    <div className="item-price">
                        333.00
                    </div>
                </li>
                <li className="td td-number">
                    <div className="item-number">
                        <span>×</span>2
                    </div>
                </li>
                <li className="td td-operation">
                    <div className="item-operation">

                    </div>
                </li>
            </ul>

            <ul className="item-list">
                <li className="td td-item">
                    <div className="item-pic">
                        <a href="#" className="J_MakePoint">
                            <img src="../images/kouhong.jpg_80x80.jpg" className="itempic J_ItemImg">
                        </a>
                    </div>
                    <div className="item-info">
                        <div className="item-basic-info">
                            <a href="#">
                                <p>美康粉黛醉美唇膏 持久保湿滋润防水不掉色</p>
                                <p className="info-little">颜色：12#川南玛瑙
                                    <br/>包装：裸装 </p>
                            </a>
                        </div>
                    </div>
                </li>
                <li className="td td-price">
                    <div className="item-price">
                        333.00
                    </div>
                </li>
                <li className="td td-number">
                    <div className="item-number">
                        <span>×</span>2
                    </div>
                </li>
                <li className="td td-operation">
                    <div className="item-operation">

                    </div>
                </li>
            </ul>
        </div>
        <div className="order-right">
            <li className="td td-amount">
                <div className="item-amount">
                    合计：676.00
                    <p>含运费：<span>10.00</span></p>
                </div>
            </li>
            <div className="move-right">
                <li className="td td-status">
                    <div className="item-status">
                        <p className="Mystatus">交易成功</p>
                        <p className="order-info"><a href="orderinfo.html">订单详情</a></p>
                        <p className="order-info"><a href="logistics.html">查看物流</a></p>
                    </div>
                </li>
                <li className="td td-change">
                    <div className="am-btn am-btn-danger anniu">
                        删除订单
                    </div>
                </li>
            </div>
        </div>
    </div>
</div>*/}

function formatDate(times){
    var year=times.getFullYear();
    var month=times.getMonth()+1;
    var date=times.getDate();
    var hour=times.getHours();
    var minute=times.getMinutes();
    var second=times.getSeconds();
    return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
}
