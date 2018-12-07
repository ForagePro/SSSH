var path = "/" + location.pathname.split("/")[1];
$(function () {
    $("#title").html('<strong class="am-text-danger am-text-lg">添加地址</strong> / <small>Add&nbsp;address</small>');
    $("#btn").html('<a class="am-btn am-btn-danger" id="toSave" onclick="toSave()">保存</a>');
    //显示地址
    $.get(path+"/read/toShow",function (data) {
        //console.log(data);
        for (var i in data){
            var mode=data[i];
            var adds;
            var cla;
            if (mode.status==0){
                adds="设为默认";
                cla="user-addresslist";
            } else {
                adds="默认地址";
                cla="user-addresslist defaultAddr";
            }
            $("#address1").append('<li class="'+cla+'">\n' +
                '\t\t\t\t\t\t\t\t<span class="new-option-r"><i class="am-icon-check-circle"></i><a onclick="toUpdateStatus('+mode.id+','+mode.status+')">'+adds+'</a></span>\n' +
                '\t\t\t\t\t\t\t\t<p class="new-tit new-p-re">\n' +
                '\t\t\t\t\t\t\t\t\t<span class="new-txt">'+mode.name+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t<span class="new-txt-rd2">'+mode.phone+'</span>\n' +
                '\t\t\t\t\t\t\t\t</p>\n' +
                '\t\t\t\t\t\t\t\t<div class="new-mu_l2a new-p-re">\n' +
                '\t\t\t\t\t\t\t\t\t<p class="new-mu_l2cw">\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="title">地址：</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="province">'+mode.province+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="city">'+mode.city+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="dist">'+mode.county+'</span>\n' +
                '\t\t\t\t\t\t\t\t\t\t<span class="street">'+mode.address+'</span></p>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t\t<div class="new-addr-btn">\n' +
                '\t\t\t\t\t\t\t\t\t<a href="#form" onclick="toEdit('+mode.id+')"><i class="am-icon-edit"></i>编辑</a>\n' +
                '\t\t\t\t\t\t\t\t\t<span class="new-addr-bar">|</span>\n' +
                '\t\t\t\t\t\t\t\t\t<a href="javascript:void(0);" onclick="delClick('+mode.id+');"><i class="am-icon-trash"></i>删除</a>\n' +
                '\t\t\t\t\t\t\t\t</div>\n' +
                '\t\t\t\t\t\t\t</li>');
        }
    },"json");


});
//地址
function toSave() {
    var name=$("#user-name").val();
    var phone=$("#user-phone").val();
    var code=$("#user-code").val();
    var province=$("#s_province").val();
    var city=$("#s_city").val();
    var county=$("#s_county").val();
    var address=$("#user-intro").val();
    if (pho()&&name.length!=0&&postcode(code)&&province!='省份'&&city!='地级市'&&county!='市、县级市'&&address.length!=0){
        $.post(path+"/read/toAdd",{"name":name,"phone":phone,"code":code,"province":province,"city":city,"county":county,"address":address},function () {
            window.location.href="address.html";
        });
    }
    else {
        alert("请填写完整的信息!");
    }
}
function toUpdate(id){
    var name=$("#user-name").val();
    var phone=$("#user-phone").val();
    var code=$("#user-code").val();
    var province=$("#s_province").val();
    var city=$("#s_city").val();
    var county=$("#s_county").val();
    var address=$("#user-intro").val();
    if (pho() && name.length != 0 && postcode(code) && province != '省份' && city != '地级市' && county != '市、县级市' && address.length != 0) {
        $.post(path + "/read/toUpdate", {
            "id": id,
            "name": name,
            "phone": phone,
            "code": code,
            "province": province,
            "city": city,
            "county": county,
            "address": address
        }, function () {
            window.location.href = "address.html";
        });
    }
    else {
        alert("请填写完整的信息!");
    }
}
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
//设置默认地址
function toUpdateStatus(id,status) {
    if (status==0){
        status=1;
    } else {
        status=0;
    }
    $.post(path+"/read/toUpdateStatus",{"id":id,"status":status},function (data) {
        if (data=="true"){
            window.location.href="address.html";
        }

    },"json")
}
function delClick(id) {
    var flag=confirm("确认删除？")
    if (flag){
        $.post(path+"/read/toDelete",{"id":id},function () {
            window.location.href="address.html";
        });
    }
}
//编辑信息
// 获取信息并赋值
function toEdit(id) {

    $("#title").html('<strong class="am-text-danger am-text-lg">修改地址</strong> / <small>Edit&nbsp;address</small>');
    $("#btn").html('<a class="am-btn am-btn-danger" id="toEdit" onclick="toUpdate(' + id + ')">保存</a>');
    $.get(path + "/read/toFind", {"id": id}, function (data) {
        //console.log(data);
        $("#user-name").val(data.name);
        $("#user-phone").val(data.phone);
        $("#user-code").val(data.code);
        $("#s_province").val(data.province);
        $("#s_city").append('<option value="' + data.city + '">' + data.city + '</option>');
        $("#s_city").val(data.city);
        $("#s_county").append('<option value="' + data.county + '">' + data.county + '</option>');
        $("#s_county").val(data.county);
        $("#user-intro").val(data.address);
    }, "json");
}