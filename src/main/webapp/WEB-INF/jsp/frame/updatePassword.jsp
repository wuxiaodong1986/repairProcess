<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="个人信息/修改密码">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人信息</strong> / <small>修改密码</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="updatePassword.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>

          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">旧密码</label>
            <div class="am-u-sm-9">
              <input type="password" name="oldPassword" value="" placeholder="旧密码">
              <small>输入旧密码。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">新密码</label>
            <div class="am-u-sm-9">
              <input type="password" name="newPassword" value="" placeholder="新密码">
              <small>输入新密码。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">重新输入</label>
            <div class="am-u-sm-9">
              <input type="password" name="rePassword" value="" placeholder="重新输入">
              <small>重新输入密码。</small>
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
$("form").submit(function(e){
	if ($("input[name='oldPassword']").val()=='')
	{
		alert("请输入旧密码");
		return false;
	}
	if ($("input[name='newPassword']").val()=='')
	{
		alert("请输入新密码");
		return false;
	}
	if ($("input[name='newPassword']").val() != $("input[name='rePassword']").val())
	{
		alert("两次输入密码不一致");
		return false;
	}
	return true;
	});
</script>
</body>
</html>

