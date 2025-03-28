package br.com.fiap;


import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioEstagiario;
import br.com.fiap.entity.FuncionarioSenior;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        Funcionario funcionario1 = new Funcionario("Carlos", 40, 50);
        FuncionarioSenior senior1 = new FuncionarioSenior("Ana", 45, 80);
        FuncionarioEstagiario estagiario1 = new FuncionarioEstagiario("Pedro", 30, 30);

        funcionarioDao.salvar(funcionario1);
        funcionarioDao.salvar(senior1);
        funcionarioDao.salvar(estagiario1);

        //listar funcionarios
        List<Funcionario> funcionarios = funcionarioDao.listarTodos();
        funcionarios.forEach(f -> System.out.println(f.imprimirInformacao()));

//        // Atualizar um funcionário
        funcionario1.setValorHora(60);
        funcionarioDao.atualizar(funcionario1);
//
//        // Deletar um funcionário
        funcionarioDao.deletar(funcionario1.getId());
    }
}