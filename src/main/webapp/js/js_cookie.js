var cook_date_str=new Date().getDate();
//删除cookie
function delCookie(name){
   document.cookie = name+"=;expires="+(new Date(0)).toGMTString()+';path=/';
}
/*//删除cookie
function delCookie(name){
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString()+';path=/';
}*/
//添加cookie
function addCookie(objName,objValue,objHours){     
    var str = objName + "=" + escape(objValue)+';path=/';
    if(objHours > 0){                               //为时不设定过期时间，浏览器关闭时cookie自动消失
        var date = new Date();
        var ms = objHours*3600*1000;
        date.setTime(date.getTime() + ms);
        str += "; expires=" + date.toGMTString();
   }
   document.cookie = str;
}
/*function SetCookie(name,value)//两个参数，一个是cookie的名子，一个是值
{
    var Days = 30; //此 cookie 将被保存 30 天
    var exp = new Date();    //new Date("December 31, 9998");
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}*/
function getCookie(name)//取cookies函数       
{
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
     if(arr != null) return unescape(arr[2]); return null;
}
/*//获得coolie 的值
function cookie(name){   
   var cookieArray=document.cookie.split("; "); //得到分割的cookie名值对   
   var cookie=new Object();   
   for (var i=0;i<cookieArray.length;i++){   
      var arr=cookieArray[i].split("=");       //将名和值分开   
      if(arr[0]==name)return unescape(arr[1]); //如果是指定的cookie，则返回它的值   
   }
   return "";
}
function getCookie(objName){//获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for(var i = 0;i < arrStr.length;i ++){
        var temp = arrStr[i].split("=");
        if(temp[0] == objName) return unescape(temp[1]);
   }
}*/
$(function(){
	init_cookzc();
});
function init_cookzc(){
	  var jls=getCookie(cook_date_str+'zc-add-jls');
	  //var jls='';
	  var yxzc='',payback='';
	  yxzc=getCookie(cook_date_str+'zc-yxzc');
	  payback =getCookie(cook_date_str+'zc-payback');
	if(jls!=null&&jls.length>0){
		var param = {
				returnType : 'json',
				aaa : jls
			};
			$.post(	$("#path").val() + '/app/search/checkcookie',param,function(msg) {
				if(msg.code==1){
					addCookie(cook_date_str+'zc-add-jls',msg.bbb.substring(1, msg.bbb.length-1),1);
					if(yxzc!=null)
						yxzc=yxzc.replace("]", ",")+JSON.stringify(msg.yxzc).replace("[", "");
					else
						 yxzc=JSON.stringify(msg.yxzc);
					if(payback!=null)
						payback=payback.replace("]", ",")+JSON.stringify(msg.payback).replace("[", "");
					else
						payback=JSON.stringify(msg.payback);
					addCookie(cook_date_str+'zc-yxzc',yxzc,1);addCookie(cook_date_str+'zc-payback',payback,1);
				}
		}, 'json');
	}
	if( typeof init_co_zc=== 'function' ){
		init_co_zc(yxzc,payback);
	}
}
/**
 * 使用示例
 * 		
*	
			addCookie("username", user, 604800);
			addCookie("password", pass, 604800);
 * 
 * 
 */
