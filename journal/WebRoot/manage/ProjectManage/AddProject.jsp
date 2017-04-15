<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/css/datetimepicker.css" />
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-ProjectManage-AddProject.js"></script> 
</head>
<body>
	<s:form action="projectActions" namespace="/actions/project" cssClass="form-horizontal" theme="simple">
		<input type="hidden" name="method" value="addProject" />
		<fieldset style="margin-left:-20px">
		<div class="control-group">
		</div>
			<div class="control-group">
				<label class="control-label">项目名</label>
				<div class="controls">
					<input name="projectName"  style="width:210px" type="text" placeholder="请填写项目名称" form-val="empty" class="input-xlarge" maxlength="20" >
					<br/><span id="projectNameErrorInfo" class="input-error-info"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">负责人</label>
				<div class="controls">
				    <s:select name="managerId" list="staffInfoList" theme="simple" cssClass="input-xlarge" style="width:225px" listKey="staffID" listValue="name">
					</s:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">开始日期</label>
				<div class="controls">
					<div id="txtStartDate" class="input-append date">
					    <input id="startDateValue" name="startDate" size="12" style="width:185px" type="text" readonly>
					    <span class="add-on"><i class="icon-th"></i></span>
					    <br/><span id="startDateErrorInfo" class="input-error-info"></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">计划完成日期</label>
				<div class="controls">
					<div id="txtPlanEndDate" class="input-append date">
					    <input id="planEndDateValue" name="planEndDate" size="12" style="width:185px" type="text" readonly>
					    <span class="add-on"><i class="icon-th"></i></span>
					    <br/><span id="planEndDateErrorInfo" class="input-error-info"></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注</label>
				<div class="controls">
					<div class="textarea">
						<textarea name="remark" form-val="maxlength(1000)"  style="margin: 0px; height: 104px; width: 210px;resize:none;"></textarea>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="float:right;margin-right:195px">
					<button id="btnSubmit" class="btn btn-primary" type="submit" style="margin-right:10px">提交</button>
					<button class="btn btn-primary" type="reset">重置</button>
				</div>
			</div>
		</fieldset>
	</s:form>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
	${actionScript }
</body>
</html>
