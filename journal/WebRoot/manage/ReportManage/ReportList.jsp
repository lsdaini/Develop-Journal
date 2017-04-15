<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/templets/bootstrap-head.jsp"%>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-ReportManage-ReportList.js"></script>
<title></title>
</head>
<body >
	<s:form action="reportActions" namespace="/actions/report" theme="simple">
		<input type="hidden" name="method" value="getReportList" />
		<input type="hidden" id="hidReporterID" value="${reporterID}" />
			<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="#"><i class="icon-envelope"></i>汇报管理</a>
		    </div>
	   	</div>
	</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
				</div>
			</div>
			<div class="row-fluid">
				<div class="span12">
					<div class="control-group">
						<div class="controls">
							<s:select 
								id="staffList" 
								name="reporterID"
								list="reporters" 
								theme="simple" 
								cssClass="input-xlarge" 
								style="width:285px" 
								listKey="staffID"
								listValue="name" 
								value="%{reporterID}"
								headerKey="" 
								headerValue="全部职工">
							</s:select>
						</div>
					</div>
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>汇报人</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>提交时间</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<p:pages pageIndex="${pageIndex }" pageCount="${pageCount}" styleClass="page" recordCount="${recordCount}" theme="text">
							<tbody>
								<s:iterator value="reportInfoList" status="items" id="course">
									<s:if test="%{#items.count!=0}">
										<tr>
											<td><s:property value="reporter.name" />
											</td>
											<td><s:date name="startDate" format="yyyy-MM-dd" />
											</td>
											<td><s:date name="endDate" format="yyyy-MM-dd" />
											</td>
											<td><s:date name="reportTime" format="yyyy-MM-dd HH:mm" />
											</td>
											<td>
												<s:if test="%{status == @lms.code.beans.enums.LMS_WorkReportStatus@UnAudit}">
													<span style="color:red"><s:property value="status.Descript" /></span>
												</s:if>
												<s:else>
													<span style="color:green"><s:property value="status.Descript" /></span>
												</s:else>
											</td>
											<td>
												<button 
													operate="view" 
													reportid="${reportID}"
													staffid="${reporter.staffID}"
													start-date="<s:date name="startDate" format="yyyy-MM-dd" />"
													end-date="<s:date name="endDate" format="yyyy-MM-dd" />"
													selectedStaffID="${reporterID}"
													class="btn" type="button" title="查看汇报详细">
													<em class="icon-list-alt"></em>
												</button>
											</td>
										</tr>
									</s:if>
								</s:iterator>
							</tbody>
					</table>
					</p:pages>
				</div>
			</div>
		</div>
	</s:form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
<script>
function setSelectedItem(){
	$("#reportListSelect option[value="+$("#hidStaffBackId").val()+"]").attr("selected","selected");
}
</script>
