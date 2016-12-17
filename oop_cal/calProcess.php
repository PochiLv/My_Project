<?php
	require_once "calculator.class.php";
	require_once "operService.class.php";
	
	$num1=$_REQUEST['num1'];
	$num2=$_REQUEST['num2'];
	$oper=$_REQUEST['oper'];

	
	if(!isset($num1)||!is_numeric($num1)){
		die("没有输入正确的数字！<br>");
	}
	if(!isset($num2)||!is_numeric($num2)){
		die("没有输入正确的数字！<br>");
	}
	if(!isset($oper)){
		die("操作符为空!<br>");
	}
	


	$calculator=new Calculator($num1,$num2,$oper);
	$service=new OperService($calculator);
	$result=$service->calculate();
	echo "结果为：".$result."<br>";
?>
<a href="calView.php">返回主页面</a>