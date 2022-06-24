package com.autobots.automanager.selectors;

import java.util.List;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entitades.Documento;

@Component
public class DocumentoSelecionador {
	public Documento selecionar(List<Documento> documentos, long id) {
		Documento selecionado = null;
		for (Documento documento : documentos) {
			if (documento.getId() == id) {
				selecionado = documento;
			}
		}
		return selecionado;
	}
}
