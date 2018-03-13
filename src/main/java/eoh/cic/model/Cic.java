package eoh.cic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import eoh.cic.jpa.PersistentObject;

/**
 * Mapped Super Class for all CIC objects, at this poitnonly storing id and last
 * auditUser values
 * 
 * @author Gerhard Goosen
 *
 */
@Entity

public class Cic implements PersistentObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6441705361212346042L;

	public Cic() {
		super();
		this.cicTimeStamp = new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pojo_id_generator")
	@TableGenerator(name = "pojo_id_generator", pkColumnName = "pojo_id_generator", allocationSize = 100, table = "GENERATORS", valueColumnName = "SEQ_NUMBER", pkColumnValue = "ENTITY")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	private String cicType;
	private String subject;
	private String body;
	private String sourceSystem;
	private Date cicTimeStamp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String toString() {
		return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
	}

	public boolean equals(Object other) {
		return (other instanceof Cic) && (id != null) ? id.equals(((Cic) other).id) : (other == this);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

}
