<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${result.totalCount}</span>
						条记录，当前第
						<span class="ui_txt_bold04">${result.currentPage}/${result.totalPage}</span>
						页
					</div>
					<div class="ui_frt">
						<input type="button" value="首页" class="ui_input_btn01 btn_page" data-page="1"/>
						<input type="button" value="上一页" class="ui_input_btn01 btn_page" data-page="${result.prevPage}" />
						<input type="button" value="下一页" class="ui_input_btn01 btn_page" data-page="${result.nextPage}"/>
						<input type="button" value="尾页" class="ui_input_btn01 btn_page" data-page="${result.totalPage}"/>

						<select list="{3,5,10}" name="pageSize" class="ui_select02">
							<option>3</option>
							<option>5</option>
							<option>10</option>
						</select>
						<script type="text/javascript">
						$("[name=pageSize] option").each(function(index,option){
							if(option.value == '${result.pageSize}'){
								option.selected = true;
							}
						})
						</script>
						转到第
						<input type="text" name="currentPage" id="currentPage"
							value="${result.currentPage}" class="ui_input_txt01" />
						页
						<input type="button" class="ui_input_btn01" value="跳转" />
					</div>
				</div>
</body>
</html>