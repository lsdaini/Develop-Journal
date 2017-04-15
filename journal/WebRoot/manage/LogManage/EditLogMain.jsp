<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>ztree/css/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="<%=SiteConfig.DoMain %>ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-LogManage-EditLogMain.js"></script>
<style>
.left_descript{margin-left:10px;line-height:31px;font-size:12px}
</style>
</head>
<body>
<div class="container-fluid" style="margin:10px 10px 10px 10px">
	<div class="row-fluid" style="border:1px solid #ccc">
		<div class="span12">
			<div class="row-fluid">
				<div class="span4" style="border-right:1px solid #ccc;width:220px">
					<div class="row-fluid" style="border-bottom:1px solid #ccc;">
						<div class="span12">
							<span class="left_descript">如果日志属非项目，此项不选</span>
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12" style="overflow-x:auto;overflow-y:auto;width:220px;height:400px">
						 	 <ul id="projectTree" class="ztree"></ul>
						</div>
					</div>
				</div>
				<div class="span8">
					<iframe id="frmContent"  frameborder="0"  scrolling="no" style="width:395px;height:420px"></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
</body>
</html>
