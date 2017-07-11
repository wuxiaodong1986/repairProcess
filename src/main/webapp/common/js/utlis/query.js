function resetSearchform(formid)
{
	$("#"+formid)[0].reset();
	
	$("#"+formid + " input[type='text'][readonly]").each(function(){
	    $(this).next().val('');
	  });
}

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
			loadTbody(colobjects);
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
			$("#add").show();
			$("#listform input").click(function(
			){
				$("#listform input").parent().parent().removeClass("am-active");
				$(this).parent().parent().addClass("am-active");
				
				$("#operation button").hide();
				$("#add").show();
				$("#edit").show();
				$("#delete").show();
			});
			$("#add").unbind("click");
			$("#add").click(function(
			){
				window.location.href=updateurl;
			});
			$("#edit").unbind("click");
			$("#edit").click(function(
			){
				var id=$("#listform input:radio[name='id']:checked").val();
				if(id==null)
				{
					alert("请选择一条记录!");
					return false;
				}
				window.location.href=updateurl+'?id='+id;
			});
			$("#delete").unbind("click");
			$("#delete").click(function(
			){
				var id=$("#listform input:radio[name='id']:checked").val();
				if(id==null)
				{
					alert("请选择一条记录!");
					return false;
				}
				if (confirm('你确实要删除吗?'))
				{
					window.location.href=delurl+'?id='+id;
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