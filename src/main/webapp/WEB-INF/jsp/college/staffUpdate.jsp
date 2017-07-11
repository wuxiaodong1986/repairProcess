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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">教工管理</strong> / <small><c:if test="${object.id==0}">新增</c:if><c:if test="${object.id!=0}">修改</c:if>教工</small></div>
    </div>
    
    <hr/>
    
    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-4 am-u-md-push-8">
      </div>
      <div class="am-u-sm-12 am-u-md-8 am-u-md-pull-4">
        <form method="post" action="updateStaff.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
          <input type="hidden" name = "id" value="${object.id}">
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">工号</label>
            <div class="am-u-sm-9">
              <input type="text" name="username" value="${object.username}" placeholder="工号" <c:if test="${object.id!=0}">readonly="readonly"</c:if> required>
              <small>输入工号。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">姓名</label>
            <div class="am-u-sm-9">
              <input type="text" name="name" value="${object.name}" placeholder="姓名">
              <small>输入姓名。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">职位</label>
            <div class="am-u-sm-9">
              <input type="text" name="staff_position" value="${object.staff_position}" placeholder="职位">
              <small>输入职位。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">部门</label>
            <div class="am-u-sm-9">
              <input type="text" name="staff_org_name" readonly="readonly" value="${applicationScope.collegeOrgMap[object.staff_org_id].name}" class="am-input-sm">
	          <input type="hidden" name="staff_org_id" value="${object.staff_org_id}">
              <small>请选择部门。</small>
            </div>
          </div>
          
          <div class="am-form-group">
            <label for="user-name" class="am-u-sm-3 am-form-label">联系方式</label>
            <div class="am-u-sm-9">
              <input type="text" name="phone" value="${object.phone}" placeholder="联系方式">
              <small>手机号码你懂的...</small>
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
            <label for="user-name" class="am-u-sm-3 am-form-label">账户状态</label>
            <div class="am-u-sm-9">
              <select name="status">
                <c:forEach var="config" items="${applicationScope.configListMap['opr_status']}">
                  <option value="${config.cfg_key}" <c:if test="${object.status==config.cfg_key}">selected="selected"</c:if> >${config.cfg_value}</option>
                </c:forEach>
              </select>
              <small>请选择账户状态。</small>
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
initTree("getJsonOrgTree.htm", "staff_org_name", "staff_org_id");
</script>
</body>
</html>

