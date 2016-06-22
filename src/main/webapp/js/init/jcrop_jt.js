var logo_jcrop_api, logo_boundx, logo_boundy,logo_whbl,logo_left,logo_top,logo_width,logo_height;
$(function(){
});
$('#loginimg-close').click(function(){
	$('#portrait').modal('hide');
	cancellogo();
});
$('#loginimg-enter').click(function(){
	if($('#imgsrc').val()=='')
		return ;
	var param={imgAlias:'_two',imgsrc:$('#imgsrc').val(),imgw:80,imgh:80,left:logo_left,top:logo_top,width:logo_width,height:logo_height};
	$.post($('#path').val()+'/app/upload/imgCut',param,function(msg){
		if(msg!=null){
			if(msg.code==1){
				$('#logo-hi').val(msg._two);
				$('#logo-img').prop('src',$('#path').val()+msg._two);
			}else
				alert('jtcs error');
		}else{
			alert('jt error');
		}
		$('#portrait').modal('hide');
		cancellogo();
	},'json');
});
function jcrop_logo(data){
//	alert(data);
	var msg =$.parseJSON(data);
	if(msg.code==1){
		var imgPath=msg.imagePath;
		if(imgPath!=null&&imgPath.length>1){
			logo_jcrop_api.destroy();
			
			 var width=msg.width;
			 var height=msg.height;
			 if(width<=450&&height<=300){
				 $("#logo").css({width: '',height: ''});
				 $("#logojttp").css({"padding-top":'0px',height:'300px'});
				 logo_whbl=1;
			 }else{
				 var ww=width/450;
				 var hh=height/300;
//				 alert(logo_whbl+', ww='+ww+',  hh='+hh);
				 if(ww>hh){//按照宽计算
//					 alert(" ww=按照宽计算");
					 $("#logo").css({width: '450px',height: ''});
					 var top=Math.round((300-(height/ww))/2);
					 $("#logojttp").css({"padding-top":top+'px',height:300-top+'px'});
					 logo_whbl=ww;
				 }else{
//					 alert(" hh=按照高计算");
					 $("#logo").css({height: '300px',width: ''});
					 $("#logojttp").css({"padding-top":'0px',height:'300px'});
					logo_whbl=hh;
				 }
			 }
			 
			/* if(width<=300&&height<=300){
				 $("#logo").css({width: '',height: ''});
				 $("#logojttp").css({"padding-top":'0px',height:'300px'});
				 logo_whbl=1;
			 }else if (width>=height) {
				$("#logo").css({width: '300px',height: ''});
				 logo_whbl=width/300;
				 var top=Math.round((300-(height/logo_whbl))/2);
				 $("#logojttp").css({"padding-top":top+'px',height:300-top+'px'});
			 }else{
				$("#logo").css({height: '300px',width: ''});
				 $("#logojttp").css({"padding-top":'0px',height:'300px'});
				logo_whbl=height/300;
			 }*/
			 $("#imgsrc").val(imgPath);
			 $("#logo").prop("src",$("#path").val()+imgPath);
			 $("#logopreview").prop("src",$("#path").val()+imgPath);
			 logoinit();
			 
			setTimeout(function(){
				logo_jcrop_api.animateTo([0,0,80,80]);
			},500);
		}
	}else{
		alert(msg.text);
	}
}
function showCoords_logo(c) {
	/*jQuery('#txtX1').val(Math.round((c.x)*logo_whbl));
    jQuery('#txtY1').val(Math.round((c.y)*logo_whbl));
    jQuery('#txtX2').val(Math.round((c.x2)*logo_whbl));
    jQuery('#txtY2').val(Math.round((c.y2)*logo_whbl));*/
    
	logo_left=Math.round((c.x)*logo_whbl);
	logo_top=Math.round((c.y)*logo_whbl);
	logo_width=Math.round((c.x2)*logo_whbl);
	logo_height=Math.round((c.y2)*logo_whbl);

//    jQuery('#txtW').val('50');
//    jQuery('#txtH').val('50');
    if (parseInt(c.w) > 0){
        var rx =80/ c.w;
        var ry =80/ c.h;
        $('#logopreview').css({
          width: Math.round(rx * logo_boundx) + 'px',
          height: Math.round(ry * logo_boundy) + 'px',
          marginLeft: '-' + Math.round(rx * c.x) + 'px',
          marginTop: '-' + Math.round(ry * c.y) + 'px'
        });
     }
};
function logoinit(){
	jQuery('#logo').Jcrop({
		  minSize:[60,60],
          onChange: showCoords_logo,
          onSelect: showCoords_logo,
          aspectRatio:1/1,
          sideHandles: true,
          cornerHandles:false,
          allowMove:   true
         // allowSelect:false
        },function(){
            logo_jcrop_api = this;
            var bounds = this.getBounds();
            logo_boundx = bounds[0];
            logo_boundy = bounds[1];
       });
}
function cancellogo(){
	logo_jcrop_api.destroy();
	$("#logo").prop("src",$("#path").val()+"/js/jcrop/de.jpg");
	$("#logopreview").prop("src",$("#path").val()+"/js/jcrop/de.jpg");
	$("#imgsrc").val("");
	$("#logo").css({width: '',height: ''});
	$("#logoPath").val("");
}

