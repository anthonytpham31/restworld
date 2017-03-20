package restworld.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import restworld.component.ServiceUtilities;
import restworld.component.ServiceUtilities.IdChecker;
import restworld.dto.GroupDto;
import restworld.mapper.GroupMapper;
import restworld.persistence.entity.Group;
import restworld.persistence.repository.GroupRepository;

@Service
public class GroupService {

	GroupRepository groupRepository;
	GroupMapper groupMapper;
	ServiceUtilities serviceUtilities;
	IdChecker idChecker;
	
	public GroupService(GroupRepository groupRepository, GroupMapper groupMapper, ServiceUtilities serviceUtilities) {
		super();
		this.groupRepository = groupRepository;
		this.groupMapper = groupMapper;
		this.serviceUtilities = serviceUtilities;
		this.idChecker = serviceUtilities.buildIdChecker(Group.class, this::has);
	}
	
	public List<GroupDto> index() {
		return groupRepository
				.findAll()
				.stream()
				.map(groupMapper::toGroupDto)
				.collect(Collectors.toList());
	}

	public List<GroupDto> sorted(Sort sort) {
		return groupRepository
				.findAll(sort)
				.stream()
				.map(groupMapper::toGroupDto)
				.collect(Collectors.toList());
	}
	
	public List<GroupDto> size(Integer min, Integer max) {
		return groupRepository
				.findAll()
				.stream()
				.map(groupMapper::toGroupDto)
				.filter(groupDto -> groupDto.getSize() >= min && max >= groupDto.getSize())
				.collect(Collectors.toList());
	}

	public Page<GroupDto> paged(Pageable pageable) {
		return groupRepository
				.findAll(pageable)
				.map(groupMapper::toGroupDto);
	}

	public List<GroupDto> byExample(GroupDto example) {
		return groupRepository
				.findAll(Example.of(groupMapper.toGroup(example)))
				.stream()
				.map(groupMapper::toGroupDto)
				.collect(Collectors.toList());
	}


	public GroupDto get(Long id) {
		idChecker.exists(id);
		return groupMapper.toGroupDto(groupRepository.findOne(id));
	}

	public Long post(GroupDto groupDto) {
		return groupRepository.save(groupMapper.toGroup(groupDto)).getId();
	}

	public boolean has(Long id) {
		if(id != null)
			return groupRepository.exists(id);
		return false;
	}

	public void put(Long id, GroupDto groupDto) {
		idChecker.exists(id);
		Group group = groupMapper.toGroup(groupDto);
		group.setId(id);
		groupRepository.save(group);
	}
	
	public void patch(Long id, GroupDto groupDto) {
		idChecker.exists(id);
		groupRepository.save(serviceUtilities.copyNonNullProperties(groupMapper.toGroup(groupDto), groupRepository.findOne(id)));
	}
	
	public void delete(Long id) {
		idChecker.exists(id);
		groupRepository.delete(id);
	}
}
