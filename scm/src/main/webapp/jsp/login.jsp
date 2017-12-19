<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/common/commomhead.jspf"%>
<title>Login page</title>
</head>
<body>
	<div id="loginDiv">
		<form id="ff" method="post" action="${proPath}/account/login.action">
			<div>
				<label for="accLogin">账号:</label>
				<!-- 采用 js验证 -->
				<input type="text" name="accLogin" />
			</div>
			<div>
				<label for="accPass">密码:</label>
				<!-- 行内验证
				<input class="easyui-validatebox" type="text" name="name"
					data-options="required:true,missingMessage:'请填写密码！'" />
					 -->
				<input type="text" name="accPass" />
			</div>
			<div style="color:red">${requestScope.msg}</div>
		</form>
	</div>
	<script type="text/javascript">
	/*
	 *＄｛选择器｝把ｄｏｍ对象转为ｊｑｕｅｒｙ对象，一般再处理成easyUI对象，例如$("name='accName'").validatebox({})就转为easyUI对象,这样easyUI对象就可以使用相关的语法进行处理，如：$('selector').plugin('method', parameter); 
	 */
	//下边的大括号和json的数据格式：{属性：值，属性：值}一样
	
		$("[name='accLogin']").validatebox({
			required : true,
			missingMessage : '请填写账号！'
		});
		$("[name='accPass']").validatebox({
			required : true,
			missingMessage : '请填写密码！'
		});
		//禁用验证
		$("#ff").form("disableValidation");
		//把div的内容作为对话框方式弹出
		$('#loginDiv').dialog({
			title : '登陆入口',
			width : 250,
			height : 150,
			closed : false,
			cache : false,
			//设置关闭按钮可见
			closable : false,
			//模式化窗口.如果为true将锁屏
			modal : true,
			buttons : [ {
				text : '登陆',
				handler : function() {
					//启用验证
					$("#ff").form("enableValidation");
					//validate方法:进行form表单字段的验证，通过返回true
					if ($("#ff").form("validate")) {
						alert("login");
						//$("#ff").get(0).submit();
						$("#ff").submit();
	
					}
				}
			}, {
				text : '取销',
				handler : function() {
					$("#ff").form("reset");
					alert("取消");
				}
			} ]
		});
	</script>
</body>
</html>