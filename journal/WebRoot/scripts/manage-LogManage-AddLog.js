var selectDate = getParam("selectDate");
var currentDate = new Date(selectDate);
var startDate = currentDate.format("yyyy-MM-dd")+" 0:00";
var endDate = currentDate.format("yyyy-MM-dd")+" 23:59";
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
$(document).ready(function(){
	$("#txtStartTime").datetimepicker(timepickerOptions).on('changeDate', function(ev){
		var endTime = new Date(ev.date);
		endTime.setHours(endTime.getHours() - 8);
		var start = parseInt(endTime.format("hhmm"));
		var end = parseInt($("#txtEndTime > input").val().replace(":",""));
		if(start > end){
			endTime.setMinutes(endTime.getMinutes() + 5);
			$("#txtEndTime > input").val(endTime.format("hh:mm"));
		}
		$("#txtEndTime").datetimepicker('setStartDate', endTime);
	});
	$("#txtEndTime").datetimepicker(timepickerOptions).on('changeDate',function(ev){
		var startTime = new Date(ev.date);
		startTime.setHours(startTime.getHours() - 8);
		var start = parseInt($("#txtStartTime > input").val().replace(":",""));
		var end = parseInt(startTime.format("hhmm"));
		if(start > end){
			startTime.setMinutes(startTime.getMinutes() - 5);
			$("#txtStartTime > input").val(startTime.format("hh:mm"));
		}
	});
  $("#slider" ).slider({slide: function( event, ui ) {
	  $(this).prev("input").val(ui.value+" %");
  }});
  $("#btnSubmit").click(fnSubmitAddLog);
});

function fnAddLogSuccess(){
	window.parent.parent.DialogAddLogResult = "success";
	window.parent.parent.DialogAddLog.close();
};
function fnAddLogFialed(){
	window.parent.parent.DialogAddLogResult = "fialed";
	window.parent.parent.DialogAddLog.close();
};
function fnSubmitAddLog(){
	var blIsNoError = excute_form_validator();
	var selectedNodes = window.parent.$.fn.zTree.getZTreeObj("projectTree").getSelectedNodes();
	var taskid = selectedNodes.length > 0
	? selectedNodes[0].taskID === undefined 
		? ""
		: selectedNodes[0].taskID
	: "";
	if(blIsNoError){
		$("#txtStartTime > input").val(selectDate+" "+$("#txtStartTime > input").val());
		$("#txtEndTime > input").val(selectDate+" "+$("#txtEndTime > input").val());
		$("#hidProjectTask").val(taskid);
	}
	return blIsNoError;
};
