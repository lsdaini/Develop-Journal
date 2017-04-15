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
	$("#btnSubmit").click(fnAddNewProjectInfo);
});

function fnAddNewProjectInfo(){
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
function fnAddNewProjectSuccess(){
	window.parent.DialogAddProjectResult = "success";
	window.parent.Messenger().post({
		message:"新项目已添加！"
	   ,hideAfter:3
	});
	window.parent.DialogAddProject.close();
};
function fnProjectNameIsExist(){
	$("#projectNameErrorInfo").text("该项目名称已存在！");
};
function fnPlanEndDateError(){
	$("#planEndDateErrorInfo").text("计划完成日期不能小于开始日期！");
    $("#planEndDateValue").val("");
};
function fnStartDateError(){
	$("#startDateErrorInfo").text("开始日期不能大于计划完成日期！");
	$("#startDateValue").val("");
};