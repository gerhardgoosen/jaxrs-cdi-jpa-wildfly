package eoh.cic.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import eoh.cic.jpa.PersistentObject;
@javax.persistence.Entity

@Audited 

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("Entity") 
public class Entity  extends DefaultPojo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1852790482100279406L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pojo_id_generator")
	@TableGenerator(name = "pojo_id_generator", pkColumnName = "pojo_id_generator", allocationSize = 100, table = "GENERATORS", valueColumnName = "SEQ_NUMBER", pkColumnValue = "ENTITY")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
 
	private String entityName;
	private String emailAddress;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = Cic.class, mappedBy = "entity", cascade = { CascadeType.ALL })
	private List<Cic> cicList;
	
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

	public List<Cic> getCicList() {
		return cicList;
	}

	public void setCicList(List<Cic> cicList) {
		this.cicList = cicList;
	}
}
