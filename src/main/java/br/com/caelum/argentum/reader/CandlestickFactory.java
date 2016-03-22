package br.com.caelum.argentum.reader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.caelum.argentum.CandleBuilder;
import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, List<Negocio> negocios){
		
		double maximo = Double.MIN_VALUE;		
		double minimo = Double.MAX_VALUE;		
		double volume = 0;
		
		for (Negocio negocio : negocios) {
			volume += negocio.getVolume();
			
			if (negocio.getPreco() > maximo){
				maximo = negocio.getPreco();
			}
			if (negocio.getPreco() < minimo){
				minimo = negocio.getPreco();
			}
		}
		
		double abertura = negocios.isEmpty() ? 0 : negocios.get(0).getPreco();
		double fechamento = negocios.isEmpty() ? 0 : negocios.get(negocios.size() - 1).getPreco();
		
		/*
		CandleBuilder builder = new CandleBuilder();		
		builder.abertura(abertura);
		builder.fechamento(fechamento);
		builder.minimo(minimo);
		builder.maximo(maximo);
		builder.volume(volume);
		builder.data(data);
		*/
		
		/*
		 * Os ‘setters’ aqui possuem nomes mais curtos e expressivos. Mais ainda: utilizando o pattern 
		 * fluent interface, podemos tornar o código acima mais conciso, sem perder a legibilidade:
		 */
		Candlestick candle = new CandleBuilder().abertura(abertura).fechamento(fechamento).minimo(minimo)
			.maximo(maximo).volume(volume).data(data).geraCandle();
		
		//return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
		return candle;
		
	}

	public boolean isMesmoDia(Calendar data1, Calendar data2) {		
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH)
				&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
				&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public List<Candlestick> constroiCandles(List<Negocio> todosNegocios) {
		
		// ordena todos os negocios em ordem crescente de data
		Collections.sort(todosNegocios);		
		
		List<Candlestick> candles = new ArrayList<Candlestick>();
		
		// lista com negocios que sejam do mesmo dia que dataPrimeiro
		List<Negocio> negociosMesmoDia = new ArrayList<Negocio>();
		Calendar dataPrimeiro = todosNegocios.get(0).getData();
		
		for (Negocio negocio : todosNegocios) {			
			/*
			 * com o uso Collections.sort não precisamos mais 
			 * verificar se os negocios estão vindo em ordem crescente
			*/
			if(dataPrimeiro.after(negocio.getData())){
				throw new IllegalStateException("Negocios em ordem errada");
			}
			
			// se não for mesmo dia, fecha candle e reinicia variáveis
			if(!isMesmoDia(dataPrimeiro, negocio.getData())){
				candles.add(constroiCandleParaData(dataPrimeiro, negociosMesmoDia));
				
				negociosMesmoDia = new ArrayList<Negocio>();
				dataPrimeiro = negocio.getData();
			}
			
			negociosMesmoDia.add(negocio);			
		}
		
		// adiciona último candle
		candles.add(constroiCandleParaData(dataPrimeiro, negociosMesmoDia));
		return candles;		
	}

}
