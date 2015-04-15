package br.com.caelum.agiletickets.domain.precos;

import java.math.BigDecimal;

import br.com.caelum.agiletickets.models.Sessao;
import br.com.caelum.agiletickets.models.TipoDeEspetaculo;

public class CalculadoraDePrecos {

	public static BigDecimal calcula(Sessao sessao, Integer quantidade) {
		Integer ingressosReservados = sessao.getIngressosReservados();
		Integer totalIngressos = sessao.getTotalIngressos();
		TipoDeEspetaculo tipo = sessao.getEspetaculo().getTipo();
		BigDecimal preco = sessao.getPreco();
		double taxaOcupacao = (totalIngressos.doubleValue() - ingressosReservados.doubleValue()) / totalIngressos.doubleValue();
		
		if (taxaOcupacao <= tipo.getLimitePreco())
			preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(tipo.getAcrescimo())));
		
		if (tipo.equals(TipoDeEspetaculo.BALLET) || tipo.equals(TipoDeEspetaculo.ORQUESTRA)){
			Integer duracaoEmMinutos = sessao.getDuracaoEmMinutos();
			
			if(duracaoEmMinutos > 60){
				preco = preco.add(sessao.getPreco().multiply(BigDecimal.valueOf(0.10)));
			}
		}
		
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

}