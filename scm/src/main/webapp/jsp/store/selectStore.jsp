<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/commomhead.jspf"%>
<html>
<head>


<title></title>
<style type="text/css">
.searchbox{
	margin:-3
}
</style>
<script type="text/javascript" src="${proPath}/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
	$(function(){
	        var win = parent.$("iframe[title='商品采购']").get(0).contentWindow;//返回ifram页面窗体对象（window)
			$('#dg').datagrid({    
			    //支持多条件查询
			    url:'${proPath}/storeHouse/selectPageUseDyc.action', 
			    
			    fitColumns:true,
			    nowrapL:true,
			    //idField:
			    rownumbers:true,
			    pagination:true,
			    pageSize:5,
			    pageList:[2,5,10,20],
			    
			     queryParams: {
					supName: '%%',
					supAddress:'%%'			
				}, 
						    
			    toolbar: [{
					iconCls: 'icon-ok',
					text:'选择仓库',
					handler: function(){
					var row =  $("#dg").datagrid("getSelected");
						alert(row.shId);
						win.$("#ff").form('load',{
							shId:row.shId,
							shName:row.shName
						});
						parent.$("#win").window("close");
					}
					
				},'-',{
					text:"名称：<input type='text' id='shName' name='shName'/>",					
				}
				,'-',{
					text:"地址：<input type='text' id='shAddress' name='shAddress'/>",					
				}],			       
			    
				columns : [ [{
					checkbox:true,
				}, {
				field : 'shId',
				title : '仓库编号',
				width : 100
			}, {
				field : 'shName',
				title : '仓库名称',
				width : 100
			},{
				field : 'shResponsible',
				title : '责任人',
				width : 100
			},{
				field : 'shPhone',
				title : '联系电话',
				width : 100
			},{
				field : 'shAddress',
				title : '联系地址',
				width : 100
			}, {
				field : 'shType',
				title : '仓库类型',
				width : 100
			},{
				field : 'shRemark',
				title : '备注',
				width : 100,
				align : 'right'
			} ] ]
		});
		
			$('#shAddress').searchbox({ 
			searcher:function(value,name){ 
				alert("shAddress:"+value); 
				alert("shName:"+$('#shName').val());
				$('#dg').datagrid('load',{
					shName: '%'+$('#shName').val()+'%',
					shAddress:'%'+value+'%'		
				});					
			}, 
			prompt:'' 
		});

	});
</script>
</head>

<body>

	<table id="dg"></table>
</body>
</html>