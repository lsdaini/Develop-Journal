$(document).ready(function(){
	$('button[operate="view"]').click(fnViewLeaderEvalu);
	$('button[operate="edit"]').click(fnEditOneWorkReport);
	$('button[operate="delete"]').click(fnDeleteOneWorkReport);
});

function fnViewLeaderEvalu(){
	var dialogUrl = SiteConfig.DoMain+'actions/report/reportActions.action?method=getReportDetail&operate=view&reportID='+$(this).attr("reportid");
	window.parent.DialogViewLeaderEvalu.options.remote = dialogUrl;
	window.parent.DialogViewLeaderEvalu.show();
};

function fnEditOneWorkReport(){
	var dialogUrl = SiteConfig.DoMain+"actions/report/reportActions.action?method=getReportDetail&operate=edit&reportID="+$(this).attr("reportid");
	$(window.parent.DialogEditOneReport.options.content).attr("src",dialogUrl);
	window.parent.DialogEditOneReport.options.onClose = function(){
		if(window.parent.DialogEditOneReportResult == "success"){
			window.parent.DialogEditOneReportResult = undefined;
			window.location.href = SiteConfig.DoMain+"actions/reportActions.action?method=getMyWorkReports";
		}
	};
	window.parent.DialogEditOneReport.show();
};

function fnDeleteOneWorkReport(){
	var reportid = $(this).attr("reportid");
	$("#hidReportId").val(reportid);
	window.parent.DialogConfirm.options.action = function(){
		$("#reportActions").trigger("submit");
	};
	window.parent.DialogConfirm.show();
};

function fnDeleteSuccess(){
	window.parent.Messenger().post({
		message:"汇报已删除！"
	   ,hideAfter:3
	});
};

function fnDeleteFialed(){
	window.parent.Messenger().post({
		message:"汇报删除失败！"
	   ,hideAfter:3
	});
};