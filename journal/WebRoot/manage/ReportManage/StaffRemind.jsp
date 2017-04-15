<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/templets/bootstrap-head.jsp"%>
<style type="text/css">
	table a{float:right;}
	.iknow{margin-left:20px}
</style>
<script src="<%=SiteConfig.DoMain%>scripts/manage-ReportManage-StaffRemind.js"></script>
</head>
<body>
	<s:form theme="simple">
		<table class="table table-bordered table-hover">
			<tbody>
				<s:iterator value="needAuditReports" status="source">
					<s:if test="%{#source.count!=0}">
						<tr>
							<td isleader="true" reportid="<s:property value="reportID" />">
								<s:property value="reporter.name" />：<s:date name="reportTime" format="yyyy年MM月dd日 HH时mm分ss秒" />，向您提交了工作汇报，请及时审阅。
								<a href="#" class="iknow">我知道了</a>
								<a 
									href="#"
									staffid="${reporter.staffID}"
									start-date="<s:date name="startDate" format="yyyy-MM-dd" />"
									end-date="<s:date name="endDate" format="yyyy-MM-dd" />"
									selectedStaffID="${reporterID}"
									class="donow">立即审阅</a>
							</td>
						</tr>
					</s:if>
				</s:iterator>
				<s:iterator value="auditedReports" status="source">
					<s:if test="%{#source.count!=0}">
						<tr>
							<td isleader="false" reportid="<s:property value="reportID" />">
								<s:property value="leader.name" />：审阅了您的工作汇报，请及时查收。<br/>
								<a href="#" class="iknow">我知道了</a><a href="#" class="donow">立即查看</a>
							</td>
						</tr>
					</s:if>
				</s:iterator>
			</tbody>
		</table>
	</s:form>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
