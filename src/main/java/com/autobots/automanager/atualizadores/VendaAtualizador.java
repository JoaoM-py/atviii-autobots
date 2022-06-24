package com.autobots.automanager.atualizadores;

import com.autobots.automanager.entitades.Venda;

public class VendaAtualizador {
	private StringVerificador verificador = new StringVerificador();
	private MercadoriaAtualizador mercadoriaoAtualizador = new MercadoriaAtualizador();
	private ServicoAtualizador servicoAtualizador = new ServicoAtualizador();
	private VeiculoAtualizador veiculoAtualizador = new VeiculoAtualizador();
	private UsuarioAtualizador usuarioAtualizador = new UsuarioAtualizador();





	private void atualizarDados(Venda venda, Venda atualizacao) {
		if (!verificador.verificar(atualizacao.getIdentificacao())) {
			venda.setIdentificacao(atualizacao.getIdentificacao());
		}
		if (!(atualizacao.getCadastro() == null)) {
			venda.setCadastro(atualizacao.getCadastro());
		}
	}

	public void atualizar(Venda venda, Venda atualizacao) {
		atualizarDados(venda, atualizacao);
		mercadoriaoAtualizador.atualizar(venda.getMercadorias(), atualizacao.getMercadorias());
		servicoAtualizador.atualizar(venda.getServicos(), atualizacao.getServicos());
		veiculoAtualizador.atualizar(venda.getVeiculo(), atualizacao.getVeiculo());
		usuarioAtualizador.atualizar(venda.getFuncionario(), atualizacao.getFuncionario());
		usuarioAtualizador.atualizar(venda.getCliente(), atualizacao.getCliente());
	}
}