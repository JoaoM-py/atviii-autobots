package com.autobots.automanager.entitades;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.autobots.automanager.enumeracoes.PerfilUsuario;

import org.springframework.hateoas.RepresentationModel;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Usuario extends RepresentationModel<Usuario> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column
	private String nomeSocial;
	@ElementCollection(fetch = FetchType.LAZY )
	private List<PerfilUsuario> perfis = new ArrayList<>();
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Telefone> telefones = new ArrayList<>();
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Documento> documentos = new ArrayList<>();
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Email> emails = new ArrayList<>();
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	private List<Credencial> credenciais = new ArrayList<>();
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	private List<Mercadoria> mercadorias = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY , cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Venda> vendas = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY , cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Veiculo> veiculos = new ArrayList<>();
}