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
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-ProjectManage-EditProject.js"></script> 
</head>
<body>
	<s:form action="projectActions" namespace="/actions/project" cssClass="form-horizontal" theme="simple">
		<input type="hidden" name="method" value="editProject"/>
		<input type="hidden" name="projectID" value="${projectInfo.projectID}" />
		<input type="hidden" id="startDateValue" value="<s:date name="projectInfo.startDate" format="yyyy-MM-dd"/>">
		<fieldset>
		<div class="control-group">
		</div>
			<div class="control-group">
				<label class="control-label">项目名</label>
				<div class="controls">
					<input id="projectNameInput" name="projectName" value="${projectInfo.name}" style="width:255px" type="text" form-val="empty" class="input-xlarge" maxlength="20"/>
				    <br/><span id="projectNameErrorInfo" class="input-error-info"></span>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">完成日期</label>
				<div class="controls">
					<div id="txtEndDate" class="input-append date">
					    <input id="endDateValue" name="endDate" value="<s:date name="projectInfo.endDate" format="yyyy-MM-dd"/>"  size="12" style="width:230px" type="text" onfocus="this.blur();" readonly/>
					    <span class="add-on"><i class="icon-th"></i></span>
					    <br/><span id="endDateErrorInfo" class="input-error-info"></span>
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">备注</label>
				<div class="controls">
					<div class="textarea">
						<textarea name="remark" style="margin: 0px; height: 104px; width: 255px;resize:none;">${projectInfo.remark}</textarea>
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="float:right;margin-right:110px">
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
