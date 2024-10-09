package br.com.alura.challenges.shopping.list.models;

import br.com.alura.challenges.shopping.list.utils.CurrencyFormatUtil;

import java.math.BigDecimal;
import java.util.Objects;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return Objects.equals(getName(), product.getName()) && Objects.equals(getPrice(), product.getPrice());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getPrice());
	}

	@Override
	public String toString() {
		return "Produto: %s, Preço: %s" .formatted(getName(), new CurrencyFormatUtil().toFormat(getPrice()));
	}

	@Override
	public int compareTo(Product o) {
		return getPrice().compareTo(o.getPrice());
	}

}
