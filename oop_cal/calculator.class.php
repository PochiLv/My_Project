<?php
	class Calculator{
		// 成员变量 数字和操作符
		private $num1;
		private $num2;
		private $oper;

		// 构造方法
		function __construct($num1,$num2,$oper){
			$this->num1=$num1;		
			$this->num2=$num2;
			$this->oper=$oper;
		}
		// set get方法
		public function setNum1($num1){
			$this->num1=$num1;		
		}
		public function setNum2($num2){
			$this->num2=$num2;
		}
		public function setOper($oper){
			$this->oper=$oper;	
		}

		public function getNum1(){
			return $this->num1;
		}
		public function getNum2(){
			return $this->num2;
		}
		public function getOper(){
			$this->oper;	
		}
		// 加减乘除的操作
		public function calculate(){
			switch($this->oper){
				case "+":
					return $this->num1+$this->num2;
					
				case "-":
					return $this->num1-$this->num2;
					
				case "*":
					return $this->num1*$this->num2;
					
				case "/":
					return $this->num1/$this->num2;
				default:
					return "error operator!!!";
			}
		}

	}

?>