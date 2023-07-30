package br.com.projeto.api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    
    //Atributo
    @Id //faz com q codigo sera primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera valores crescentes a partir de 1
    private int codigo;
    private String nome;
    private int idade;

    //Get e Set
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    
}
