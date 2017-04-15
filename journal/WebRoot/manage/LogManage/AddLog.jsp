<%@page import="lms.code.beans.enums.LMS_LogFreeType"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-AddLog.js"></script>
</head>
<body>
	<form id="addNewLogForm" action="<%=SiteConfig.DoMain%>actions/log/logActions.action?method=addLog"  class="form-horizontal" method="post">
		<input type="hidden" id="hidProjectTask" name="projectTask" />
		<fieldset style="margin-left:-100px">
			<div class="control-group"></div>
			<div class="control-group">
				<label class="control-label" >开始时间</label>
				<div class="controls">
					<div id="txtStartTime" class="input-append date form_datetime">
					    <input size="16" name="startTime" style="width:185px" type="text" value="0:00" readonly="readonly" />
					    <span class="add-on"><i class="icon-th"></i></span>
					</div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >结束时间</label>
				<div class="controls">
					<div id="txtEndTime" class="input-append date form_datetime">
					    <input size="16" name="endTime"  style="width:185px" type="text" value="0:05"  readonly="readonly" />
					    <span class="add-on"><i class="icon-th"></i></span>
					</div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" >完成比例</label>
				<div class="controls">
					<input name="percentage"   style="width:210px" type="text" value="0 %"  readonly="readonly" />
					<div id="slider" style="width:210px;margin-top:10px;margin-left:5px"></div>
					<p class="help-block"></p>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" style="margin-top:40px">工作内容</label>
				<div class="controls">
					<div class="textarea">
						<textarea name="content" style="margin: 0px; height: 104px; width: 270px;" form-val="empty,maxlength(1000)" val-empty-msg="工作内容不能为空!"></textarea>
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
	</form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript}
</body>
</html>
