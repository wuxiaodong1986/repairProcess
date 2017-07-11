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
<%@include file="../frame/head.jsp" %>

<div class="am-cf admin-main">
<%@include file="../frame/menu.jsp" %>

  <!-- content start -->
  <div class="admin-content">
    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备报修</strong> / <small><c:if test="${beanName=='applyRepairProcess'}">报修申请</c:if><c:if test="${beanName=='repairAcceptProcess'}">维修员受理</c:if><c:if test="${beanName=='simpleRpairProcess'}">维修员初诊</c:if><c:if test="${beanName=='repairManagerApprovalProcess'}">维修主管审批</c:if><c:if test="${beanName=='eduManager'}">现教中心主管审批</c:if><c:if test="${beanName=='assetManagerProcess'}">固定资产管理处主管审批</c:if><c:if test="${beanName=='staffYuanzhangApprovalProcess'}">现教中心院长审批</c:if><c:if test="${beanName=='pubYuanzhangApprovalProcess'}">教学院长审批</c:if><c:if test="${beanName=='doRepairProcess'}">修理</c:if><c:if test="${beanName=='viewProcess'}">报修单查看</c:if></small></div>
    </div>

    <hr/>
    <form method="post" action="nextRepairprocess.htm" id="updateform" class="am-form am-form-horizontal" onsubmit="return submitCheck();" data-am-validator>
    
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
        <jsp:useBean id="now" class="java.util.Date" />
        
        <div class="am-tab-panel am-fade am-in am-active" id="tab1">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">报修类型：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <input type="hidden" name="belong_type" value="${equipment.belong_type}">
              <c:if test="${equipment.belong_type=='2'}">办公设备</c:if><c:if test="${equipment.belong_type=='3'}">教学设备</c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备名称/品牌/型号：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${equipment.name}/${equipment.brand}/${equipment.model}
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">购置日期/质保期：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${equipment.purchase}/${equipment.shelf}
            </div>
          </div>
          
          <c:if test="${equipment.belong_type=='2'}">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备所有人：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${applicationScope.operatorMap[equipment.owner].name}
            </div>
          </div>
          </c:if>
          
          <c:if test="${equipment.belong_type=='3'}">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">设备所在地：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              ${applicationScope.collegeAddressMap[equipment.address].name}
            </div>
          </div>
          </c:if>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">故障现象：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='applyRepairProcess'}">
              <textarea name="dscb" required>${object.dscb}</textarea>
              </c:if>
              <c:if test="${beanName!='applyRepairProcess'}">
              ${object.dscb}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">报修人/报修时间：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='applyRepairProcess'}">
			  <input type="hidden" name="apply_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              <input type="hidden" name="o_id" value="${sessionScope.operator.id}">
              ${sessionScope.operator.name}/<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='applyRepairProcess'}">
              ${applicationScope.operatorMap[object.o_id].name}/${object.apply_date}
              </c:if>
            </div>
          </div>
          
        </div>
          
        <div class="am-tab-panel am-fade am-in am-active" id="tab2">
          
          <c:if test="${beanName=='simpleRpairProcess'}">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">初诊结果：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <select name="first_step_type" data-am-selected="{btnSize: 'sm'}">
                <option value="0">--请选择--</option>
                <option value="1">维修完成</option>
                <option value="2">耗材审批</option>
              </select>
            </div>
          </div>
          </c:if>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">承修人：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='repairAcceptProcess'}">
              <input type="hidden" name=repairer value="${sessionScope.operator.id}">
              ${sessionScope.operator.name}
              </c:if>
              <c:if test="${beanName!='repairAcceptProcess'}">
              ${applicationScope.operatorMap[object.repairer].name}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">起修时间：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess'}">
              <input type="hidden" name="repair_from_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.repair_from_date}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top first_step_type_shenpi">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">预计完成时间：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess'}">
              <input type="text" name="expect_date" value="${object.expect_date}" placeholder="预计完成时间" data-am-datepicker readonly>
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.expect_date}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top first_step_type_shenpi">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">初步判断：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess'}">
              <textarea name="first_step" required>${object.first_step}</textarea>
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.first_step}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top first_step_type_shenpi">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">更换零部件清单：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess'}">
              <textarea name="repair_parts" required>${object.repair_parts}</textarea>
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.repair_parts}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top first_step_type_shenpi">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">预算：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess'}">
              <textarea name="budget" required>${object.budget}</textarea>
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.budget}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top first_step_type_finish">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">结论：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='simpleRpairProcess' || beanName=='doRepairProcess'}">
              <textarea name="conclusion" required>${object.conclusion}</textarea>
              </c:if>
              <c:if test="${beanName!='simpleRpairProcess'}">
              ${object.conclusion}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">验收人：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='doRepairProcess'}">
              <input type="hidden" name="finisher" value="${sessionScope.operator.id}">
              ${sessionScope.operator.name}
              </c:if>
              <c:if test="${beanName!='doRepairProcess'}">
              ${applicationScope.operatorMap[object.finisher].name}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">维修主管：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='repairManagerApprovalProcess'}">
              <input type="hidden" name="repairManager" value="${sessionScope.operator.id}">
              <input type="hidden" name="repairManager_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              同意。——&nbsp;&nbsp;${sessionScope.operator.name}&nbsp;&nbsp;<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='repairManagerApprovalProcess' && 0 != object.repairManager && !empty(object.repairManager)}">
              同意。——&nbsp;&nbsp;${applicationScope.operatorMap[object.repairManager].name}&nbsp;&nbsp;${object.repairManager_date}
              </c:if>
            </div>
          </div>
          
        </div>
          
        <div class="am-tab-panel am-fade am-in am-active" id="tab3">
        
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">现教中心主管：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='eduManagerProcess'}">
              <input type="hidden" name="eduManager" value="${sessionScope.operator.id}">
              <input type="hidden" name="eduManager_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              同意。——&nbsp;&nbsp;${sessionScope.operator.name}&nbsp;&nbsp;<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='eduManagerProcess' && 0 != object.eduManager && !empty(object.eduManager)}">
              同意。——&nbsp;&nbsp;${applicationScope.operatorMap[object.eduManager].name}&nbsp;&nbsp;${object.eduManager_date}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">固定资产管理处主管：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='assetManagerProcess'}">
              <input type="hidden" name="assetManager" value="${sessionScope.operator.id}">
              <input type="hidden" name="assetManager_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              同意。——&nbsp;&nbsp;${sessionScope.operator.name}&nbsp;&nbsp;<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='assetManagerProcess' && 0 != object.assetManager && !empty(object.assetManager)}">
              同意。——&nbsp;&nbsp;${applicationScope.operatorMap[object.assetManager].name}&nbsp;&nbsp;${object.assetManager_date}
              </c:if>
            </div>
          </div>
          
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">主管院领导：</div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end am-text-left">
              <c:if test="${beanName=='staffYuanzhangApprovalProcess' || beanName=='pubYuanzhangApprovalProcess'}">
              <input type="hidden" name="yuanzhang" value="${sessionScope.operator.id}">
              <input type="hidden" name="yuanzhang_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
              同意。——&nbsp;&nbsp;${sessionScope.operator.name}&nbsp;&nbsp;<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
              </c:if>
              <c:if test="${beanName!='staffYuanzhangApprovalProcess' && beanName!='pubYuanzhangApprovalProcess' && 0 != object.yuanzhang && !empty(object.yuanzhang)}">
              同意。——&nbsp;&nbsp;${applicationScope.operatorMap[object.yuanzhang].name}&nbsp;&nbsp;${object.yuanzhang_date}
              </c:if>
            </div>
          </div>
          
        </div>
        
      </div>
    </div>
    
    <div class="am-margin">
      <c:if test="${beanName!='finishProcess' && beanName!='viewProcess'}">
      <button type="submit" class="am-btn am-btn-primary am-btn-xs">保存</button>
      </c:if>
      <button type="button" onclick="history.go(-1);" class="am-btn am-btn-primary am-btn-xs">返回</button>
  	</div>
  	
    </form>
    
  </div>
  <!-- content end -->
