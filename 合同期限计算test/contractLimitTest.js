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
		alert("�������뿪ʼ����");
		return;
	}
	var effectDate = new Date(effectDateStr);
	if(effectDate=="Invalid Date"){
		alert("��ʼ���ڲ��Ϸ�");
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
		alert("�������뿪ʼ����");
		return;
	}
	var effectDate = new Date(effectDateStr);
	if(effectDate=="Invalid Date"){
		alert("��ʼ���ڲ��Ϸ�");
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
	obj.value = obj.value.replace(/[^-\d.]/g,""); //���"����"��"."������ַ�
	obj.value = obj.value.replace(/^\./g,""); //��֤��һ���ַ������ֶ�����С����
	obj.value = obj.value.replace(/\.{2,}/g,"."); //ֻ������һ��. ��������С����
	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//ֻ������һ��. ��������С����
	obj.value = obj.value.replace(/^-/g,"$#$").replace(/-/g,"").replace("$#$","-");//ֻ������ͷ�ĸ���. �������ĸ���
	obj.value = obj.value.replace(/^(\-)*(\d+)\..*$/,'$1$2'); //ֻȡ��������
};


