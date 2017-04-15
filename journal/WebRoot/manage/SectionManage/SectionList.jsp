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
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-SectionManage-SectionList.js"></script>
<title></title>
<style type="text/css">
#tool_bar ul{list-style: none;margin-top:5px;margin-left:18px}
#tool_bar li{width:24px;height:24px;float:left;cursor:pointer}
#tool_bar li i{margin-left:5px;margin-top:3px}
</style>
</head>
<body >
 <input type="hidden" value="${projectInfo.projectID}" id="projectid"/>
 	<div id="header_nav_container" class="container-fluid">
	    <div class="row-fluid">
		    <div id="header_nav" class="span12">
		    	 <a href="<%=SiteConfig.DoMain %>actions/project/projectActions.action?method=getProjectInfoList" class="nav_prep"><i class="icon-wrench"></i>项目管理</a> <a href="#">阶段管理</a>
		    </div>
    	</div>
	</div>
   <div class="container-fluid">
   <div class="row-fluid">
	   <div class="span12" class=""></div>
	</div>
	<div id="main_container" class="row-fluid" style="border:1px solid #ccc">
		<div class="span12">
			<div class="row-fluid">
				<div class="span12" style="text-align:center;border-bottom:1px solid #ccc;">
				  <span style="line-height:29px;font-size:14px;font-weight:bold"><s:property value="projectInfo.Name"/></span>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4" style="border-right:1px solid #ccc;width:220px">
					<div class="row-fluid" style="background-color:#F9F9F9">
						<div id="tool_bar" class="span12" style="border-bottom:1px solid #ccc;">
							<ul>
								<li><i operate="NewSection" class="icon-plus-sign"></i></li>
								<li><i operate="NewTask" class="icon-plus"></i></li>
								<li><i operate="Edit" class="icon-edit"></i></li>
								<li><i operate="Remove" class="icon-remove"></i></li>
							</ul>
						</div>
					</div>
					<div class="row-fluid">
						<div id="leftTree" class="span12" style="overflow-x:auto;overflow-y:auto;width:220px;">
						 	 <ul id="sectionTree" class="ztree"></ul>
						</div>
					</div>
				</div>
				<div id="right_container" class="span8" style="margin-top:10px;">
				 	<iframe id="frmContent" scrolling="auto" style="width:100%;height:100%"  src="<%=SiteConfig.DoMain%>actions/section/sectionActions.action?method=initialAddSection&projectID=${projectInfo.projectID}"  onload="setHeight();" frameborder="0"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
	<%@ include file="/templets/bootstrap-bottom.jsp"%>
	${actionScript }
</body>
</html>
