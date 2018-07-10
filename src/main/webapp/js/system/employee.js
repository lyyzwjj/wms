$(function() {
	// 全部从左移动到右
	$("#selectAll").click(function(){
		$(".all_roles option").appendTo($(".selected_roles"))
	})
	// 把选中的从左移动到右
	$("#deselectAll").click(function(){
		$(".all_roles").append($(".selected_roles option"))
	})
	// 全部从右移动到左
	$("#select").click(function(){
		$(".all_roles option:selected").appendTo($(".selected_roles"))
	})
	// 把选中的从右移动到左
	$("#deselect").click(function(){
		$(".all_roles").append($(".selected_roles option:selected"))
	})
	//在表单提交之前,应该选中所有已经分配的权限选项
	$("#editForm").submit(function(){
		$(".selected_roles option").prop("selected",true)
	})
	//把已经分配的权限从所有的权限列表中移除
	//1:获取已经分配权限的的id,存储到ids数组中
	var ids = [];
	/*$.each($(".selected_roles option"),function(index,option){
		console.log(arguments);
		ids[index] = option.value
	});
	console.log(ids);*/
	//2:迭代所有的权限列表的每一个<option>
	ids = $.map($(".selected_roles option"),function(option){
		return option.value;
	})
	//循环遍历左边中所有的option,将其option的value属性的值和数组中的值比较
	$.each($(".all_roles option"),function(index,option){
		//获取当前元素在数组中的索引
		if($.inArray(option.value,ids)!=-1){
			$(option).remove()
		}
	})
		
	
});


$(function() {
	var role = null;
	$(".ui_checkbox01").click(function() {
		if (this.checked) {
			role = $(".role").detach();
		} else {
			$(this).parents("tr").after(role);
		}
	});
});