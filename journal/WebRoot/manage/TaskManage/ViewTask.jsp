<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="/page-tags" prefix="p"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<%@ include file="/templets/bootstrap-head.jsp"%>
<link rel="stylesheet" href="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/css/datetimepicker.css" />
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=SiteConfig.DoMain%>bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<body>
<s:form action="taskActions" namespace="/actions/task" theme="simple">
<fieldset>
    <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table class="table table-bordered table-striped">
				    <tr>
				       <td colspan="2" style="text-align:center;line-height:29px;font-size:16px;font-weight:bold">查看任务</td>
				    </tr>
					<tr>
						<td class="span4"><label class="control-label">名称</label>
						</td>
						<td class="span8">
						<div class="controls">
					      <input value="${taskInfo.name}" name="taskName" style="width:97%;" type="text" class="input-xlarge" maxlength="20" readonly/>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">责任人</label>
						</td>
						<td>
						<div class="controls">
						<span onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();">
				        <s:select value="taskInfo.acceptor.staffID" name="acceptorId" list="staffInfoList" theme="simple" cssClass="input-xlarge" style="width:100%;" listKey="staffID" listValue="name"></s:select>
				        </span>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">验收人</label>
						</td>
						<td>
						<div class="controls">
						<span onmousemove="this.setCapture();" onmouseout="this.releaseCapture();" onfocus="this.blur();">
				        <s:select value="taskInfo.manager.staffID" name="managerId" list="staffInfoList" theme="simple" cssClass="input-xlarge" style="width:100%;" listKey="staffID" listValue="name"></s:select>
				        </span>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">开始日期</label>
						</td>
						<td>
						<div class="controls">
								<div id="txtStartDate" class="input-append date">
									<input id="startDateValue" value="<s:date name="taskInfo.startDate" format="yyyy-MM-dd"/>" name="startDate" size="12"
										style="width:91%" type="text" readonly> <span
										class="add-on"><i class="icon-th"></i>
									</span>
									<div><label id="startDateErrorInfo" style="color: red;font-size:12px;"></label></div>
								</div>
						</div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">计划完成日期</label>
						</td>
						<td>
						<div class="controls">
								<div id="txtPlanEndDate" class="input-append date">
									<input id="planEndDateValue" value="<s:date name="taskInfo.planEndDate" format="yyyy-MM-dd"/>" name="planEndDate" size="12"
										style="width: 91%" type="text" readonly> <span
										class="add-on"><i class="icon-th"></i>
									</span>
									<div><label id="planEndDateErrorInfo" style="color: red;font-size:12px;"></label></div>
								</div>
						</div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">完成日期</label>
						</td>
						<td>
							<div class="controls">
								<div id="txtEndDate" class="input-append date">
								    <input id="endDateValue" value="<s:date name="taskInfo.endDate" format="yyyy-MM-dd"/>" name="endDate" size="12"
										style="width: 91%" type="text" readonly> 
									<span class="add-on"><i class="icon-th"></i></span>
									<div><label id="endDateErrorInfo" style="color: red;font-size:12px;"></label></div>
								</div>
							</div></td>
					</tr>
					<tr>
						<td><label class="control-label">备注</label>
						</td>
						<td>
							<div class="controls">
								<textarea name="remark"
									style="margin: 0px; height: 104px; width: 97%;resize:none;" readonly>${taskInfo.remark}</textarea>
							</div></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</fieldset>
</s:form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript }
</body>
</html>