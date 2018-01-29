<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById('ftime').valueAsDate = new Date();
			document.getElementById('ttime').valueAsDate = new Date();

		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cust/download/">下载列表</a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/cust/download/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间(开始)：</label>
				<input type="date" id="ftime" value="2016-11-20/"/>
			</li>
			<li><label>时间(截止)：</label>
				<input type="date" id="ttime" value="2016-12-20/"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>下载人次</th>
				<th>卸载人次</th>
				<th>注册人次</th>
				<th>登陆人次</th>
				<th>详细情况</th>
			</tr>
		<tbody>
		</tbody>
		</thead>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>