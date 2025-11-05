package br.com.navegahc.dao;

import br.com.navegahc.beans.TempoUso;
import br.com.navegahc.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TempoUsoDAO {

    public Connection minhaConexao;

    public TempoUsoDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(TempoUso tempoUso) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO FORMULARIO_NAVEGA_HC (id, experiencia, sugestao, tempo, frequencia) VALUES (?, ?, ?, ?, ?)"
        );
        stmt.setInt(1, tempoUso.getId());
        stmt.setString(2, tempoUso.getExperiencia());
        stmt.setString(3, tempoUso.getSugestao());
        stmt.setString(4, tempoUso.getTempo());
        stmt.setString(5, tempoUso.getFrequencia());

        stmt.executeUpdate();
        stmt.close();
        return "Tempo de uso cadastrado com sucesso!";
    }

    //Read
    public List<TempoUso> selecionar() throws SQLException {
        List<TempoUso> listaTempoUso = new ArrayList<TempoUso>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM FORMULARIO_NAVEGA_HC"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            TempoUso objTempoUso = new TempoUso();
            objTempoUso.setId(rs.getInt("id"));
            objTempoUso.setExperiencia(rs.getString("experiencia"));
            objTempoUso.setSugestao(rs.getString("sugestao"));
            objTempoUso.setTempo(rs.getString("tempo"));
            objTempoUso.setFrequencia(rs.getString("frequencia"));

            listaTempoUso.add(objTempoUso);
        }
        return listaTempoUso;
    }

    //Update
    public String atualizar(TempoUso tempoUso) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET experiencia = ?, sugestao = ?, tempo = ?, frequencia = ? WHERE id = ?"
        );
        stmt.setString(1, tempoUso.getExperiencia());
        stmt.setString(2, tempoUso.getSugestao());
        stmt.setString(3, tempoUso.getTempo());
        stmt.setString(4, tempoUso.getFrequencia());
        stmt.setInt(5, tempoUso.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Tempo de uso atualizado com sucesso";
        } else {
            return "Nenhum tempo de uso encontrado com o ID informado.";
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
            return "Tempo de uso deletado com sucesso!";
        } else {
            return "Nenhum tempo de uso encontrado com esse ID.";
        }
    }
}
