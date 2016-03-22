package br.com.caelum.argentum;

import java.util.Calendar;

import static org.junit.Assert.*;
import org.junit.Test;


public class CandlestickTest {
	
	@Test(expected=IllegalArgumentException.class)
	public void testPrecoMaximoNaoPodeSerMenorQueMinimo(){
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCandlestickComDataNula(){
		new Candlestick(10, 20, 10, 20, 10000, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCandlestickComValoresNegativos(){
		new Candlestick(-1, -10, -10, -1, -10000, Calendar.getInstance());
	}
	
	@Test 
	public void testSeEhAltaQuandoAberturaIgualFechamento(){
		Candlestick candle = new Candlestick(10, 10, 5, 10, 10000, Calendar.getInstance());
		
		assertEquals(true, candle.isAlta());		
	}


}
