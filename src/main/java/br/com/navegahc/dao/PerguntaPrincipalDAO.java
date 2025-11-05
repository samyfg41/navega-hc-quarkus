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

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(PerguntaPrincipal perguntaPrincipal) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO FORMULARIO_NAVEGA_HC (id, pergunta) VALUES (?, ?)"
        );
        stmt.setInt(1, perguntaPrincipal.getId());
        stmt.setString(2, perguntaPrincipal.getPergunta());

        stmt.executeUpdate();
        stmt.close();
        return "Pergunta cadastrada com sucesso!";
    }

    //Read
    public List<PerguntaPrincipal> selecionar() throws SQLException {
        List<PerguntaPrincipal> listaPerguntas = new ArrayList<PerguntaPrincipal>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM FORMULARIO_NAVEGA_HC"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            PerguntaPrincipal objPergunta = new PerguntaPrincipal();
            objPergunta.setId(rs.getInt("id"));
            objPergunta.setPergunta(rs.getString("pergunta"));

            listaPerguntas.add(objPergunta);
        }
        return listaPerguntas;
    }

    //Update
    public String atualizar(PerguntaPrincipal perguntaPrincipal) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET pergunta = ? WHERE id = ?"
        );
        stmt.setString(1, perguntaPrincipal.getPergunta());
        stmt.setInt(2, perguntaPrincipal.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Pergunta atualizada com sucesso";
        } else {
            return "Nenhuma pergunta encontrada com o ID informado.";
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
            return "Pergunta deletada com sucesso!";
        } else {
            return "Nenhuma pergunta encontrada com esse ID.";
        }
    }
}


