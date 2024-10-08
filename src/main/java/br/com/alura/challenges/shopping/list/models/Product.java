package br.com.alura.challenges.shopping.list.models;

import java.math.BigDecimal;

public class Product implements Comparable<Product> {

	private final String name;
	private final BigDecimal price;

	public Product(final String name, final BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return """
   			Produto: %s
   			Preço: R$ % .2%f
			""".formatted(
				getName(),
				getPrice()
			);
	}

	@Override
	public int compareTo(Product o) {
		return getPrice().compareTo(o.getPrice());
	}
}
