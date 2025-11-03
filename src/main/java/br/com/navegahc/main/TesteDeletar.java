package br.com.navegahc.main;

import br.com.navegahc.dao.*;

import javax.swing.*;
import java.sql.SQLException;

public class TesteDeletar {

    //Int
    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UsuarioDAO usuarioDAO = null;

        try {
            int id = inteiro("Digite o ID do registro que deseja deletar:");

            usuarioDAO = new UsuarioDAO();
            System.out.println(usuarioDAO.deletar(id));

        } finally {
            // ✅ FECHA A CONEXÃO
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }
}

