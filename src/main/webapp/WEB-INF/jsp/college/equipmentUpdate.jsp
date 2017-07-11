<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="新增/修改设备">
  <meta name="keywords" content="error">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <link rel="icon" type="image/png" href="${applicationScope.oss}ui/amaze/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="${applicationScope.oss}ui/amaze/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="eface" />
  <link rel="stylesheet" href="${applicationScope.oss}ui/amaze/css/amazeui.min.css"/>
  <link rel="stylesheet" href="${applicationScope.oss}ui/amaze/css/admin.css">
</head>
<body>
<%@include file="../frame/head.jsp" %>

<div class="am-cf admin-main">
<%@include file="../frame/menu.jsp" %>

  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备管理</strong> / <small><c:if test="${object.id==0}">新增</c:if><c:if test="${object.id!=0}">修改</c:if>设备</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="updateEquipment.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
          <input type="hidden" name = "id" value="${object.id}">
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备名称</label>
            <div class="am-u-sm-9">
              <input type="text" name="name" value="${object.name}" placeholder="设备名称" required>
              <small>输入设备名称。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备归属</label>
            <div class="am-u-sm-9">
              <select name="belong_type" id="belong_type">
  	            <c:forEach var="config" items="${applicationScope.configListMap['equm_type']}">
                <option value="${config.cfg_key}" <c:if test="${object.belong_type==config.cfg_key}">selected="selected"</c:if> >${config.cfg_value}</option>
                </c:forEach>
              </select>
              <small>选择设备归属类型。</small>
            </div>
          </div>
          
          <div class="am-form-group" id="address-div">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备所在地</label>
            <div class="am-u-sm-9">
              <input type="text" name="address_name" readonly="readonly" value="${applicationScope.collegeAddressMap[object.address].name}" class="am-input-sm">
	          <input type="hidden" name="address" value="${object.address}">
              <small>选择设备所在地。</small>
            </div>
          </div>
          
          <div class="am-form-group" id="owner-div">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备所有人</label>
            <div class="am-u-sm-9">
              <div style="width: 55%; float: left;">
	            <input type="text" name="ownerorg_name" readonly="readonly" value="${applicationScope.collegeOrgMap[object.ownerorg].name}" class="am-input-sm">
	            <input type="hidden" name="ownerorg" value="123">
	          </div>
	          <div style="width: 44%; float: right;">
	            <select name="owner">
	              <option value="${object.owner}" selected="selected">${applicationScope.operatorMap[object.owner].name}</option>
	            </select>
	          </div>
              <small>选择设备所有人。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备品牌</label>
            <div class="am-u-sm-9">
              <input type="text" name="brand" value="${object.brand}" placeholder="设备品牌">
              <small>输入你的设备品牌。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备型号</label>
            <div class="am-u-sm-9">
              <input type="text" name="model" value="${object.model}" placeholder="设备型号">
              <small>输入你的设备型号。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">购置日期</label>
            <div class="am-u-sm-9">
              <input type="text" name="purchase" value="${object.purchase}" placeholder="购置日期" data-am-datepicker readonly>
              <small>输入购置日期。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">质保期</label>
            <div class="am-u-sm-9">
              <input type="text" name="shelf" value="${object.shelf}" placeholder="质保期" data-am-datepicker readonly>
              <small>输入质保期。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <div class="am-u-sm-4 am-u-sm-push-3">
              <button type="submit" class="am-btn am-btn-primary">保存</button>
            </div>
            <div class="am-u-sm-5">
              <button type="button" onclick="history.go(-1);" class="am-btn am-btn-primary">返回</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- content end -->
</div>

<%@include file="../frame/foot.jsp" %>

<script type="text/javascript" src="common/js/utlis/tree.js"></script>
<script type="text/javascript">
initTree("getJsonOrgTree.htm", "ownerorg_name", "ownerorg");
initTree("getJsonAddressTree.htm", "address_name", "address");
showBelong();

function showBelong()
{
	$("#address-div").hide();
	$("#owner-div").hide();
	if ("2" == $("select[name='belong_type']").val())
	{
		$("#owner-div").show();
	}
	if ("3" == $("select[name='belong_type']").val())
	{
		$("#address-div").show();
	}
}

$("select[name='belong_type']").change(function(
){
	showBelong();
});
		
$("input[name='ownerorg']").change(function(
){
	var staff_org_id = $("input[name='ownerorg']").val();
	$.get(
	"getJsonOrgOperators.htm",
	{staff_org_id : staff_org_id},
	function(data, textStatus)
	{
		var ownerSelect = $("select[name='owner']");
		ownerSelect.empty();
		ownerSelect.append("<option value=''>--请选择--</option>");
		
		var operators = data.operators;
		for (var o in operators)
		{
			var operator = operators[o];
			ownerSelect.append("<option value='"+operator.id+"'>"+operator.name+"</option>");
		}
	},"json");
});
</script>
</body>
</html>

