<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="新增/修改我的设备">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备管理</strong> / <small><c:if test="${object.id==0}">新增</c:if><c:if test="${object.id!=0}">修改</c:if>我的设备</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="updateMyEquipment.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
          <input type="hidden" name = "id" value="${object.id}">
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">设备名称</label>
            <div class="am-u-sm-9">
              <input type="text" name="name" value="${object.name}" placeholder="设备名称" required>
              <small>输入设备名称。</small>
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
</body>
</html>

