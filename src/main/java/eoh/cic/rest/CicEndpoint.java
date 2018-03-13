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

import eoh.cic.model.Cic;
import eoh.cic.rest.control.AuditControl;
import eoh.cic.rest.control.CicControl;

@Path("/cic")
public class CicEndpoint {

	private static final Logger log = Logger.getLogger(CicEndpoint.class.getName());

	@Inject
	private CicControl cicControl;

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
	public Response getCIC(@PathParam("id") Long id) {
		log.log(Level.INFO, "getCIC({0})", id);
		try {

			return Response.ok(mapper.writeValueAsString(cicControl.getCic(id))).build();

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error on CicEndpoint.getCIC : {0}", e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@GET
	@Path("/audit/{id}")
	@Produces("application/json")
	public Response getCICAudit(@PathParam("id") Long id) {
		log.log(Level.INFO, "getCICAudit({0})", id);
		try {

			return Response.ok(mapper.writeValueAsString(auditControl.getAuditTrail(Cic.class, id))).build();

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error on CicEndpoint.getCICAudit : {0}", e.getMessage());
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createCIC(String data) {
		log.log(Level.INFO, "createCIC --> \n \n {0}", data);
		try {

			return Response.ok(this.cicControl.createCic(mapper.readValue(data, Cic.class))).build();
		}
		catch (Exception e) {
			e.printStackTrace();
			log.log(Level.SEVERE, "Error on CicEndpoint.createCIC : " + e.getMessage(), e);
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

}
