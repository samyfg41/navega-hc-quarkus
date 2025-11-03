package br.com.navegahc.dao;

import br.com.navegahc.beans.DispositivoAcesso;
import br.com.navegahc.beans.Usuario;
import br.com.navegahc.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Connection minhaConexao;

    public UsuarioDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // ✅ ADICIONA ESSE MÉTODO
    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }

    //Create
    public String inserir(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "INSERT INTO FORMULARIO_NAVEGA_HC (nome, idade, tipo_dispositivo, sistema_dispositivo) VALUES (?, ?, ?, ?)",
                new String[] {"id"}
        );
        stmt.setString(1, usuario.getNome());
        stmt.setInt(2, usuario.getIdade());

        if (usuario.getDispositivoAcesso() != null){
            stmt.setString(3, usuario.getDispositivoAcesso().getTipo());
            stmt.setString(4, usuario.getDispositivoAcesso().getSistema());
        } else {
            stmt.setString(3, null);
            stmt.setString(4, null);
        }

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            usuario.setId(rs.getInt(1));
        }

        stmt.close();
        return "Usuário cadastrado com sucesso! ID gerado: " + usuario.getId();
    }

    //Read
    public List<Usuario> selecionar() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "SELECT * FROM FORMULARIO_NAVEGA_HC"
        );

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Usuario objUsuario = new Usuario();
            objUsuario.setId(rs.getInt("id"));
            objUsuario.setNome(rs.getString("nome"));
            objUsuario.setIdade(rs.getInt("idade"));

            DispositivoAcesso objDispositivoAcesso = new DispositivoAcesso();
            objDispositivoAcesso.setTipo(rs.getString("tipo_dispositivo"));
            objDispositivoAcesso.setSistema(rs.getString("sistema_dispositivo"));
            objUsuario.setDispositivoAcesso(objDispositivoAcesso);

            listaUsuarios.add(objUsuario);
        }
        return listaUsuarios;
    }

    //Update
    public String atualizar(Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement(
                "UPDATE FORMULARIO_NAVEGA_HC SET nome = ?, idade = ?, tipo_dispositivo = ?, sistema_dispositivo = ? WHERE id = ?"
        );
        stmt.setString(1, usuario.getNome());
        stmt.setInt(2, usuario.getIdade());

        if (usuario.getDispositivoAcesso() != null){
            stmt.setString(3, usuario.getDispositivoAcesso().getTipo());
            stmt.setString(4, usuario.getDispositivoAcesso().getSistema());
        } else {
            stmt.setString(3, null);
            stmt.setString(4, null);
        }

        stmt.setInt(5, usuario.getId());

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Usuário atualizado com sucesso";
        } else {
            return "Nenhum usuário encontrado com o ID informado.";
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
            return "Usuário deletado com sucesso!";
        } else {
            return "Nenhum usuário encontrado com esse ID.";
        }
    }
}
