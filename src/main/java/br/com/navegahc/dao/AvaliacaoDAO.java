package br.com.navegahc.dao;

import br.com.navegahc.beans.Avaliacao;
import br.com.navegahc.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public Connection minhaConexao;

    public AvaliacaoDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // ✅ MUDOU: agora recebe o feedbackId como parâmetro e faz UPDATE
    public String atualizar(int feedbackId, Avaliacao avaliacao) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET avaliar = ? WHERE id = ?"
        );
        stmt.setDouble(1, avaliacao.getAvaliar());
        stmt.setInt(2, feedbackId);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Avaliação cadastrada com sucesso!";
        } else {
            return "Erro ao cadastrar avaliação.";
        }
    }

    //Read
    public List<Avaliacao> selecionar() throws SQLException {
        List<Avaliacao> listaAvaliacoes = new ArrayList<Avaliacao>();
        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FORMULARIO_NAVEGA_HC");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Avaliacao objAvaliacao = new Avaliacao();
            objAvaliacao.setId(rs.getInt("id"));
            objAvaliacao.setAvaliar(rs.getDouble("avaliar"));
            listaAvaliacoes.add(objAvaliacao);
        }
        return listaAvaliacoes;
    }

    //Update (para atualizar um registro existente pelo ID)
    public String atualizarPorId(Avaliacao avaliacao) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET avaliar = ? WHERE id = ?"
        );
        stmt.setDouble(1, avaliacao.getAvaliar());
        stmt.setInt(2, avaliacao.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Avaliação atualizada com sucesso";
        } else {
            return "Nenhuma avaliação encontrada com o ID informado.";
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
            return "Avaliação deletada com sucesso!";
        } else {
            return "Nenhuma avaliação encontrada com esse ID.";
        }
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}
