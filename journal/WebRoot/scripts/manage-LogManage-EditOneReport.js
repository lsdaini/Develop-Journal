$(document).ready(function(){
	$("#btnSubmit").click(fnSubmitEditReport);
});

function fnSubmitEditReport(){
	var blIsError = excute_form_validator();
	return blIsError;
};

function fnEditedOneReport(){
	window.parent.DialogEditOneReportResult = "success";
	window.parent.Messenger().post({
		message:"汇报编辑成功！"
	   ,hideAfter:3
	});
	window.parent.DialogEditOneReport.close();
};