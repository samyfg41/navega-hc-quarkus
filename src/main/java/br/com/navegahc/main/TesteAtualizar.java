package br.com.navegahc.main;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import javax.swing.*;
import java.sql.SQLException;

public class TesteAtualizar {

    //String
    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    //Int
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    //Double
    static double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            //Instanciar objetos
            Usuario objUsuario = new Usuario();
            DispositivoAcesso objDispositivoAcesso = new DispositivoAcesso();
            TempoUso objTempoUso = new TempoUso();
            PerguntaPrincipal objPerguntaPrincipal = new PerguntaPrincipal();
            Dificuldade objDificuldade = new Dificuldade();
            Avaliacao objAvaliacao = new Avaliacao();

            //Atribuir a objUsuario
            objUsuario.setId(inteiro("Digite o ID do usuário para atualizar:"));
            objUsuario.setNome(texto("Digite o novo nome do usuário:"));
            objUsuario.setIdade(inteiro("Digite a nova idade do usuário:"));

            //Atribuir a objDispositivoAcesso
            objDispositivoAcesso.setTipo(texto("Digite o novo tipo de dispositivo:"));
            objDispositivoAcesso.setSistema(texto("Digite o novo sistema operacional:"));

            //Atribuir o dispositivo ao usuário
            objUsuario.setDispositivoAcesso(objDispositivoAcesso);

            usuarioDAO = new UsuarioDAO();
            System.out.println(usuarioDAO.atualizar(objUsuario));

            //TempoUsoDAO
            objTempoUso.setId(objUsuario.getId());
            objTempoUso.setExperiencia(texto("Digite a nova experiência:"));
            objTempoUso.setSugestao(texto("Digite a nova sugestão:"));
            objTempoUso.setTempo(texto("Digite o novo tempo de uso:"));
            objTempoUso.setFrequencia(texto("Digite a nova frequência:"));

            tempoUsoDAO = new TempoUsoDAO();
            System.out.println(tempoUsoDAO.atualizar(objTempoUso)); // ✅ CORRIGIDO

            //PerguntaPrincipalDAO
            objPerguntaPrincipal.setId(objUsuario.getId());
            objPerguntaPrincipal.setPergunta(texto("Digite a nova pergunta:"));

            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            System.out.println(perguntaPrincipalDAO.atualizar(objPerguntaPrincipal));

            //DificuldadeDAO
            objDificuldade.setId(objUsuario.getId());
            objDificuldade.setTipo(texto("Digite o novo tipo de dificuldade:"));
            objDificuldade.setDescricao(texto("Digite a nova descrição da dificuldade:"));

            dificuldadeDAO = new DificuldadeDAO();
            System.out.println(dificuldadeDAO.atualizar(objDificuldade));

            //AvaliacaoDAO
            objAvaliacao.setId(objUsuario.getId());
            objAvaliacao.setAvaliar(real("Digite a nova avaliação:"));

            avaliacaoDAO = new AvaliacaoDAO();
            System.out.println(avaliacaoDAO.atualizar(objAvaliacao));

        } finally {
            // ✅ FECHA AS CONEXÕES
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (tempoUsoDAO != null) {
                try { tempoUsoDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (perguntaPrincipalDAO != null) {
                try { perguntaPrincipalDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (dificuldadeDAO != null) {
                try { dificuldadeDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (avaliacaoDAO != null) {
                try { avaliacaoDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }
}


