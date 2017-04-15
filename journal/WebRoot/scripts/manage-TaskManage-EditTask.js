var timepickerOptions = {
	pickerPosition : "bottom-left",
	autoclose : true,
	todayBtn:true,
	todayHighlight:true,
	format : 'yyyy-mm-dd',
	language : 'zh-CN',
	startView : 2,
	minView : 2,
	maxView : 4
};

$(document).ready(function(){
	$("#txtStartDate").datetimepicker(timepickerOptions).on('changeDate', function(ev){
		$("#txtStartDate").datetimepicker('update', ev.date.format("yyyy-MM-dd"));
		if($("#planEndDateValue").val()!=null&&$("#planEndDateValue").val()!=""&&ev.date.format("yyyy-MM-dd")>$("#planEndDateValue").val()){
			fnStartDateError();
		}
	});
	$("#txtPlanEndDate").datetimepicker(timepickerOptions).on('changeDate',function(ev){
		$("#txtPlanEndDate").datetimepicker('update', ev.date.format("yyyy-MM-dd"));
		if($("#startDateValue").val()!=null&&$("#startDateValue").val()!=""&&ev.date.format("yyyy-MM-dd")<$("#startDateValue").val()){
			fnPlanEndDateError();
		}
	});
	fnTaskEndDate();
	$("#btnSubmit").click(fnEditSection);
});


function fnPlanEndDateError(){
	$("#planEndDateErrorInfo").text("计划完成日期不能小于开始日期！");
    $("#planEndDateValue").val("");
};
function fnStartDateError(){
	$("#startDateErrorInfo").text("开始日期不能大于计划完成日期！");
	$("#startDateValue").val("");
};
function fnEndDateError(){
	$("#endDateErrorInfo").text("结束日期不能小于开始日期！");
	$("#endDateValue").val("");
};

function fnTaskEndDate(){
	if($("#taskEndDateId").val()==null||$("#taskEndDateId").val()==""){
		$("#txtEndDate").datetimepicker(timepickerOptions).on('changeDate',function(ev){
			$("#txtEndDate").datetimepicker('update', ev.date.format("yyyy-MM-dd"));
			if($("#startDateValue").val()!=null&&$("#startDateValue").val()!=""&&ev.date.format("yyyy-MM-dd")<$("#startDateValue").val()){
				fnEndDateError();
			}
		});
	}
}

function fnEditSection(){
	if($("#startDateValue").val()!=null&&$("#startDateValue").val()!=""){
		if($("#planEndDateValue").val()!=null&&$("#planEndDateValue").val()!=""){
			return excute_form_validator();
		}else{
			$("#planEndDateErrorInfo").text("计划完成日期不能为空！");
			return false;
		}
	}else{
		$("#startDateErrorInfo").text("开始日期不能为空！");
		return false;
	}
};

function fnEditTaskSuccess(taskEdit){
	window.parent.parent.Messenger().post({
		message:"该任务已修改！"
	   ,hideAfter:3
	});
	var sectionTree = window.parent.$.fn.zTree.getZTreeObj("sectionTree");
	(sectionTree.getSelectedNodes())[0].name=taskEdit.name;
	sectionTree.updateNode((sectionTree.getSelectedNodes())[0]);
	var iframe = window.parent.document.getElementById("frmContent");
	iframe.src=SiteConfig.DoMain+"actions/task/taskActions.action?method=initialViewTask&taskID="+(sectionTree.getSelectedNodes())[0].taskID;
};

function fnTaskNameIsExist(){
	$("#taskNameErrorInfo").text("该任务名称已存在！");
};

