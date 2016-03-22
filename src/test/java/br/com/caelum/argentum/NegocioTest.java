package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

public class NegocioTest {

	@Test
	public void testDataDoNegocioEhImutavel(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		
		Negocio n = new Negocio(10, 15, c);
		
		// essa mudança não deveria ficar visível, vamos testar:
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegocioComDataNula(){
		Negocio n = new Negocio(10, 5, null);
	}

}

