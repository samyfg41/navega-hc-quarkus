package br.com.navegahc.beans;

public class PerguntaPrincipal {

    private int id;
    private String pergunta;

    public PerguntaPrincipal() {
    }

    public PerguntaPrincipal(int id, String pergunta) {
        this.id = id;
        this.pergunta = pergunta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    @Override
    public String toString() {
        return "PerguntaPrincipal: " +
                "\nID: " + id +
                "\nPergunta:" + pergunta;
    }
}
