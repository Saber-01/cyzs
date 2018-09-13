function getTime(){
 var today = new Date(); //获得当前时间
 var hh = today.getHours();  //获得小时、分钟、秒
 var mm = today.getMinutes();
 var ss = today.getSeconds();
 var ww ;
 switch (hh){
 case 24:
 case 0:
 case 1:
 case 2:
 case 3:
 case 4:
 case 5:
	ww="凌晨好！";
 	break;
 case 6:
 case 7:
 case 8:
 case 9:
 case 10:
 case 11:
	ww="上午好！";
	break;
 case 12:
 case 13:
	ww="中午好！";
	break;
 case 14:
 case 15:
 case 16:
 case 17:
 case 18:
	ww="下午好！";
	break;
 case 19:
 case 20:
 case 21:
 case 22:
 case 23:
	ww="晚上好！";
	break;
 
 }
 if(hh<10){
     hh = "0"+hh;
 }
 if(mm<10){
     mm = "0"+mm;
 }
 if(ss<10){
     ss = "0"+ss;
 }
 /*设置div的内容为当前时间*/
 document.getElementById("myclock").innerHTML="<h1>"+ww+"现在是:"+hh+":"+mm+":"+ss+"<h1>";
/*
  使用setTimeout在函数getTime()体内再次调用setTimeout
  设置定时器每隔1秒（1000毫秒），调用函数getTime()执行，刷新时钟显示
*/
  setTimeout("getTime()",1000);
}