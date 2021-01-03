package jwt.service;



import jwt.Entity.Permission;
import jwt.Entity.User;
import jwt.dto.PermissionDto;

import java.util.ArrayList;
import java.util.List;

public interface PermissionService {

	Permission saveDefaultPermissions(User user);

	ArrayList<Permission> getPermissionsByUser(User user);

	boolean savePermission(List<Permission> permission);

	List<PermissionDto> getPermissionByUserId(String id);

	boolean removePermission(int id);
}
