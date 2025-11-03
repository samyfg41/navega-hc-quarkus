package br.com.navegahc.main;

import br.com.navegahc.dao.*;

import javax.swing.*;
import java.sql.SQLException;

public class TesteDeletar {

    //int
    static Integer inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            int idUsuario = inteiro("[translate:Informe o ID do usuário a ser deletado:]");
            usuarioDAO = new UsuarioDAO();
            System.out.println(usuarioDAO.deletar(idUsuario));

            int idTempoUso = inteiro("[translate:Informe o ID da informação TempoUso a ser deletada:]");
            tempoUsoDAO = new TempoUsoDAO();
            System.out.println(tempoUsoDAO.deletar(idTempoUso));

            int idPerguntaPrincipal = inteiro("[translate:Informe o ID da informação PerguntaPrincipal a ser deletada:]");
            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            System.out.println(perguntaPrincipalDAO.deletar(idPerguntaPrincipal));

            int idDificuldade = inteiro("[translate:Informe o ID da informação Dificuldade a ser deletada:]");
            dificuldadeDAO = new DificuldadeDAO();
            System.out.println(dificuldadeDAO.deletar(idDificuldade));

            int idAvaliacao = inteiro("[translate:Informe o ID da Avaliação a ser deletada:]");
            avaliacaoDAO = new AvaliacaoDAO();
            System.out.println(avaliacaoDAO.deletar(idAvaliacao));

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("[translate:Erro ao deletar:] " + e.getMessage());
        } finally {
            try {
                if (usuarioDAO != null) usuarioDAO.fecharConexao();
                if (tempoUsoDAO != null) tempoUsoDAO.fecharConexao();
                if (perguntaPrincipalDAO != null) perguntaPrincipalDAO.fecharConexao();
                if (dificuldadeDAO != null) dificuldadeDAO.fecharConexao();
                if (avaliacaoDAO != null) avaliacaoDAO.fecharConexao();
            } catch (SQLException e) {
                System.out.println("[translate:Erro ao fechar conexão:] " + e.getMessage());
            }
        }
    }
}
