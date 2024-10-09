package br.com.alura.challenges.shopping.list.models;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {

	private final Buyer buyer;
	private List<Product> products = new LinkedList<>();

	public ShoppingCart(final Buyer buyer) {
		this.buyer = buyer;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void addProduct(final Product product) {
		this.products.add(product);
	}

	public void addManyProduct(final List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(getBuyer());
		getProducts().forEach(result::append);
		return result.toString();
	}

}
