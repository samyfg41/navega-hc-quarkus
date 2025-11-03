package br.com.navegahc.beans;

public class Dificuldade {

    private int id;
    private String tipo;
    private String descricao;

    public Dificuldade() {
    }

    public Dificuldade(int id, String tipo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Dificuldade: " +
                "\nID: " + id +
                "\nTipo: " + tipo +
                "\nDescrição: " + descricao;
    }
}
