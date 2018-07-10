<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery/plugins/echarts/echarts-all.js"></script>
</head>
<body>
	 <div id="main" style="height:400px;width:700px;"></div>
	 <script type="text/javascript">
	     // 基于准备好的dom，初始化echarts实例
	     var myChart = echarts.init(document.getElementById('main'));
	     var option = {
	    		    title : {
	    		        text: '销售报表',
	    		        subtext: '${groupByType}',
	    		        x:'center'
	    		    },
	    		    tooltip : {
	    		        trigger: 'item',
	    		        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    		    },
	    		    legend: {
	    		        orient : 'vertical',
	    		        x : 'left',
	    		        data:${groupByTypes}
	    		    },
	    		    toolbox: {
	    		        show : true,
	    		        feature : {
	    		            mark : {show: true},
	    		            dataView : {show: true, readOnly: false},
	    		            magicType : {
	    		                show: true, 
	    		                type: ['pie', 'funnel'],
	    		                option: {
	    		                    funnel: {
	    		                        x: '25%',
	    		                        width: '50%',
	    		                        funnelAlign: 'left',
	    		                        max: 1548
	    		                    }
	    		                }
	    		            },
	    		            restore : {show: true},
	    		            saveAsImage : {show: true}
	    		        }
	    		    },
	    		    calculable : true,
	    		    series : [
	    		        {
	    		            name:'销售总额',
	    		            type:'pie',
	    		            radius : '55%',
	    		            center: ['50%', '60%'],
	    		            data:${datas}
	    		        }
	    		    ]
	    		};
      // 使用刚指定的配置项和数据显示图表。
         myChart.setOption(option);
</script>
</body>
</html>