package com.autobots.automanager.LinkAdders;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.VeiculoControle;
import com.autobots.automanager.entitades.Veiculo;


@Component
public class VeiculoLink implements LinkAdder<Veiculo> {

	@Override
	public void addLink(List<Veiculo> lista) {
		for (Veiculo veiculo : lista) {
			long id = veiculo.getId();
			Link linkProprio = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(VeiculoControle.class)
							.obterVeiculo(id))
					.withSelfRel();
			veiculo.add(linkProprio);
		}
	}

	@Override
	public void addLink(Veiculo objeto) {
		Link linkProprio = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.obterVeiculos())
				.withRel("veiculos");
		objeto.add(linkProprio);
	}
}
