package br.com.alura.challenges.shopping.list.services;

import br.com.alura.challenges.shopping.list.models.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IShoppingService {
	void showInitScreen();
	List<Product> showCartOptions();
	BigDecimal getTotalBy(List<Product> products);
}
