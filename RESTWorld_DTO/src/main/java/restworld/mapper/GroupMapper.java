package restworld.mapper;

import org.mapstruct.Mapper;

import restworld.dto.GroupDto;
import restworld.persistence.entity.Group;

@Mapper(componentModel = "spring", uses = {ReferenceMapper.class})
public interface GroupMapper {
	
	GroupDto toGroupDto(Group group);
	
	Group toGroup(GroupDto groupDto);
}
