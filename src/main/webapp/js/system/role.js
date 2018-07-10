$(function () {
   /* 权限列表移动操作*/
   //全选
    $("#selectAll").click(function () {
        $(".all_permission option").appendTo( $(".selected_permission"))
    });
    //全移
    $("#deselectAll").click(function () {
        $(".selected_permission option").appendTo( $(".all_permission"))
    });
   //选中移动
    $("#select").click(function () {
        $(".all_permission option:selected").appendTo( $(".selected_permission"))
    });
    //选中移除
    $("#deselect").click(function () {
        $(".selected_permission option:selected").appendTo( $(".all_permission"))
    });
    //移除掉已选择的列表项
    //思路: 先获取到已选择的列表项的所有的value ids 然后再依次遍历所有的列表项,如果在ids中,则移除掉
    var ids=$.map($(".selected_permission option"),function (item) {
        return $(item).val();
    })
    $.each($(".all_permission option"),function (index,item) {
        //通过$.inArray()判断是否在数组ids中
        if($.inArray($(item).val(),ids)!=-1){
            $(item).remove();
        }
    })
   /* 权菜单移动操作*/
   //全选
    $("#mselectAll").click(function () {
        $(".all_menu option").appendTo( $(".selected_menu"))
    });
    //全移
    $("#mdeselectAll").click(function () {
        $(".selected_menu option").appendTo( $(".all_menu"))
    });
   //选中移动
    $("#mselect").click(function () {
        $(".all_menu option:selected").appendTo( $(".selected_menu"))
    });
    //选中移除
    $("#mdeselect").click(function () {
        $(".selected_menu option:selected").appendTo( $(".all_menu"))
    });
    //移除掉已选择的列表项
    //思路: 先获取到已选择的列表项的所有的value ids 然后再依次遍历所有的列表项,如果在ids中,则移除掉
    var mids=$.map($(".selected_menu option"),function (item) {
        return $(item).val();
    })
    $.each($(".all_menu option"),function (index,item) {
        //通过$.inArray()判断是否在数组ids中
        if($.inArray($(item).val(),mids)!=-1){
            $(item).remove();
        }
    })
  /* /!* 菜单列表移动操作*!/
   //全选
    $("#mselectAll").click(function () {
        $(".all_menu option").appendTo( $(".selected_menu"))
    });
    //全移
    $("#mdeselectAll").click(function () {
        $(".selected_menu option").appendTo( $(".all_menu"))
    });
   //选中移动
    $("#mselect").click(function () {
        $(".all_menu option:selected").appendTo( $(".selected_menu"))
    });
    //选中移除
    $("#mdeselect").click(function () {
        $(".selected_menu option:selected").appendTo( $(".all_menu"))
    });
    //移除掉已选择的列表项
    //思路: 先获取到已选择的列表项的所有的value ids 然后再依次遍历所有的列表项,如果在ids中,则移除掉
    ids=$.map($(".selected_menu option"),function (item) {
        return $(item).val();
    })
    $.each($(".all_menu option"),function (index,item) {
        //通过$.inArray()判断是否在数组ids中
        if($.inArray($(item).val(),ids)!=-1){
            $(item).remove();//如果在该ids数组中,就删除该option元素
        }
    })*/
})

$(function () {
    //在提交表单之前,选中所有已选择的列表项
    $("#editForm").submit(function () {
        $(".selected_permission option").prop("selected",true);
        $(".selected_menu option").prop("selected",true);
    });
})