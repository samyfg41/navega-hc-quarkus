package br.com.navegahc;

import br.com.navegahc.bo.FeedbackBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    // GET - Listar todos
    @GET
    public Response selecionarRs() throws ClassNotFoundException, SQLException {
        FeedbackBO feedbackBO = new FeedbackBO();
        List<Map<String, Object>> listaFeedbacks = feedbackBO.selecionarBo();

        Response.ResponseBuilder response = Response.ok(listaFeedbacks);
        response.header("Access-Control-Allow-Origin", "*");
        return response.build();
    }

    // GET - Buscar por ID
    @GET
    @Path("/{id}")
    public Response buscarPorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        FeedbackBO feedbackBO = new FeedbackBO();

        try {
            Map<String, Object> feedback = feedbackBO.buscarPorIdBo(id);
            Response.ResponseBuilder response = Response.ok(feedback);
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // POST - Criar novo
    @POST
    public Response inserirRs(Map<String, Object> feedbackCompleto) throws ClassNotFoundException, SQLException {
        FeedbackBO feedbackBO = new FeedbackBO();

        try {
            feedbackBO.inserirBo(feedbackCompleto);

            Response.ResponseBuilder response = Response.status(Response.Status.CREATED)
                    .entity("{\"message\": \"Feedback criado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // PUT - Atualizar
    @PUT
    @Path("/{id}")
    public Response atualizarRs(@PathParam("id") int id, Map<String, Object> feedbackCompleto) throws ClassNotFoundException, SQLException {
        FeedbackBO feedbackBO = new FeedbackBO();

        try {
            feedbackCompleto.put("id", id);
            feedbackBO.atualizarBo(feedbackCompleto);

            Response.ResponseBuilder response = Response.ok("{\"message\": \"Feedback atualizado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    public Response deletarRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        FeedbackBO feedbackBO = new FeedbackBO();

        try {
            feedbackBO.deletarBo(id);

            Response.ResponseBuilder response = Response.ok("{\"message\": \"Feedback deletado com sucesso\"}");
            response.header("Access-Control-Allow-Origin", "*");
            return response.build();

        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    // OPTIONS - CORS
    @OPTIONS
    @Path("{path:.*}")
    public Response options() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .build();
    }
}
