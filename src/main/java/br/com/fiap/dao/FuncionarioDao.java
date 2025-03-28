package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;

import java.util.List;

public interface FuncionarioDao {
    void salvar(Funcionario funcionario);
    Funcionario buscarPorId(Long id);
    List<Funcionario> listarTodos();
    void atualizar(Funcionario funcionario);
    void deletar(Long id);
}
