package restworld.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import restworld.persistence.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long>{
	
	public Group findByName(String name);
	
	@Query("SELECT g FROM Group g WHERE (g.size >= :min AND g.size <= :max)")
	public List<Group> findBySize(@Param("min") Integer min, @Param("max") Integer max);
}
