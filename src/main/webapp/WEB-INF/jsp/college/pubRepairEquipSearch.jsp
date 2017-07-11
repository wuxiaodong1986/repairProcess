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
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">设备报修</strong> / <small>公共设备</small></div>
    </div>
    
    <form method="post" action="#" id="searchform" class="am-form" onsubmit="resetPageNum(); loadformlist(); return false;">
      <input type="hidden" id="pageNum" name="pageNum" value="1">
	  <input type="hidden" name="belong_type" value="3">
	  <div class="am-g">
	    <div class="am-u-sm-6 am-u-md-3 am-u-lg-3 am-text-right am-form-group">
	      设备名称
	    </div>
	    <div class="am-u-sm-6 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="name" class="am-input-sm">
	    </div>
			
	    <div class="am-u-sm-6 am-u-md-3 am-u-lg-3 am-text-right am-form-group">
	      品牌
	    </div>
	    <div class="am-u-sm-6 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="brand" class="am-input-sm">
	    </div>
	  </div>
	  
	  <div class="am-g">
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-text-center am-form-group">
	      设备所在地
	    </div>
	    <div class="am-u-sm-12 am-u-md-3 am-u-lg-3 am-form-group am-u-end">
	      <input type="text" name="address_name" readonly="readonly" class="am-input-sm">
	      <input type="hidden" name="address">
	    </div>
	  </div>
	  
	  <div class="am-g">
	    <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-text-right">
	      <button type="submit" class="am-btn am-btn-primary am-btn-xs am-btn-lg">查&nbsp&nbsp&nbsp询</button>
	    </div>
	    <div class="am-u-sm-2 am-u-md-2 am-u-lg-2">
	    </div>
	    <div class="am-u-sm-5 am-u-md-5 am-u-lg-5 am-text-left">
	      <button type="reset" class="am-btn am-btn-primary am-btn-xs am-btn-lg">重&nbsp&nbsp&nbsp置</button>
	    </div>
	  </div>
    </form>
    
    <div id="form_list">
    
      <div class="am-u-sm-12 am-u-md-6" id="operation">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
            <button id="repair" type="button" class="am-btn am-btn-default" ><span class="am-icon-plus"></span> 我要报修</button>
          </div>
        </div>
      </div>
      
      <div class="am-u-sm-12" >
        <form id="listform" class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
              <tr>
                <th class="table-check"></th>
                <th class="table-title">设备名称</th>
                <th class="table-title">品牌</th>
                <th class="table-title">型号</th>
                <th class="table-title">质保期</th>
                <th class="table-title">购置日期</th>
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
<script type="text/javascript">
var listurl = "repairEquips.htm";
var repairurl = "preApplyRepair.htm";

initTree("getJsonAddressTree.htm", "address_name", "address");

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

