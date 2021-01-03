package jwt.repository;

import jwt.Entity.Permission;
import jwt.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
   
	ArrayList<Permission> findPermissionsByUser(User user);

	@Query("from Permission p  where  p.user.userId =?1 ")
	List<Permission> getPermissionByUserId(int userId);
}