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
	fnSectionEndDate();
	$("#btnSubmit").click(fnEditSection);
});


function fnPlanEndDateError(){
	$("#planEndDateErrorInfo").text("计划完成日期不能小于开始日期！");
    $("#planEndDateValue").val("");
};
function fnStartDateError(){
	$("#startDateErrorInfo").text("开始日期不能大于计划完成日期！");
	$("#startDateValue").val("");
};
function fnEndDateError(){
	$("#endDateErrorInfo").text("结束日期不能小于开始日期！");
	$("#endDateValue").val("");
};

function fnSectionEndDate(){
	if($("#sectionEndDateId").val()==null||$("#sectionEndDateId").val()==""){
		$("#txtEndDate").datetimepicker(timepickerOptions).on('changeDate',function(ev){
			$("#txtEndDate").datetimepicker('update', ev.date.format("yyyy-MM-dd"));
			if($("#startDateValue").val()!=null&&$("#startDateValue").val()!=""&&ev.date.format("yyyy-MM-dd")<$("#startDateValue").val()){
				fnEndDateError();
			}
		});
	}
}

function fnEditSection(){
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

function fnEditSectionSuccess(sectionEdit){
	window.parent.parent.Messenger().post({
		message:"该阶段已修改！"
	   ,hideAfter:3
	});
	var sectionTree = window.parent.$.fn.zTree.getZTreeObj("sectionTree");
	(sectionTree.getSelectedNodes())[0].name=sectionEdit.name;
	sectionTree.updateNode((sectionTree.getSelectedNodes())[0]);
	var iframe = window.parent.document.getElementById("frmContent");
	iframe.src=SiteConfig.DoMain+"actions/section/sectionActions.action?method=initialViewSection&sectionID="+(sectionTree.getSelectedNodes())[0].sectionID;
};

function fnSectionNameIsExist(){
	$("#sectionNameErrorInfo").text("该阶段名称已存在！");
};

