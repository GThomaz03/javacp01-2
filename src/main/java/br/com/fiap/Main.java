package br.com.fiap;


import br.com.fiap.dao.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoImpl;
import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.FuncionarioEstagiario;
import br.com.fiap.entity.FuncionarioSenior;
import br.com.fiap.exceptions.HorasTrabalhadasInvalidas;
import br.com.fiap.exceptions.ValorInvalido;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        FuncionarioDao funcionarioDao = new FuncionarioDaoImpl();

        Funcionario funcionario1 = null;
        FuncionarioSenior senior1 = null;
        FuncionarioEstagiario estagiario1 = null;

        try {
            funcionario1 = new Funcionario("Carlos", 40, 50);
        } catch (HorasTrabalhadasInvalidas | ValorInvalido e) {
            System.out.println("Erro ao criar funcionário: " + e.getMessage());
        }

        try {
            senior1 = new FuncionarioSenior("Ana", 45, 80, 500);
        } catch (HorasTrabalhadasInvalidas | ValorInvalido e) {
            System.out.println("Erro ao criar funcionário senior: " + e.getMessage());
        }

        try {
            estagiario1 = new FuncionarioEstagiario("Pedro", 30, 30, 0.8);
        } catch (HorasTrabalhadasInvalidas | ValorInvalido e) {
            System.out.println("Erro ao criar funcionário estagiário: " + e.getMessage());
        }

        if (funcionario1!=null) {
            funcionarioDao.salvar(funcionario1);

        }
        if(senior1!=null){
            funcionarioDao.salvar(senior1);
        }

        if(estagiario1!=null){
            funcionarioDao.salvar(estagiario1);
        }


        //listar funcionarios
        List<Funcionario> funcionarios = funcionarioDao.listarTodos();
        funcionarios.forEach(f -> System.out.println(f.imprimirInformacao()));

        // Atualizar -> apenas se funcionário1 foi instanciado corretamente
        if (funcionario1 != null) {
            funcionario1.setValorHora(60);
            funcionarioDao.atualizar(funcionario1);

        } else {
            System.out.println("Não foi possível atualizar funcionário1 pois ele não foi criado corretamente.");
        }

        // Deletar apenas se funcionário1 foi instanciado corretamente
        if (funcionario1 != null) {

            funcionarioDao.deletar(funcionario1.getId());
        } else {
            System.out.println("Não foi possível deletar funcionário1 pois ele não foi criado corretamente.");
        }
    }
}