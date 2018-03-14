package eoh.cic.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import eoh.cic.model.Entity;
import eoh.cic.rest.control.AuditControl;
import eoh.cic.rest.control.EntityControl;

@Path("/entity")
public class EntityEndpoint {

	private static final Logger log = Logger.getLogger(EntityEndpoint.class.getName());

	@Inject
	private EntityControl entityControl;

	@Inject
	private AuditControl auditControl;

	private ObjectMapper mapper;

	@PostConstruct
	private void postConstruct() {
		  this.mapper = new ObjectMapper();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getEntity(@PathParam("id") Long id) {
		log.log(Level.INFO, "getEntity({0})", id);
		try {

			return Response.ok(mapper.writeValueAsString(this.entityControl.getEntity(id))).build();

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error on getEntity.getEntity : {0}", e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createEntity(String data) {
		log.log(Level.INFO, "createEntity --> \n \n {0}", data);
		try {

			return Response.ok(this.entityControl.createEntity(mapper.readValue(data, Entity.class))).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, "Error on EntityEndpoint.createCIC : " + e.getMessage(), e);
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	
	
	@GET
	@Path("/audit/{id}")
	@Produces("application/json")
	public Response getAuditHistory(@PathParam("id") Long id) {
		log.log(Level.INFO, "getAuditHistory({0})", id);
		try {

			return Response.ok(mapper.writeValueAsString(auditControl.getAuditTrail(Entity.class, id))).build();

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error on EntityEndpoint.getAuditHistory : {0}", e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
	}
}
