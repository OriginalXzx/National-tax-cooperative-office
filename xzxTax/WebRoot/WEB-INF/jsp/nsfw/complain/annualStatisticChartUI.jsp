<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	Calendar cal = Calendar.getInstance();
	int curYear = cal.get(Calendar.YEAR);//当前年份
	request.setAttribute("curYear", curYear);
	List yearList = new ArrayList();
	//设置年份下拉框，近五年的年份
	for(int i=0;i<5;i++){
	     yearList.add(i, curYear--);
	}
	request.setAttribute("yearList", yearList);
%>

<!DOCTYPE HTML>
<html>
<head>
<%@include file="/common/header.jsp"%>
<title>年度投诉统计图</title>
<script type="text/javascript" src="${basePath }js/fusioncharts/js/fusioncharts.js"></script>
<script type="text/javascript" src="${basePath }js/fusioncharts/js/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript">
  //一进入页面后执行，dom加载完后执行
  $(document).ready(doAnnualStatistic());
		
//根据年份统计投诉数
  function doAnnualStatistic(){
	//1、获取年份
	  var year = $("#year option:selected").val();
	if(year == "" || year == undefined){
		  year = "${curYear}";
	  }
	//2、统计年度投诉数据并展示图表
	  $.ajax({
		  url:"${basePath}nsfw/complain_getAnnualStatisticData.action",
		  data:{"year":year},
		  type:"post",
		  dataType:"json",
		  success: function(data){
			if(data != undefined && data != ""){
				//根据统计结果展示图表
				 var fc = new FusionCharts({
				"type": "line",
				"renderAt": "chartContainer",
				"width": "600",
				"height": "400",
				"dataFormat": "json",
				"dataSource":  {
				"chart": {
				"caption": year + " 年度投诉数统计图",
				"xAxisName": "月份",
				"yAxisName": "投诉数",
				"theme": "fint"
				         },
				"data": data.chartData
				      }

				  });
				  fc.render();
			  } else {alert("统计投诉数失败！");}
		  },
		  error: function(){alert("统计投诉数失败！");}
	  });
  }
</script>
</head>
<body>
  <br>
	<div style="text-align:center;width:100%;">
    <s:select id="year" list="#request.yearList" onchange="doAnnualStatistic()" cssStyle="margin-left:200px;" ></s:select>
    </div>
   <br>
   <div id="chartContainer"style="text-align:center;width:100%;"></div>
  
</body>
</html>

