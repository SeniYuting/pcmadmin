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
		<li class="active"><a href="${ctx}/cust/user/">User列表</a></li>
		<%--<shiro:hasPermission name="cust:user:edit"><li><a href="${ctx}/cust/user/form">User添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="customer" action="${ctx}/cust/user/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>account：</label>
				<form:input path="account" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>登陆名称</th>
				<th>姓名</th>
				<th> 性别</th>
				<th>地址</th>
				<th>联系方式</th>
				<th>注册时间</th>
				<th>最后一次登陆时间</th>
				<th>操作</th>
				<%--<shiro:hasPermission name="cust:user:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="customer">
			<%--<tr><a href="${ctx}/cust/user/form?id=${customer.id}">--%>
			<tr>
				<td>
					${customer.account}
				</td>
				<td>
						${customer.name}
				</td>
				<td>
					<c:if test="${customer.gender == '0'}">男</c:if>
					<c:if test="${customer.gender == '1'}">女</c:if>
				</td>
				<td>
						${customer.address}
				</td>
				<td>
						${customer.mobile}
				</td>
				<td>
						${customer.createdate}
				</td>
				<td>
						${customer.lastlogindate}
				</td>
				<shiro:hasPermission name="cust:user:edit"><td>
    				<%--<a href="${ctx}/cust/user/form?id=${customer.id}">修改</a>--%>
					<a href="${ctx}/cust/user/delete?id=${customer.id}" onclick="return confirmx('确认要删除该User吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>