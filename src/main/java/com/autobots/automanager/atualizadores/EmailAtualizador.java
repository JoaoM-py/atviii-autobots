package com.autobots.automanager.atualizadores;

import java.util.List;

import com.autobots.automanager.entitades.Email;

public class EmailAtualizador {
	private StringVerificador verificador = new StringVerificador();

	public void atualizar(Email email, Email atualizacao) {
		if (atualizacao != null) {

			if (!verificador.verificar(atualizacao.getEndereco())) {
				email.setEndereco(atualizacao.getEndereco());
			}
		}
	}

	public void atualizar(List<Email> emails, List<Email> atualizacoes) {
		for (Email atualizacao : atualizacoes) {
			for (Email email : emails) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == email.getId()) {
						atualizar(email, atualizacao);
					}
				}
			}
		}
	}
}
