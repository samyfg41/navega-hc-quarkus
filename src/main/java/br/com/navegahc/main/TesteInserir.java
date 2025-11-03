package br.com.navegahc.main;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import javax.swing.*;
import java.sql.SQLException;

public class TesteInserir {

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
            objUsuario.setNome(texto("Digite o nome do usuário:"));
            objUsuario.setIdade(inteiro("Digite a idade do usuário:"));

            //Atribuir a objDispositivoAcesso
            objDispositivoAcesso.setTipo(texto("Digite o tipo de dispositivo (ex: celular, desktop):"));
            objDispositivoAcesso.setSistema(texto("Digite o sistema operacional (ex: Android, iOS, Windows):"));

            //Atribuir o dispositivo ao usuário
            objUsuario.setDispositivoAcesso(objDispositivoAcesso);

            usuarioDAO = new UsuarioDAO();
            System.out.println(usuarioDAO.inserir(objUsuario));

            //TempoUsoDAO
            objTempoUso.setId(objUsuario.getId());
            objTempoUso.setExperiencia(texto("Como foi sua experiência? (ex: Boa, Ruim, Regular):"));
            objTempoUso.setSugestao(texto("Alguma sugestão de melhoria?"));
            objTempoUso.setTempo(texto("Há quanto tempo usa? (ex: 1 ano, 6 meses):"));
            objTempoUso.setFrequencia(texto("Com que frequência usa? (ex: diária, semanal):"));

            tempoUsoDAO = new TempoUsoDAO();
            System.out.println(tempoUsoDAO.inserir(objTempoUso));

            //PerguntaPrincipalDAO
            objPerguntaPrincipal.setId(objUsuario.getId());
            objPerguntaPrincipal.setPergunta(texto("Qual sua principal dúvida ou pergunta?"));

            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            System.out.println(perguntaPrincipalDAO.inserir(objPerguntaPrincipal));

            //DificuldadeDAO
            objDificuldade.setId(objUsuario.getId());
            objDificuldade.setTipo(texto("Tipo de dificuldade (ex: login, navegação, carregamento):"));
            objDificuldade.setDescricao(texto("Descreva a dificuldade:"));

            dificuldadeDAO = new DificuldadeDAO();
            System.out.println(dificuldadeDAO.inserir(objDificuldade));

            //AvaliacaoDAO
            objAvaliacao.setId(objUsuario.getId());
            objAvaliacao.setAvaliar(real("Avalie de 0 a 10:"));

            avaliacaoDAO = new AvaliacaoDAO();
            System.out.println(avaliacaoDAO.inserir(objAvaliacao));

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


