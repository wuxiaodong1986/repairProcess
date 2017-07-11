<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="设备报修">
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
<%@include file="../../frame/head.jsp" %>

<div class="am-cf admin-main">
<%@include file="../../frame/menu.jsp" %>

  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备报修</strong> / <small>现教中心处长审批</small></div>
    </div>

    <hr/>
    <form method="post" action="nextRepairprocess.htm" id="updateform" class="am-form am-form-horizontal" data-am-validator>
    
    <div class="am-tabs am-margin" data-am-tabs>
      <ul class="am-tabs-nav am-nav am-nav-tabs">
        <li class="am-active"><a href="#tab1">报修人填写</a></li>
        <li><a href="#tab2">现教中心填写</a></li>
        <li><a href="#tab3">领导审批</a></li>
      </ul>
      
      <div class="am-tabs-bd">
        <input type="hidden" name="id" value="${object.id}">
        <input type="hidden" name="e_id" value="${equipment.id}">
        <input type="hidden" name="status" value="${object.status}">
        
        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">报修类型：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              <input type="hidden" name="belong_type" value="${equipment.belong_type}">
              <c:if test="${equipment.belong_type=='2'}">办公设备</c:if><c:if test="${equipment.belong_type=='3'}">教学设备</c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备名称/品牌/型号：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${equipment.name}/${equipment.brand}/${equipment.model}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">购置日期/质保期：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${equipment.purchase}/${equipment.shelf}
            </div>
          </div>
          
          <c:if test="${equipment.belong_type=='2'}">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备所有人：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.operatorMap[equipment.owner].name}
            </div>
          </div>
          </c:if>
          
          <c:if test="${equipment.belong_type=='3'}">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备所在地：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.collegeAddressMap[equipment.address].name}
            </div>
          </div>
          </c:if>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">故障现象：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${object.dscb}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">报修人/报修时间：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${applicationScope.operatorMap[object.o_id].name}/${object.apply_date}
            </div>
          </div>
          
        </div>
          
        <div class="am-tab-panel am-fade am-in am-active" id="tab2">
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">承修人：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.operatorMap[object.repairer].name}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">起修时间：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.repair_from_date}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">预计完成时间：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.expect_date}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">初步判断：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.first_step}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">更换零部件清单：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.repair_parts}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">预算：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.budget}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">结论：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${object.conclusion}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">验收人：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.operatorMap[object.finisher].name}
            </div>
          </div>
          
        </div>
          
        <div class="am-tab-panel am-fade am-in am-active" id="tab3">
        
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">维修主管：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.operatorMap[object.repairManager].name}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">后勤处／教务处 主管：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${sessionScope.operator.name}
              <input type="hidden" name="chuzhang" value="${sessionScope.operator.id}">
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">主管院领导：</div>
            <div class="am-u-sm-8 am-u-md-10 am-text-left">
              ${applicationScope.operatorMap[object.yuanzhang].name}
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
    
    <div class="am-margin">
      <button type="submit" class="am-btn am-btn-primary am-btn-xs">同意</button>
      <button type="button" onclick="history.go(-1);" class="am-btn am-btn-primary am-btn-xs">返回</button>
  	</div>
  	
    </form>
    
  </div>
  <!-- content end -->
</div>

<%@include file="../../frame/foot.jsp" %>

<script type="text/javascript">

</script>
</body>
</html>

