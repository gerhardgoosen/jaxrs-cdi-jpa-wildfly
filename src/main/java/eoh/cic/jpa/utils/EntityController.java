package eoh.cic.jpa.utils;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import eoh.cic.jpa.AppPersistanceContext;
import eoh.cic.model.Cic;

/**
 * @author gerhardg
 *
 */
@RequestScoped
public class EntityController implements Serializable {

	private static final long serialVersionUID = -5466724800006932165L;

	private static final Logger log = Logger.getLogger(EntityController.class.getName());
	@Inject
	@AppPersistanceContext
	@PersistenceContext(name = "primary", unitName = "primary", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	


	@PostConstruct
	public void postConstruct() {
		log.info("@PostConstruct EntityController -->");
	 
	}

	@PreRemove
	public void destroy() {

		log.info("@PreRemove EntityController -->");
	}

	/**
	 * 
	 * @param p
	 */
	@Transactional
	public void updateModelObject(Cic p) {
		log.info("EntityController - update (class" + p.getClass().getSimpleName() + " - " + p.getId() + ")");

		try {
			getEntityManager().merge(p);
		}
		catch (Exception e) {
			// e.printStackTrace();
			log.log(Level.SEVERE, "EntityController.updateModelObject", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param currentItem
	 * @throws Exception
	 */
	@Transactional
	public Cic saveModelObject(Cic currentItem) throws Exception {
		log.info(
				"EntityController - save (" + currentItem.getClass().getName() + ", id : " + currentItem.getId() + ")");

		try {
			getEntityManager().persist(currentItem);
			return currentItem;
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "EntityController.saveModelObject", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param currentItem
	 */
	@Transactional
	public void removeModelObject(Cic currentItem) {
		try {
			getEntityManager().refresh(currentItem);
			getEntityManager().remove(currentItem);
		}
		catch (Exception e) {
			// e.printStackTrace();
			log.log(Level.SEVERE, "EntityController.removeModelObject", e);
			throw e;
		}
	}

	/**
	 * 
	 * @param modelObjectClass
	 * @param modelObject
	 * @return
	 */
	@Transactional
	public Cic refreshModelObject(Class modelObjectClass, Cic modelObject) {
		Cic tmp = null;
		try {
			tmp = (Cic) getEntityManager().find(modelObjectClass, modelObject.getId());
		}
		catch (Exception e) {
			// e.printStackTrace();
			log.log(Level.SEVERE, "EntityController.refreshModelObject", e);
			throw e;
		}

		return tmp;
	}
	
	/**
	 * 
	 * @return
	 */
	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * 
	 * @param em
	 */
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
