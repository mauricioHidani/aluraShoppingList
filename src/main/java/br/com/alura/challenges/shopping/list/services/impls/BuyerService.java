package br.com.alura.challenges.shopping.list.services.impls;

import br.com.alura.challenges.shopping.list.models.Buyer;
import br.com.alura.challenges.shopping.list.services.IBuyerService;

import java.math.BigDecimal;
import java.util.Scanner;

public class BuyerService implements IBuyerService {

	private final Scanner scanner;

	public BuyerService(final Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public Buyer input() {
		System.out.print("Digite o nome do comprador: ");
		final var name = scanner.nextLine();

		System.out.print("Digite o limite do cartão: ");
		final var cardLimit = BigDecimal.valueOf(scanner.nextDouble());

		final var buyer = new Buyer(name, cardLimit);
		System.out.println("Comprador Informado\n" + buyer);

		return buyer;
	}

}
