<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="新增/修改地址">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">地址管理</strong> / <small><c:if test="${empty(object.id)}">新增</c:if><c:if test="${!empty(object.id)}">修改</c:if>地址</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="updateAddress.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
          <input type="hidden" name = "id" value="${object.id}">
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">地址</label>
            <div class="am-u-sm-9">
              <input type="text" name="name" value="${object.name}" placeholder="地址" required>
              <small>输入地址。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">上级地址</label>
            <div class="am-u-sm-9">
              <input type="text" name="p_id_name" readonly="readonly" value="${applicationScope.collegeAddressMap[object.p_id].name}" class="am-input-sm" required>
	          <input type="hidden" name="p_id" value="${object.p_id}">
              <small>请选择上级地址。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">描述信息</label>
            <div class="am-u-sm-9">
              <input type="text" name="dscb" value="${object.dscb}" placeholder="描述信息">
              <small>输入描述信息。</small>
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
initTree("getJsonAddressTree.htm", "p_id_name", "p_id");
</script>
</body>
</html>

