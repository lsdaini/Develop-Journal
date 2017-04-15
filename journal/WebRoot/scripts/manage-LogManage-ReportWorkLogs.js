$(document).ready(function(){
	var startDate = window.parent.DialogSubmitReport.options.startDate.format("yyyy年MM月dd日");
	var endDate = window.parent.DialogSubmitReport.options.endDate.format("yyyy年MM月dd日");
	$("#spnTitle").text(startDate+"  至    "+endDate);
	$("#hidStartDate").val(window.parent.DialogSubmitReport.options.startDate.format('yyyy-MM-dd'));
	$("#hidEndDate").val(window.parent.DialogSubmitReport.options.endDate.format('yyyy-MM-dd'));
	$("#selLeaders option").each(function(i){
		if(i == 0){
			$(this).attr("selected","selected");
		}
	});
	$("#btnSubmit").click(function(){
		var blIsError = excute_form_validator();
		return blIsError;
	});
});


function fnReportWorkLogsSuccess(){
	window.parent.Messenger().post({
		message:"您的工作汇报已提交，请耐心等待主管审阅！"
	   ,hideAfter:3
	});
	window.parent.DialogSubmitReport.close();
};

function fnReportWorkLogsDateError(){
	window.parent.Messenger().post({
		message:"对不起，您提交的日期段有误，汇报失败！"
	   ,type:'error'
	   ,hideAfter:3
	});
	window.parent.DialogSubmitReport.close();
};