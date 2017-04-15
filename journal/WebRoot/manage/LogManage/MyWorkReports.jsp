<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-MyWorkReports.js"></script>
</head>
<body>
	<s:form action="reportActions" namespace="/actions/report" theme="simple">
		<input type="hidden" name="method" id="hidMethod" value="deleteOneReport"/>
		<input type="hidden" name="reportID" id="hidReportId" value="0"/>
		<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="<%=SiteConfig.DoMain %>actions/log/logActions.action?method=initializeSelectDays" class="nav_prep"><i class="icon-list-alt"></i>日期选择</a> <a href="#" >我的汇报</a>
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
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>开始日期</th>
								<th>结束日期</th>
								<th>天数</th>
								<th>提交时间</th>
								<th>提交领导</th>
								<th>审阅状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<p:pages pageIndex="${pageIndex}" pageCount="${pageCount}" styleClass="page" recordCount="${recordCount}" theme="text">
							<tbody>
								<s:iterator value="reportInfoList" status="items">
									<s:if test="%{#items.count!=0}">
										<tr>
											<td><s:date name="startDate" format="yyyy年MM月dd日" />
											</td>
											<td><s:date name="endDate" format="yyyy年MM月dd日" />
											</td>
											<td><s:property value="usedDays" />
											</td>
											<td><s:date name="reportTime" format="MM月dd日 HH时mm分" />
											</td>
											<td><s:property value="leader.name" />
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
												<s:if test="status == @lms.code.beans.enums.LMS_WorkReportStatus@Audited">
													<button operate="view" reportid="${reportID}" class="btn" type="button" title="查看领导评价"><em class="icon-list-alt"></em></button>
												</s:if>
												<s:if test="status == @lms.code.beans.enums.LMS_WorkReportStatus@UnAudit">
													<button operate="edit" reportid="${reportID}" class="btn" type="button" title="编辑汇报"><em class="icon-edit" ></em></button> 													
													<button operate="delete" reportid="${reportID}" class="btn" type="button" title="删除汇报"><em class="icon-remove" ></em></button>
												</s:if>
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
	${actionScript}
</body>
</html>
