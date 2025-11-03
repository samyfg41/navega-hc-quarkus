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

    // ✅ MUDOU: agora recebe o feedbackId como parâmetro e faz UPDATE
    public String atualizar(int feedbackId, TempoUso tempoUso) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET experiencia = ?, sugestao = ?, tempo = ?, frequencia = ? WHERE id = ?"
        );
        stmt.setString(1, tempoUso.getExperiencia());
        stmt.setString(2, tempoUso.getSugestao());
        stmt.setString(3, tempoUso.getTempo());
        stmt.setString(4, tempoUso.getFrequencia());
        stmt.setInt(5, feedbackId);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Tempo de uso cadastrado com sucesso!";
        } else {
            return "Erro ao cadastrar tempo de uso.";
        }
    }

    //Read
    public List<TempoUso> selecionar() throws SQLException {
        List<TempoUso> listaTempoUso = new ArrayList<TempoUso>();
        PreparedStatement stmt = minhaConexao.prepareStatement("SELECT * FROM FORMULARIO_NAVEGA_HC");

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

    //Update (para atualizar um registro existente pelo ID)
    public String atualizarPorId(TempoUso tempoUso) throws SQLException {
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
            return "Informação de TempoUso atualizada com sucesso";
        } else {
            return "Nenhuma informação de TempoUso encontrada com o ID informado.";
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
            return "Informação de TempoUso deletada com sucesso!";
        } else {
            return "Nenhuma informação de TempoUso encontrada com esse ID.";
        }
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}
