
package eoh.cic.rest.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;

import eoh.cic.jpa.utils.EntityController;
import eoh.cic.model.Cic;
import eoh.cic.rest.CicApiException;

@Named(value = "CicControl")
@RequestScoped
public class CicControl {
	private static final Logger log = Logger.getLogger(CicControl.class.getName());

	@Inject
	EntityController entityControl;

	@PostConstruct
	public void initilize() {
		log.info("[@PostConstruct CicControl]");
	}

	@PreRemove
	public void destroy() {
		log.info("[@PreRemove CicControl]");
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Cic getCic(Long id) throws CicApiException {
		log.log(Level.INFO, "CicControl.getCic({0});", id);
		try {

			return (Cic) this.entityControl.getEntityManager().find(Cic.class, id);

		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error in CicControl.getCic : " + e.getMessage(), e);
			throw new CicApiException(e);
		}

	}

	@Transactional
	public Cic createCic(Cic data) throws CicApiException {
		log.log(Level.INFO, "create Cic ({0});", data);
		try {
			return (Cic) this.entityControl.saveModelObject(data);
		}
		catch (Exception e) {
			log.log(Level.SEVERE, "Error in CicControl.createCic: " + e.getMessage(), e);
			throw new CicApiException(e);
		}

	}


}
