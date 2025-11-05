package br.com.navegahc.main;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TesteSelecionar {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            // UsuarioDAO
            usuarioDAO = new UsuarioDAO();
            List<Usuario> listaUsuarios = usuarioDAO.selecionar();

            if (listaUsuarios != null) {
                for (Usuario usuario : listaUsuarios) {
                    System.out.println("\nFORMULÁRIO DE FEEDBACK: ");
                    System.out.println("\nINFORMAÇÃO USUÁRIO: ");
                    System.out.println("ID: " + usuario.getId());
                    System.out.println("Nome: " + usuario.getNome());
                    System.out.println("Idade: " + usuario.getIdade());

                    if (usuario.getDispositivoAcesso() != null) {
                        System.out.println("\nINFORMAÇÃO DISPOSITIVO DE ACESSO: ");
                        System.out.println("Tipo: " + usuario.getDispositivoAcesso().getTipo());
                        System.out.println("Sistema: " + usuario.getDispositivoAcesso().getSistema());
                    }
                }
            }

            // TempoUsoDAO
            tempoUsoDAO = new TempoUsoDAO();
            List<TempoUso> listaTempoUso = tempoUsoDAO.selecionar();

            if (listaTempoUso != null) {
                for (TempoUso tempoUso : listaTempoUso) {
                    System.out.println("\nINFORMAÇÃO TEMPO DE USO: ");
                    System.out.println("Experiência: " + tempoUso.getExperiencia());
                    System.out.println("Sugestão: " + tempoUso.getSugestao());
                    System.out.println("Tempo: " + tempoUso.getTempo());
                    System.out.println("Frequência: " + tempoUso.getFrequencia());
                }
            }

            // PerguntaPrincipalDAO
            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            List<PerguntaPrincipal> listaPerguntas = perguntaPrincipalDAO.selecionar();

            if (listaPerguntas != null) {
                for (PerguntaPrincipal pergunta : listaPerguntas) {
                    System.out.println("\nPERGUNTA PRINCIPAL: ");
                    System.out.println("Pergunta: " + pergunta.getPergunta());
                }
            }

            // DificuldadeDAO
            dificuldadeDAO = new DificuldadeDAO();
            List<Dificuldade> listaDificuldades = dificuldadeDAO.selecionar();

            if (listaDificuldades != null) {
                for (Dificuldade dificuldade : listaDificuldades) {
                    System.out.println("\nDIFICULDADE: ");
                    System.out.println("Tipo: " + dificuldade.getTipo());
                    System.out.println("Descrição: " + dificuldade.getDescricao());
                }
            }

            // AvaliacaoDAO
            avaliacaoDAO = new AvaliacaoDAO();
            List<Avaliacao> listaAvaliacoes = avaliacaoDAO.selecionar();

            if (listaAvaliacoes != null) {
                for (Avaliacao avaliacao : listaAvaliacoes) {
                    System.out.println("\nAVALIAÇÃO: ");
                    System.out.println("Nota: " + avaliacao.getAvaliar());
                }
            }

        } finally {
            // fechando as conexões
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


