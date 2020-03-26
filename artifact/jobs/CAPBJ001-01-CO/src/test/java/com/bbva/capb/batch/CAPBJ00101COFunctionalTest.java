package com.bbva.capb.batch;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;

public class CAPBJ00101COFunctionalTest{
	Step1 sp1;
	Step2 sp2;
	
	@Before
	public void inicializar(){
		sp1 = new Step1();
		sp2 = new Step2();
	}
	
	@Test
	public void step1Test(){
		try{
			Assert.assertEquals(sp1.execute(null, null), null);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	@Test
	public void step2Test(){
		try{
			Assert.assertEquals(sp2.execute(null, null), null);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
}