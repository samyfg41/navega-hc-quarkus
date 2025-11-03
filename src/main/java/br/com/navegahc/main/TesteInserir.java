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

    //int
    static Integer inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    //double
    static Double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            // ========== 1. USUÁRIO  ==========
            Usuario objUsuario = new Usuario();
            usuarioDAO = new UsuarioDAO();

            objUsuario.setNome(texto("Digite o nome do usuário"));
            objUsuario.setIdade(inteiro("Digite a idade do usuário"));

            DispositivoAcesso objDispositivoAcesso = new DispositivoAcesso();
            objDispositivoAcesso.setTipo(texto("Tipo de dispositivo (ex: celular, computador, tablet):"));
            objDispositivoAcesso.setSistema(texto("Sistema do dispositivo (ex: Android, iOS, Windows):"));

            objUsuario.setDispositivoAcesso(objDispositivoAcesso);

            System.out.println(usuarioDAO.inserir(objUsuario));


            int feedbackId = objUsuario.getId();
            System.out.println("ID do feedback gerado: " + feedbackId);

            // ========== 2. TEMPO DE USO  ==========
            TempoUso objTempoUso = new TempoUso();
            tempoUsoDAO = new TempoUsoDAO();

            objTempoUso.setExperiencia(texto("Como foi sua experiência ao utilizar o site?"));
            objTempoUso.setSugestao(texto("Nos ajude a melhorar, deixe sua sugestão:"));
            objTempoUso.setTempo(texto("Há quanto tempo você conhece ou usa o site Navega HC?"));
            objTempoUso.setFrequencia(texto("Com que frequência você utiliza o site?"));

            System.out.println(tempoUsoDAO.atualizar(feedbackId, objTempoUso));

            // ========== 3. PERGUNTA PRINCIPAL (UPDATE) ==========
            PerguntaPrincipal objPerguntaPrincipal = new PerguntaPrincipal();
            perguntaPrincipalDAO = new PerguntaPrincipalDAO();

            objPerguntaPrincipal.setPergunta(texto("Você gostou do site NavegaHC?"));

            System.out.println(perguntaPrincipalDAO.atualizar(feedbackId, objPerguntaPrincipal));

            // ========== 4. DIFICULDADE (UPDATE) ==========
            Dificuldade objDificuldade = new Dificuldade();
            dificuldadeDAO = new DificuldadeDAO();

            objDificuldade.setTipo(texto("Tipo de dificuldade (ex: login, consulta, ajuda):"));
            objDificuldade.setDescricao(texto("Descreva como foi sua dificuldade:"));

            System.out.println(dificuldadeDAO.atualizar(feedbackId, objDificuldade));

            // ========== 5. AVALIAÇÃO (UPDATE) ==========
            Avaliacao objAvaliacao = new Avaliacao();
            avaliacaoDAO = new AvaliacaoDAO();

            objAvaliacao.setAvaliar(real("Deixe sua avaliação (1 a 5) de como foi sua visita:"));

            System.out.println(avaliacaoDAO.atualizar(feedbackId, objAvaliacao));

            System.out.println("\nFEEDBACK COMPLETO CADASTRADO COM SUCESSO!");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERRO: " + e.getMessage());

        } finally {
            // Fecha todas as conexões
            try {
                if (usuarioDAO != null) usuarioDAO.fecharConexao();
                if (tempoUsoDAO != null) tempoUsoDAO.fecharConexao();
                if (perguntaPrincipalDAO != null) perguntaPrincipalDAO.fecharConexao();
                if (dificuldadeDAO != null) dificuldadeDAO.fecharConexao();
                if (avaliacaoDAO != null) avaliacaoDAO.fecharConexao();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}

