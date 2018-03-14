package eoh.cic.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
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

	 
	private String entityName;
	private String emailAddress;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(targetEntity = Cic.class, mappedBy = "entity", cascade = { CascadeType.ALL })
	private List<Cic> cicList;
	
	 
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
