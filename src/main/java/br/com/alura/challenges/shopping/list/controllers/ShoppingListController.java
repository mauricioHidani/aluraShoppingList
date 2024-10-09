package br.com.alura.challenges.shopping.list.controllers;

import br.com.alura.challenges.shopping.list.models.Buyer;
import br.com.alura.challenges.shopping.list.models.Product;
import br.com.alura.challenges.shopping.list.models.ShoppingCart;
import br.com.alura.challenges.shopping.list.services.IBuyerService;
import br.com.alura.challenges.shopping.list.services.IShoppingService;
import br.com.alura.challenges.shopping.list.utils.CurrencyFormatUtil;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingListController {

	private final IBuyerService buyerService;
	private final IShoppingService productService;

	public ShoppingListController(
		final IBuyerService buyerService,
		final IShoppingService productService
	) {
		this.buyerService = buyerService;
		this.productService = productService;
	}

	public void start() {
		this.productService.showInitScreen();

		final var buyer = buyerService.input();
		final ShoppingCart cart = new ShoppingCart(buyer);
		final List<Product> products = productService.showCartOptions();
		cart.addManyProduct(products);

		try {
			checkCardLimit(buyer, products);
			System.out.println("""
					Compra finalizada,
					Obrigado pela compra %s, volte sempre
					Total de produtos do carrinho: %d
					Total comprado: %s
					""".formatted(
							buyer.getName(),
							products.size(),
							new CurrencyFormatUtil().toFormat(productService.getTotalBy(products))
					)
			);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	private void checkCardLimit(final Buyer buyer, final List<Product> products) {
		var total = productService.getTotalBy(products);
		var cardLimit = buyer.getCardLimit();

		if (cardLimit.compareTo(total) >= 0) {
			return;
		}
		throw new RuntimeException(
			"%s seu limite do cartão é insuficiente para finalizar a compra.".formatted(buyer.getName())
		);
	}

	private BigDecimal getTotalByProducts(final List<Product> products) {
		return products.stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

}
