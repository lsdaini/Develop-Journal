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
	$("#txtEndDate").datetimepicker(timepickerOptions).on('changeDate',function(ev){
		$("#txtEndDate").datetimepicker('update', ev.date.format("yyyy-MM-dd"));
		if($("#startDateValue").val()!=null&&$("#startDateValue").val()!=""&&ev.date.format("yyyy-MM-dd")<$("#startDateValue").val()){
			fnEndDateError();
		}
	});
});

$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEditProject);
});

function fnSubmitEditProject(){
	return excute_form_validator();
};

function fnEditProjectSuccess(){
	window.parent.DialogEditProjectResult = "success";
	window.parent.Messenger().post({
		message:"该项目已修改！"
	   ,hideAfter:3
	});
	window.parent.DialogEditProject.close();
};

function fnProjectNameIsExist(){
	$("#projectNameErrorInfo").text("该项目已存在！");
};
function fnEndDateError(){
	$("#endDateErrorInfo").text("结束日期不能小于开始日期！");
	$("#endDateValue").val("");
};