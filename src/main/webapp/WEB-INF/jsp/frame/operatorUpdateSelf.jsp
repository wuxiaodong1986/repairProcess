<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="新增/修改教工">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人信息</strong> / <small>资料修改</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="operatorUpdateSelf.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
          <input type="hidden" name = "id" value="${object.id}">
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">工号</label>
            <div class="am-u-sm-9">
              <input type="text" name="username" value="${object.username}" placeholder="工号" <c:if test="${object.id!=0}">readonly="readonly"</c:if> required>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>
            <div class="am-u-sm-9">
              <input type="text" name="name" value="${object.name}" placeholder="姓名">
              <small>输入姓名。</small>
            </div>
          </div>
          
          <div class="am-form-group" id="address-div">
            <label for="user-name" class="am-u-sm-3 am-form-label">办公地点</label>
            <div class="am-u-sm-9">
              <input type="text" name="staff_workaddress_name" readonly="readonly" value="${applicationScope.collegeAddressMap[object.staff_workaddress].dscb}" class="am-input-sm">
	          <input type="hidden" name="staff_workaddress" value="${object.staff_workaddress}">
              <small>选择办公地点。</small>
            </div>
          </div>
          
          <div class="am-form-group" id="address-div">
            <label for="user-name" class="am-u-sm-3 am-form-label">宿舍</label>
            <div class="am-u-sm-9">
              <input type="text" name="staff_room_name" readonly="readonly" value="${applicationScope.collegeAddressMap[object.staff_room].dscb}" class="am-input-sm">
	          <input type="hidden" name="staff_room" value="${object.staff_room}">
              <small>选择宿舍。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">联系方式</label>
            <div class="am-u-sm-9">
              <input type="text" name="phone" value="${object.phone}" placeholder="联系方式">
              <small>手机号码你懂的...</small>
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
          
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
          </div>
          <div class="am-form-group">
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
initTree("getJsonAddressTree.htm", "staff_workaddress_name", "staff_workaddress");
initTree("getJsonAddressTree.htm", "staff_room_name", "staff_room");
</script>
</body>
</html>

