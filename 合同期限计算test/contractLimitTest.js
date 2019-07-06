$(document).ready(function(){
	initializeDOM();
});

function initializeDOM(){
	initializeDataChange();
};

function initializeDataChange(){
	$("#endDate").change(function(){
		calContractLimitAuto();
	});
	$("#contractLimitAuto").change(function(){
		calEndDate();
	});
	$("#contractLimitAuto").on("keyup",function(){
		integerInputLimit($("#contractLimitAuto"));
	});
};

function calContractLimitAuto(){
	var effectDateStr = $("#effectDate").val();
	if(!effectDateStr){
		alert("请先输入开始日期");
		return;
	}
	var effectDate = new Date(effectDateStr);
	if(effectDate=="Invalid Date"){
		alert("开始日期不合法");
		return;
	}
	var endDateStr = $("#endDate").val();
	if(!endDateStr){
		return;
	}
	var endDate = new Date(endDateStr);
	if(endDate == "Invalid Date"){
		return;
	}
	$("#contractLimitAuto").val(Math.round((endDate.getTime()-effectDate.getTime())/(24*60*60*1000*365)*10)/10);
};

function calEndDate(){
	var effectDateStr = $("#effectDate").val();
	if(!effectDateStr){
		alert("请先输入开始日期");
		return;
	}
	var effectDate = new Date(effectDateStr);
	if(effectDate=="Invalid Date"){
		alert("开始日期不合法");
		return;
	}
	var contractLimitStr = $("#contractLimitAuto").val();
	if(!contractLimitStr){
		return;
	}
	var contractLimit = parseInt(contractLimitStr);
	if(isNaN(contractLimit)){
		return;
	}
	effectDate.setFullYear(effectDate.getFullYear()+contractLimit);
	effectDate.setDate(effectDate.getDate()-1);
	var resultDateStr = effectDate.getFullYear() + "-" + (effectDate.getMonth()+1) + "-" + effectDate.getDate();
	$("#endDate").val(resultDateStr);
};

function integerInputLimit(field){
	var obj = field[0];
	obj.value = obj.value.replace(/[^-\d.]/g,""); //清除"数字"和"."以外的字符
	obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是小数点
	obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的小数点
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//只保留第一个. 清除多余的小数点
	obj.value = obj.value.replace(/^-/g,"$#$").replace(/-/g,"").replace("$#$","-");//只保留开头的负号. 清除多余的负号
	obj.value = obj.value.replace(/^(\-)*(\d+)\..*$/,'$1$2'); //只取整数部分
};


