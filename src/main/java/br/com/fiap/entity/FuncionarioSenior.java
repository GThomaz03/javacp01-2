package br.com.fiap.entity;

import br.com.fiap.exceptions.HorasTrabalhadasInvalidas;
import br.com.fiap.exceptions.ValorInvalido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_FUNCIONARIO_SENIOR")
public class FuncionarioSenior extends Funcionario{
    @Column(name = "bonus")
    private double bonus = 500;

    public FuncionarioSenior(String nome, double horasTrabalhadas, double valorHora, double bonus) throws ValorInvalido, HorasTrabalhadasInvalidas {
        super(nome, horasTrabalhadas, valorHora);

        if(bonus<0){
            throw new ValorInvalido("Valor do bônus não deve ser menor que zero.");
        }
        this.bonus = bonus;
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
    public void setBonus(double bonus) throws ValorInvalido {
        if(bonus<0){
            throw new ValorInvalido("Valor do bônus não deve ser menor que zero.");
        }
        this.bonus = bonus;
    }
}
