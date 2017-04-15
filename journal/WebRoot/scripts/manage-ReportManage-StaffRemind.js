$(document).ready(function(){
	$(".iknow").click(function(){
		var blIsLeader = $(this).parent().attr("isleader") == "true";
		var reportID = $(this).parent().attr("reportid");
		var thisBtn = $(this);
		var params = {
			"method" : "doUpdateRemindStatus"
		   ,"reportid" : reportID
		   ,"isleader" : blIsLeader
		};
		var postUrl = SiteConfig.DoMain+"actions/ajax/ajaxActions.action";
		CreatePost(params,postUrl,function(result){
			if(result.data == 1){
				$(thisBtn).parent().parent().remove();
			}
		},true);
	});
	
	$(".donow").click(function(){
		var blIsLeader = $(this).parent().attr("isleader") == "true";
		var reportID = $(this).parent().attr("reportid");
		window.DialogMyRemind.close();
		if(!blIsLeader){
			var dialogUrl = SiteConfig.DoMain+'actions/report/reportActions.action?method=getReportDetail&operate=view&reportID='+reportID;
			window.DialogViewLeaderEvalu.options.remote = dialogUrl;
			window.DialogViewLeaderEvalu.show();
			var params = {
				"method" : "doUpdateRemindStatus"
			   ,"reportid" : reportID
			   ,"isleader" : blIsLeader
			};
			var postUrl = SiteConfig.DoMain+"actions/ajax/ajaxActions.action";
			CreatePost(params,postUrl,function(result){},true);
		    window.parent.GetStaffReminds();
		}else{
			var dialogUrl = 
				SiteConfig.DoMain
				+'actions/report/reportActions.action?method=getOneReport&reportID='
				+reportID
				+'&staffID='+$(this).attr("staffid")
				+'&startDate='+$(this).attr("start-date")
				+'&endDate='+$(this).attr("end-date")
				+'&selectedStaffID='+$(this).attr("selectedStaffID");
			$("#frmContent").attr("src",dialogUrl);
		}
	});
});