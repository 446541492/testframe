/**
 * html判断大小， js上传
 */
var  html_file_upload=function(file,p,file_id,meth,gname,imgWH){//{'gname':"richfile",'imgWH':'1'}
	setTimeout(function(){
		var files =file.files;
		var size = files[0]. size;  
		var type= files[0]. type;
//		alert('size='+size+',  type='+type+',  meth='+meth+',  file_id='+file_id);
		var reg=/^image.*/;
		if(!reg.test(type)){alert('请选择图片文件！');}
		else if(size>3145728) {alert('图片大小不能超过 3M');}
		else{
			$.ajaxFileUpload({
				url:p+'/upload/image',//用于文件上传的服务器端请求地址
				secureuri:false,//一般设置为false
				fileElementId:file_id,//文件上传空间的id属性  <input type="file" id="file" name="file" />
				data:{'gname':gname,'imgWH':imgWH},
				type:'post',
				success: function (data, status){  //服务器成功响应处理函数
					if(data=='')
		        		data='{"code":2,"text":"session过期，请登录后刷新刷新页面"}';
		        	eval( "var _function = " + meth );
		        	_function(data);
				},
				error: function (data, status, e){//服务器响应失败处理函数
					if(data=='')
		        		data='{"code":3,"text":"上传错误"}';
					eval( "var _function = " + meth );
		        	_function(data);
				}
			});
		}
	},50);
};
/**
 * 说明，依赖 uploadify.css,jquery.uploadify-utf8.js,style.css，jquery.js以及flash。
 * 页面，初始化时，取项目根路径，sessionid
 * 兼容 ie 火狐，谷歌，360，
 * @param file_id file标签id
 * @param p 项目域名
 * @param param_str //页面初始化后，json数据 gname //上传图片路径分组，
 * @param meth //上传后回调函数，方法名
 * @param se //sessionid  页面初始化时候取当前session
 */
function file_uploadify(file_id,p,meth,se,param){
	if (typeof(Worker) !== "undefined"){//支持html5
		var oldfile=$('#'+file_id);
		oldfile.after('<div style="position:relative;width:96px;height:36px;background:#1476e5;color:#fff;line-height:36px;text-align:center;">选择图片'+
				'<input type="file" id="'+file_id+'" name="ddd" onchange="html_file_upload(this,\''+p+'\',\''+file_id+'\',\''+meth+'\',\''+param.gname+'\',\''+param.imgWH+'\')" accept="image/*" ></div>');
		oldfile.remove();
	}else{
		var fls = flashChecker();
		if(fls.f) {
			se=get_sessionid();
			$('#'+file_id).uploadify({
		        'swf'       : p+'/js/uploadify/uploadify.swf',
		        'uploader'       : p+'/upload/image;jsessionid='+se+'?',
		        'fileTypeDesc'   : '图片文件(JPG,PNG,GIF)',
		        'fileTypeExts'   : '*.jpg;*.png;*.gif',
		        'buttonClass':'btn-primary',
		        'buttonText'	   : "选择图片",
		        'multi'          : true,
		        'auto'        : true,
		        'removeCompleted': true,
		        'method'   	   : 'post',
		        'fileSizeLimit'  : '3072KB',
		        'queueSizeLimit' :1,
		        'formData'	   :param,//sessionId
		        height        : 36,
		        width         : 96,
		        'onUploadStart'   : function(file) {
		        	 $(".uploadify-queue").hide();
		        },
		        'onUploadSuccess':function(file, data, response)  {
//		        	eval(meth+ "( '字符串参数')");
	//	        	alert(data);
		        	if(data=='')
		        		data='{"code":2,"text":"session过期，请登录后刷新刷新页面"}';
		        	eval( "var _function = " + meth );
		        	_function(data);
		        },
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
		        	eval( "var _function = " + meth );
		        	_function('{"code":3,"text":"上传错误"}');
		        }
		    });
		}else{alert('您没有安装flash,不支持HTML5,上传图片功能不能使用!');	}
	}
}

function get_sessionid(){
	var str = document.cookie;
	if(str != ""){
		var arrStr =str.split("; ");
		for(var i = 0;i < arrStr.length;i ++){
			var temp = arrStr[i].split("=");
			if(temp[0]=='log-sion-s')	return temp[1];
		} 
	}
	return "";
}
