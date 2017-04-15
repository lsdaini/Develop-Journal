$(document).ready(function(){
	$("#btnNewProject").click(fnNewProject);
	$('button[operate="editProject"]').click(fnEditOneProject);
	$('button[operate="deleteProject"]').click(fnDeleteOneProject);
	$('button[operate="sectionList"]').click(fnSectionList);
});

function fnNewProject(){
	window.parent.DialogAddProject.options.onClose = function(){
		if (window.parent.DialogAddProjectResult !== undefined && window.parent.DialogAddProjectResult === "success") {
			window.location = SiteConfig.DoMain+"actions/project/projectActions.action?method=getProjectInfoList";
		}
	};
	window.parent.DialogAddProject.show();
};

function fnEditOneProject(){
	var projectid = $(this).attr("projectid");
	var content = window.parent.DialogEditProject.options.content;
	var contentUrl = SiteConfig.DoMain +"actions/project/projectActions.action?method=initialEditProject&projectID="+projectid;
	$(content).attr("src",contentUrl);
	window.parent.DialogEditProject.options.onClose = function(){
		if (window.parent.DialogEditProjectResult !== undefined && window.parent.DialogEditProjectResult === "success") {
			window.location = SiteConfig.DoMain+"actions/project/projectActions.action?method=getProjectInfoList";
		}
	};
	window.parent.DialogEditProject.show();
};

function fnDeleteOneProject(){
	$("#hidDeleteProjectID").val($(this).attr("projectid"));
	window.parent.DialogConfirm.options.action = function(){
		$("#projectActions").trigger("submit");
		window.parent.Messenger().post({
			message:"该项目已删除！"
		   ,hideAfter:3
		});
	};
	window.parent.DialogConfirm.show();
};

function fnSectionList(){
	var projectid = $(this).attr("projectid");
	var iframe = window.parent.document.getElementById("frmContent");
	iframe.src=SiteConfig.DoMain +"actions/section/sectionActions.action?method=initialSectionList&projectID="+projectid;
}