package br.com.caelum.agiletickets.models;

public enum TipoDeEspetaculo {
	
	CINEMA(0.05, 0.10),
	SHOW(0.05, 0.10), 
	TEATRO(1.0, 0.0), 
	BALLET(0.50, 0.20), 
	ORQUESTRA(0.50, 0.20);
	
	private double limitePreco, acrescimo;
	
	private TipoDeEspetaculo(double limitePreco, double acrescimo){
		this.limitePreco = limitePreco;
		this.acrescimo = acrescimo;
	}
	
	public double getLimitePreco(){return this.limitePreco;}
	public double getAcrescimo(){return this.acrescimo;}
}
