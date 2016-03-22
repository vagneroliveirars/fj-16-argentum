package br.com.caelum.argentum;

import java.util.Calendar;

public final class Negocio implements Comparable<Negocio>{
	
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	public Negocio(double preco, int quantidade, Calendar data) {
		
		if (data == null){
			throw new IllegalArgumentException("Data não pode ser nula");
		}
		
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	// devolve o volume de dinheiro daquele Negocio
	public double getVolume(){		
		return preco * quantidade; 
	}

	@Override
	public int compareTo(Negocio outroNegocio) {
		if (this.data.before(outroNegocio.data)) {
            return -1;
        }
        if (this.data.after(outroNegocio.data)) {
            return 1;
        }
        return 0;
	}

}
