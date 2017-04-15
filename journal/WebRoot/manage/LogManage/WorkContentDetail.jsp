<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
</head>
<body>
	<div style="margin: 10px 10px 10px 10px">
		<s:property value="@dev.frame.filter.HtmlFilter@filterWarpCharacter(logDetail.content)" escape="false"/>  
	</div>
</body>
</html>