</div>

<%@include file="../frame/foot.jsp" %>

<script type="text/javascript">
function submitCheck()
{
	<c:if test="${beanName=='simpleRpairProcess'}">
	if ($("select[name='first_step_type']").val()==0)
	{
		alert("请选择初诊结果");
		return false;
	}
	
	if ($("select[name='first_step_type']").val()==1)
	{
		$("input[name='expect_date']").val('');
		$("textarea[name='first_step']").val('');
		$("textarea[name='repair_parts']").val('');
		$("input[name='budget']").val('');
	}
	if ($("select[name='first_step_type']").val()==2)
	{
		$("textarea[name='conclusion']").val('');
	}
	</c:if>
	return true;
}
<c:if test="${beanName=='simpleRpairProcess'}">
$(".first_step_type_shenpi").hide();
$(".first_step_type_finish").hide();
$("select[name='first_step_type']").change(function(
){
	$(".first_step_type_shenpi").hide();
	$(".first_step_type_finish").hide();
	if ($("select[name='first_step_type']").val()==1)
	{
		$(".first_step_type_finish").show();
		$("#updateform").attr("action", "finishRepairprocess.htm");
	}
	else if ($("select[name='first_step_type']").val()==2)
	{
		$(".first_step_type_shenpi").show();
		$("#updateform").attr("action", "nextRepairprocess.htm");
	}
});
</c:if>


</script>
</body>
</html>

