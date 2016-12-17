<?php
	require_once "calculator.class.php";
	class OperService{
		private $calculator;

		function __construct($calculator){
			$this->calculator=$calculator;
		}

		public function setCalculator($calculator){
			$this->calculator=$calculator;
		}
		public function getCalculator(){
			return $this->calculator;
		}

		public function calculate(){
			return $this->calculator->calculate();
		}
	}
?>