
//通过value匹配相关的text，并返回，如果匹配不到，直接返回value
function valueToText(array,value){
		//var array = "${applicationScope.sysParam.supType}";
		array=array.substring(1,(array.length-1));
		var sp=array.split(",");					
		for(j=0;j<sp.length;j++){
			//alert("sp之ss=="+sp[j]);
			var k=sp[j].split("=","100");
			//如果是k[0]和value都为数字也可以用下面的代码
			//if(parseInt(k[0])==parseInt(value)){
			if($.trim((k[0]))==$.trim(value)){
				return k[1];
			}
		}
		return value;	
}
