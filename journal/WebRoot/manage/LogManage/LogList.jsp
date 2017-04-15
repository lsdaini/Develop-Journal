<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>scripts/manage-LogManage-LogList.js"></script>
</head>
<body>
	<s:form action="logActions" namespace="/actions/log" theme="simple">
		<input type="hidden" name="method" value="deleteOneLog" />
		<input type="hidden" name="logId" value="0" id="hidDeleteLogId"/>
		<input type="hidden" id="hidSelectDate" name="selectDate" value="${selectDate}" />
		<div id="header_nav_container" class="container-fluid">
		    <div class="row-fluid">
			    <div id="header_nav" class="span12">
			    	 <a href="<%=SiteConfig.DoMain %>actions/log/logActions.action?method=initializeSelectDays" class="nav_prep"><i class="icon-list-alt"></i>日期选择</a> <a href="#">日志列表</a>
		    </div>
   		</div>
	</div>
	<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<div class="page-header">
				<h3>
					<s:property value="@lms.common.ToolUtil@doGetLogListTitle(selectDate)"/>  
				</h3>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12" style="height:50px">
			<div class="btn-group">
				 <button id="btnNewLog" class="btn" type="button"><em class="icon-plus"></em>&nbsp;&nbsp;新建日志</button>
			</div>
		</div>
	</div>
			<div class="row-fluid">
				<div class="span12">
					<table  class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>工作内容</th>
								<th style="width:80px">开始时间</th>
								<th style="width:80px">结束时间</th>
								<th style="width:120px">耗时</th>
								<th style="width:80px">完成比例</th>
								<th style="width:200px">操作</th>
							</tr>
						</thead>
						<p:pages pageIndex="${pageIndex }" pageCount="${pageCount}" styleClass="page" recordCount="${recordCount}" theme="text">
							<tbody>
								<s:iterator value="logList" status="status">
									<s:if test="%{#status.count!=0}">
										<tr>
											<td class="warptd">
												<a text-warp="class,warptd" href="#" operate="workContentDetail" logid="${logID}">
													<s:property value="content" />
												</a>
											</td>
											<td><s:date name="startTime" format="HH时mm分" />
											</td>
											<td><s:date name="endTime" format="HH时mm分" />
											</td>
											<td><s:property value="usedTime" />
											</td>
											<td><s:property value="percentage" />
											</td>
											<td>
												<button operate="editLog" class="btn" logid="${logID}" type="button"><em class="icon-edit" title="编辑日志"></em></button>
												<button operate="deleteLog" class="btn" logid="${logID}" type="button"><em class="icon-remove" title="删除日志"></em></button>
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
