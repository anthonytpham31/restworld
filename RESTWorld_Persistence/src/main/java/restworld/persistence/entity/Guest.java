package restworld.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import restworld.persistence.entity.embeddable.Credentials;
import restworld.persistence.entity.embeddable.FullName;
import restworld.persistence.entity.superclass.BaseEntity;

@Entity
public class Guest implements BaseEntity<Long> {

	@Id
	@GeneratedValue
	private Long id;
	
	private FullName name;
	
	private Credentials credentials;
	
	@ManyToOne
	private Group members;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public FullName getName() {
		return name;
	}

	public void setName(FullName name) {
		this.name = name;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	public Group getMembers() {
		return members;
	}

	public void setMembers(Group members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Guest other = (Guest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
