package br.com.navegahc.services;

import br.com.navegahc.beans.Usuario;
import br.com.navegahc.bo.FeedbackBO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.sql.SQLException;
import java.util.*;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)  // ✅ TEM ISSO?
@Consumes(MediaType.APPLICATION_JSON)  // ✅ E ISSO?
public class FeedbackResource {

    private FeedbackBO feedbackBO = new FeedbackBO();

    @GET
    public List<Map<String, Object>> selecionarRs() throws ClassNotFoundException, SQLException {
        return feedbackBO.selecionarBo();
    }

    @GET
    @Path("/{id}")
    public Map<String, Object> buscarPorIdRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        return feedbackBO.buscarPorIdBo(id);
    }

    @POST
    public Response inserirRs(Usuario usuario, @Context UriInfo uriInfo) throws ClassNotFoundException, SQLException {
        feedbackBO.inserirBo(usuario);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(usuario.getId()));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarRs(Usuario usuario, @PathParam("id") int id) throws ClassNotFoundException, SQLException {
        usuario.setId(id);
        feedbackBO.atualizarBo(usuario);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarRs(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        feedbackBO.deletarBo(id);
        return Response.ok().build();
    }
}
