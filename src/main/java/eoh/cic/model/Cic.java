package eoh.cic.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Mapped Super Class for all CIC objects, at this poitnonly storing id and last
 * auditUser values
 * 
 * @author Gerhard Goosen
 *
 */
@Entity
@Audited

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("Cic")
public class Cic extends DefaultPojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441705361212346042L;

	public Cic() {
		super();
		this.cicTimeStamp = new Date();
	}
 
	private String cicType;
	private String subject;
	private String body;
	private String sourceSystem;
	private Date cicTimeStamp;

	@ManyToOne
	@JoinColumn(name = "entity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_cic_entity"))
	private eoh.cic.model.Entity entity;
 
	public String getCicType() {
		return cicType;
	}

	public void setCicType(String cicType) {
		this.cicType = cicType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public Date getCicTimeStamp() {
		return cicTimeStamp;
	}

	public void setCicTimeStamp(Date cicTimeStamp) {
		this.cicTimeStamp = cicTimeStamp;
	}

	 
	public eoh.cic.model.Entity getEntity() {
		return entity;
	}

	public void setEntity(eoh.cic.model.Entity entity) {
		this.entity = entity;
	}

}
