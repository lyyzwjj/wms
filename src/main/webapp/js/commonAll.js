jQuery.ajaxSettings.traditional = true; 
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
});

//翻页效果
$(function(){
	$(".btn_page").click(function(){
		var currentPage = $(this).data("page")||1;
		$("#currentPage").val(currentPage);
		$("#searchForm").submit();
		console.log("你好");
	})
	$("[name=pageSize]").change(function(){
		$("#currentPage").val(1);
		$("#searchForm").submit();
	})
	$("[value=跳转]").click(function(){
		$("#searchForm").submit();
	})
});
function confirmDialog(content,ok,cancel,icon){
	$.dialog({
		title:"温馨提示",
		content:content,
		ok:ok,
		cancel:cancel,
		icon:icon,
		lock:true
	})

}
//审核
$(function(){
	$(".btn_audit").click(function(){
		var url = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"您确认审核吗?",
			ok:function(){
				$.get(url,function(data){
					if(data.success){
						confirmDialog("审核成功",function(){window.location.reload();},false,"succeed");
					}else{
						confirmDialog(data.errorMsg,true,false,"error");
					}
				});
			},
			cancel:true,
			skin:"green",
			lock:true,
			icon:"warning"
		})
	});
})

//单个删除
$(function(){
	$(".btn_delete").click(function(){
		var url = $(this).data("url");
		$.dialog({
			title:"温馨提示",
			content:"您确认删除吗?",
			ok:function(){
				$.get(url,function(data){
					if(data.success){
						confirmDialog("删除成功",function(){window.location.reload();},false,"succeed");
					}else{
						confirmDialog(data.errorMsg,true,false,"warning");
					}
				});
			},
			cancel:true,
			skin:"green",
			lock:true,
			icon:"warning"
		})
	});
})
//删除全部
$(function(){
	$(".btn_batchdelete").click(function(){
		var url = $(this).data("url");
		var size = $(".acb:checked").size();
		if(size==0){
			confirmDialog("请勾选要删除的员工",true,false,"warning")
			return;
		}
		var ids = [];
		$(".acb:checked").each(function(index,checkbox){
			ids[index]=$(checkbox).data("id");	
		});
		confirmDialog("确认批量删除员工",function(){
			$.get(url,{ids:ids},function(data){
				if(data.success){
					confirmDialog("批量删除成功",function(){window.location.reload()},false,"succeed")
				}else{
					confirmDialog(data.errorMsg,true,false,"error")
				}
			})
			
		},true,"warning")
	});
})
$(function(){
	$("#all").change(function(){
		$(".acb").prop("checked",this.checked);
	});
})