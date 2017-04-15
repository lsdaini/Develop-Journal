$(document).ready(function() {
	$("#navPage a").each(function(){
		if($(this).attr("href").indexOf("void(0)") < 0){
			var pageHref = $(this).attr("href");
			pageHref+="&staffID="+$("#hidStaffId").val()+'&reporterID='+$("#hidReporterID").val();
			$(this).attr("href",pageHref);
		}
	});
	$('button[operate="view"]').click(function(){
		var dialogUrl = 
		SiteConfig.DoMain
		+'actions/report/reportActions.action?method=getOneReport&reportID='
		+$(this).attr("reportid")
		+'&staffID='+$(this).attr("staffid")
		+'&startDate='+$(this).attr("start-date")
		+'&endDate='+$(this).attr("end-date")
		+'&selectedStaffID='+$(this).attr("selectedStaffID");
		window.location.href = dialogUrl;
	});
	$("#staffList").change(function(){
		$("#reportActions").trigger("submit");
	});
});
