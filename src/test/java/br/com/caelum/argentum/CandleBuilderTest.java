package br.com.caelum.argentum;

import org.junit.Test;


public class CandleBuilderTest {
	
	@Test(expected=IllegalStateException.class)
	public void testGeracaoDeCandleSemTodosOsDadosNecessarios(){
		Candlestick candle = new CandleBuilder().geraCandle();
	}

}
