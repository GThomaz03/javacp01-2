package br.com.fiap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_FUNCIONARIO_SENIOR")
public class FuncionarioSenior extends Funcionario{
    @Column(name = "bonus")
    private double bonus = 500;

    public FuncionarioSenior(String nome, double horasTrabalhadas, double valorHora) {
        super(nome, horasTrabalhadas, valorHora);
    }

    public FuncionarioSenior(){

    }

    @Override
    public double calcularSalario() {
        int qntBonus = (int) (getHorasTrabalhadas()/15);
        return super.calcularSalario()+(this.bonus * qntBonus);
    }

    @Override
    public String imprimirInformacao() {
        return super.imprimirInformacao() +
                "bonus: " + bonus;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }
}
