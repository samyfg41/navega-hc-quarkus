package br.com.navegahc.beans;

public class DispositivoAcesso {

    private String tipo;
    private String sistema;

    public DispositivoAcesso() {
    }

    public DispositivoAcesso(String tipo, String sistema) {
        this.tipo = tipo;
        this.sistema = sistema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        return  "\nTipo: " + tipo +
                "\nSistema: " + sistema;
    }
}
