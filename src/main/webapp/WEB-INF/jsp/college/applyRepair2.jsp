<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="设备报修/我的设备">
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
		<div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备报修</strong> / <small>我的设备</small></div>
    </div>

	<hr/>
<style>
.repair_table{text-align: center;}
.repair_table th{text-align: center;}
.repair_table textarea{width: 100%;}
</style>
	<div class="am-g">
		<form method="post" action="#" id="updateform" class="am-form am-form-horizontal" data-am-validator onsubmit="update(); return false;">
			<input type="hidden" name="e_id" value="${object.id}">
			<input type="hidden" name="status" value="1">
			<div class="am-u-sm-11 am-u-sm-centered">
				<table class="am-table am-table-bordered repair_table">
					<tr>
						<th width="7%" rowspan="3">报<br>修<br>人<br>填<br>写</th>
						<th width="15%">报修类型</th>
						<td width="15%"><c:if test="${object.belong_type=='2'}">办公设备</c:if><c:if test="${object.belong_type=='3'}">教学设备</c:if></td>
						<th width="15%">设备名称及品牌</th>
						<td width="15%">${object.name}/<br>${object.brand}</td>
						<th width="15%">设备所在地</th>
						<td width="18%"></td>
					</tr>
					<tr>
						<th rowspan="2" style="border-left:1px solid #ddd;">故障现象<br>报修原因</th>
						<td rowspan="2" colspan="3">
					    	<textarea name="dscb" required></textarea>
						</td>
						<th>报修时间</th>
						<jsp:useBean id="now" class="java.util.Date" />
						<td>
							<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
							<input type="hidden" name="apply_date" value="<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />">
						</td>
					</tr>
					<tr>
						<th style="border-left:1px solid #ddd;">报修人</th>
						<td>
							${sessionScope.operator.name}
							<input type="hidden" name="o_id" value="${sessionScope.operator.id}">
						</td>
					</tr>
					
					<!--<tr>
						<td colspan="7" style="padding: 0px; border-width: 2px;"></td>
					</tr>-->
					
					<tr>
						<th rowspan="6">现<br>教<br>中<br>心<br>填<br>写</th>
						<th>起修时间</th>
						<td></td>
						<th>预计完成时间</th>
						<td></td>
						<th>是否过质保</th>
						<td></td>
					</tr>
					<tr>
						<th rowspan="2" style="border-left:1px solid #ddd;">初步判断</th>
						<td rowspan="2" colspan="3">
						</td>
						<th colspan="2">更换零部件清单</th>
					</tr>
					<tr>
						<td rowspan="4" colspan="2" style="border-left:1px solid #ddd;">
						</td>
					</tr>
					<tr>
						<th style="border-left:1px solid #ddd;">结论</th>
						<td colspan="3">
						</td>
					</tr>
					<tr>
						<th rowspan="2" style="border-left:1px solid #ddd;">承修人<br>（或单位）</th>
						<td rowspan="2">
						</td>
						<th rowspan="2">验收签字</th>
						<td rowspan="2">
						</td>
					</tr>
					
					<tr>
						<td colspan="7" style="padding: 0px; border-width: 4px;"></td>
					</tr>
					
					<tr>
						<th rowspan="2">领<br>导<br>签<br>字</th>
						<th rowspan="2">报修部门<br>主管签字</th>
						<td rowspan="2"></td>
						<th rowspan="2">后勤处／教务处<br>主管签字</th>
						<td rowspan="2"></td>
						<th rowspan="2">主管院领导<br>签字</th>
						<td rowspan="2"></td>
					</tr>
				</table>
			</div>
			<div class="am-form-group">
				<div class="am-u-sm-4 am-u-sm-push-3">
					<button type="submit" class="am-btn am-btn-primary">保存</button>
				</div>
				<div class="am-u-sm-5">
					<button type="button" onclick="loadcontent('staffRepairEquipSearch.htm');" class="am-btn am-btn-primary">返回</button>
				</div>
			</div>
		</form>
	</div>
  </div>
  <!-- content end -->
