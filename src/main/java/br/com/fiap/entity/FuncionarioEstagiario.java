package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_FUNCIONARIO_ESTAGIARIO")
public class FuncionarioEstagiario extends Funcionario{
    @Column(name = "desconto")
    private double desconto = 0.8;

    public FuncionarioEstagiario(String nome, double horasTrabalhadas, double valorHora) {
        super(nome, horasTrabalhadas, valorHora);
    }

    public FuncionarioEstagiario(){

    }

    @Override
    public double calcularSalario() {
        return super.calcularSalario()*desconto;
    }

    @Override
    public String imprimirInformacao() {
        return super.imprimirInformacao() +
                    "desconto: " + desconto;
    }

    public double getDesconto() { return desconto; }
    public void setDesconto(double desconto) { this.desconto = desconto; }
}
