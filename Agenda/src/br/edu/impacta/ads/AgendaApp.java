package br.edu.impacta.ads;

import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;

public class AgendaApp {
	private static Scanner entrada = new Scanner(System.in);

	//private static List<Contato> contatos = new ArrayList<>();
	private static IContatoDao dao = new ContatoDaoArquivo("contatos.txt");

	public static void main(String[] args) {
		boolean sair = false;
		while (!sair) {
			int opcao = apresentarMenuPrincipal();
			switch(opcao) {
			case 1: inserirContato(); break;
			case 2: buscarContato(); break;
			case 3: sair = true; break;
			default: out.println("ERRO: opcao invalida!");
			}
		}
		out.println("\nFim do programa!");
	}

	private static int apresentarMenuPrincipal() {
		boolean inteiro = false;
		int opcao = 0;
		while(!inteiro) {
			out.println("\nAGENDA TELEFONICA!");
			out.println("(1) Inserir");
			out.println("(2) Buscar");
			out.println("(3) Sair");
			out.print("Escolha uma opcao: ");
			String s = entrada.nextLine();
			try {
				opcao = Integer.parseInt(s);
				inteiro = true;
			} catch(Exception e) {
				out.println("ERRO: opcao deve ser um valor inteiro!");
			}
		}
		return opcao;
	}

	private static void inserirContato() {
		out.println("\nINSERCAO DE NOVO CONTATO:");
		String nome = lerNome();
		String telefone = lerTelefone();
		Contato c = new Contato(nome, telefone);

		// if (contatos.contains(c)) {
		if (dao.existe(c)) {

			out.println("Este contato ja esta cadastrado!");
		}
		else {

			// contatos.add(c);
			dao.inserir(c);

			out.println("Contato inserido!");
		}
	}

	private static String lerNome() {
		boolean valido = false;
		String nome = "";
		while (!valido) {
			out.print("Nome: ");
			nome = entrada.nextLine();
			if (nome.length()==0 || nome.length()>200) {
				out.println("ERRO: nome de tamanho invalido!");
			} else { valido = true; }
		}
		return nome;
	}

	private static String lerTelefone() {
		boolean valido = false;
		String telefone = "";
		while (!valido) {
			out.print("Telefone: ");
			telefone = entrada.nextLine();
			if (telefone.length()==0 || telefone.length()>25) {
				out.println("ERRO: telefone de tamanho invalido!");
			} else { valido = true; }
		}
		return telefone;
	}

	private static void buscarContato() {
		out.println("\nBUSCA DE CONTATOS:");
		String nome = lerNome();

		List<Contato> resultado = dao.buscar(nome);


		if (resultado.size() == 0) {
			out.println("Nao ha contato com este nome!");
		} else {
			out.println("\nResultado da busca:");
			for (Contato c: resultado) {
				out.println(c);
			}
		}
	}

}
