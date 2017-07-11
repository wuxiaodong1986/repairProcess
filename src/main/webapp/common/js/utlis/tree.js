function initTree(url, namekey, idkey)
{
	var nameInput = $("input[name='"+namekey+"']");
	nameInput.click(function(e)
	{
		
		if($(".common-tree").length != 0)
		{
			$(".common-tree").remove();
		}
		else
		{
			$(".common-tree").remove();
			loadTree(url, namekey, idkey);
			e.stopPropagation();
		}
		
	});
	
	$(document).on("click",".common-tree",function(e){
		e.stopPropagation();
	});
	$(document).click(function(
	){
		if($(".common-tree").length != 0)
		{
			$(".common-tree").remove();
			$("input[name='"+namekey+"']").val("");
			$("input[name='"+idkey+"']").val("");
		}
	});
}
function loadTree(url, namekey, idkey)
{
	$.get(
	url,
	{},
	function(data, textStatus)
	{
		var treeDiv = $("<div class=\"common-tree\" style=\"border: 1px solid #ccc; margin-top: -1px; z-index:100; position:absolute; max-height:300px;overflow:auto;  \" hidden=\"hidden\"></div>");
		var ul = $("<ul class=\"am-list admin-sidebar-list\"></ul>");
		var nodes = data.tree.nodes;
		treeRecursive(nodes, ul, namekey, idkey);
		treeDiv.append(ul);
		$("input[name='"+namekey+"']").parent().append(treeDiv);
		$(".common-tree").slideDown(300);
		$(document).off("click",".common-tree-menu");
		$(document).on("click",".common-tree-menu",function(
		){
			if($(this).parent().next().is(":visible"))
			{
				$(this).parent().next().slideUp(300);
			}
			else
			{
				$(this).parent().next().slideDown(300);
			}
		});
		$(".common-tree").css("width",$("input[name='"+namekey+"']").css("width"));
	},"json");
}
function treeRecursive(nodes, top, namekey, idkey)
{
	for (var o in nodes)
	{
		var node = nodes[o];
		var li = $("<li></li>");
		if (node.nodes.length == 0)
		{
			var a = $("<a href=\"#\" class=\"am-cf\" style=\"height: 38.5px; padding-top: 7px;\"><span class=\"am-icon-group\"></span> <span onclick=\"selectTreeNode('"+namekey+"','"+node.name+"','"+idkey+"','"+node.id+"');\" >"+node.name+"</span></a>");
			li.append(a);
		}
		else
		{
			var a = $("<a href=\"#\" class=\"am-cf\" style=\"height: 38.5px; padding-top: 7px;\"><span class=\"am-icon-plus-square common-tree-menu\"></span> <span onclick=\"selectTreeNode('"+namekey+"','"+node.name+"','"+idkey+"','"+node.id+"');\" >"+node.name+"</span></a>");
			var ul = $("<ul class=\"am-list admin-sidebar-sub\" hidden=\"hidden\"></ul>");
			treeRecursive(node.nodes, ul, namekey, idkey);
			li.append(a);
			li.append(ul);
		}
		top.append(li);
	}
}
function selectTreeNode(namekey, name, idkey, id)
{
	$("input[name='"+namekey+"']").val(name);
	$("input[name='"+idkey+"']").val(id);
	$("input[name='"+idkey+"']").change();
	$("input[name='"+idkey+"']").next().slideUp(300);
	$(".common-tree").remove();
}