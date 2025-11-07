package br.com.navegahc.bo;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import java.sql.SQLException;
import java.util.*;

public class FeedbackBO {

    // Selecionar TUDO
    public List<Map<String, Object>> selecionarBo() throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            usuarioDAO = new UsuarioDAO();
            tempoUsoDAO = new TempoUsoDAO();
            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            dificuldadeDAO = new DificuldadeDAO();
            avaliacaoDAO = new AvaliacaoDAO();

            List<Usuario> usuarios = usuarioDAO.selecionar();
            List<TempoUso> temposUso = tempoUsoDAO.selecionar();
            List<PerguntaPrincipal> perguntas = perguntaPrincipalDAO.selecionar();
            List<Dificuldade> dificuldades = dificuldadeDAO.selecionar();
            List<Avaliacao> avaliacoes = avaliacaoDAO.selecionar();

            List<Map<String, Object>> resultado = new ArrayList<>();

            for (Usuario usuario : usuarios) {
                Map<String, Object> feedback = new LinkedHashMap<>();

                feedback.put("id", usuario.getId());
                feedback.put("nome", usuario.getNome());
                feedback.put("idade", usuario.getIdade());

                if (usuario.getDispositivoAcesso() != null) {
                    feedback.put("tipoDispositivo", usuario.getDispositivoAcesso().getTipo());
                    feedback.put("sistemaDispositivo", usuario.getDispositivoAcesso().getSistema());
                } else {
                    feedback.put("tipoDispositivo", null);
                    feedback.put("sistemaDispositivo", null);
                }

                String experiencia = null;
                String sugestao = null;
                String tempo = null;
                String frequencia = null;

                for (TempoUso t : temposUso) {
                    if (t.getId() == usuario.getId()) {
                        experiencia = t.getExperiencia();
                        sugestao = t.getSugestao();
                        tempo = t.getTempo();
                        frequencia = t.getFrequencia();
                        break;
                    }
                }

                feedback.put("experiencia", experiencia);
                feedback.put("sugestao", sugestao);
                feedback.put("tempo", tempo);
                feedback.put("frequencia", frequencia);

                String pergunta = null;
                for (PerguntaPrincipal p : perguntas) {
                    if (p.getId() == usuario.getId()) {
                        pergunta = p.getPergunta();
                        break;
                    }
                }
                feedback.put("pergunta", pergunta);

                String tipoDificuldade = null;
                String descricaoDificuldade = null;
                for (Dificuldade d : dificuldades) {
                    if (d.getId() == usuario.getId()) {
                        tipoDificuldade = d.getTipo();
                        descricaoDificuldade = d.getDescricao();
                        break;
                    }
                }
                feedback.put("tipoDificuldade", tipoDificuldade);
                feedback.put("descricaoDificuldade", descricaoDificuldade);

                Double avaliar = null;
                for (Avaliacao a : avaliacoes) {
                    if (a.getId() == usuario.getId()) {
                        avaliar = a.getAvaliar();
                        break;
                    }
                }
                feedback.put("avaliar", avaliar);

                resultado.add(feedback);
            }

