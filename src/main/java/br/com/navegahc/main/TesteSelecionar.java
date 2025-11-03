package br.com.navegahc.main;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import java.sql.SQLException;
import java.util.List;

public class TesteSelecionar {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> listaUsuarios = usuarioDAO.selecionar();

        if(listaUsuarios != null){
            System.out.println("===== [translate:INFORMAÇÕES USUÁRIO] =====");
            System.out.printf("%-5s %-20s %-10s %-15s %-15s%n", "[translate:ID]", "[translate:NOME]", "[translate:IDADE]", "[translate:TIPO_DISPOSITIVO]", "[translate:SISTEMA_DISPOSITIVO]");
            System.out.println("-----------------------------------------------------------------------------");
            for (Usuario u : listaUsuarios) {
                String tipoDispositivo = (u.getDispositivoAcesso() != null) ? u.getDispositivoAcesso().getTipo() : "-";
                String sistemaDispositivo = (u.getDispositivoAcesso() != null) ? u.getDispositivoAcesso().getSistema() : "-";
                System.out.printf("%-5d %-20s %-10s %-15s %-15s%n",
                        u.getId(),
                        u.getNome(),
                        u.getIdade(),
                        tipoDispositivo,
                        sistemaDispositivo
                );
            }
        }

        //TempoUsoDAO
        TempoUsoDAO tempoUsoDAO = new TempoUsoDAO();

        List<TempoUso> listaTempoUso = tempoUsoDAO.selecionar();

        if(listaTempoUso != null){
            System.out.println("\n===== [translate:INFORMAÇÕES TEMPO DE USO] =====");
            System.out.printf("%-5s %-15s %-20s %-10s %-10s%n", "[translate:ID]", "[translate:EXPERIÊNCIA]", "[translate:SUGESTÃO]" , "[translate:TEMPO]", "[translate:FREQUÊNCIA]");
            System.out.println("----------------------------------------------------------------");
            for (TempoUso t : listaTempoUso){
                System.out.printf("%-5d %-15s %-20s %-10s %-10s%n",
                        t.getId(),
                        t.getExperiencia(),
                        t.getSugestao(),
                        t.getTempo(),
                        t.getFrequencia()
                );
            }
        }

        //PerguntaPrincipalDAO
        PerguntaPrincipalDAO perguntaPrincipalDAO = new PerguntaPrincipalDAO();

        List<PerguntaPrincipal> listaPerguntaPrincipal = perguntaPrincipalDAO.selecionar();

        if(listaPerguntaPrincipal != null){
            System.out.println("\n===== [translate:INFORMAÇÕES PERGUNTA PRINCIPAL] =====");
            System.out.printf("%-5s %-20s%n", "[translate:ID]", "[translate:PERGUNTA]");
            System.out.println("-----------------------------------");
            for (PerguntaPrincipal p : listaPerguntaPrincipal){
                System.out.printf("%-5d %-20s%n",
                        p.getId(),
                        p.getPergunta()
                );
            }
        }

        //DificuldadeDAO
        DificuldadeDAO dificuldadeDAO = new DificuldadeDAO();

        List<Dificuldade> listaDificuldades = dificuldadeDAO.selecionar();

        if(listaDificuldades != null){
            System.out.println("\n===== [translate:INFORMAÇÕES DIFICULDADE] =====");
            System.out.printf("%-5s %-20s %-20s%n", "[translate:ID]", "[translate:TIPO]", "[translate:DESCRIÇÃO]");
            System.out.println("---------------------------------------------------");
            for (Dificuldade d : listaDificuldades){
                System.out.printf("%-5d %-20s %-20s%n",
                        d.getId(),
                        d.getTipo(),
                        d.getDescricao()
                );
            }
        }

        //AvaliacaoDAO
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        List<Avaliacao> listaAvaliacoes = avaliacaoDAO.selecionar();

        if(listaAvaliacoes != null){
            System.out.println("\n===== [translate:INFORMAÇÕES DIFICULDADE] =====");
            System.out.printf("%-5s %-10s%n", "[translate:ID]", "[translate:AVALIAR]");
            System.out.println("-----------------------------------");
            for (Avaliacao a : listaAvaliacoes){
                System.out.printf("%-5d %-10s%n",
                        a.getId(),
                        a.getAvaliar()
                );
            }
        }
    }
}

