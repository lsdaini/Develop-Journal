$(document).ready(function(){
	$("#selControlSize").change(function(){
		changeCalSize($(this).val());
		$.cookie("CalendarOption",$(this).val(),{expires:365});
	});
	$("#selControlDays").change(function(){$('#calOne').data('days', $('#selControlDays').val());});
	$("#btnMyReports").click(function(){
		window.location.href = SiteConfig.DoMain+"actions/reportActions.action?method=getMyWorkReports";
	});
	$('#calOne').jCal({
		day:			new Date(),
		days:			1,
		showMonths:		2,
		monthSelect:	true,
		dCheck:		    CalendarCheck,
		callback:	    CalendarCallBack
	});
	var tempCalendarOption = $.cookie("CalendarOption");
	tempCalendarOption = tempCalendarOption == null ? 40 : tempCalendarOption;
	$("#selControlSize").val(tempCalendarOption);
	changeCalSize(tempCalendarOption);
});
function changeCalSize (daySize) {
	var daySize = (parseInt(daySize) || 30), 
		monthSize = ( daySize + 2 ) * 7,
		titleSize = monthSize - 16,
		titleMsgSize = ( titleSize / 2 ) - 4;
	$('head:first').append(
		'<style>' +
			'.jCalMo .day,.jCalMo .invday,.jCalMo .pday,.jCalMo .aday,.jCalMo .selectedDay,.jCalMo .dow { width:' + daySize + 'px !important; height:' + daySize + 'px !important; }' +
			'.jCalMo .dow { height:auto !important }' +
			'.jCalMo, .jCalMo .jCal { width:' + monthSize + 'px !important; }' +
			'.jCalMo .month { width:' + titleSize + 'px !important; }' +
			'.jCalMo .month span { width:' + titleMsgSize  + 'px !important; }' +
		'</style>');
};

function CalendarCheck(day){
	var currentDate = new Date();
	var dateDiv = String.format('#c2d_{0}_{1}_{2}',(currentDate.getMonth()+1),currentDate.getDate(),currentDate.format('yyyy'));
	$(dateDiv,'#calOne').css({'border':'1px solid blue'});
	var reportData = jQuery.parseJSON($("#hidReportedData").val());
	for (var i = 0; i < reportData.length; i++) {
		var dataItem = reportData[i];
		var startDate = new Date(dataItem.key.split('-')[0],(dataItem.key.split('-')[1] - 1),dataItem.key.split('-')[2]);
		var endDate = new Date(dataItem.value.split('-')[0],(dataItem.value.split('-')[1] - 1),dataItem.value.split('-')[2]);
		while (true) {
			var startDateDiv = String.format('#c2d_{0}_{1}_{2}',(startDate.getMonth()+1),startDate.getDate(),startDate.format('yyyy'));
			$(startDateDiv,'#calOne').css({'border':'1px solid red'});
			startDate.setDate(startDate.getDate() + 1);
			if(startDate > endDate) break;
		}
	}
	
	if ( day.getTime() == (new Date('8/7/2008')).getTime() )
		return 'invday';
	else if (day.getDate() != 3)
		return 'day';
	else
		return 'invday';
};

function CalendarCallBack(day, days){
	$('#calOneDays').val( days );
	$(this._target).find('.dInfo').remove();
	var dCursor = new Date(day.getTime());
	if (days == 1) {
		$("#hidSelectDate").val(day.format("yyyy-MM-dd"));
		$("#getLogsByDate").trigger("submit");
	} else {
		var dialogUrl = SiteConfig.DoMain+"actions/log/logActions.action?method=initializeReportWorkLogs";
		var endDate = new Date(day);
		endDate.setDate((endDate.getDate() + days - 1));
		window.parent.DialogSubmitReport.options.onClose = function(){
			window.location.href = window.location.href;
		};
		window.parent.DialogSubmitReport.options.startDate = day;
		window.parent.DialogSubmitReport.options.endDate = endDate;
		$(window.parent.DialogSubmitReport.options.content).attr("src",dialogUrl);
		window.parent.DialogSubmitReport.show();
	}
	if ( typeof $(this._target).data('day') == 'object' &&
		 $(this._target).data('day').getTime() == day.getTime() &&
		 $(this._target).data('days') == days ) {
		$('#calOneResult').append('<div style="clear:both; font-size:7pt;">' + days + ' days starting ' +
			( day.getMonth() + 1 ) + '/' + day.getDate() + '/' + day.getFullYear() + ' RECLICKED</div>');
	} else {
		$('#calOneResult').append('<div style="clear:both; font-size:7pt;">' + days + ' days starting ' +
			( day.getMonth() + 1 ) + '/' + day.getDate() + '/' + day.getFullYear() + '</div>');
	}
	return true;
};