<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<%@ include file="/templets/bootstrap-head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-ReportManage-GetReportInfo.js"></script>
<title></title>
</head>
<body>
<s:form action="reportActions" namespace="/actions/report" theme="simple">
<input type="hidden" id="hidReportId" value="${reportID}"/>
<input type="hidden" id="hidStaffId" value="${staffID}"/>
<input type="hidden" id="hidSelectedStaffID" value="${selectedStaffID }"/>
<input type="hidden" id="hidStartDate" value="<s:date name="startDate" format="yyyy-MM-dd" />"/>
<input type="hidden" id="hidEndDate" value="<s:date name="endDate" format="yyyy-MM-dd" />"/>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div>
				<table id="tblReport" reportid="${reportInfo.reportID}">
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">汇报人&nbsp;&nbsp;&nbsp;</span>
						</td>
						<td>
							${reportInfo.reporter.name}
						</td>
					</tr>
					<tr height="10px"></tr>
					<tr>
						<td>
							<span style="font-size:18;font-weight:bold">时间范围&nbsp;&nbsp;</span>
						</td>
						<td>
							<s:property value="@lms.common.ToolUtil@doGetReportListTitleDate(reportInfo.startDate)"/>  
								&nbsp;&nbsp;至&nbsp;&nbsp;  
							<s:property value="@lms.common.ToolUtil@doGetReportListTitleDate(reportInfo.endDate)"/>  
						</td>
					</tr>
				</table>
			</div>
				<table  class="table table-bordered table-hover" style="TABLE-LAYOUT:fixed" id="mytable">
					<thead>
						<tr>
							<th>工作内容</th>
							<th style="width:120px">开始时间</th>
							<th style="width:120px">结束时间</th>
							<th style="width:120px">耗时</th>
							<th style="width:80px">完成比例</th>
						</tr>
					</thead>
					<p:pages pageIndex="${pageIndex }" pageCount="${pageCount}" styleClass="page" recordCount="${recordCount}" theme="text">
						<tbody>
							<s:iterator value="staffLogs" status="status">
								<s:if test="%{#status.count!=0}">
									<td class="warptd">
										<a id="detailbtn" operate="seeDetail" staffid="${logID}"  text-warp="class,warptd" href="#">
											<s:property value="content" />
										</a>
									</td>
									<td>
										<s:property value="@lms.common.ToolUtil@doGetReportListDate(startTime)"/>
										<s:date name="startTime" format="HH 时 mm 分" />
									</td>
									<td>
										<s:property value="@lms.common.ToolUtil@doGetReportListDate(endTime)"/>
										<s:date name="endTime" format="HH 时 mm 分" />
									</td>
									<td><s:property value="usedTime" /></td>
									<td><s:property value="percentage" /></td>
									</tr>
								</s:if>
							</s:iterator>
						</tbody>
				</table>
				</p:pages>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">工作汇总</th>
						</tr>
					</thead>
					<tr>
						<td id="summary">
							<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(reportInfo.summary)" escape="false" />  
						</td>
					</tr>
				</table>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">自我评价</th>
						</tr>
					</thead>
					<tr>
						<td id="selfEvalu">
							<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(reportInfo.selfEvalu)" escape="false" />  
						</td>
					</tr>
				</table>
				<table class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="100%">主管评价</th>
						</tr>
					</thead>
					<tr >
						<td>
						<textarea id="bossEvalu" form-val="maxlength(1000)"  type="text" style="width:98.7%;height:150px" >${reportInfo.bossEvalu}</textarea>
						</td>
					</tr>
				</table>
		</div>	
	</div>
	<div class="controls" style="float:right;margin-right:30px">
		<button type="button" id="btnSubmit" class="btn btn-primary" data-loading-text="正在提交..." style="position: relative;">提交</button>
		<button type="button" id="back" class="btn btn-primary"  style="margin-right:550px">返回</button>
	</div>
	</div>		
</s:form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript }
</body>
</html>
