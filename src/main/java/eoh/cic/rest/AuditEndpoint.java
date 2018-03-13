package eoh.cic.rest;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import eoh.cic.model.Cic;
import eoh.cic.rest.control.AuditControl;

public class AuditEndpoint {

	private static final Logger log = Logger.getLogger(AuditEndpoint.class.getName());
 
	@Inject
	private AuditControl auditControl;

	private ObjectMapper mapper;

	@PostConstruct
	private void postConstruct() {
		ObjectMapper mapper = new ObjectMapper();
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
			log.log(Level.SEVERE, "Error on AuditEndpoint.getCICAudit : " + e.getMessage(), e);
			return Response.status(500).entity(e.getMessage()).build();
		}
	}

	 

}
