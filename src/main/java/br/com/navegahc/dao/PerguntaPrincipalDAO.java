package br.com.navegahc.dao;

import br.com.navegahc.beans.PerguntaPrincipal;
import br.com.navegahc.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerguntaPrincipalDAO {

    public Connection minhaConexao;

    public PerguntaPrincipalDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // ✅ MUDOU: agora recebe o feedbackId como parâmetro e faz UPDATE
    public String atualizar(int feedbackId, PerguntaPrincipal perguntaPrincipal) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET pergunta = ? WHERE id = ?"
        );
        stmt.setString(1, perguntaPrincipal.getPergunta());
        stmt.setInt(2, feedbackId);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Pergunta cadastrada com sucesso!";
        } else {
            return "Erro ao cadastrar pergunta.";
        }
    }

    //Read
    public List<PerguntaPrincipal> selecionar() throws SQLException {
        List<PerguntaPrincipal> listaPerguntaPrincipal = new ArrayList<PerguntaPrincipal>();
        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FORMULARIO_NAVEGA_HC");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            PerguntaPrincipal objPerguntaPrincipal = new PerguntaPrincipal();
            objPerguntaPrincipal.setId(rs.getInt("id"));
            objPerguntaPrincipal.setPergunta(rs.getString("pergunta"));
            listaPerguntaPrincipal.add(objPerguntaPrincipal);
        }
        return listaPerguntaPrincipal;
    }

    //Update (para atualizar um registro existente pelo ID)
    public String atualizarPorId(PerguntaPrincipal perguntaPrincipal) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET pergunta = ? WHERE id = ?"
        );
        stmt.setString(1, perguntaPrincipal.getPergunta());
        stmt.setInt(2, perguntaPrincipal.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de PerguntaPrincipal atualizadas com sucesso";
        } else {
            return "Nenhuma informação de PerguntaPrincipal encontrada com o ID informado.";
        }
    }

    //Delete
    public String deletar(int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "DELETE FROM FORMULARIO_NAVEGA_HC WHERE id = ?"
        );

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de PerguntaPrincipal deletadas com sucesso!";
        } else {
            return "Nenhuma informação de PerguntaPrincipal encontrada com esse ID.";
        }
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}

