var path = "/" + location.pathname.split("/")[1];
$(function () {
    //添加图片
    $("#save").click(function () {
        if ($("#type1").val().length!=0&&$("#sort").val().length!=0&&$("#imgUpload").val().length!=0){
            // $.post(path+"/round/toAdd",{"type1":$("#type1").val(),"sort":$("#sort").val(),"file":$(".webuploader-element-invisible")[0]});
            console.log($("#imgUpload").val());
            var formData=new FormData();
            formData.append("type",$("#type1").val());
            formData.append("sort",$("#sort").val());
            formData.append("myFile",$("#imgUpload")[0].files[0]);
            $.ajax({
                url : path+"/round/toAdd",
                type : 'POST',
                data : formData,
                dataType : "json",
                async :false,
                cache : false,
                processData : false,
                contentType : false,
                success : function (data) {
                    alert(data);
                    window.location.href="picture-list.html"
                }
            });
        }else {
            alert("请填写完整的信息！")
        }
    });
});