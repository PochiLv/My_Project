<!DOCTYPE html>
<html>
	<meta charset="utf-8">
	<head>
		<title>简易计算器</title>
		<link rel="stylesheet" type="text/css" href="calView.css">
	</head>
	<body>
		<div class="all">
		<h1>简易计算器</h1>
		<form action="calProcess.php" method="post">
			<div class="div_input">
				<span>数字一</span><input type="text" name="num1" class="text_input"/>
			</div>
			<div class="div_input">
				<span>数字二</span><input type="text" name="num2" class="text_input"/>
			</div>
			<div class="div_input">
				<span>运算符</span>
				<select name="oper">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
				</select>
			</div>
			<div class="div_input" id="submit">
				<input type="submit" value="计算"/>
			</div>
			<div class="div_input" id="reset">
				<input type="reset" value="清空"/>
			</div>
		</form>
		</div>
	</body>
</html>