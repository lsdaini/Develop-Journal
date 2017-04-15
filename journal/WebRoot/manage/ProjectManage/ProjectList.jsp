<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>ztree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=SiteConfig.DoMain %>ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-ProjectManage-ProjectList.js"> </script>
<title></title>
</head>
<body>
<s:form action="projectActions" namespace="/actions/project" theme="simple">
    <input type="hidden" name="method" value="deleteProject" />
	<input type="hidden" name="projectID" id="hidDeleteProjectID" value="0" />
	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
	    <div id="header_nav" class="span12">
	    	 <a href="#"><i class="icon-wrench"></i>项目管理</a>
	    </div>
    	</div>
	</div>
    <div class="container-fluid">
    <div class="row-fluid">
    	<div class="span12"></div>
    </div>
	<div class="row-fluid">
		<div class="span12" style="height:50px">
			<div class="btn-group">
				 <button id="btnNewProject" class="btn" type="button"><em class="icon-plus"></em>&nbsp;&nbsp;新建项目</button>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span12">
				<table  class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>项目名</th>
							<th>负责人</th>
							<th>开始时间</th>
							<th>结项时间</th>
							<th>计划结项时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<p:pages pageIndex="${pageIndex }" pageCount="${pageCount}" styleClass="page" recordCount="${recordCount}" theme="text">
						<tbody>
						<s:iterator value="projectList" status="status">
							<s:if test="%{#status.count!=0}">
								<tr>
									<td><s:property value="name" /></td>
									<td><s:property value="manager.name" /></td>
									<td><s:date name="startDate" format="yyyy-MM-dd"/></td>
									<td><s:date name="endDate" format="yyyy-MM-dd"/></td>
									<td><s:date name="planEndDate" format="yyyy-MM-dd"/></td>
									<td>
										<button operate="editProject" class="btn" projectid="${projectID}" type="button" title="编辑"><em class="icon-edit"></em></button>
										<button operate="sectionList" class="btn" projectid="${projectID}" type="button" title="阶段管理"><em class="icon-signal"></em></button>
										<button operate="deleteProject" class="btn" projectid="${projectID}" type="button" title="删除"><em class="icon-remove"></em></button>
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
