<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/commomhead.jspf"%>
<title>My JSP</title>
</head>
<body>

	<form id="ff" method="post">
	   <div>
			<label for="shId">仓库编号:</label> <input type="text" name="shId" readonly="readonly" />
		</div>
		<div>
			<label for="shName">仓库名:</label> <input type="text" name="shName" />
		</div>
		<div>
			<label for="shResponsible">责任人:</label> <input type="text"
				name="shResponsible" />
		</div>
		<div>
			<label for="shPhone">联系电话:</label> <input type="text"
				name="shPhone" />
		</div>
		<div>
			<label for="shAddress">联系地址:</label> <input type="text"
				name="shAddress" />
		</div>
		<div>
			<label for="shType">仓库类型:</label> <input type="text"
				name="shType" />
		</div>
		<div>
			<label for="shRemark">备注:</label>
			<textarea name="shRemark"></textarea>
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='仓库管理']").get(0).contentWindow;//返回ifram页面文档（window)
		    var array =win.$("#dg").datagrid("getSelections");
		    alert(array[0].shId);
		    $("#ff").form('load',{
				shId : array[0].shId,
				shName : array[0].shName,
				shResponsible : array[0].shResponsible,
				shPhone : array[0].shPhone,
				shAddress:array[0].shAddress,
				shType:array[0].shType,
				shRemark:array[0].shRemark
			});		
			$("[name='shName']").validatebox({
				required : true,
				missingMessage : '请填写仓库名！'
			});
			$("[name='shResponsible']").validatebox({
				required : true,
				missingMessage : '请填写出责任人！'
			});
			$("[name='shPhone']").validatebox({
				required : true,
				missingMessage : '请填写联系电话！'
			});
			$("[name='shAddress']").validatebox({
				required : true,
				missingMessage : '请填写联系地址！'
			});
			$("[name='shType']").validatebox({
				required : true,
				missingMessage : '请填写仓库类型！'
			});
			
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() {
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) {
					alert("------------");
					$('#ff').form('submit', {
						url : '${proPath}/storeHouse/update.action',
						onSubmit : function() {
							return true;
						},
						success : function(count) {							
								//可以定义为对应消息框
                        	     if(count>0){
									alert("修改成功！");									
								}else{
									alert("修改失败！");
								}
								parent.$("#win").window("close");
								win.$("#dg").datagrid("reload");							
						}
					});

				}

			});

		});
	</script>
</body>
</html>
