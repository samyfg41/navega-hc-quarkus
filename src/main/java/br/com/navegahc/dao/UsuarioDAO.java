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

    //Create
    public String inserir(Usuario usuario) throws SQLException {
        String sql = "Insert into FORMULARIO_NAVEGA_HC (nome, idade, tipo_dispositivo, sistema_dispositivo) values (?, ?, ?, ?)";

        PreparedStatement stmt = minhaConexao.prepareStatement(
                "Insert into FORMULARIO_NAVEGA_HC (nome, idade, tipo_dispositivo, sistema_dispositivo) values (?, ?, ?, ?)", new String[] {"id"}
        );
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getIdade());

            if (usuario.getDispositivoAcesso() !=null){
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
        PreparedStatement stmt = minhaConexao.prepareStatement("select * from FORMULARIO_NAVEGA_HC");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()){
            Usuario objUsuario = new Usuario();
            objUsuario.setId(rs.getInt(1));
            objUsuario.setNome(rs.getString(2));
            objUsuario.setIdade(rs.getInt(3));

            DispositivoAcesso objDispositivoAcesso = new DispositivoAcesso();
            objDispositivoAcesso.setTipo(rs.getString(4));
            objDispositivoAcesso.setSistema(rs.getString(5));
            objUsuario.setDispositivoAcesso(objDispositivoAcesso);

            listaUsuarios.add(objUsuario);
        }
        return listaUsuarios;
    }

    //UpDate
    public String atualizar (Usuario usuario) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Update FORMULARIO_NAVEGA_HC  set NOME = ?, IDADE = ?, TIPO_DISPOSITIVO = ?, SISTEMA_DISPOSITIVO = ? where id = ?");
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
    public String deletar (int id) throws SQLException {
        PreparedStatement stmt = minhaConexao.prepareStatement
                ("Delete from FORMULARIO_NAVEGA_HC where id = ?");

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();
        stmt.close();

        if (linhasAfetadas > 0) {
            return "Usuário deletado com sucesso!";
        } else {
            return "Nenhum usuário encontrado com esse ID.";
        }
    }

    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}
