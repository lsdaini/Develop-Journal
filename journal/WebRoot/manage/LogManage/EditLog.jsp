<%@page import="lms.code.beans.enums.LMS_LogFreeType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/css/datetimepicker.css" />
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-jqueryUI/css/custom-theme/jquery-ui-1.9.2.custom.css" type="text/css"></link>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-jqueryUI/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-EditLog.js"></script>
<style type="text/css">
	#freeType *{float:left;margin-left:3px;}
</style>
</head>
<body>
<s:form action="logActions"  namespace="/actions/log" theme="simple" cssClass="form-horizontal">
	<input type="hidden" id="hidProjectTask" name="projectTask" value="${logDetail.task.taskID}"/>
	<input type="hidden" id="hidSectionId" value="${logDetail.task.section.sectionID}"/>
	<input type="hidden" id="hidProjectId" value="${logDetail.task.section.project.projectID}"/>
	<input type="hidden" id="hidLogStr" value="${logString}"/>
	<input type="hidden" name="method" value="editOneLog" />
	<input type="hidden" name="logId" value="${logDetail.logID}" />
	<input type="hidden" id="hidSelectDate" name="selectDate" value="<s:property value="@dev.frame.convert.DateTimeConvert@format(logDetail.startTime,'yyyy-MM-dd')"/>" />
		<fieldset style="margin-left:-100px">
			<div class="control-group"></div>
			<div class="control-group">
				<label class="control-label" >开始时间</label>
				<div class="controls">
					<div id="txtStartTime" class="input-append date form_datetime">
					  	<input size="16" name="startTime" style="width:185px" type="text" value="<s:property value="@dev.frame.convert.DateTimeConvert@format(logDetail.startTime,'HH:mm')"/>" readonly="readonly" />
					    <span class="add-on"><i class="icon-th"></i></span>
					</div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >结束时间</label>
				<div class="controls">
					<div id="txtEndTime" class="input-append date form_datetime">
					    <input size="16" name="endTime"  style="width:185px" type="text" value="<s:property value="@dev.frame.convert.DateTimeConvert@format(logDetail.endTime,'HH:mm')"/>"   readonly="readonly" />
					    <span class="add-on"><i class="icon-th"></i></span>
					</div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >完成比例</label>
				<div class="controls">
					<input name="percentage" id="txtPercentage" style="width:210px" type="text" value="${logDetail.percentage }"  readonly="readonly" />
					<div id="slider" style="width:210px;margin-top:10px;margin-left:5px"></div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" style="margin-top:40px">工作内容</label>
				<div class="controls">
					<div class="textarea">
						<textarea name="content" style="margin: 0px; height: 104px; width: 270px;" form-val="empty,maxlength(1000)" val-empty-msg="工作内容不能为空!">${logDetail.content}</textarea> 
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls" style="float:left;margin-right:95px">
					<button id="btnSubmit" class="btn btn-primary" type="submit" style="margin-right:10px">提交</button>
					<button class="btn btn-primary" type="reset">重置</button>
				</div>
			</div>
		</fieldset>
</s:form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript}
</body>
</html>
