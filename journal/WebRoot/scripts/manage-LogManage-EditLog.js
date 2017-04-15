
$(document).ready(function(){
	var currentDate = new Date($("#hidSelectDate").val());
	var startDate = currentDate.format("yyyy-MM-dd")+" 0:00";
	var endDate = currentDate.format("yyyy-MM-dd")+" 23:59";
	var prepTaskId = 0;
	var timepickerOptions = {
		minView : 0,
		pickerPosition : "bottom-left",
		maxView : 0,
		autoclose : true,
		startDate : startDate,
		endDate : endDate,
		format : 'hh:ii',
		language : 'zh-CN',
		startView : 0
	};
	$("#txtStartTime").datetimepicker(timepickerOptions).on('changeDate', function(ev){
		var endTime = new Date(ev.date);
		endTime.setHours(endTime.getHours() - 8);
		var start = parseInt(endTime.format("hhmm"));
		var end = parseInt($("#txtEndTime > input").val().replace(":",""));
		if(start >= end){
			endTime.setMinutes(endTime.getMinutes() + 5);
			$("#txtEndTime").datetimepicker('setStartDate', endTime);
			$("#txtEndTime > input").val(endTime.format("hh:mm"));
		}
	});
	$("#txtEndTime").datetimepicker(timepickerOptions).on('changeDate',function(ev){
		var startTime = new Date(ev.date);
		startTime.setHours(startTime.getHours() - 8);
		var start = parseInt(startTime.format("hhmm"));
		var end = parseInt($("#txtStartTime > input").val().replace(":",""));
		if(start <= end){
			startTime.setMinutes(startTime.getMinutes() - 5);
			$("#txtStartTime").datetimepicker('setEndDate', startTime);
			$("#txtStartTime > input").val(startTime.format("hh:mm"));
		}
	});
	$("#slider" ).slider({slide: function( event, ui ) {  $(this).prev("input").val(ui.value+" %"); }});
	var sliderValue = parseInt($("#txtPercentage").val().replace(" %",""));
	$("#slider" ).slider("option","value",sliderValue);
	$("#btnSubmit").click(fnSubmitEditLog);
	try {
		var projectTree = window.parent.$.fn.zTree.getZTreeObj("projectTree");
		var projectNode = projectTree.getNodeByParam("nodeKey", String.format("node_project_{0}",$("#hidProjectId").val()), null);
		projectTree.expandNode(projectNode,true,false,false,true);
		projectTree.setting.callback.onExpand = function(event, treeId, treeNode){
			var sectionNode = projectTree.getNodeByParam("nodeKey",String.format("node_section_{0}",$("#hidSectionId").val()),treeNode);
			if(sectionNode != null){
				projectTree.expandNode(sectionNode,true,false,false,true);	
			}else{
				var taskArray = $("#hidLogStr").val().split(";");
				for ( var i = 0; i < taskArray.length; i++) {
					var taskItem = taskArray[i];
					var taskNode = projectTree.getNodeByParam("nodeKey",String.format("node_task_{0}",taskItem),treeNode);
					if(prepTaskId < taskItem){
						prepTaskId = taskItem;
						if($("#hidProjectTask").val() == taskItem){
							projectTree.selectNode(taskNode);
							break;
						}else{
							projectTree.expandNode(taskNode,true,false,false,true);
							break;
						}
					}
				}
			}
			
		}
	} catch (e) {
	}
});
function fnEditLogSuccess(){
	window.parent.parent.DialogEditOneLogResult = "success";
	window.parent.parent.Messenger().post({
		message:"日志信息已更改！"
	   ,hideAfter:3
	});
	window.parent.parent.DialogEditOneLog.close();
};
function fnEditLogFialed(){
	window.parent.parent.DialogEditOneLogResult = "fialed";
	window.parent.parent.DialogEditOneLog.close();
};

function fnSubmitEditLog(){
	var blIsNoError = excute_form_validator();
	var selectedNodes = window.parent.$.fn.zTree.getZTreeObj("projectTree").getSelectedNodes();
	var taskid = selectedNodes.length > 0
	? selectedNodes[0].taskID === undefined 
		? ""
		: selectedNodes[0].taskID
	: "";
	if(blIsNoError){
		$("#txtStartTime > input").val($("#hidSelectDate").val()+" "+$("#txtStartTime > input").val());
		$("#txtEndTime > input").val($("#hidSelectDate").val()+" "+$("#txtEndTime > input").val());
		$("#hidProjectTask").val(taskid);
	}
	return blIsNoError;
};
