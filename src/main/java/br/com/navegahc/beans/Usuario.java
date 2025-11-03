package br.com.navegahc.beans;

public class Usuario {

    private int id;
    private String nome;
    private int idade;
    private DispositivoAcesso dispositivoAcesso;

    public Usuario() {
    }

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DispositivoAcesso getDispositivoAcesso() {
        return dispositivoAcesso;
    }

    public void setDispositivoAcesso(DispositivoAcesso dispositivoAcesso) {
        this.dispositivoAcesso = dispositivoAcesso;
    }

    @Override
    public String toString() {
        return "\nFORMULÁRIO DE FEEDBACK: " +
                "\n\nINFORMAÇÃO USUÁRIO: " +
                "\nID: " + id +
                "\nNome: " + nome +
                "\nIdade: " + idade +
                "\n\nINFORMAÇÃO DISPOSITIVO DE ACESSO: " + dispositivoAcesso;
    }

    // Lógica de negócio - Verifica a idade
    public boolean maiorDeIdade(){
        return this.idade >= 18;
    }
}
