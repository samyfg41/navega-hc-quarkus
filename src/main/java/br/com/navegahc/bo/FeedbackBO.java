package br.com.navegahc.bo;

import br.com.navegahc.beans.*;
import br.com.navegahc.dao.*;

import java.sql.SQLException;
import java.util.*;

public class FeedbackBO {

    UsuarioDAO usuarioDAO;
    TempoUsoDAO tempoUsoDAO;
    PerguntaPrincipalDAO perguntaPrincipalDAO;
    DificuldadeDAO dificuldadeDAO;
    AvaliacaoDAO avaliacaoDAO;

    // Selecionar TUDO (com ordem garantida)
    public List<Map<String, Object>> selecionarBo() throws ClassNotFoundException, SQLException {
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
            Map<String, Object> feedback = new LinkedHashMap<>();  // ✅ MUDOU AQUI

            // ✅ ORDEM ESPECÍFICA
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

            // Busca tempo de uso
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

            // Busca pergunta
            String pergunta = null;
            for (PerguntaPrincipal p : perguntas) {
                if (p.getId() == usuario.getId()) {
                    pergunta = p.getPergunta();
                    break;
                }
            }
            feedback.put("pergunta", pergunta);

            // Busca dificuldade
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

            // Busca avaliação
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
    }

    // Buscar por ID (com ordem garantida)
    public Map<String, Object> buscarPorIdBo(int id) throws ClassNotFoundException, SQLException {
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

        Map<String, Object> feedback = new LinkedHashMap<>();  // ✅ MUDOU AQUI

        // ✅ ORDEM ESPECÍFICA
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
    }

    // Inserir
    public void inserirBo(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new SQLException("Nome é obrigatório");
        }

        if (usuario.getIdade() < 0) {
            throw new SQLException("Idade deve ser maior que zero");
        }

        usuarioDAO.inserir(usuario);
    }

    // Atualizar
    public void atualizarBo(Usuario usuario) throws ClassNotFoundException, SQLException {
        usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.selecionar();
        boolean existe = false;

        for (Usuario u : usuarios) {
            if (u.getId() == usuario.getId()) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            throw new SQLException("Feedback não encontrado com ID: " + usuario.getId());
        }

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new SQLException("Nome é obrigatório");
        }

        if (usuario.getIdade() < 0) {
            throw new SQLException("Idade deve ser maior que zero");
        }

        usuarioDAO.atualizar(usuario);
    }

    // Deletar
    public void deletarBo(int id) throws ClassNotFoundException, SQLException {
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
    }
}


