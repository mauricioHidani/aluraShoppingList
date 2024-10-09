package br.com.alura.challenges.shopping.list.services.impls;

import br.com.alura.challenges.shopping.list.enums.CartOption;
import br.com.alura.challenges.shopping.list.models.Product;
import br.com.alura.challenges.shopping.list.services.IShoppingService;
import br.com.alura.challenges.shopping.list.utils.CurrencyFormatUtil;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingService implements IShoppingService {

	private final Scanner scanner;

	public ShoppingService(final Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public void showInitScreen() {
		System.out.println("""
			SHOPPING CART
			   ___ ___
			 /| |/|\\| |\\
			/_| ´ |.` |_\\
			  |   |.  |
			  |   |.  |
			  |___|.__|
			Challenge Alura ONE G7
			Tuesday, 8th October 2024 - Ada Lovelace Day
			""");
	}

	@Override
	public List<Product> showCartOptions() {
		List<Product> products = new ArrayList<>();
		var option = CartOption.START;
		while (option != CartOption.EXIT) {
			System.out.println("""
    			Opções do carrinho de compras:
    			1 - Adicionar produto
    			2 - Tirar produto
    			3 - Mostrar produto(s)
    			0 - Sair do carrinho
				""");

			var choice = scanner.nextInt();
			if (choice >= 0 && choice < CartOption.values().length) {
				option = CartOption.values()[choice];
			}
			else {
				System.out.println("Opção selecionada é inválida.");
				continue;
			}

			switch (option) {
				case PUT_IN -> products.add(addProduct());
				case TAKE_OUT -> products = removeProduct(products);
				case SHOW -> showProducts(products);
				default -> option = CartOption.EXIT;
			}
		}
		return products;
	}

	@Override
	public BigDecimal getTotalBy(List<Product> products) {
		final var result = products.stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		return result;
	}

	private void showProducts(final List<Product> products) {
		Collections.sort(products);
		products.forEach(System.out::println);
	}

	private Product addProduct() {
		for (int i = 0; i < 4; i++) {
			try {
				return getProduct();
			}
			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}

		throw new RuntimeException(
			"Não foi adicionado o produto por não atender aos valores inseridos em 3 tentativas."
		);
	}

	private List<Product> removeProduct(final List<Product> products) {
		var target = getProduct();
		return products.stream().filter(product -> !product.equals(target)).toList();
	}

	private Product getProduct() {
		System.out.print("Nome do produto: ");
		final var name = scanner.nextLine();
		if (name.length() < 3) {
			throw new IllegalArgumentException("Um produto não pode ter menos de 3 caracteres.");
		}

		System.out.print("Preço do produdo: ");
		final var price = BigDecimal.valueOf(scanner.nextDouble());
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Um produto não pode possuir um preço negativo.");
		}

		final var product = new Product(name, price);
		System.out.println("Produto inserido\n" + product);

		return product;
	}

}
