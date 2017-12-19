<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/commomhead.jspf"%>
<title>列表页面</title>
<style type="text/css">
.searchbox {
	margin: -2;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
	    var editIndex = undefined;
		$('#dg').datagrid({
			//设置为true,自动适应，不会出现滚动条，如果要冻结列，需要设置为false，并设置width值
			fitColumns : true,
			//width : 800,
			toolbar : [ {
				iconCls : 'icon-add',
				text : '添加商品',
				handler : function() {
					//alert('添加按钮');
					//指向父窗体的id为win的div
					parent.$('#win').window({   
						title : '选择和填写商品信息', 
					    width:800,    
					    height:500,
					    modal:true,
					    //通过加载新的页面填充内容
					    content:"<iframe src='${proPath}/base/goURL/goods/select.action' title='添加需采购商品' height='100%' width='100%' frameborder='0px' ></iframe>"
					});
				}
			}, '-', {
				iconCls : 'icon-remove',
				text : '删除商品',
				handler : function() {
					//$("#dg").datagrid("getSelections")
					//获取选中的记录
					var array = $("#dg").datagrid("getSelections");
					//alert(array);
					//判断是否选中
					if (array.length > 0) {
					//定义数组，通过下边的用来存储选中记录的Id
						//var ids = new Array();
						for (i = (array.length-1); i >=0;i--) {
							//ids[i] = array[i].goodsId;
							alert(array[i].goodsId);
							//getRowIndex方法使用goodsId字段获取行索引号时，一般需要配置idField : 'goodsId',
							
							var rowid = $("#dg").datagrid("getRowIndex",array[i].goodsId);
							alert("rowid："+rowid);
							$("#dg").datagrid("deleteRow",rowid);
							
						}
						//alert("ids" + ids);
						//如果需要锁整个页面，前面加parent.
						/* parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
							if (r) {
								alert(r);
								$.ajax({
								  url: "${proPath}/supplier/deleteByPks.action",
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
						}); */

					} else {
						$.messager.show({
							title : '操作提示',
							msg : '请先选择要删除的记录。',
							timeout : 4000,
							showType : 'slide'
						});

					}

				}
			}, '-', {
				iconCls : 'icon-edit',
				text : '提交采购',
				handler : function() {
				  alert('提交按钮');
				  var rows = $("#dg").datagrid("getRows");
						console.info(rows);				
						//把对象转成JSON格式的字符串
						 rows =JSON.stringify(rows);
						 console.info(rows);
						//提交采购到服务端
						 $('#ff').form('submit', {    
						    url:'${proPath}/buyOrder/insertBuyOrder.action',    
						    onSubmit: function(param){    
						    param.rows = rows; 
						    return true;   
						    },
						    success:function(data){  
						    //自己处理返回的信息  
						    alert(data);   
						    }      
						}); 
				}
			}],
			//设定是否换行显示数据， true为不换行
			nowrap : true,
			//指定主键字段
			idField : 'goodsId',			
			rownumbers : true,
	        //建议加入商品编号
			columns : [ [ {
				checkbox : true
			}, {
				field : 'goodsId',
				width : 100,
				title : '编号'
			}, {
				field : 'goodsName',
				title : '名称',
				width : 100
			}, {
				field : 'goodsUnit',
				title : '单位',
				width : 100
			}, {
				field : 'goodsType',
				title : '型号',
				width : 100
			}, {
				field : 'goodsColor',
				title : '颜色',
				width : 100
				/* formatter : function(value, row, index) {
					return valueToText("${applicationScope.sysParam.goodsColor}",value);
					//return value;	
				} */
			}, {
				field : 'bodAmount',
				title : '数量',
				editor:{type:'numberbox',options:{precision:2}},
				width : 100
			}, {
				field : 'bodBuyPrice',
				title : '进价',
				editor:{type:'numberbox',options:{precision:2}},
				width : 100
			}, {
				field : 'bodTotalPrice',
				width : 100,
				editor:{type:'numberbox',options:{precision:2}},
				title : '总价'
			} ,{
			    field : 'bodTotalPrice',
			    title : '手机串号',
			    editor: 'textarea',
			    width : 100
			
			}] ],
			 //双击单元格，进行编辑，需要预先定义editIndex变量，用来存放行的下标；
			//字段需要指明editor编辑类型，才能出现可编辑效果
			onDblClickCell: function(index,field,value){
			
				//结束编辑行, 可以不用，不会被调用，将在单击事件处理
					if(editIndex != undefined){							
						//alert(" onDblClickCell editIndex != undefined");			
					  $(this).datagrid('endEdit', editIndex);
						editIndex = undefined;
					}
					alert("dbclick");		
					//设置可编辑行	
					$(this).datagrid('beginEdit', index);
					editIndex = index;
					//alert("dbclick");				
					var ed = $(this).datagrid('getEditor', {index:index,field:field});
					//alert("field||index:"+field+index);				
					$(ed.target).focus();
			},
			//单击单元格时，如果存在编辑的单元格，结束编辑,这里的参数 index没有用
			 onClickCell: function(index,field,value){
			
				if(editIndex!= undefined){					
					alert(" onClickCell editIndex != undefined");			
				 
				  //计算总价
				  var amount = $(this).datagrid('getEditor', {index:editIndex,field:"bodAmount"});
				  var buyPrice=$(this).datagrid('getEditor', {index:editIndex,field:"bodBuyPrice"});
				  //alert(amount);
				  var bodTotalPrice =$(buyPrice.target).numberbox('getValue')*$(amount.target).numberbox('getValue');
				  //alert("bodTotalPrice"+bodTotalPrice);
				  var ed = $(this).datagrid('getEditor', {index:editIndex,field:"bodTotalPrice"});
				  //alert($(ed.target).numberbox('getValue'));	
				  $(ed.target).numberbox('setValue', bodTotalPrice);
				 // alert(" onClickCell editIndex != undefined4");	
				  //结束编辑行
				  $(this).datagrid('endEdit', editIndex);
					editIndex = undefined;
				}				
			}
		
		}); 
	
	    $('#supName').searchbox({ 
			searcher:function(value,name){ 
			//alert(value + "," + name);
			 			parent.$('#win').window({    
							title :'选择供应商',						
						    width:800,    
						    height:600,    
						    modal:true,
						    content:"<iframe src='${proPath}/base/goURL/supplier/selectSupplier.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
						});  
			}, 
			prompt:'请选择供应商' 
		});
		
		
		 $('#shName').searchbox({ 
			searcher:function(value,name){ 
			//alert(value + "," + name);
			 			parent.$('#win').window({    
							title :'选择仓库',						
						    width:800,    
						    height:600,    
						    modal:true,
						    content:"<iframe src='${proPath}/base/goURL/store/selectStore.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
						});  
			}, 
			prompt:'请选择仓库' 
		});
		
		//禁用供应商信息的输入
		var box = $('#supName').searchbox('textbox');//获取控件文本框对象
		box.attr('disabled', true);// 设置无效，禁用输入

		
		
		//禁用供应商信息的输入
		var box = $('#shName').searchbox('textbox');//获取控件文本框对象
		box.attr('disabled', true);// 设置无效，禁用输入

	    $('#ff').form("disableValidation");
	});
</script>
<body style="margin:0px;">
	<form style="padding:15px;margin:0px;background:#eee;" id="ff"
		action="${proPath}/buyOrder/insertBuyOrder.action">
		请填写采购信息：<br> 
		供应商：<input type="hidden" id="supId" name="supId" required="required"/>
		     <input type="text" id="supName" name="supName" required="required"/>
		仓库：<input type="hidden" id="shId" name="shId" required="required"/>
		    <input type="text" id="shName" name="shName" required="required"/>
		日期：<input type="text" id="boDate" class="easyui-datebox" name="boDate" ></input> 
		应付：<input type="text" id="boPayable" name="boPayable" required="required"/><br> 
		已付：<input type="text" id="boPaid" name="boPaid" required="required"/> 
		欠款：<input type="text" id="boArrears" name="boArrears" required="required"/> 
		原始单号：<input type="text" id="boOriginal" name="boOriginal" required="required"/><br> 
		经手人：<input type="text" id="boAttn" name="boAttn" required="required"/> 
		操作员：<input type="text" id="boOperator"name="boOperator" value="${sessionScope.account.accLogin}"/> 
		备注：<input type="text" id="boRemark" name="boRemark" />
	</form>
	<table id="dg"></table>
</body>
</html>
