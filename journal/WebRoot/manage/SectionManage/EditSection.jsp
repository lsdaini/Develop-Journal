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
<script type="text/javascript" src="<%=SiteConfig.DoMain %>scripts/manage-SectionManage-EditSection.js"></script>
</head>
<body>
<s:form action="sectionActions" namespace="/actions/section" theme="simple">
<input type="hidden" name="method" value="editSection"/>
<input type="hidden" name="sectionID" value="${sectionInfo.sectionID}" />
<input type="hidden" id="sectionEndDateId" value="${sectionInfo.endDate}"/>
<fieldset>
    <div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<table class="table table-bordered table-striped">
				    <tr>
				       <td colspan="2" style="text-align:center;line-height:29px;font-size:16px;font-weight:bold">编辑阶段</td>
				    </tr>
					<tr>
						<td class="span4"><label class="control-label">名称</label>
						</td>
						<td class="span8">
						<div class="controls">
					      <input id="sectionNameInput" value="${sectionInfo.name}" name="sectionName" style="width:97%;" type="text" form-val="empty" class="input-xlarge" maxlength="20" />
					      <div><label id="sectionNameErrorInfo" style="color: red;font-size:12px;"></label></div>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">责任人</label>
						</td>
						<td>
						<div class="controls">
				        <s:select value="sectionInfo.acceptor.staffID" name="acceptorId" list="staffInfoList" theme="simple" cssClass="input-xlarge" style="width:100%;" listKey="staffID" listValue="name"></s:select>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">验收人</label>
						</td>
						<td>
						<div class="controls">
				        <s:select value="sectionInfo.manager.staffID" name="managerId" list="staffInfoList" theme="simple" cssClass="input-xlarge" style="width:100%;" listKey="staffID" listValue="name"></s:select>
				        </div>
						</td>
					</tr>
					<tr>
						<td><label class="control-label">开始日期</label>
						</td>
						<td>
						<div class="controls">
								<div id="txtStartDate" class="input-append date">
									<input id="startDateValue" value="<s:date name="sectionInfo.startDate" format="yyyy-MM-dd"/>" name="startDate" size="12"
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
									<input id="planEndDateValue" value="<s:date name="sectionInfo.planEndDate" format="yyyy-MM-dd"/>" name="planEndDate" size="12"
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
								    <input id="endDateValue" value="<s:date name="sectionInfo.endDate" format="yyyy-MM-dd"/>" name="endDate" size="12"
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
									style="margin: 0px; height: 104px; width: 97%;resize:none;">${sectionInfo.remark}</textarea>
							</div></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="row-fluid">
		  <div class="control-group">
				<div class="controls" style="float:right;margin-right:10px">
					<button id="btnSubmit" class="btn btn-primary" type="submit" style="margin-right:10px">提交</button>
					<button class="btn btn-primary" type="reset">重置</button>
				</div>
	      </div>
		</div>
	</div>
	</fieldset>
</s:form>
<%@ include file="/templets/bootstrap-bottom.jsp"%>
${actionScript }
</body>
</html>
