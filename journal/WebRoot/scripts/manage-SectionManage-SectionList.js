$(window).resize(fnResize);
$(document).ready(function(){
	fnResize();
	$('i[operate="NewTask"],i[operate="Edit"],i[operate="Remove"]').css({"opacity":"0.3","cursor":"default"});
	fnSectionList();
	$('i[operate="NewSection"]').attr("title","新建阶段").click(fnNewSection);
});
//说明：调整页面布局
function fnResize() {
    $("body").height(window.parent.MainHeight);
    $("body").width(window.parent.MainWidth);
    $("#main_container").height(window.parent.MainHeight-120);
    $("#leftTree").height(window.parent.MainHeight-180);
   $("#right_container").width(window.parent.MainWidth-300);
   $("#right_container").height(window.parent.MainHeight-180);
};
function fnNewSection(){
	var projectid = $("#projectid").val();
	var iframe = document.getElementById("frmContent");
	iframe.src=SiteConfig.DoMain+"actions/section/sectionActions.action?method=initialAddSection&projectID="+projectid;
}

function fnNewTask(){
	var treeObj = $.fn.zTree.getZTreeObj("sectionTree");
	var iframe = document.getElementById("frmContent");
	var selectedNode = treeObj.getSelectedNodes()[0];
	selectedNode.sectionID = selectedNode.getParentNode() == null
	? selectedNode.sectionID
	: selectedNode.getParentNode().sectionID
	document.getElementById("frmContent").src = selectedNode.isSection 
	? SiteConfig.DoMain+"actions/task/taskActions.action?method=initialAddTask&sectionID="+selectedNode.sectionID
	: SiteConfig.DoMain+"actions/task/taskActions.action?method=initialAddTask&sectionID="+selectedNode.sectionID+"&parentID="+selectedNode.taskID;
}

function fnEdit(){
	$(this).attr("title","").css({"opacity":"0.3","cursor":"default"}).unbind("click");
	var treeObj = $.fn.zTree.getZTreeObj("sectionTree");
	var nodes = treeObj.getSelectedNodes();
	var iframe = document.getElementById("frmContent");
	if(nodes!=null&&nodes[0]!=null){
		if(nodes[0].isSection){
			iframe.src=SiteConfig.DoMain+"actions/section/sectionActions.action?method=initialEditSection&sectionID="+nodes[0].sectionID+"&projectID="+$("#projectid").val();
		}else{
			iframe.src=SiteConfig.DoMain+"actions/task/taskActions.action?method=initialEditTask&taskID="+nodes[0].taskID+"&projectID="+$("#projectid").val();
		}
	}
}

function fnDelete(){
	var treeObj = $.fn.zTree.getZTreeObj("sectionTree");
	var selectedNode = treeObj.getSelectedNodes()[0];
	window.parent.DialogConfirm.options.action = function(){
		var deleteUrl = SiteConfig.DoMain+"actions/ajax/ajaxActions.action";
		var paramData = selectedNode.isSection 
		? {"method":"doDeleteOneSection","sectionID":selectedNode.sectionID}
		: {"method":"doDeleteOneTask","taskID":selectedNode.taskID};
		CreatePost(paramData,deleteUrl,function(result){
			if(result.data == 1){
				var selectNode = selectedNode.getParentNode() == null ? selectedNode.getNextNode() : selectedNode.getParentNode() ;
				treeObj.removeNode(selectedNode);
				treeObj.selectNode(selectNode);
				treeNode_click(null,null,selectNode,null);
			}
		},true);
	};
	window.parent.DialogConfirm.show();
};

function fnDeleteSuccess(){
	window.parent.Messenger().post({
		message:"该节点已删除！"
	   ,hideAfter:3
	});
	var iframe = document.getElementById("frmContent");
	iframe.src="";
}

function setHeight() {
	var ifm= document.getElementById("frmContent");   
	var subWeb = document.frames ? document.frames["frmContent"].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) {
	   ifm.height = subWeb.body.scrollHeight+100;
	}   
}

function fnSectionList(){
	var ztreeSetting = {
		async:{
			enable: true,
            dataFilter: GetSectionsCallBack,
            autoParam: ["id=projectID"],
            otherParam: { "otherParam": "zTreeAsyncTest" },
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            type:"post",
            url: getAsyncUrl
		},
		view: {
			selectedMulti: false
		},
		callback: {
            onAsyncError: function (event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
            },
            onAsyncSuccess: function (event, treeId, treeNode, msg) {
            },
            onClick: treeNode_click
        }
	};
	$.fn.zTree.init($("#sectionTree"), ztreeSetting);
}
function getAsyncUrl(treeId, treeNode) {
	if(treeNode === undefined){
		return SiteConfig.DoMain+"actions/ajax/ajaxActions.action?method=doSearchSections&projectID="+$("#projectid").val();
	}
	var taskid = treeNode.isSection ? "" : treeNode.taskID;
	treeNode.sectionID = treeNode.getParentNode() != null ? treeNode.getParentNode().sectionID : treeNode.sectionID;
	return SiteConfig.DoMain+"actions/ajax/ajaxActions.action?method=doSearchSectionTasks&sectionID="+treeNode.sectionID +'&taskID='+taskid;
};

function treeNode_click(event, treeId, treeNode,clickFlag) {
	$('i[operate="NewTask"],i[operate="Edit"],i[operate="Remove"]').css({"opacity":"1","cursor":"pointer"});
	$('i[operate="NewSection"]').attr("title","新建阶段").click(fnNewSection);
	$('i[operate="NewTask"]').attr("title","新建阶段任务").click(fnNewTask);
	$('i[operate="Edit"]').attr("title","编辑").click(fnEdit);
	$('i[operate="Remove"]').attr("title","移除").click(fnDelete);
	var iframe = document.getElementById("frmContent");
	if(treeNode.isSection){
		iframe.src=SiteConfig.DoMain+"actions/section/sectionActions.action?method=initialViewSection&sectionID="+treeNode.sectionID;
    }else{
    	iframe.src=SiteConfig.DoMain+"actions/section/taskActions.action?method=initialViewTask&taskID="+treeNode.taskID;
    }
}

function GetSectionsCallBack(treeId, parentNode, result){
	 var data = $.parseJSON(result.result).data;
	 var childNodes = [];
     for (var i = 0;i<data.length;i++) {
    	 var dataItem = data[i];
    	 if(dataItem.taskID === undefined){
    		 childNodes[i] = {name: dataItem.name,sectionID: dataItem.sectionID,isParent:true,isSection:true,icon:SiteConfig.DoMain+"images/section.png"};	 
    	 }else{
    		 childNodes[i] = {name: dataItem.name,taskID: dataItem.taskID,isParent:true,isSection:false,icon:SiteConfig.DoMain+"images/task.png"};
    	 }
     }
     return childNodes;
};