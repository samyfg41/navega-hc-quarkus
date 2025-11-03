package br.com.navegahc.beans;

public class Avaliacao {

    private int id;
    private double avaliar;

    public Avaliacao() {
    }

    public Avaliacao(int id, double avaliar) {
        this.id = id;
        this.avaliar = avaliar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAvaliar() {
        return avaliar;
    }

    public void setAvaliar(double avaliar) {
        this.avaliar = avaliar;
    }

    @Override
    public String toString() {
        return "\n\nINFORMAÇÕES DE AVALIAÇÃO:" +
                "\nID: " + id +
                "\nAvaliação: " + avaliar;
    }

    // Lógica de negócio - Classificando a avaliação
    public String getNota(){
        if (avaliar >= 4.5) return "Exelente";
        if (avaliar >= 3.0) return "Bom";
        if (avaliar >= 2.0) return "Regular";
        return "Ruim";
    }
}
