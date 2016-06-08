<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<title>My JSP 'MyMap.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="${request.contextPath}/js/jquery-1.11.3.min.js"></script>

<link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css" />
<script src="http://cache.amap.com/lbs/static/es5.min.js"></script>
<script src="http://webapi.amap.com/maps?v=1.3&key=ba85bc236b53e2b9be0d486867b59a92&&plugin=AMap.Scale,AMap.OverView,AMap.ToolBar,AMap.Autocomplete"></script>
<script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<style>
.marker {
	color: #ff6600;
	padding: 4px 10px;
	border: 1px solid #fff;
	white-space: nowrap;
	font-size: 12px;
	font-family: "";
	background-color: #0066ff;
}
</style>

</head>
<body>
	<div id="container"></div>
	<div class="button-group">
		<input type="button" class="button" id="control" value="显示/隐藏实时路况" />
	</div>
	<div id="myPageTop">
		<table>
			<tr>
				<td>
					<label>按关键字搜索：</label>
				</td>
				<td class="column2">
					<label>左击获取经纬度：</label>
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
				</td>
				<td class="column2">
					<input type="text" readonly="true" id="lnglat">
				</td>
			</tr>
		</table>
	</div>
	<div class='button-group' style="background-color: #0d9bf2;right: 160px">
		<input type="checkbox" onclick="toggleScale(this)" />
		比例尺
		<input type="checkbox" id="toolbar" onclick="toggleToolBar(this)" />
		工具条
		<input type="checkbox" id="toolbarDirection" disabled onclick="toggleToolBarDirection(this)" />
		工具条方向盘
		<input type="checkbox" id="toolbarRuler" disabled onclick="toggleToolBarRuler(this)" />
		工具条标尺
		<input type="checkbox" id="overview" onclick="toggleOverViewShow(this)" />
		显示鹰眼
		<input type="checkbox" id="overviewOpen" disabled onclick="toggleOverViewOpen(this)" />
		展开鹰眼
	</div>
	<div class="button-group" style="background-color: #0d9bf2;right: 160px;bottom:100px">
		<input type="button" class="button" value="添加点标记覆盖物" id="addMarker" />
		<input type="button" class="button" value="更新点标记覆盖物" id="updateMarker" />
		<input type="button" class="button" value="删除点标记覆盖物" id="clearMarker" />
	</div>
	<div class="button-group" style="background-color: #0d9bf2;bottom:100px">
		<input type="button" class="button" value="开始动画" id="start" />
		<input type="button" class="button" value="停止动画" id="stop" />
	</div>
	<script>
		var scale = new AMap.Scale({
			visible : false
		}), toolBar = new AMap.ToolBar({
			visible : false
		}), overView = new AMap.OverView({
			visible : false
		}), marker, map = new AMap.Map("container", {
			center : [ 113.954032, 22.536678 ],
			zoom : 13,
			resizeEnable : true
		});

		map.addControl(scale);
		map.addControl(toolBar);
		map.addControl(overView);
		//轨迹回放
		var lineArr = [];
		map.on("complete", completeEventHandler);
		AMap.event.addDomListener(document.getElementById('start'), 'click',
				function() {
					marker.moveAlong(lineArr, 500);
				}, false);
		AMap.event.addDomListener(document.getElementById('stop'), 'click',
				function() {
					marker.stopMove();
				}, false);

		// 地图图块加载完毕后执行函数
		function completeEventHandler() {
			marker = new AMap.Marker({
				map : map,
				position : [ 113.954032, 22.536678 ],
				icon : "http://webapi.amap.com/images/car.png",
				offset : new AMap.Pixel(-26, -13),
				autoRotation : true
			});
			var lngX = 113.954032, latY = 22.536678;
			lineArr.push([ lngX, latY ]);
			for (var i = 1; i < 3; i++) {
				lngX = lngX + Math.random() * 0.05;
				if (i % 2) {
					latY = latY + Math.random() * 0.0001;
				} else {
					latY = latY + Math.random() * 0.06;
				}
				lineArr.push([ lngX, latY ]);
			}
			// 绘制轨迹
			var polyline = new AMap.Polyline({
				map : map,
				path : lineArr,
				strokeColor : "#00A", //线颜色
				strokeOpacity : 1, //线透明度
				strokeWeight : 3, //线宽
				strokeStyle : "solid" //线样式
			});
			map.setFitView();
		}
		//自定义标记
		AMap.event.addDomListener(document.getElementById('addMarker'),
				'click', function() {
					addMarker();
				}, false);
		AMap.event.addDomListener(document.getElementById('updateMarker'),
				'click', function() {
					marker && updateMarker();
				}, false);
		AMap.event.addDomListener(document.getElementById('clearMarker'),
				'click', function() {
					if (marker) {
						marker.setMap(null);
						marker = null;
					}
				}, false);

		// 实例化点标记
		function addMarker() {
			if (marker) {
				return;
			}
			marker = new AMap.Marker(
					{
						icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
						position : [ 113.954032, 22.536678 ]
					});
			marker.setMap(map);
		}

		function updateMarker() {
			// 自定义点标记内容
			var markerContent = document.createElement("div");

			// 点标记中的图标
			var markerImg = document.createElement("img");
			markerImg.className = "markerlnglat";
			markerImg.src = "http://webapi.amap.com/theme/v1.3/markers/n/mark_r.png";
			markerContent.appendChild(markerImg);

			// 点标记中的文本
			var markerSpan = document.createElement("span");
			markerSpan.className = 'marker';
			markerSpan.innerHTML = "Hi，我换新装备啦！";
			markerContent.appendChild(markerSpan);

			marker.setContent(markerContent); //更新点标记内容
			marker.setPosition([ 116.391467, 39.927761 ]); //更新点标记位置
		}

		//实时路况图层
		var trafficLayer = new AMap.TileLayer.Traffic({
			zIndex : 10
		});
		trafficLayer.setMap(map);

		var isVisible = true;
		AMap.event.addDomListener(document.getElementById('control'), 'click',
				function() {
					if (isVisible) {
						trafficLayer.hide();
						isVisible = false;
					} else {
						trafficLayer.show();
						isVisible = true;
					}
				}, false);
		//为地图注册click事件获取鼠标点击出的经纬度坐标
		var clickEventListener = map.on('click', function(e) {
			document.getElementById("lnglat").value = e.lnglat.getLng() + ','
					+ e.lnglat.getLat()
		});
		var auto = new AMap.Autocomplete({
			input : "tipinput"
		});
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
		function select(e) {
			if (e.poi && e.poi.location) {
				map.setZoom(15);
				map.setCenter(e.poi.location);
			}
		}
		function toggleScale(checkbox) {
			if (checkbox.checked) {
				scale.show();
			} else {
				scale.hide();
			}
		}
		function toggleToolBar(checkbox) {
			if (checkbox.checked) {
				showToolBar();
				showToolBarDirection();
				showToolBarRuler();
			} else {
				hideToolBar();
				hideToolBarDirection();
				hideToolBarRuler();
			}
		}
		function toggleToolBarDirection(checkbox) {
			if (checkbox.checked) {
				toolBar.showDirection()
			} else {
				toolBar.hideDirection()
			}
		}
		function toggleToolBarRuler(checkbox) {
			if (checkbox.checked) {
				toolBar.showRuler();
			} else {
				toolBar.hideRuler();
			}
		}
		function toggleOverViewShow(checkbox) {
			if (checkbox.checked) {
				overView.show();
				document.getElementById('overviewOpen').disabled = false;
			} else {
				overView.hide();
				document.getElementById('overviewOpen').disabled = true;
			}
		}
		function toggleOverViewOpen(checkbox) {
			if (checkbox.checked) {
				overView.open();
			} else {
				overView.close();
			}
		}
		function showToolBar() {
			document.getElementById('toolbar').checked = true;
			document.getElementById('toolbarDirection').disabled = false;
			document.getElementById('toolbarRuler').disabled = false;
			toolBar.show();
		}
		function hideToolBar() {
			document.getElementById('toolbar').checked = false;
			document.getElementById('toolbarDirection').disabled = true;
			document.getElementById('toolbarRuler').disabled = true;
			toolBar.hide();
		}
		function showToolBarDirection() {
			document.getElementById('toolbarDirection').checked = true;
			toolBar.showDirection();
		}
		function hideToolBarDirection() {
			document.getElementById('toolbarDirection').checked = false;
			toolBar.hideDirection();
		}
		function showToolBarRuler() {
			document.getElementById('toolbarRuler').checked = true;
			toolBar.showRuler();
		}
		function hideToolBarRuler() {
			document.getElementById('toolbarRuler').checked = false;
			toolBar.hideRuler();
		}
	</script>
</html>
