package br.com.fiap.entity;

import br.com.fiap.exceptions.HorasTrabalhadasInvalidas;
import br.com.fiap.exceptions.ValorInvalido;

import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TAB_FUNCIONARIOS")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @Column(name = "nome_funcionario")
    private String nome;

    @Column(name = "horas_trabalhadas")
    private double horasTrabalhadas;

    @Column(name = "valor_hora")
    private double valorHora;

    public Funcionario(String nome, double horasTrabalhadas, double valorHora) throws HorasTrabalhadasInvalidas, ValorInvalido {

        if (horasTrabalhadas < 0) {
            throw new HorasTrabalhadasInvalidas("Horas trabalhadas não pode ser menor que zero.");
        }

        if (valorHora <= 0) {
            throw new ValorInvalido("Valor da hora deve ser maior do que zero.");
        }

        this.nome = nome;
        this.horasTrabalhadas = horasTrabalhadas;
        this.valorHora = valorHora;
    }

    public Funcionario(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) throws HorasTrabalhadasInvalidas{
        if(horasTrabalhadas<0){
            throw new HorasTrabalhadasInvalidas("Horas trabalhadas não pode ser menor que zero.");
        }

        this.horasTrabalhadas = horasTrabalhadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) throws ValorInvalido{

        if (valorHora<=0) {
            throw new ValorInvalido("Valor da hora deve ser maior do que zero.");
        }

        this.valorHora = valorHora;
    }

    public double calcularSalario(){
        return this.horasTrabalhadas*this.valorHora;
    }

    public String imprimirInformacao(){
        return
                "nome: " + nome +
                ", horasTrabalhadas: " + horasTrabalhadas +
                ", valorHora: " + valorHora +
                ", salario: " + calcularSalario();
    }

}
