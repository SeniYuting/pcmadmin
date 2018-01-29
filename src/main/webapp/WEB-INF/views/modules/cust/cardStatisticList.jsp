<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User统计信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var ioption = ${option};

//			var dlist= ioption.series[1].data;
//			var pied = [
//				{name:"男", value:dlist[0]},
//				{name:"女", value:dlist[1]},
//				{name:"不详", value:dlist[2]},
//			];
//			ioption.series[1].data = pied;
			<%--var toption = {--%>
				<%--title: {--%>
					<%--x: 'center',--%>
					<%--text: '用户交换名片情况'--%>
				<%--},--%>
				<%--tooltip: {--%>
					<%--trigger: 'item'--%>
				<%--},--%>
				<%--toolbox: {--%>
					<%--show: true,--%>
					<%--feature: {--%>
						<%--dataView: {show: true, readOnly: false},--%>
						<%--restore: {show: true},--%>
						<%--refresh: {show:true},--%>
						<%--saveAsImage: {show: true}--%>
					<%--}--%>
				<%--},--%>
				<%--calculable: true,--%>
				<%--xAxis: [--%>
					<%--{--%>
						<%--type: 'category',--%>
						<%--data: ${option.xAxis.data}--%>
					<%--}--%>
				<%--],--%>
				<%--yAxis: [--%>
					<%--{--%>
						<%--type: 'value'--%>
					<%--}--%>
				<%--],--%>
				<%--series: [--%>
					<%--{--%>
						<%--type: 'bar',--%>
						<%--itemStyle: {--%>
							<%--normal: {--%>
								<%--color: function(params) {--%>
									<%--// build a color map as your need.--%>
									<%--var colorList = [--%>
										<%--'#C1232B','#B5C334','#FCCE10','#E87C25','#60C0DD',--%>
										<%--'#9BCA63','#FE8463','#FAD860','#F3A43B',--%>
										<%--'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'--%>
									<%--];--%>
									<%--return colorList[params.dataIndex]--%>
								<%--},--%>
								<%--label: {--%>
									<%--show: true,--%>
									<%--position: 'top'--%>
<%--//									formatter: '{b}:{c}'--%>
								<%--}--%>
							<%--}--%>
						<%--},--%>
						<%--data: ${option.data.series[0].data}--%>
					<%--}--%>
				<%--]--%>
			<%--};--%>

			ioption.legend.y = "bottom";

			var psLineChar = echarts.init(document.getElementById('psLine'));
			console.log(ioption);
			console.log(ioption.toString());

			psLineChar.setOption(ioption);
		});
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cust/statistic/">Card统计信息</a></li>
	</ul>
	<sys:message content="${message}"/>
	<div style="padding:10px;clear: both;">
		<div id="psLine" style="height:500px;"></div>
	</div>
</body>
</html>