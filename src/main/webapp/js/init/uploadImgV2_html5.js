/*依赖jquery  uploadify ajaxfileupload json2(为：兼容ie8以下)
 * 截图 依赖 Jcrop//$v[0]
 * */
/**
 * html判断大小， js上传
 */
$(document).on('change','.change-file',function(){
	_this=$(this);
	var param=JSON.parse(decodeURI(_this.next('input').val()));
	if(typeof param.onSelect =='string'){
		eval( "var onSelect=" + param.onSelect );
		onSelect (new Object(),_this);
	}
	setTimeout(function(){
		var files =_this[0].files;
		var size = files[0]. size;  
		var type= files[0]. type;
		/*alert('----'+size+',****'+type+typeof(type));*/
		eval( "var _function = " + param.meth );
		var reg=/^image.*/;
		if(type!=null&&type.length>0&&!reg.test(type)){alert('请选择图片文件！');_function('{"code":0}');}
		else if(size>3145728) {alert('图片大小不能超过 3M');_function('{"code":0}');}
		else{
			$.ajaxFileUpload({
				url:param.p+'/upload/image',//用于文件上传的服务器端请求地址
				secureuri:false,//一般设置为false
				fileElementId:_this.prop('id'),//文件上传空间的id属性  <input type="file" id="file" name="file" />
				data:param,
				type:'post',
				success: function (data, status){  //服务器成功响应处理函数
					if(data=='')
		        		data='{"code":2,"text":"session过期，请登录后刷新刷新页面"}';
		        	_function(data,'ssssss');
				},
				error: function (data, status, e){//服务器响应失败处理函数
					if(data=='')
		        		data='{"code":3,"text":"上传错误"}';
		        	_function(data);
				}
			});
		}
	},50);
});

/**
 * 说明，依赖 
 * 兼容 ie 火狐，谷歌，360，
 * @param file_id file标签id
 * @param p 项目域名
 * @param param_str //页面初始化后，json数据 gname //上传图片路径分组，
 * @param meth //上传后回调函数，方法名
 * @param se //sessionid  页面初始化时候取当前session
 * @param onSelect 上传前回调函数名（string
 * @param type 定制化，上传图标
 */
function file_uploadify(file_id,p,meth,se,param){
//	if (typeof(Worker) !== "undefined"){//支持html5
		var oldfile=$('#'+file_id);
		param.meth=meth;param.p=p;
		if(typeof param.type=='undefined')
			oldfile.after('<div style="position:relative;width:96px;height:36px;background:#1476e5;color:#fff;line-height:36px;text-align:center;">选择图片'+
				'<input type="file" id="'+file_id+'" name="ddd'+file_id+'" class="change-file"  accept="image/*" style="position:absolute;width:96px;height:36px;opacity:0;top:0;left:0;">'
				+'<input type="hidden" value="'+encodeURI(JSON.stringify(param))+'"></div>');
		else if(param.type=='1'){
			/*oldfile.after('<input type="file" id="'+file_id+'" name="ddd'+file_id+'" class="change-file" style="display:none;"  accept="image/*" >'
					+'<input type="hidden" value="'+encodeURI(JSON.stringify(param))+'">');*/
			oldfile.after('<input type="file" id="'+file_id+'" name="ddd'+file_id+'" class="file change-file" accept="image/*" >'
					+'<input type="hidden" value="'+encodeURI(JSON.stringify(param))+'">');
		}
		oldfile.remove();
//	}else{alert('您的浏览器不支持HTML5,上传图片功能不能使用!');}
}

/**
 * 使用示例
 * 		<input type="file" id="img1" data-options="required:true,missingMessage:'请选择封面图片'">
		<br /> <span style="font-size:12px;color:#666;">支持jpg、jpeg、png、gif格式;建议尺寸680*260px;</span><br />
		<input id="imgVal" type="hidden" name="imgPreview" value="">
		<img src="${CommonConfig.fileServer}" onerror="javascript:this.src='${pageContext.request.contextPath}/img/pic_default.gif'" width="65%" height="170px" style="margin:15px 0;" id="index1">
		
	$(function() {
				//头像图片上传例子
		file_uploadify('img1','${pageContext.request.contextPath}','img1','<%=session.getId()%>', {
					'classify' : "head",type:"1"
				});
	});
	//图片上传例子
	function img1(data) {
		var dat = $.parseJSON(data);
		if (dat.status == 1) {
			$('#index1').attr('src',
					'${CommonConfig.fileServer}' + dat.imagePath);
			$('input[name=imgPreview]').val(dat.imagePath);
		}else{
			alert(dat.imagePath)
		}
	}
 * 
 * 
 */
