<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-sco/css/scojs.css" ></link>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-sco/js/sco.tooltip.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-ReportWorkLogs.js"></script>
</head>
<body>
<form id="reportWorkLogsForm" action="<%=SiteConfig.DoMain%>actions/report/reportActions.action?method=reportWorkLogs" class="form-horizontal" method="post">
	<input type="hidden" name="startDate" id="hidStartDate" />
	<input type="hidden" name="endDate" id="hidEndDate" />
	<fieldset style="margin-left:-120px;">
		<div class="control-group"></div>
		<div class="control-group">
			<div class="controls">
				<span id="spnTitle" name="name"></span>
				<p class="help-block"></p>
			</div>
		</div>
			<div class="control-group">
			<div class="controls">
				<s:select id="selLeaders" name="leaders" list="leaderList" theme="simple" listKey="staffID" listValue="name" multiple="true" cssClass="input-xlarge"  data-trigger="tooltip" data-content="请选择需要提交的领导！"  style="width:445px;height:100px">
						
				</s:select>
			</div>
		</div>
	
		<div class="control-group">
			<div class="controls">
				<div class="textarea">
					<textarea id="txtSummary" name="summary"  data-trigger="tooltip" data-content="请对您本周的工作进行总结！"  style="margin: 0px; height: 104px; width: 430px;" form-val="empty,maxlength(1000)"  val-empty-msg="工作汇总不能为空！"> </textarea>
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<div class="textarea">
					<textarea id="txtSelfEvalu" name="selfEvalu" data-trigger="tooltip" data-content="请对您本周的工作进行自我评价！" form-val="maxlength(1000)"  style="margin: 0px; height: 104px; width: 430px;"> </textarea>
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
