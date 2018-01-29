<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>User统计信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var ioption = ${option};
			console.log(ioption);
			var toption = {
				title: {
					x: 'center',
					text: 'Tag与用户关系'
				},
				tooltip: {
					trigger: 'item'
				},
				toolbox: {
					show: true,
					feature: {
						dataView: {show: true, readOnly: false},
						restore: {show: true},
						refresh: {show:true},
						saveAsImage: {show: true}
					}
				},
				calculable: true,
//				grid: {
//					borderWidth: 0,
//					y: 80,
//					y2: 60
//				},
				xAxis: [
					{
						type: 'category',
						data: ioption.xAxis[0].data
					}
				],
				yAxis: [
					{
						type: 'value'
					}
				],
				series: [
					{
						type: 'bar',
						itemStyle: {
							normal: {
								color: function(params) {
									// build a color map as your need.
									var colorList = [
										'#C1232B','#B5C334','#FCCE10','#E87C25','#60C0DD',
										'#9BCA63','#FE8463','#FAD860','#F3A43B',
										'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
									];
									return colorList[params.dataIndex]
								},
								label: {
									show: true,
									position: 'top'
//									formatter: '{b}:{c}'
								}
							}
						},
						data: ioption.series[0].data
					}
				]
			};


			console.log(toption);
			var psLineChar = echarts.init(document.getElementById('psLine'));

//
			ioption.series[0].itemStyle.normal.color = function(params) {
				// build a color map as your need.
				var colorList = [
					'#C1232B','#B5C334','#FCCE10','#E87C25','#60C0DD',
					'#9BCA63','#FE8463','#FAD860','#F3A43B',
					'#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
				];
				return colorList[params.dataIndex]
			};
			psLineChar.setOption(ioption);
		});
	</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cust/statistic/">User统计信息</a></li>
	</ul>
	<sys:message content="${message}"/>
	<div style="padding:10px;clear: both;">
		<div id="psLine" style="height:500px;"></div>
	</div>
</body>
</html>