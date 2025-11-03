package br.com.navegahc.dao;

import br.com.navegahc.beans.Dificuldade;
import br.com.navegahc.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DificuldadeDAO {

    public Connection minhaConexao;

    public DificuldadeDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // ✅ MUDOU: agora recebe o feedbackId como parâmetro e faz UPDATE
    public String atualizar(int feedbackId, Dificuldade dificuldade) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET tipo_dificuldade = ?, descricao_dificuldade = ? WHERE id = ?"
        );
        stmt.setString(1, dificuldade.getTipo());
        stmt.setString(2, dificuldade.getDescricao());
        stmt.setInt(3, feedbackId);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Dificuldade cadastrada com sucesso!";
        } else {
            return "Erro ao cadastrar dificuldade.";
        }
    }

    //Read
    public List<Dificuldade> selecionar() throws SQLException {
        List<Dificuldade> listaDificuldades = new ArrayList<Dificuldade>();
        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FORMULARIO_NAVEGA_HC");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Dificuldade objDificuldade = new Dificuldade();
            objDificuldade.setId(rs.getInt("id"));
            objDificuldade.setTipo(rs.getString("tipo_dificuldade"));
            objDificuldade.setDescricao(rs.getString("descricao_dificuldade"));
            listaDificuldades.add(objDificuldade);
        }
        return listaDificuldades;
    }

    //Update (para atualizar um registro existente pelo ID)
    public String atualizarPorId(Dificuldade dificuldade) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET tipo_dificuldade = ?, descricao_dificuldade = ? WHERE id = ?"
        );
        stmt.setString(1, dificuldade.getTipo());
        stmt.setString(2, dificuldade.getDescricao());
        stmt.setInt(3, dificuldade.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Informações de Dificuldade atualizadas com sucesso";
        } else {
            return "Nenhuma informação de Dificuldade encontrada com o ID informado.";
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
            return "Informações de Dificuldade deletadas com sucesso!";
        } else {
            return "Nenhuma informação de Dificuldade encontrada com esse ID.";
        }
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}

