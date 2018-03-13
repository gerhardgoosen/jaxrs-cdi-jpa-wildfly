package eoh.cic.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
@javax.persistence.Entity
public class Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pojo_id_generator")
	@TableGenerator(name = "pojo_id_generator", pkColumnName = "pojo_id_generator", allocationSize = 100, table = "GENERATORS", valueColumnName = "SEQ_NUMBER", pkColumnValue = "ENTITY")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
 
	private String entityName;
	private String emailAddress;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 
	public String toString() {
		return String.format("%s[id=%s]", getClass().getSimpleName(), getId());
	}

	public boolean equals(Object other) {
		return (other instanceof Entity) && (id != null) ? id.equals(((Entity) other).id) : (other == this);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
