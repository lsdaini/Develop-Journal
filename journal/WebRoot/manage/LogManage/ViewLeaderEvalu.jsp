<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
</head>
<body>
   <div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<dl>
				<dt>
					工作汇总
				</dt>
				<dd>
					<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(reportInfo.summary)" escape="false"/>  
				</dd>
				<dt>
					自我评价
				</dt>
				<dd>
					<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(reportInfo.selfEvalu)" escape="false"/>  
				</dd>
				<dt>
					领导评价
				</dt>
				<dd>
					<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(reportInfo.bossEvalu)" escape="false"/>  
				</dd>
			</dl>
		</div>
	</div>
</div>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript}
</body>
</html>
