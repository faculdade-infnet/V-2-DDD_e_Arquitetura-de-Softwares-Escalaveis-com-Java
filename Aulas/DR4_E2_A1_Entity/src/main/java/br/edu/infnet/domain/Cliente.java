package br.edu.infnet.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private Email email;

    @Column(name = "fone")
    private Telefone fone;

    public Cliente(String nome, Email email, Telefone fone) {
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public Cliente() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getFone() {
        return fone;
    }

    public void setFone(Telefone fone) {
        this.fone = fone;
    }
}
