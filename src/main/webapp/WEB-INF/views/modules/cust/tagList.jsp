<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>Tag管理</title>
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
		<li class="active"><a href="${ctx}/cust/tag/">Tag列表</a></li>
		<shiro:hasPermission name="cust:tag:edit"><li><a href="${ctx}/cust/tag/form">Tag添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="tag" action="${ctx}/cust/tag/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>Tag名称：</label>
				<form:input path="tag_content" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<%--<th>TagID</th>--%>
				<th>名称</th>
				<th>描述</th>
				<th>操作</th>
				<%--<shiro:hasPermission name="cust:tag:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tag">
			<%--<tr><a href="${ctx}/cust/tag/form?id=${tag.id}">--%>
			<tr>
				<%--<td>--%>
					<%--<a href="${ctx}/cust/tag/form?id=${tag.id}">--%>
					<%--&lt;%&ndash;${tag.id}&ndash;%&gt;--%>
				<%--</td>--%>
				<td>
					<a href="${ctx}/cust/tag/form?id=${tag.id}">${tag.tag_content}</a>
				</td>
				<td>
						${tag.tag_description}
				</td>
				<shiro:hasPermission name="cust:tag:edit"><td>
    				<a href="${ctx}/cust/tag/form?id=${tag.id}">修改</a>
					<a href="${ctx}/cust/tag/delete?id=${tag.id}" onclick="return confirmx('确认要删除该Tag吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>