<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="机构管理/机构列表">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">机构管理</strong> / <small>机构列表</small></div>
    </div>
    
    <form method="post" action="#" id="searchform" class="am-form" onsubmit="resetPageNum(); loadformlist(); return false;">
      <input type="hidden" id="pageNum" name="pageNum" value="1">
	  <div class="am-g">
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      部门编号
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="id" class="am-input-sm">
	    </div>
			
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      部门名称
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="name" class="am-input-sm">
	    </div>
	    
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      上级部门
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="p_id_name" readonly="readonly" class="am-input-sm">
	      <input type="hidden" name="p_id">
	    </div>
	    
	  </div>
		
	  <div class="am-g">
	    <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-text-right">
	      <button type="submit" class="am-btn am-btn-primary am-btn-xs am-btn-lg">查&nbsp&nbsp&nbsp询</button>
	    </div>
	    <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
	    </div>
	    <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-text-left">
	      <button type="button" onclick="resetSearchform('searchform');" class="am-btn am-btn-primary am-btn-xs am-btn-lg">重&nbsp&nbsp&nbsp置</button>
	    </div>
	  </div>
    </form>
    
    <div id="form_list">
    
      <div class="am-u-sm-12 am-u-md-6" id="operation">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
            <button id="add" type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 新增</button>
            <button id="edit" type="button" class="am-btn am-btn-default"><span class="am-icon-edit"></span> 修改</button>
            <button id="delete" type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
          </div>
        </div>
      </div>
      
      <div class="am-u-sm-12" >
        <form id="listform" class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"></th>
                <th class="table-id" style="width: 20%;">部门ID</th>
                <th class="table-title">部门名称</th>
                <th class="table-type">上级部门</th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
          <div class="am-cf">
            共<span id="count" style="color: #0e90d2;"></span>条记录
            <div class="am-fr">
              <ul class="am-pagination" id="pagination">
              </ul>
            </div>
          </div>
          <hr />
        </form>
      </div>
    </div>
    
    <div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
      <div class="am-modal-dialog">
        <div class="am-modal-hd">${alertMessage}</div>
        <div class="am-modal-footer">
         <span class="am-modal-btn">确定</span>
        </div>
      </div>
    </div>
    
  </div>
  <!-- content end -->
</div>

<%@include file="../frame/foot.jsp" %>
<script type="text/javascript" src="common/js/utlis/tree.js"></script>
<script type="text/javascript" src="common/js/utlis/query.js"></script>
<script type="text/javascript">
var alertMessage = '${alertMessage}';
if (''!=alertMessage)
{
  $("#my-alert").modal('open');
}

initTree("getJsonOrgTree.htm", "p_id_name", "p_id");

var listurl = "orgList.htm";
var updateurl = "preUpdateOrg.htm";
var delurl = "delOrg.htm";

$("#form_list").hide();
$("#searchform").submit();

function loadTbody(colobjects)
{
  for (var o in colobjects)
  {
    var colobject = colobjects[o];
    var colobjecttr = $("<tr></tr>");
    
    if (colobject['id'] == 1)
    {
      colobjecttr.append('<td></td>');
    }
    else
    {
      colobjecttr.append('<td><input type="radio" name="id" value="'+colobject['id']+'"></td>');
    }
    
    colobjecttr.append('<td>'+colobject['id']+'</td>');
    colobjecttr.append('<td>'+colobject['name']+'</td>');
    colobjecttr.append('<td>'+colobject['p_id']+'</td>');
    $("#listform table tbody").append(colobjecttr);
  }
}

</script>
</body>
</html>

