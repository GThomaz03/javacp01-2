package br.com.fiap.entity;

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

    public Funcionario(String nome, double horasTrabalhadas, double valorHora) {
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

    public void setHorasTrabalhadas(double horasTrabalhadas) {
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

    public void setValorHora(double valorHora) {
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
