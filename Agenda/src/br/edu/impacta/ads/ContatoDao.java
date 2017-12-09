package br.edu.impacta.ads;

import java.util.ArrayList;
import java.util.List;

public class ContatoDao implements IContatoDao {

	private List<Contato> contatos = new ArrayList<>();

	@Override
	public List<Contato> buscar(String nome) {
		List<Contato> resultado = new ArrayList<>();
		for (Contato c : contatos) {
			if (nome.equals(c.getNome())) {
				resultado.add(c);
			}
		}
		return resultado;
	}

	@Override
	public void inserir(Contato c) {
		contatos.add(c);
	}

	@Override
	public boolean existe(Contato c) {
		return contatos.contains(c);
	}

}
