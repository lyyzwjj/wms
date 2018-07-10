/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});

//新增跳转按钮
$(function(){
	$(".btn_input").click(function(){
		window.location.href=$(this).data("url");
	})
})
//翻页效果
$(function(){
	$(".btn_page").click(function(){
		var currentPage = $(this).data("page")||1;
		$("#currentPage").val(currentPage);
		$("#searchForm").submit();
	})
	$("[name=pageSize]").change(function(){
		$("#currentPage").val(1);
		$("#searchForm").submit();
	})
	$("[value=跳转]").click(function(){
		$("#searchForm").submit();
	})
})