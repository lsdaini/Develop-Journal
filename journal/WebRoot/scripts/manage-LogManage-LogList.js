$(document).ready(function(){
	$("#btnNewLog").click(fnCreateNewLog);
	$("#navPage a").each(function(){
		if($(this).attr("href").indexOf("void(0)") < 0){
			var pageHref = $(this).attr("href");
			pageHref+="&selectDate="+$("#hidSelectDate").val();
			$(this).attr("href",pageHref);
		}
	});
	$('[operate="workContentDetail"]').click(fnWorkContentDetail);
	$('[operate="editLog"]').click(fnEditLog);
	$('[operate="deleteLog"]').click(fnDeleteLog);
});

function fnWorkContentDetail(){
	var logid = $(this).attr("logid");
	var dialogUrl = SiteConfig.DoMain+"actions/log/logActions.action?method=getLogWorkContent&logId="+logid;
	window.parent.DialogWorkContentDetail.options.remote = dialogUrl;
	window.parent.DialogWorkContentDetail.show();
};

function fnEditLog(){
	window.parent.DialogEditOneLog.options.onClose = function(){
		if(window.parent.DialogEditOneLogResult == "success"){
			window.parent.DialogEditOneLogResult = undefined;
			window.location = SiteConfig.DoMain+"actions/log/logActions.action?method=getLogsByDate&selectDate="+$("#hidSelectDate").val();
		}
	};
	var logid = $(this).attr("logid");
	var dialogUrl = SiteConfig.DoMain +"manage/LogManage/EditLogMain.jsp?logid="+logid;
	$(window.parent.DialogEditOneLog.options.content).attr("src",dialogUrl);
	window.parent.DialogEditOneLog.show();
};

function fnDeleteLog(){
	var logid = $(this).attr("logid");
	$("#hidDeleteLogId").val(logid);
	window.parent.DialogConfirm.options.action = function(){
		$("#logActions").trigger("submit");
	};
	window.parent.DialogConfirm.show();
};

function fnCreateNewLog(){
	window.parent.DialogAddLog.options.onClose = function(){
		if(window.parent.DialogAddLogResult == "success"){
			window.parent.DialogAddLogResult = undefined;
			window.location = SiteConfig.DoMain+"actions/log/logActions.action?method=getLogsByDate&selectDate="+$("#hidSelectDate").val();
			window.parent.Messenger().post({
				message:"新日志已添加！"
			   ,hideAfter:3
			});
		}
	};
    var iframeUrl = SiteConfig.DoMain +"manage/LogManage/AddLogMain.jsp?selectDate="+$("#hidSelectDate").val();
    $(window.parent.DialogAddLog.options.content).attr("src",iframeUrl);
	window.parent.DialogAddLog.show();
};