window.jQuery || document.write('<script src="basic/js/jquery.min.js "></script>');
var path = "/" + location.pathname.split("/")[1];
$(function () {
   $.get(path+"/round/toFind",function (data) {
       //console.log(data);
       for(var i = 0;i<5;i++){
           if (i<data.length){
               $("#img"+(i+1)).attr("src",path+data[i].imgPath);
           }else {
               $("#img"+(i+1)).attr("src","../images/00011.jpg");
           }
       }


       /*for (var i in data){
           var mode=data[i];
           /!*$("#imgUl").append('<li class="banner'+i+'"><a href=""><img src="'+(path+mode.imgPath)+'"></a></li>');*!/
           // $("#imgUl").append('<li class="banner1 clone" aria-hidden="true" style="width: 922px; float: left; display: block;"><a href="introduction.html"><img src="'+(path+mode.imgPath)+'" draggable="false"></a></li>');
       }*/
   },"json"); 
});