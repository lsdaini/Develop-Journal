$(document).ready(function() {
	$('[operate="seeDetail"]').click(fnSeeContent);
	$("#btnSubmit").click(fnSubmitEditBossEvalu);
	$("#navPage a").each(function(){
		if($(this).attr("href").indexOf("void(0)") < 0){
			var pageHref = $(this).attr("href");
			pageHref+="&reportID="+$("#hidReportId").val();
			pageHref+="&staffID="+$("#hidStaffId").val();
			pageHref+="&startDate="+$("#hidStartDate").val();
			pageHref+="&endDate="+$("#hidEndDate").val();
			$(this).attr("href",pageHref);
		}
	});
	$("#back").click(fnback);
});
/**
 * 查看详细
 */
function fnSeeContent(){
	var contentUrl = SiteConfig.DoMain+"actions/log/logActions.action?method=getLogWorkContent&logId="+$(this).attr("staffid");
	window.parent.DialogWorkContentDetail.options.remote = contentUrl;
	window.parent.DialogWorkContentDetail.show();
}
/**
 * 提交按钮
 */
function fnSubmitEditBossEvalu(){
	$('#btnSubmit').prop('disabled',true).attr('class','btn btn-primary disabled').button('loading');
	var postUrl = SiteConfig.DoMain +"actions/ajax/ajaxActions.action";
	var param = {
		"method":"doBossEvalu"
	   ,"reportid":$("#tblReport").attr("reportid")
	   ,"bossevalu":$("#bossEvalu").val()
	};
	CreatePost(param,postUrl,function(result){
		if(result.data == 1){
			$('#btnSubmit').removeProp('disabled').attr('class','btn btn-primary').button('reset');
			  window.parent.GetStaffReminds();
	    	  window.parent.Messenger().post({
	    			message:"评价成功！"
	    		   ,hideAfter:3
	    		});
	    		window.parent.DialogConfirmLeaderEvalu.options.action = function(){
	    			var selectedStaffID = $("#hidSelectedStaffID").val();
	    			if(selectedStaffID == ""||selectedStaffID == null){
	    				window.location.href = SiteConfig.DoMain +"actions/report/reportActions.action?method=getReportList";
	    			}else{
	    				window.location.href =  SiteConfig.DoMain +"actions/report/reportActions.action?method=getReportList&reporterID="+selectedStaffID;
	    			}
	    		};
	    		window.parent.DialogConfirmLeaderEvalu.show();
		}
	},true);
}
/**
 * 返回按钮
 */
function fnback(){
	var selectedStaffID = $("#hidSelectedStaffID").val();
	if(selectedStaffID == ""||selectedStaffID == null){
	window.location.href = SiteConfig.DoMain +"actions/report/reportActions.action?method=getReportList";
	}else{
		window.location.href =  SiteConfig.DoMain +"actions/report/reportActions.action?method=getReportList&reporterID="+selectedStaffID;
	}
}

