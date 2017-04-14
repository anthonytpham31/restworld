package restworld.persistence.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import restworld.persistence.entity.superclass.BaseEntity;

@Entity
@Table(name = "NewGroup")
public class Group implements BaseEntity<Long> {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Integer size;
	
	@NotNull
	@Column(unique = true, nullable = false)
	private String name;

	@OneToMany(mappedBy = "members")
	private Set<Guest> groupMembers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Guest> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<Guest> groupMembers) {
		this.groupMembers = groupMembers;
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
