<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>信息管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <link href="/style/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="/js/commonAll.js"></script>
    <script type="text/javascript" src="/js/jquery/plugins/jquery-form/jquery.form.min.js"></script>
    <script type="text/javascript" src="/js/artDialog/plugins/My97DatePicker/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript"
            src="/js/jquery/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
    <script type="text/javascript" src="/js/jquery/plugins/artDialog/plugins/iframeTools.source.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".searchproduct").click(function(){
                var currentTr = $(this).closest("tr");
                art.dialog.open("/product/selectList", {
                        id: 'ajxxList',
                        title: '选择商品',
                        width: 950,
                        height: 600,
                        left: '50%',
                        top: '50%',
                        background: '#000000',
                        opacity: 0.1,
                        lock: true,
                        resize: false,
                        close: function(){
                            var productInfo = art.dialog.data("productInfo"); // 读取子窗口返回的数据
                            if(productInfo){
                            currentTr.find("[tag=name]").val(productInfo.name)
                            currentTr.find("[tag=brand]").text(productInfo.brandName)
                            currentTr.find("[tag=costPrice]").val(productInfo.costPrice)
                            currentTr.find("[tag=pid]").val(productInfo.id)
                                $.dialog.removeData();
                            }
                        }
                    });
            });
            $("[tag=costPrice],[tag=number]").change(function(){
                var currentTr = $(this).closest("tr");
                var number = parseFloat(currentTr.find("[tag=number]").val())||0;
                var costPrice = parseFloat(currentTr.find("[tag=costPrice]").val())||0;
                currentTr.find("[tag=amount]").text((number*costPrice).toFixed(2));
            })
            $(".appendRow").click(function () {
                var newTr = $("#edit_table_body tr:first").clone(true)
                newTr.find("span").text("");
                newTr.find(":input").val("");
                $("#edit_table_body").append(newTr);
            })
            $("#editForm").submit(function () {
                $("#edit_table_body tr").each(function(index,tr){
                    $(tr).find("[tag=pid]").prop("name","items["+index+"].product.id")
                    $(tr).find("[tag=number]").prop("name","items["+index+"].number")
                    $(tr).find("[tag=costPrice]").prop("name","items["+index+"].costPrice")
                    $(tr).find("[tag=remark]").prop("name","items["+index+"].remark")
                })
            })
            $("#editForm").ajaxForm(function (data) {
                if(data.success){
                    confirmDialog("保存成功",function () {
                        window.location.href="/stockIncomeBill/list";
                    },false,"succeed")
                }else{
                    confirmDialog(data.errorMsg,true,false,"error")
                }
            })
            $(".removeItem").click(function () {
                var currentTr = $(this).closest("tr")
                if($("#edit_table_body tr").size()==1){
                    currentTr.find(":input").val("");
                    currentTr.find("span").text("");
                }else{
                    currentTr.remove();
                }
            });
        })

    </script>
</head>
<body>
<form name="editForm" action="/stockIncomeBill/saveOrUpdate" method="post" id="editForm">
    <input name="id" type="hidden" value="${stockIncomeBill.id}">
    <div id="container">
        <div id="nav_links">
            <span style="color: #1A5CC6;">入库单编辑</span>
            <div id="page_close">
                <a>
                    <img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
                </a>
            </div>
        </div>
        <div class="ui_content">
            <table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
                <tr>
                    <td class="ui_text_rt" width="140" >入库单编码</td>
                    <td class="ui_text_lt">
                        <input name="sn" value="${stockIncomeBill.sn}"  class="ui_input_txt02"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">仓库</td>
                    <td class="ui_text_lt">
                        <select name="depot.id" class="ui_select01">
                            <c:forEach var="depot" items="${depots}">
                                <option value="${depot.id}" ${depot.id == stockIncomeBill.depot.id ? "selected":""}>${depot.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td class="ui_text_rt " width="140" >业务时间</td>
                    <td class="ui_text_lt">
                        <fmt:formatDate value="${stockIncomeBill.vdate}"  var="vdate" pattern="yyyy-MM-dd"></fmt:formatDate>
                        <input name="vdate" value="${vdate}" onClick="WdatePicker({
                            maxDate:new Date()
                        })" class="ui_input_txt02  Wdate"/>
                    </td>
                </tr>
                <tr>
                    <td class="ui_text_rt" width="140">单据明细</td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
                        <table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
                            <thead>
                            <tr>
                                <th width="10"></th>
                                <th width="200">货品</th>
                                <th width="120">品牌</th>
                                <th width="80">价格</th>
                                <th width="80">数量</th>
                                <th width="80">金额小计</th>
                                <th width="150">备注</th>
                                <th width="60"></th>
                            </tr>
                            </thead>
                            <tbody id="edit_table_body">
                            <%--如果是添加,那么直接写死一行明细--%>
                            <c:if test="${stockIncomeBill.id==null}">
                                <tr>
                                    <td></td>
                                    <td>
                                        <input disabled="true" readonly="true" class="ui_input_txt02" tag="name"/>
                                        <img src="/images/common/search.png" class="searchproduct"/>
                                        <input type="hidden"  tag="pid"/>
                                    </td>
                                    <td><span tag="brand"></span></td>
                                    <td><input tag="costPrice"
                                               class="ui_input_txt02"/></td>
                                    <td><input tag="number"
                                               class="ui_input_txt02"/></td>
                                    <td><span tag="amount"></span></td>
                                    <td><input tag="remark"
                                               class="ui_input_txt02"/></td>
                                    <td>
                                        <a href="javascript:;" class="removeItem">删除明细</a>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${stockIncomeBill.id!=null}">
                                <c:forEach items="${stockIncomeBill.items}" var="item">
                                    <tr>
                                        <td></td>
                                        <td>
                                            <input disabled="true" readonly="true" class="ui_input_txt02" tag="name" value="${item.product.name}"/>
                                            <img src="/images/common/search.png" class="searchproduct"/>
                                            <input type="hidden" tag="pid" value="${item.product.id}"/>
                                        </td>
                                        <td><span tag="brand">${item.product.brandName}</span></td>
                                        <td><input tag="costPrice" value="${item.costPrice}" 
                                                   class="ui_input_txt02"/></td>
                                        <td><input tag="number"
                                                   class="ui_input_txt02" value="${item.number}"/></td>
                                        <td><span tag="amount">${item.amount}</span></td>
                                        <td><input tag="remark" value="${item.remark}"
                                                   class="ui_input_txt02"/></td>
                                        <td>
                                            <a href="javascript:;" class="removeItem">删除明细</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td class="ui_text_lt">
                        &nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
                        &nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>