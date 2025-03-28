package br.com.fiap.dao;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.util.GeradorSQL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FuncionarioDaoImpl implements FuncionarioDao {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
    private EntityManager em = emf.createEntityManager();

    @Override
    public void salvar(Funcionario funcionario) {
        boolean transacaoCometida = false;
        try {
            em.getTransaction().begin();
            em.persist(funcionario);
            em.getTransaction().commit();
            transacaoCometida = true;

            String sqlInsert = GeradorSQL.gerarInsert(funcionario);
            System.out.println("SQL Gerado (Insert): " + sqlInsert);
        } catch (Exception e) {
            if (!transacaoCometida) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        Funcionario funcionario = em.find(Funcionario.class, id);

        String sqlSelect = GeradorSQL.gerarSelectPorId(Funcionario.class, id);
        System.out.println("SQL Gerado (Select por ID): " + sqlSelect);

        return funcionario;
    }

    @Override
    public List<Funcionario> listarTodos() {
        List<Funcionario> funcionarios = em.createQuery("SELECT f FROM Funcionario f", Funcionario.class).getResultList();

        String sqlSelectAll = GeradorSQL.gerarSelect(Funcionario.class);
        System.out.println("SQL Gerado (Select *): " + sqlSelectAll);

        return funcionarios;
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        try {
            em.getTransaction().begin();
            em.merge(funcionario);
            em.getTransaction().commit();

            String sqlUpdate = GeradorSQL.gerarUpdate(funcionario, funcionario.getId());
            System.out.println("SQL Gerado (Update): " + sqlUpdate);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Long id) {
        try {
            em.getTransaction().begin();
            Funcionario funcionario = em.find(Funcionario.class, id);
            if (funcionario != null) {
                em.remove(funcionario);
            }
            em.getTransaction().commit();

            String sqlDelete = GeradorSQL.gerarDelete(Funcionario.class, id);
            System.out.println("SQL Gerado (Delete): " + sqlDelete);
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