</div>

<%@include file="../frame/foot.jsp" %>

<script type="text/javascript">
var listurl = "staffRepairEquips.htm";
var repairurl = "preApplyRepair.htm";

var alertMessage = '${alertMessage}';
if (''!=alertMessage)
{
	 $("#my-alert").modal('open');
}

$("#form_list").hide();
$("#searchform").submit();
function resetPageNum()
{
    $("#pageNum").val("1");
}
var count;
function loadformlist()
{	
	$.ajax({
		type: "POST",
		url: listurl,
		data: $("#searchform").serialize(),
		dataType: "json",
		success: function(data, textStatus)
		{
			$("#form_list").show();
			//加载表内容
			$("#listform table tbody").empty();
			var colobjects = data.objects;
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
				
				colobjecttr.append('<td>'+colobject['name']+'</td>');
				colobjecttr.append('<td>'+colobject['brand']+'</td>');
				colobjecttr.append('<td>'+colobject['model']+'</td>');
				colobjecttr.append('<td>'+colobject['shelf']+'</td>');
				colobjecttr.append('<td>'+colobject['purchase']+'</td>');
				$("#listform table tbody").append(colobjecttr);
			}
			//加载分页
			count = data.count;
			$("#count").text(count);
			$("#pagination").empty();
			var pageSize = Math.floor((count-1)/10)+1;
			var pageNum = parseInt($("#pageNum").val());
			if(pageNum==1)
			{
				$("#pagination").append('<li class="am-disabled"><a href="#">«</a></li>');
			}
			else
			{
				$("#pagination").append('<li onclick="turnPage(0);"><a href="#">«</a></li>');
			}
			var frompage = 1;
			if (pageNum-5 > 1)
			{
				frompage = pageNum-5;
			}
			var topage = pageSize;
			if (pageNum+5 < pageSize)
			{
				topage = pageNum+5;
			}
			for(var i = frompage; i <= topage; i++)
			{
				if (pageNum == i)
				{
					$("#pagination").append('<li class="am-active"><a href="#">'+i+'</a></li>');
				}
				else
				{
					$("#pagination").append('<li onclick="turnPage('+i+');"><a href="#">'+i+'</a></li>');
				}
			}
			if(pageNum==pageSize)
			{
				$("#pagination").append('<li class="am-disabled"><a href="#">»</a></li>');
			}
			else
			{
				$("#pagination").append('<li onclick="turnPage(-1);"><a href="#">»</a></li>');
			}
			//加载操作按钮
			$("#operation button").hide();
			$("#listform input").click(function(
			){
				$("#listform input").parent().parent().removeClass("am-active");
				$(this).parent().parent().addClass("am-active");
				
				$("#operation button").hide();
				$("#repair").show();
			});
			$("#repair").click(function(
			){
				var id=$("#listform input:radio[name='id']:checked").val();
				if(id==null)
				{
					alert("请选择一条记录!");
					return false;
				}
				if (confirm('你确实要报修此设备吗?'))
				{
					window.location.href=repairurl+'?id='+id;
				};
			});
		},
		error: function(XMLHttpRequest, textStatus, errorThrown)
		{
			alert(XMLHttpRequest.status+" 你懂的");
		}
	});
}
function turnPage(turnNum)
{
	var pageSize = Math.floor((count-1)/10)+1;
	var pageNum = parseInt($("#pageNum").val());
	if(turnNum==0)
	{
		turnNum=pageNum-1;
	}
	if(turnNum==-1)
	{
		turnNum=pageNum+1;
	}
	if(turnNum<1)
	{
		turnNum=1;
	}
	if(turnNum>pageSize)
	{
		turnNum=pageSize;
	}
	$("#pageNum").val(turnNum);
	
	loadformlist();
}
</script>
</body>
</html>

