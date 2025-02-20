package view;

import controller.CarrinhoController;
import model.FormaPagamento;
import model.Pedido;
import model.Produto;
import exception.EstoqueInsuficienteException;
import exception.ProdutoNaoEncontradoException;
import model.Usuario;

import java.util.Scanner;

public class CarrinhoView {
    private CarrinhoController carrinhoController;

    public CarrinhoView(CarrinhoController carrinhoController) {
        this.carrinhoController = carrinhoController;
    }

    public void exibirCarrinho() {
        for (Produto produto : carrinhoController.listarProdutosNoCarrinho()) {
            System.out.println("Produto: " + produto.getTitulo() + ", Preço: " + produto.getPreco());
        }
    }

    public void finalizarCompra(Usuario usuario) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---------- Finalização da Compra ----------");
        System.out.println("Escolha a forma de pagamento:");
        System.out.println("1 = PIX");
        System.out.println("2 = Crédito");
        System.out.println("3 = Débito");
        System.out.println("4 = Boleto");
        System.out.print("Opção: ");

        int opcaoPagamento;
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida! Digite um número correspondente à opção.");
            sc.next();
        }
        opcaoPagamento = sc.nextInt();
        sc.nextLine();

        FormaPagamento formaPagamento;
        switch (opcaoPagamento) {
            case 1:
                formaPagamento = FormaPagamento.PIX;
                break;
            case 2:
                formaPagamento = FormaPagamento.CREDITO;
                break;
            case 3:
                formaPagamento = FormaPagamento.DEBITO;
                break;
            case 4:
                formaPagamento = FormaPagamento.BOLETO;
                break;
            default:
                System.out.println("Opção inválida! Compra cancelada.");
                return;
        }

        System.out.print("Endereço de entrega: ");
        String enderecoEntrega = sc.nextLine();

        try {
            Pedido pedido = carrinhoController.finalizarCompra(usuario, formaPagamento, enderecoEntrega);
            System.out.println("Compra finalizada com sucesso!");
            System.out.println("ID do Pedido: " + pedido.getId());
            System.out.println("Forma de Pagamento: " + pedido.getFormaPagamento());
        } catch (EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarProdutoAoCarrinho(String idProduto) {
        try {
            carrinhoController.adicionarProdutoAoCarrinho(idProduto);
            System.out.println("Produto adicionado ao carrinho com sucesso!");
        } catch (ProdutoNaoEncontradoException | EstoqueInsuficienteException e) {
            System.out.println(e.getMessage());
        }
    }
}