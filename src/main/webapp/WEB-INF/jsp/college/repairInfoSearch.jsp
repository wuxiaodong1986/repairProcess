<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!doctype html>
<html class="no-js">
<head>
  <meta charset="utf-8">
  <meta name="description" content="设备管理/设备列表">
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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">报修</strong> / <small>报修信息</small></div>
    </div>
    
    <form method="post" action="#" id="searchform" class="am-form" onsubmit="resetPageNum(); loadformlist(); return false;">
      <input type="hidden" id="pageNum" name="pageNum" value="1">
	  <div class="am-g">
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      设备归属
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
          <select name="belong_type" id="belong_type">
  	        <c:forEach var="config" items="${applicationScope.configListMap['equm_type']}">
            <option value="${config.cfg_key}" >${config.cfg_value}</option>
            </c:forEach>
          </select>
	    </div>
			
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      名称
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="name" class="am-input-sm">
	    </div>
	  </div>
	  
	  <div class="am-g">
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      设备所在地
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group">
	      <input type="text" name="address_name" readonly="readonly" class="am-input-sm">
	      <input type="hidden" name="address">
	    </div>
	    
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      设备所有人
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <div style="width: 55%; float: left;">
	        <input type="text" name="org_name" readonly="readonly" class="am-input-sm">
	        <input type="hidden" name="org_id" value="123">
	      </div>
	      <div style="width: 44%; float: right;">
	        <select name="owner">
	        </select>
	      </div>
	    </div>
	  </div>
		
	  <div class="am-g">
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      维修状态
        </div>
        <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
          <select name="repair_status">
            <option value="0">--请选择--</option>
          	<option value="1">未完成的报修</option>
            <option value="2">已完成的报修</option>
          </select>
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
            <button id="repair" type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 查看</button>
          </div>
        </div>
      </div>
      
      <div class="am-u-sm-12" >
        <form id="listform" class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"></th>
                <th class="table-id" style="width: 10%;">报修人</th>
                <th class="table-title">维修状态</th>
                <th class="table-type">报修日期</th>
                <th class="table-author">设备名称</th>
                <th class="table-date">设备归属</th>
                <th class="table-date">设备所有人</th>
                <th class="table-date">设备所在地</th>
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

initTree("getJsonOrgTree.htm", "org_name", "org_id");
initTree("getJsonAddressTree.htm", "address_name", "address");

$("input[name='org_id']").change(function(
){
	var staff_org_id = $("input[name='org_id']").val();
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


var listurl = "repairInfos.htm";
var repairurl = "repairInfoPage.htm";

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
				
				colobjecttr.append('<td>'+colobject['o_id']+'</td>');
				if (colobject['status'] == '完成')
				{
					colobjecttr.append('<td class="am-text-success">'+colobject['status']+'</td>');
				}
				else
				{
					colobjecttr.append('<td class="am-text-danger">'+colobject['status']+'</td>');
				}
				colobjecttr.append('<td>'+colobject['apply_date']+'</td>');
				colobjecttr.append('<td>'+colobject['name']+'</td>');
				colobjecttr.append('<td>'+colobject['belong_type']+'</td>');
				colobjecttr.append('<td>'+colobject['owner']+'</td>');
				colobjecttr.append('<td>'+colobject['address']+'</td>');
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
				window.location.href=repairurl+'?id='+id;
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

