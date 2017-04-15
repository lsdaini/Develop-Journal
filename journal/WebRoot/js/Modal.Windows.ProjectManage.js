$(document).ready(function(){
	InitializeAddProject();
	InitializeEditProject();
});

//新建项目
function InitializeAddProject(){
	if(window.DialogAddProject === undefined){
		var iframeElement = $("<iframe>").attr({
			"frameborder" : "0",
			"scrolling" : "no",
			"src" : SiteConfig.DoMain +"actions/project/projectActions.action?method=initialAddProject"
		}).css({
			"width" : "100%",
			"height" : "450px"
		});
		window.DialogAddProject = $.scojs_modal({
			width:"480px",
			target : '#modal_addProject',
			title : '新建项目',
			content:iframeElement
		});
	}
};
//编辑项目
function InitializeEditProject() {
	if(window.DialogEditProject === undefined){
		var iframeElement = $("<iframe>").attr({
			"frameborder" : "0",
			"scrolling" : "no",
			"src" : SiteConfig.DoMain +"actions/project/projectActions.action?method=initialEditProject"
		}).css({
			"width" : "100%",
			"height" : "330px"
		});
		window.DialogEditProject = $.scojs_modal({
			target : '#modal_editProject',
			title : '项目编辑',
			content:iframeElement
		});
	}
};