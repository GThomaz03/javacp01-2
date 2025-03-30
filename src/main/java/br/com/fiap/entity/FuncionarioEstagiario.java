package br.com.fiap.entity;

import br.com.fiap.exceptions.HorasTrabalhadasInvalidas;
import br.com.fiap.exceptions.ValorInvalido;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_FUNCIONARIO_ESTAGIARIO")
public class FuncionarioEstagiario extends Funcionario{
    @Column(name = "desconto")
    private double desconto = 0.8;

    public FuncionarioEstagiario(String nome, double horasTrabalhadas, double valorHora, double desconto) throws ValorInvalido, HorasTrabalhadasInvalidas {
        super(nome, horasTrabalhadas, valorHora);
        //desconto deve ser entre 0 e 1
        if (desconto<0 || desconto>1) {
            throw new ValorInvalido("Valor do desconto deve ser entre 0 e 1.");
        }
        this.desconto = desconto;
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
    public void setDesconto(double desconto) throws ValorInvalido {
        if(desconto<0 || desconto>1){
            throw new ValorInvalido("Valor do desconto deve ser entre 0 e 1.");
        }
        this.desconto = desconto;
    }
}
