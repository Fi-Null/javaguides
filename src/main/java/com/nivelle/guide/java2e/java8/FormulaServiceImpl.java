package com.nivelle.guide.java2e.java8;


import org.springframework.stereotype.Service;

@Service("formulaService")
public class FormulaServiceImpl implements FormulaService {


   public double calculate(int a){
       return Math.sqrt(a*100);
   }
}
