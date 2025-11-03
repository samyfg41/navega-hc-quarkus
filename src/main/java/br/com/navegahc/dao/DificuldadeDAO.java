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

    // ✅ ADICIONA ESSE MÉTODO
    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(Dificuldade dificuldade) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO FORMULARIO_NAVEGA_HC (id, tipo_dificuldade, descricao_dificuldade) VALUES (?, ?, ?)"
        );
        stmt.setInt(1, dificuldade.getId());
        stmt.setString(2, dificuldade.getTipo());
        stmt.setString(3, dificuldade.getDescricao());

        stmt.executeUpdate();
        stmt.close();
        return "Dificuldade cadastrada com sucesso!";
    }

    //Read
    public List<Dificuldade> selecionar() throws SQLException {
        List<Dificuldade> listaDificuldades = new ArrayList<Dificuldade>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM FORMULARIO_NAVEGA_HC"
        );

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

    //Update
    public String atualizar(Dificuldade dificuldade) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET tipo_dificuldade = ?, descricao_dificuldade = ? WHERE id = ?"
        );
        stmt.setString(1, dificuldade.getTipo());
        stmt.setString(2, dificuldade.getDescricao());
        stmt.setInt(3, dificuldade.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Dificuldade atualizada com sucesso";
        } else {
            return "Nenhuma dificuldade encontrada com o ID informado.";
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
            return "Dificuldade deletada com sucesso!";
        } else {
            return "Nenhuma dificuldade encontrada com esse ID.";
        }
    }
}


