<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cust/comment/">User列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="comment" action="${ctx}/cust/comment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>评分星级：</label>
				<form:input path="star" htmlEscape="false" maxlength="64" type="number" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用户主键</th>
				<th>反馈意见</th>
				<th>评分星级</th>
				<th>时间</th>
				<th>操作</th>
				<%--<shiro:hasPermission name="cust:comment:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comment">
			<%--<tr><a href="${ctx}/cust/comment/form?id=${comment.id}">--%>
			<tr>
				<td>
					${comment.user_id}
				</td>
				<td>
						${comment.content}
				</td>

				<td>
						${comment.star}
				</td>
				<td>
						${comment.createdate}
				</td>
				<shiro:hasPermission name="cust:comment:edit"><td>
					<a href="${ctx}/cust/comment/delete?id=${comment.id}" onclick="return confirmx('确认要删除该User吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>