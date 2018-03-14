package eoh.cic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import eoh.cic.jpa.PersistentObject;

/**
 * @author gpgoosen
 * @since 2015/11/03
 *
 */
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public abstract class DefaultPojo implements PersistentObject, Serializable {

	private static final long serialVersionUID = -8901066761396617423L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "pojo_id_generator")
	@TableGenerator(name = "pojo_id_generator", pkColumnName = "pojo_id_generator", allocationSize = 100, table = "GENERATORS", valueColumnName = "SEQ_NUMBER", pkColumnValue = "ENTITY")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

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
		return (other instanceof DefaultPojo) && (id != null) ? id.equals(((DefaultPojo) other).id) : (other == this);
	}

	@Override
	public int hashCode() {
		return (id != null) ? (this.getClass().hashCode() + id.hashCode()) : super.hashCode();
	}
}
