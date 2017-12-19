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
	
			$('#dg').datagrid({    
			    //url:'${proPath}/supplier/selectPage.action', //通过关键字查询
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
					iconCls: 'icon-add',
					text:'新增',
					handler: function(){
					parent.$('#win').window({
					   width:600,
					   height:400,
					   modal:true,
					   content:"<iframe src='${proPath}/base/goURL/store/insertStore.action' height='100%' width='100%' frameborder='0px' ></iframe>"
					});
					}
				},'-',{
					iconCls: 'icon-edit',
					text:'修改',
					handler: function(){
						alert('修改按钮');
						var array = $("#dg").datagrid("getSelections");
						if(array.length==1){
						parent.$('#win').window({  
							title:'修改页面',  
						    width:600,    
						    height:400,    
						    modal:true,
						    content:"<iframe src='${proPath}/base/goURL/store/updateStore.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
						}); 
						
					}else{
						$.messager.show({
							title : '操作提示',
							msg : '请只选择一条需要修改的记录。',
							timeout : 4000,
							showType : 'slide'
						});
					}
					}
				},'-',{
					iconCls: 'icon-remove',
					text:'删除',
					handler: function(){
						//$("#dg").datagrid("getSelections")
					//获取选中的记录
					var array = $("#dg").datagrid("getSelections");
					//alert(array);
					//判断是否选中
					if (array.length > 0) {
					//定义数组，通过下边的用来存储选中记录的Id
						var ids = new Array();
						for (i = 0; i < array.length; i++) {
							ids[i] = array[i].shId;
							//alert(ids[i]);
						}
						alert("ids" + ids);
						//如果需要锁整个页面，前面加parent.
						parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
							if (r) {
								alert(r);
								$.ajax({
								  url: "${proPath}/storeHouse/deleteByPks.action",
								  type:"POST",
								  //设置为传统方式传送参数
								  traditional:true,
								  data:{pks:ids},
								  success: function(html){
								  //重新刷新页面
								    $("#dg").datagrid("reload");
								    //请除所有勾选的行
								    $("#dg").datagrid("clearSelections");
								  },
								  error: function (XMLHttpRequest, textStatus, errorThrown) {
									    $.messager.alert('删除错误','请联系管理员！','error');
									},
								  dataType:'json'
								});
							}
						});

					} else {
						$.messager.show({
							title : '操作提示',
							msg : '请先选择要删除的记录。',
							timeout : 4000,
							showType : 'slide'
						});
					}
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
		
	/* 通过关键字查询
		$('#ss').searchbox({ 
			searcher:function(value,name){ 
				alert(value + "," + name); 
				$('#dg').datagrid('load',{
					keyWord: '%'+value+'%'					
				});					
			}, 
			prompt:'请输入供应商名称' 
		}); */ 

	});
</script>
</head>

<body>

	<table id="dg"></table>
</body>
</html>