var path = "/" + location.pathname.split("/")[1];

$(function () {
    $.get(path+"/breed/getBreedName.do",
        function (data) {
            for(var i=0;i<data.length;i++){
                $("#p-e-breed").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
            }
        },"json")
});

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
    $.get(path+"/commodity/getCommodity.do",
        {"id":id},
        function (data) {
            if(data!="false"){
                $("#img").attr("src",path+data.imgPath);
                $("#p-e-sort").val(data.sort);
                $("#p-e-breed").find("option[value='"+data.breed.id+"']").attr("selected","selected");
                $("#p-e-name").val(data.name);
                $("#p-e-price").val(data.price);
                $("#p-e-downprice").val(data.downprice);
                $("#p-e-minsaleweight").val(data.minsaleweight);
                $("#p-e-stockbalance").val(data.stockbalance);
                if (data.unit=="t"){
                    $("#p-e-unit").find("option[value='1']").attr("selected","selected");
                }else if(data.unit=="kg"){
                    $("#p-e-unit").find("option[value='2']").attr("selected","selected");
                }else if(data.unit=="g"){
                    $("#p-e-unit").find("option[value='3']").attr("selected","selected");
                }
                var cont=data.details;
                UE.getEditor('editor').addListener("ready", function () {
                    // editor准备好之后才可以使用
                    UE.getEditor('editor').setContent(cont);
                });
            }
        },"json")
})

$(function() {
    var id=GetQueryString("id");
    $("#p-e-save").click(function () {
        var formData=new FormData();
        formData.append("id",id);
        formData.append("sort",$("#p-e-sort").val());
        formData.append("bId",$("#p-e-breed").val());
        formData.append("name",$("#p-e-name").val());
        formData.append("price",$("#p-e-price").val());
        formData.append("downprice",$("#p-e-downprice").val());
        formData.append("minsaleweight",$("#p-e-minsaleweight").val());
        formData.append("stockbalance",$("#p-e-stockbalance").val());
        formData.append("unit",$("#p-e-unit").find("option:selected").text());
        formData.append("details",UE.getEditor('editor').getContent());
        if($("#imgUpload")[0].files[0]!= undefined){
            formData.append("file", $("#imgUpload")[0].files[0]);
        }
            $.ajax({
                url: path + "/commodity/editCommodity.do",
                type: "POST",
                data: formData,
                dataType: "json",
                async: false,
                cache: false,
                processData: false,
                contentType: false,
                success: function (data) {
                    if (data == "true") {
                        window.location.href = "product-list.html";
                    } else {
                        alert("修改失败！");
                    }

                }
            })

    })
})