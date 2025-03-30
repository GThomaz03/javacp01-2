package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;

import java.sql.SQLException;
import java.util.List;

public interface FuncionarioDao {
    void salvar(Funcionario funcionario) throws SQLException;
    Funcionario buscarPorId(Long id) throws SQLException;
    List<Funcionario> listarTodos() throws Exception;
    void atualizar(Funcionario funcionario) throws SQLException;
    void deletar(Long id) throws SQLException;
}
