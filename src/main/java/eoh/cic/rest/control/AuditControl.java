
package eoh.cic.rest.control;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;

import eoh.cic.jpa.utils.EntityController;
import eoh.cic.model.Cic;
import eoh.cic.rest.CicApiException;
 

@Named(value = "AuditControl")
@RequestScoped
public class AuditControl {
	private static final Logger log = Logger.getLogger(AuditControl.class.getName());
	@Inject
	EntityController entityControl;

	@PostConstruct
	public void initilizeAuditControl() {
		log.info("[@PostConstruct AuditControl]");
	}

	@PreRemove
	public void destroyAuditControl() {
		log.info("[@PreRemove AuditControl]");
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public List<? extends Cic> getAuditTrail(Class  model, Long id) throws CicApiException {
		log.log(Level.INFO, "AuditControl.getAuditTrail({0});", id);
		try {
			this.entityControl.getEntityManager().find(model, id);

			AuditReader reader = AuditReaderFactory.get(this.entityControl.getEntityManager());

			boolean selectEntitiesOnly = false;
			boolean selectDeletedEntities = true;

			AuditQuery query = reader.createQuery().forRevisionsOfEntity(model, selectEntitiesOnly,
					selectDeletedEntities);
			return query.getResultList();

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error in AuditControl.getAuditTrail : " + e.getMessage(), e);
			throw new CicApiException(e);
		}

	}

}
