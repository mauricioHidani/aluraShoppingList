package br.com.alura.challenges.shopping.list;

import br.com.alura.challenges.shopping.list.controllers.ShoppingListController;
import br.com.alura.challenges.shopping.list.services.impls.BuyerService;
import br.com.alura.challenges.shopping.list.services.impls.ShoppingService;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		final var scanner = new Scanner(System.in);
		final var buyerService = new BuyerService(scanner);
		final var productService = new ShoppingService(scanner);
		final var controller = new ShoppingListController(buyerService, productService);

		controller.start();
	}
}