
package eoh.cic.rest.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import eoh.cic.jpa.utils.JPAEntityController;
import eoh.cic.model.Cic;
import eoh.cic.model.Entity;
import eoh.cic.rest.errors.CicApiException;

@Named(value = "EntityControl")
@RequestScoped
public class EntityControl {
	private static final Logger log = Logger.getLogger(EntityControl.class.getName());

	@Inject
	JPAEntityController entityControl;

	@PostConstruct
	public void initilize() {
		log.info("[@PostConstruct EntityControl]");
	}

	@PreRemove
	public void destroy() {
		log.info("[@PreRemove EntityControl]");
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Entity getEntity(Long id) throws CicApiException {
		log.log(Level.INFO, "EntityControl.getEntity({0});", id);
		try {

			return (Entity) this.entityControl.getEntityManager().find(Entity.class, id);

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error in EntityControl.getEntity : " + e.getMessage(), e);
			throw new CicApiException(e);
		}

	}

	@Transactional
	public Entity createEntity(Entity data) throws CicApiException {
		log.log(Level.INFO, "create Cic ({0});", data);
		try {

			if (data.getCicList().size() > 0) {
				data.getCicList().forEach(cicItem -> {
					cicItem.setEntity(data);
				});
			}

			return (Entity) this.entityControl.saveModelObject(data);
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error in EntityControl.createCic: " + e.getMessage(), e);
			throw new CicApiException(e);
		}

	}

}
