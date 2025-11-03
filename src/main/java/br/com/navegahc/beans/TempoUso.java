package br.com.navegahc.beans;

public class TempoUso extends Feedback {

    private String tempo;
    private String frequencia;

    public TempoUso() {
    }

    public TempoUso(int id, String experiencia, String sugestao, String tempo, String frequencia) {
        super(id, experiencia, sugestao);
        this.tempo = tempo;
        this.frequencia = frequencia;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return  "\nTempo: " + tempo +
                "\nFrequência: " + frequencia +
                "\n" + super.toString();
    }

    @Override
    public String identificador() {
        return "Retorno da classe TempoUso";
    }

    // Lógica de negócio - Verifica se o usuario usa o sistema com frequencia
    public boolean usaFrequencia(){
        return frequencia.toLowerCase().contains("diário")
                || frequencia.toLowerCase().contains("todo dia");
    }
}
