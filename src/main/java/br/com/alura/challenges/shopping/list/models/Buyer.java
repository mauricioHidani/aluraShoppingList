package br.com.alura.challenges.shopping.list.models;

import java.math.BigDecimal;

public class Buyer {

	private final String name;
	private final BigDecimal cardLimit;

	public Buyer(final String name, final BigDecimal cardLimit) {
		this.name = name;
		this.cardLimit = cardLimit;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getCardLimit() {
		return cardLimit;
	}

	@Override
	public String toString() {
		return """
      		************************************************
   			Comprador: %s
   			Limite cartão: R$ .2%f
   			************************************************
			""".formatted(
				getName(), getCardLimit()
			);
	}
}
