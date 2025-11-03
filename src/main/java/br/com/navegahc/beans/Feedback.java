package br.com.navegahc.beans;

public abstract class Feedback {

    private int id;
    private String experiencia;
    private String sugestao;

    public Feedback() {
    }

    public Feedback(int id, String experiencia, String sugestao) {
        this.id = id;
        this.experiencia = experiencia;
        this.sugestao = sugestao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    @Override
    public String toString() {
        return "\n\nINFORMAÇÕES FEEDBACK:" +
                "\nID: " + id +
                "\nExperiência: " + experiencia +
                "\nSugestão: " + sugestao;
    }

    public abstract String identificador();

    // Lógica de negócio - Verificar se o usuário deixou uma sugestão
    public boolean temSugestao(){
        return sugestao != null && !sugestao.trim().isEmpty();
    }
}