            return resultado;

        } finally {
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (tempoUsoDAO != null) {
                try { tempoUsoDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (perguntaPrincipalDAO != null) {
                try { perguntaPrincipalDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (dificuldadeDAO != null) {
                try { dificuldadeDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (avaliacaoDAO != null) {
                try { avaliacaoDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }

    // Buscar por ID
    public Map<String, Object> buscarPorIdBo(int id) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDAO = null;
        TempoUsoDAO tempoUsoDAO = null;
        PerguntaPrincipalDAO perguntaPrincipalDAO = null;
        DificuldadeDAO dificuldadeDAO = null;
        AvaliacaoDAO avaliacaoDAO = null;

        try {
            usuarioDAO = new UsuarioDAO();
            tempoUsoDAO = new TempoUsoDAO();
            perguntaPrincipalDAO = new PerguntaPrincipalDAO();
            dificuldadeDAO = new DificuldadeDAO();
            avaliacaoDAO = new AvaliacaoDAO();

            List<Usuario> usuarios = usuarioDAO.selecionar();
            Usuario usuario = null;

            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    usuario = u;
                    break;
                }
            }

            if (usuario == null) {
                throw new SQLException("Feedback não encontrado com ID: " + id);
            }

            Map<String, Object> feedback = new LinkedHashMap<>();
            feedback.put("id", usuario.getId());
            feedback.put("nome", usuario.getNome());
            feedback.put("idade", usuario.getIdade());

            if (usuario.getDispositivoAcesso() != null) {
                feedback.put("tipoDispositivo", usuario.getDispositivoAcesso().getTipo());
                feedback.put("sistemaDispositivo", usuario.getDispositivoAcesso().getSistema());
            } else {
                feedback.put("tipoDispositivo", null);
                feedback.put("sistemaDispositivo", null);
            }

            List<TempoUso> temposUso = tempoUsoDAO.selecionar();
            String experiencia = null;
            String sugestao = null;
            String tempo = null;
            String frequencia = null;
            for (TempoUso t : temposUso) {
                if (t.getId() == id) {
                    experiencia = t.getExperiencia();
                    sugestao = t.getSugestao();
                    tempo = t.getTempo();
                    frequencia = t.getFrequencia();
                    break;
                }
            }
            feedback.put("experiencia", experiencia);
            feedback.put("sugestao", sugestao);
            feedback.put("tempo", tempo);
            feedback.put("frequencia", frequencia);

            List<PerguntaPrincipal> perguntas = perguntaPrincipalDAO.selecionar();
            String pergunta = null;
            for (PerguntaPrincipal p : perguntas) {
                if (p.getId() == id) {
                    pergunta = p.getPergunta();
                    break;
                }
            }
            feedback.put("pergunta", pergunta);

            List<Dificuldade> dificuldades = dificuldadeDAO.selecionar();
            String tipoDificuldade = null;
            String descricaoDificuldade = null;
            for (Dificuldade d : dificuldades) {
                if (d.getId() == id) {
                    tipoDificuldade = d.getTipo();
                    descricaoDificuldade = d.getDescricao();
                    break;
                }
            }
            feedback.put("tipoDificuldade", tipoDificuldade);
            feedback.put("descricaoDificuldade", descricaoDificuldade);

            List<Avaliacao> avaliacoes = avaliacaoDAO.selecionar();
            Double avaliar = null;
            for (Avaliacao a : avaliacoes) {
                if (a.getId() == id) {
                    avaliar = a.getAvaliar();
                    break;
                }
            }
            feedback.put("avaliar", avaliar);

            return feedback;

        } finally {
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (tempoUsoDAO != null) {
                try { tempoUsoDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (perguntaPrincipalDAO != null) {
                try { perguntaPrincipalDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (dificuldadeDAO != null) {
                try { dificuldadeDAO.fecharConexao(); } catch (SQLException e) { }
            }
            if (avaliacaoDAO != null) {
                try { avaliacaoDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }

    // Inserir (salva todos os campos)
    public void inserirBo(Map<String, Object> feedbackCompleto) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDAO = null;

        try {
            // Cria o usuário
            Usuario usuario = new Usuario();
            usuario.setNome((String) feedbackCompleto.get("nome"));

            // Converte idade para Integer
            Object idadeObj = feedbackCompleto.get("idade");
            if (idadeObj instanceof Integer) {
                usuario.setIdade((Integer) idadeObj);
            } else if (idadeObj instanceof Double) {
                usuario.setIdade(((Double) idadeObj).intValue());
            }

            // DispositivoAcesso
            if (feedbackCompleto.get("tipoDispositivo") != null) {
                DispositivoAcesso dispositivo = new DispositivoAcesso();
                dispositivo.setTipo((String) feedbackCompleto.get("tipoDispositivo"));
                dispositivo.setSistema((String) feedbackCompleto.get("sistemaDispositivo"));
                usuario.setDispositivoAcesso(dispositivo);
            }

            // Validações básicas
            if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
                throw new SQLException("Nome é obrigatório");
            }

            if (usuario.getIdade() < 0) {
                throw new SQLException("Idade deve ser maior que zero");
            }

            // Insere o usuário e pega o ID gerado
            usuarioDAO = new UsuarioDAO();
            usuarioDAO.inserir(usuario);
            int idGerado = usuario.getId();

            // Atualiza com os dados de TempoUso
            if (feedbackCompleto.get("experiencia") != null || feedbackCompleto.get("sugestao") != null) {
                String updateTempoUso = "UPDATE FORMULARIO_NAVEGA_HC SET experiencia = ?, sugestao = ?, tempo = ?, frequencia = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateTempoUso);
                stmt.setString(1, (String) feedbackCompleto.get("experiencia"));
                stmt.setString(2, (String) feedbackCompleto.get("sugestao"));
                stmt.setString(3, (String) feedbackCompleto.get("tempo"));
                stmt.setString(4, (String) feedbackCompleto.get("frequencia"));
                stmt.setInt(5, idGerado);
                stmt.executeUpdate();
                stmt.close();
            }

            // Atualiza com a pergunta
            if (feedbackCompleto.get("pergunta") != null) {
                String updatePergunta = "UPDATE FORMULARIO_NAVEGA_HC SET pergunta = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updatePergunta);
                stmt.setString(1, (String) feedbackCompleto.get("pergunta"));
                stmt.setInt(2, idGerado);
                stmt.executeUpdate();
                stmt.close();
            }

            // Atualiza com a dificuldade
            if (feedbackCompleto.get("tipoDificuldade") != null || feedbackCompleto.get("descricaoDificuldade") != null) {
                String updateDificuldade = "UPDATE FORMULARIO_NAVEGA_HC SET tipo_dificuldade = ?, descricao_dificuldade = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateDificuldade);
                stmt.setString(1, (String) feedbackCompleto.get("tipoDificuldade"));
                stmt.setString(2, (String) feedbackCompleto.get("descricaoDificuldade"));
                stmt.setInt(3, idGerado);
                stmt.executeUpdate();
                stmt.close();
            }

            // Atualiza com a avaliação
            if (feedbackCompleto.get("avaliar") != null) {
                String updateAvaliacao = "UPDATE FORMULARIO_NAVEGA_HC SET avaliar = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateAvaliacao);

                Object avaliarObj = feedbackCompleto.get("avaliar");
                if (avaliarObj instanceof Double) {
                    stmt.setDouble(1, (Double) avaliarObj);
                } else if (avaliarObj instanceof Integer) {
                    stmt.setDouble(1, ((Integer) avaliarObj).doubleValue());
                }

                stmt.setInt(2, idGerado);
                stmt.executeUpdate();
                stmt.close();
            }

        } finally {
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }

    // Atualizar
    public void atualizarBo(Map<String, Object> feedbackCompleto) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDAO = null;

        try {
            Object idObj = feedbackCompleto.get("id");
            int id = 0;
            if (idObj instanceof Integer) {
                id = (Integer) idObj;
            } else if (idObj instanceof Double) {
                id = ((Double) idObj).intValue();
            }

            usuarioDAO = new UsuarioDAO();

            List<Usuario> usuarios = usuarioDAO.selecionar();
            boolean existe = false;

            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                throw new SQLException("Feedback não encontrado com ID: " + id);
            }

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome((String) feedbackCompleto.get("nome"));

            Object idadeObj = feedbackCompleto.get("idade");
            if (idadeObj instanceof Integer) {
                usuario.setIdade((Integer) idadeObj);
            } else if (idadeObj instanceof Double) {
                usuario.setIdade(((Double) idadeObj).intValue());
            }

            if (feedbackCompleto.get("tipoDispositivo") != null) {
                DispositivoAcesso dispositivo = new DispositivoAcesso();
                dispositivo.setTipo((String) feedbackCompleto.get("tipoDispositivo"));
                dispositivo.setSistema((String) feedbackCompleto.get("sistemaDispositivo"));
                usuario.setDispositivoAcesso(dispositivo);
            }

            if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
                throw new SQLException("Nome é obrigatório");
            }

            if (usuario.getIdade() < 0) {
                throw new SQLException("Idade deve ser maior que zero");
            }

            usuarioDAO.atualizar(usuario);

            // Atualiza outros campos
            if (feedbackCompleto.get("experiencia") != null || feedbackCompleto.get("sugestao") != null) {
                String updateTempoUso = "UPDATE FORMULARIO_NAVEGA_HC SET experiencia = ?, sugestao = ?, tempo = ?, frequencia = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateTempoUso);
                stmt.setString(1, (String) feedbackCompleto.get("experiencia"));
                stmt.setString(2, (String) feedbackCompleto.get("sugestao"));
                stmt.setString(3, (String) feedbackCompleto.get("tempo"));
                stmt.setString(4, (String) feedbackCompleto.get("frequencia"));
                stmt.setInt(5, id);
                stmt.executeUpdate();
                stmt.close();
            }

            if (feedbackCompleto.get("pergunta") != null) {
                String updatePergunta = "UPDATE FORMULARIO_NAVEGA_HC SET pergunta = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updatePergunta);
                stmt.setString(1, (String) feedbackCompleto.get("pergunta"));
                stmt.setInt(2, id);
                stmt.executeUpdate();
                stmt.close();
            }

            if (feedbackCompleto.get("tipoDificuldade") != null || feedbackCompleto.get("descricaoDificuldade") != null) {
                String updateDificuldade = "UPDATE FORMULARIO_NAVEGA_HC SET tipo_dificuldade = ?, descricao_dificuldade = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateDificuldade);
                stmt.setString(1, (String) feedbackCompleto.get("tipoDificuldade"));
                stmt.setString(2, (String) feedbackCompleto.get("descricaoDificuldade"));
                stmt.setInt(3, id);
                stmt.executeUpdate();
                stmt.close();
            }

            if (feedbackCompleto.get("avaliar") != null) {
                String updateAvaliacao = "UPDATE FORMULARIO_NAVEGA_HC SET avaliar = ? WHERE id = ?";
                java.sql.PreparedStatement stmt = usuarioDAO.minhaConexao.prepareStatement(updateAvaliacao);

                Object avaliarObj = feedbackCompleto.get("avaliar");
                if (avaliarObj instanceof Double) {
                    stmt.setDouble(1, (Double) avaliarObj);
                } else if (avaliarObj instanceof Integer) {
                    stmt.setDouble(1, ((Integer) avaliarObj).doubleValue());
                }

                stmt.setInt(2, id);
                stmt.executeUpdate();
                stmt.close();
            }

        } finally {
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }

    // Deletar
    public void deletarBo(int id) throws ClassNotFoundException, SQLException {
        UsuarioDAO usuarioDAO = null;

        try {
            usuarioDAO = new UsuarioDAO();

            List<Usuario> usuarios = usuarioDAO.selecionar();
            boolean existe = false;

            for (Usuario u : usuarios) {
                if (u.getId() == id) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                throw new SQLException("Feedback não encontrado com ID: " + id);
            }

            usuarioDAO.deletar(id);

        } finally {
            if (usuarioDAO != null) {
                try { usuarioDAO.fecharConexao(); } catch (SQLException e) { }
            }
        }
    }
}



