package br.com.ordensservico.aberturaordensservico.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "equipamentos")
public class Equipamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nome;
    private int numeroPatrimonio;

    public Equipamento() {

    }

    public Equipamento(String nome, int numeroPatrimonio) {
        this.nome = nome;
        this.numeroPatrimonio = numeroPatrimonio;
    }

    

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int numeroPatrimonio() {
        return numeroPatrimonio;
    }

    public void setNumeroPatrimonio(){
        this.numeroPatrimonio = numeroPatrimonio;
    }
}
