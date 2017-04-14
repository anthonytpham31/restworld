package restworld.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import restworld.datatype.Reference;
import restworld.persistence.entity.Guest;
import restworld.validation.group.RequiredFieldsNotNull;

public class GroupDto {
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private Integer size;
	
	@NotNull(groups = RequiredFieldsNotNull.class)
	private String name;

	private Set<Reference<Guest, Long>> guestMembers;
	
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
	
	public Set<Reference<Guest, Long>> getGuestMembers() {
		return guestMembers;
	}

	public void setGuestMembers(Set<Reference<Guest, Long>> guestMembers) {
		this.guestMembers = guestMembers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		GroupDto other = (GroupDto) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	
}
