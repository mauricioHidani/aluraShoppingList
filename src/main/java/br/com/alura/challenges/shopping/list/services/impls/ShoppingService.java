package br.com.alura.challenges.shopping.list.services.impls;

import br.com.alura.challenges.shopping.list.enums.CartOption;
import br.com.alura.challenges.shopping.list.models.Product;
import br.com.alura.challenges.shopping.list.services.IShoppingService;
import br.com.alura.challenges.shopping.list.utils.CurrencyFormatUtil;

import java.math.BigDecimal;
import java.util.*;

public class ShoppingService implements IShoppingService {

	private final Integer BREAK_LINE_COUNT = 64;

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
				"""
			);

			System.out.print("Opção: ");
			var choice = scanner.nextInt();
			if (choice >= 0 && choice < CartOption.values().length) {
				option = CartOption.values()[choice];
			}
			else {
				System.out.println("Opção selecionada é inválida.");
				continue;
			}

			switch (option) {
				case PUT_IN -> addProducts(products);
				case TAKE_OUT -> removeProduct(products);
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
		if (products.size() <= 0) {
			System.out.println("Não existem produtos para serem mostrados.");
			showBreakLine('*', BREAK_LINE_COUNT);
			return;
		}

		if (products.size() > 1) {
			Collections.sort(products);
		}
		products.forEach(System.out::println);
		showBreakLine('-', BREAK_LINE_COUNT);
		System.out.println("Total: %s".formatted(new CurrencyFormatUtil().toFormat(getTotalBy(products))));
		showBreakLine('*', BREAK_LINE_COUNT);
	}

	private void addProducts(final List<Product> products) {
		try {
			final var product = getProduct();
			products.add(product);
			System.out.printf("Inserido ao carrinho (%s)\n", product);
			showBreakLine('*', BREAK_LINE_COUNT);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("Não foi possivel adicionar um produto inválido.");
			showBreakLine('*', BREAK_LINE_COUNT);
		}
	}

	private void removeProduct(final List<Product> products) {
		if (products.size() <= 0) {
			System.out.println("Não ha produtos para serem removidos.");
			showBreakLine('*', BREAK_LINE_COUNT);
			return;
		}

		var target = getProduct();
		if (products.stream().noneMatch(product -> product.equals(target))) {
			System.out.println("Não foi possivel encontrar o produto especificado.");
			showBreakLine('*', BREAK_LINE_COUNT);
			return;
		}

		products.removeIf(product -> product.equals(target));
		System.out.println("Produto removido.");
		showBreakLine('*', BREAK_LINE_COUNT);
	}

	private Product getProduct() {
		if (scanner.hasNextLine()) {
			scanner.nextLine();
		}

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

		return new Product(name, price);
	}

	private void showBreakLine(final Character type, final Integer count) {
		var chars = new char[count];
		Arrays.fill(chars, type);
		System.out.println(new String(chars));
	}

}
