/**
 * Created by Administrator on 2016/4/1.
 */
// var ajaxFunction = _ajaxFunction;
        function setPage(thisPage,totalPage){
            thisPage = Number(thisPage);
            totalPage = Number(totalPage);
            var html = "";
            $(".page ul").html(html);
            if(thisPage < 1 || thisPage > totalPage) return;
            if(isNaN(thisPage) || isNaN(totalPage)) return;
            if(totalPage == 1) return;
            if(thisPage != 1){
                var html = "<li><a class='prv'>上一页</a></li>";
            }
            if(totalPage < 7){
                for(var i=1;i<totalPage+1;i++){
                    if(i == thisPage){
                        html += "<li><a class='hover'>"+i+"</a></li>";
                    }else{
                        html += "<li><a>"+i+"</a></li>";
                    }
                }
            }else{
                if(thisPage < 4){
                    for(var i=1;i<8;i++){
                        if(i == thisPage){
                            html += "<li><a class='hover'>"+i+"</a></li>";
                        }else{
                            html += "<li><a>"+i+"</a></li>";
                        }
                    }
                }else if(totalPage - thisPage == 2){
                    for(var i=0;i<7;i++){
                        if(i == 4){
                            html += "<li><a class='hover'>"+ (thisPage-4+i)+"</a></li>";
                        }else{
                            html += "<li><a>"+(thisPage-4+i)+"</a></li>";
                        }
                    }
                }else if(totalPage - thisPage == 1){
                    for(var i=0;i<7;i++){
                        if(i == 5){
                            html += "<li><a class='hover'>"+(thisPage-5+i)+"</a></li>";
                        }else{
                            html += "<li><a>"+(thisPage-5+i)+"</a></li>";
                        }
                    }
                }else if(totalPage - thisPage == 0){
                    for(var i=0;i<7;i++){
                        if(i == 6){
                            html += "<li><a class='hover'>"+(thisPage-6+i)+"</a></li>";
                        }else{
                            html += "<li><a>"+(thisPage-6+i)+"</a></li>";
                        }
                    }
                }else{
                    for(var i=0;i<7;i++){
                        if(i == 3){
                            html += "<li><a class='hover'>"+(thisPage-3+i)+"</a></li>";
                        }else{
                            html += "<li><a>"+(thisPage-3+i)+"</a></li>";
                        }
                    }
                }
            }
            if(thisPage != totalPage){
                html += "<li><a class='next'>下一页</a></li>";
            }
            html += "<li><input type='text'/></li><li><input type='button' value='跳转'/></li>";
            $(".page ul").html(html);
            
        }
        
        $(".page ul").on("click","a",function(){
        	var lastIndex = Number($(".page ul a.hover").text());
            if($(this).text() == "上一页"){
            	getData( lastIndex - 1);
            }else if($(this).text() == "下一页"){
            	getData( lastIndex + 1);
            }else{
            	getData( $(this).text());
            }
            
        });
        $(".page ul").on("click","input[type='button']",function(){
            if($(".page ul input[type='text']").val() != ""){
            	getData( $(".page ul input[type='text']").val());
            }
        });
        

var check = 1;
$("table").on("click","th input",function(){
    if(check == 0){
        $(this).parents("table").find("tbody input").removeAttr("checked");
        check = 1;
    }else{
        $(this).parents("table").find("tbody input").prop("checked",true);
        check = 0;
    }
});

/**
 * 使用示例
 * 		<div class="page">
*		<ul></ul>
*		</div>
*	
	$(function() {
			setPage(${page.pageNum},${page.pages});
	});
	//分页点击事件
	function getData(index) {
		$("#pageNum").val(index);
		$("form").submit();
	}
 * 
 * 
 */
