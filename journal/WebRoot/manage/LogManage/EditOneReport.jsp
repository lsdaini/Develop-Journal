<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-sco/css/scojs.css" ></link>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-sco/js/sco.tooltip.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-EditOneReport.js"></script>
</head>
<body>
<s:form action="reportActions" namespace="/actions/report" theme="simple" cssClass="form-horizontal">
	<input type="hidden" name="method" value="editOneReport" />
	<input type="hidden" name="reportID" value="${reportInfo.reportID}" />
	<fieldset style="margin-left:-120px;">
		<div class="control-group"></div>
		<div class="control-group">
			<div class="controls">
				<div class="textarea">
					<textarea id="txtSummary" name="summary"  data-trigger="tooltip" data-content="请对您本周的工作进行总结！"  style="margin: 0px; height: 104px; width: 430px;" form-val="empty,maxlength(1000)"  val-empty-msg="工作汇总不能为空！">${reportInfo.summary }</textarea>
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<div class="textarea">
					<textarea id="txtSelfEvalu" name="selfEvalu" data-trigger="tooltip" data-content="请对您本周的工作进行自我评价！" form-val="maxlength(1000)"  style="margin: 0px; height: 104px; width: 430px;">${reportInfo.selfEvalu }</textarea>
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
