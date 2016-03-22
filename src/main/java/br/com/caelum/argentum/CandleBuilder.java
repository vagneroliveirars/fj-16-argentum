package br.com.caelum.argentum;

import java.util.Calendar;

public class CandleBuilder {
	
	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;
	// booleans que indicam se cada método setter foi invocado
	private boolean aberturaIsSet = false;
	private boolean fechamentoIsSet = false;
	private boolean minimoIsSet = false;
	private boolean maximoIsSet = false;
	private boolean volumeIsSet = false;
	private boolean dataIsSet = false;
	
	public CandleBuilder abertura(double abertura){
		this.abertura = abertura;
		aberturaIsSet = true;
		return this;		
	}

	public CandleBuilder fechamento(double fechamento) {
		this.fechamento = fechamento;
		fechamentoIsSet = true;
		return this;
	}

	public CandleBuilder minimo(double minimo) {
		this.minimo = minimo;
		minimoIsSet = true;
		return this;
	}

	public CandleBuilder maximo(double maximo) {
		this.maximo = maximo;
		maximoIsSet = true;
		return this;
	}

	public CandleBuilder volume(double volume) {
		this.volume = volume;
		volumeIsSet = true;
		return this;
	}

	public CandleBuilder data(Calendar data) {
		this.data = data;
		dataIsSet = true;
		return this;
	}
	
	public Candlestick geraCandle(){
		
		if (!aberturaIsSet || !fechamentoIsSet || !minimoIsSet || !maximoIsSet || !volumeIsSet || !dataIsSet){
			throw new IllegalStateException("geraCandle não pode ser invocado antes dos outros métodos já " +
					"terem sido.");
		}		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}

}
