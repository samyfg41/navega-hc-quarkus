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

    //int
    static Integer inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    //double
    static Double real(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //UsuarioDAO
        Usuario objUsuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        objUsuario.setId(inteiro("Informe o ID do usuário a ser atualizado"));
        objUsuario.setNome(texto("Atualize o nome"));
        objUsuario.setIdade(inteiro("Atualize a idade"));

        DispositivoAcesso objDispositivoAcesso = new DispositivoAcesso();
        objDispositivoAcesso.setTipo(texto("Atualize a informação do tipo de dispositivo"));
        objDispositivoAcesso.setSistema(texto("Atualize a informação de sistema do dispositivo"));
        objUsuario.setDispositivoAcesso(objDispositivoAcesso);

        System.out.println(usuarioDAO.atualizar(objUsuario));

        //TempoUsoDAO
        TempoUso objTempoUso = new TempoUso();
        TempoUsoDAO tempoUsoDAO = new TempoUsoDAO();

        objTempoUso.setId(inteiro("Informe o ID da informação TempoUso a ser atualizada"));
        objTempoUso.setExperiencia(texto("Atualize a informação de experiência"));
        objTempoUso.setSugestao(texto("Atualize a informação da sugestão"));
        objTempoUso.setTempo(texto("Atualize a informação tempo"));
        objTempoUso.setFrequencia(texto("Atualize a informação frequência"));

        System.out.println(tempoUsoDAO.atualizarPorId(objTempoUso));

        //PerguntaPrincipalDAO
        PerguntaPrincipal objPerguntaPrincipal = new PerguntaPrincipal();
        PerguntaPrincipalDAO perguntaPrincipalDAO = new PerguntaPrincipalDAO();

        objPerguntaPrincipal.setId(inteiro("Informe o ID da informação PerguntaPrincipal a ser atualizada"));
        objPerguntaPrincipal.setPergunta(texto("Atualize a resposta da PerguntaPrincipal"));

        System.out.println(perguntaPrincipalDAO.atualizarPorId(objPerguntaPrincipal));

        //DificuldadeDAO
        Dificuldade objDificuldade = new Dificuldade();
        DificuldadeDAO dificuldadeDAO = new DificuldadeDAO();

        objDificuldade.setId(inteiro("Informe o ID da informação Dificuldade a ser atualizada"));
        objDificuldade.setTipo(texto("Atualize a informação de tipo de Dificuldade"));
        objDificuldade.setDescricao(texto("Atualize a informação de descrição de Dificuldade"));

        System.out.println(dificuldadeDAO.atualizarPorId(objDificuldade));

        //AvaliacaoDAO
        Avaliacao objAvaliacao = new Avaliacao();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        objAvaliacao.setId(inteiro("Informe o ID da Avaliação a ser atualizada"));
        objAvaliacao.setAvaliar(real("Atualize a Avaliação"));

        System.out.println(avaliacaoDAO.atualizarPorId(objAvaliacao));
    }
}

