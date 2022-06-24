package com.autobots.automanager.cadastro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class EmpresaCadastro {
  @Autowired
  RepositorioEmpresa repositorioEmpresa;
  @Autowired
  RepositorioServico repositorioServico;
  @Autowired
  RepositorioUsuario repositorioUsuario;
  @Autowired
  RepositorioVeiculo repositorioVeiculo;
  @Autowired
  RepositorioMercadoria repositorioMercadoria;
  @Autowired
  RepositorioVenda repositorioVenda;

  public Empresa cadastro(Empresa empresa) {
    empresa.setCadastro(new Date());
    Empresa empresaCriada = repositorioEmpresa.save(empresa);
    return empresaCriada;
  }

  public Empresa cadastroServico(Empresa empresa, Servico servico) {

    Set<Servico> listaServicos = empresa.getServicos();
    listaServicos.add(servico);
    empresa.setServicos(listaServicos);
    repositorioServico.save(servico);
    return repositorioEmpresa.save(empresa);
  }

  public Empresa cadastroFuncionario(Empresa empresa, Usuario funcionario) {
    Set<Usuario> listaUsuario = empresa.getUsuarios();

    listaUsuario.add(funcionario);
    empresa.setUsuarios(listaUsuario);
    return repositorioEmpresa.save(empresa);
  }

  public Venda cadastrarVenda(Empresa empresa, Venda venda) {

    Optional<Usuario> usuarioVenda = repositorioUsuario.findById(venda.getCliente().getId());
    if (usuarioVenda.isEmpty()) {
      return null;
    }

    Optional<Usuario> funcionarioVenda = repositorioUsuario.findById(venda.getFuncionario().getId());
    if (funcionarioVenda.isEmpty()) {
      return null;
    }

    Optional<Veiculo> veiculoVenda = repositorioVeiculo.findById(venda.getVeiculo().getId());
    if (veiculoVenda.isEmpty()) {
      return null;
    }

    List<Mercadoria> Mercadorias = new ArrayList<Mercadoria>();
    for (Mercadoria itemMercadoria : venda.getMercadorias()) {
      Mercadoria mercadoria = repositorioMercadoria.getById(itemMercadoria.getId());
      Mercadorias.add(mercadoria);
    }

    List<Servico> Servicos = new ArrayList<Servico>();
    for (Servico itemServico : venda.getServicos()) {
      Servico servico = repositorioServico.getById(itemServico.getId());
      Servicos.add(servico);
    }


    Venda vendaJson = new Venda();
    vendaJson.setFuncionario(funcionarioVenda.get());

    vendaJson.setVeiculo(veiculoVenda.get());

    vendaJson.setMercadorias(Mercadorias);
    vendaJson.setServicos(Servicos);

    vendaJson.setCadastro(new Date());

    Set<Venda> empresaVendas = empresa.getVendas();

    empresaVendas.add(venda);

    veiculoVenda.get().getVendas().add(vendaJson);
    Venda vendaCriada = repositorioVenda.save(vendaJson);
    repositorioEmpresa.save(empresa);
    return vendaCriada;
  }
}
