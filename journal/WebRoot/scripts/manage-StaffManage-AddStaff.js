$(document).ready(function(){
	$("#btnSubmit").click(fnAddNewStaffInfo);
});

function fnAddNewStaffInfo(){
	return excute_form_validator();
};
function fnAddNewStaffSuccess(){
	window.parent.DialogCreateStaffResult = "success";
	window.parent.Messenger().post({
		message:"新员工已添加！"
	   ,hideAfter:3
	});
	window.parent.DialogCreateStaff.close();
};

function fnStaffNameIsExist(){
	$("#staffNameInfo").text("该员工已存在！");
};
