package restworld.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import restworld.dto.GroupDto;
import restworld.service.GroupService;
import restworld.validation.group.RequiredFieldsNotNull;

@RestController
@Validated
@RequestMapping("group")
@Api(tags = {"public", "groups"})
public class GroupController {
	
	private GroupService groupService;

	public GroupController(GroupService groupService) {
		super();
		this.groupService = groupService;
	}

	@GetMapping
	@ApiOperation(value = "", nickname = "getAllGroups")
	public List<GroupDto> index() {
		return groupService.index();
	}

	@GetMapping("sorted")
	@ApiOperation(value = "", nickname = "getSortedGroups")
	public List<GroupDto> sorted(Sort sort) {
		return groupService.sorted(sort);
	}

	@GetMapping("paged")
	@ApiOperation(value = "", nickname = "getPagedGroups")
	public Page<GroupDto> paged(Pageable pageable) {
		return groupService.paged(pageable);
	}

	@GetMapping("size")
	@ApiOperation(value = "", nickname = "getSizeGroup")
	public List<GroupDto> size(@RequestParam(value = "min", defaultValue = "0") Integer min, @RequestParam(value = "max", defaultValue = "100") Integer max, HttpServletResponse httpResponse) {
		return groupService.size(min, max);
	}
	
	@PostMapping("search")
	@ApiOperation(value = "", nickname = "searchGroups")
	public List<GroupDto> byExample(GroupDto example) {
		return groupService.byExample(example);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value = "{id}")
	@ApiOperation(value = "", nickname = "verifyGroup")
	public void has(@PathVariable Long id, HttpServletResponse httpResponse) {
		if(!groupService.has(id))
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "", nickname = "getGroup")
	public GroupDto get(@PathVariable Long id) {
		return groupService.get(id);
	}
	
	@PostMapping
	@ApiOperation(value = "", nickname = "createGroup")
	public Long post(@RequestBody @Validated(RequiredFieldsNotNull.class) GroupDto groupDto, HttpServletResponse httpResponse) {
		Long id = groupService.post(groupDto);
		httpResponse.setStatus(HttpServletResponse.SC_CREATED);
		return id;
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "", nickname = "replaceGroup")
	public void put(@PathVariable Long id, @RequestBody @Validated(RequiredFieldsNotNull.class) GroupDto groupDto, HttpServletResponse httpResponse) {
		groupService.put(id, groupDto);
	}
	
	@PatchMapping("{id}")
	@ApiOperation(value = "", nickname = "updateGroup")
	public void patch(@PathVariable Long id, @RequestBody @Validated GroupDto groupDto, HttpServletResponse httpResponse) {
		groupService.patch(id, groupDto);
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "", nickname = "deleteGroup")
	public void delete(@PathVariable Long id, HttpServletResponse httpResponse) {
		groupService.delete(id);
	}
}
