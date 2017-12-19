<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/commomhead.jspf"%>
<title>My JSP</title>
</head>
<body>

	<form id="ff" method="post">
	   <div>
			<label for="goodsId">商品编号:</label> <input type="text" name="goodsId" />
		</div>
		<div>
			<label for="goodsName">商品名:</label> <input type="text" name="goodsName" />
		</div>
		<div>
			<label for="goodsUnit">单价:</label> <input type="text"
				name="goodsUnit" />
		</div>
		<div>
			<label for="goodsType">商品类型:</label> <input type="text"
				name="goodsType" />
		</div>
		<div>
			<label for="goodsColor">商品颜色：</label> 									
				<select id="cc" class="easyui-combobox" name="goodsColor" style="width:200px;">   
					<c:forEach items="${applicationScope.sysParam.goodsColor}" var="goodsColor">
					    <option value="${goodsColor.key}">${goodsColor.value}</option>   
					</c:forEach>   
				</select>  
		</div>
		<div>
			<label for="goodsStore">商品仓库:</label> <input type="text"
				name="goodsStore" />
		</div>
		<div>
			<label for="goodsLimit">库存下限:</label> <input type="text"
				name="goodsLimiit" />
		</div>
		<div>
			<label for="goodsCommission">提成:</label> <input type="text"
				name="goodsStore" />
		</div>
		<div>
			<label for="goodsProducer">生产商:</label> <input type="text"
				name="goodsProducer" />
		</div>
		<div>
			<label for="goodsSelPrice">售价:</label> <input type="text"
				name="goodsSelPrice" />
		</div>
		<div>
			<label for="goodsBuyPrice">进价:</label> <input type="text"
				name="goodsBuyPrice" />
		</div>
		
		<div>
			<label for="goodsRemark">备注:</label>
			<textarea name="goodsRemark"></textarea>
		</div>
		<div>
			<input id="btn" type="button" value="提交" />
		</div>
	</form>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='商品管理']").get(0).contentWindow;//返回ifram页面文档（window)
		
			$("[name='goodsName']").validatebox({
				required : true,
				missingMessage : '请填写商品名！'
			});
			$("[name='goodsUnit']").validatebox({
				required : true,
				missingMessage : '请填写商品单价！'
			});
			$("[name='goodsType']").validatebox({
				required : true,
				missingMessage : '请填写商品类型！'
			});
			$("[name='goodsStore']").validatebox({
				required : true,
				missingMessage : '请填写商品仓库！'
			});
			$("[name='goodsProducer']").validatebox({
				required : true,
				missingMessage : '请填写生产商！'
			});
			$("[name='goodsSelPrice']").validatebox({
				required : true,
				missingMessage : '请填写售价！'
			});
			$("[name='goodsBuyPrice']").validatebox({
				required : true,
				missingMessage : '请填写进价！'
			});
			
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() {
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) {
					alert("------------");
					$('#ff').form('submit', {
						url : '${proPath}/goods/insert.action',
						onSubmit : function() {
							return true;
						},
						success : function(count) {							
								//可以定义为对应消息框
                        	    if(count>0){
									alert("添加成功！");									
								}else{
									alert("添加失败！");
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
