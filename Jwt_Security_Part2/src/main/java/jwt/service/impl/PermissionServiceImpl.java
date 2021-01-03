package jwt.service.impl;


import jwt.Entity.Permission;
import jwt.Entity.User;
import jwt.dto.PermissionDto;
import jwt.repository.PermissionRepository;
import jwt.service.PermissionService;
import jwt.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public Permission saveDefaultPermissions(User user) {

		Permission permission = new Permission();

		//filter based on the role
		switch ( user.getRole()) {
			
			case CommonConstants.SYSTEM_USER_ADMIN:
				permission.setScreen("ALL");
				permission.setReadData(true);
				permission.setWriteData(true);
				permission.setDeleteData(true);
				
				break;
				
			case CommonConstants.SYSTEM_USER_PROPERTY_MANAGER: 
				permission.setScreen("ALL");
				permission.setReadData(true);
				permission.setWriteData(true);
				permission.setDeleteData(true);
				
				break;
				
			case CommonConstants.SYSTEM_USER_OPERATIONAL_MANAGER:
				permission.setScreen("ALL");
				permission.setReadData(true);
				permission.setWriteData(true);
				permission.setDeleteData(true);
				
				break;
				
			default : 
				permission.setScreen("ALL");
				permission.setReadData(true);
				permission.setWriteData(true);
				permission.setDeleteData(true);

		}

		/*
		permission.setScreen("all");
		permission.setReadData(true);
		permission.setWriteData(true);
		permission.setDeleteData(true);
		*/
		permission.setUser(user);
		//save the permission object
		return permissionRepository.save(permission);
	}

	@Override
	public ArrayList<Permission> getPermissionsByUser(User user) {
		
		return permissionRepository.findPermissionsByUser(user);
	}

	@Override
	public boolean savePermission(List<Permission> permission) {
		int size = permission.size();
		int count=0;
		for (Permission per : permission){
			count++;
			permissionRepository.save(per);
		}
		if(count==size){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<PermissionDto> getPermissionByUserId(String id) {
		int userId = Integer.parseInt(id.trim());
		List<Permission> permissionList = permissionRepository.getPermissionByUserId(userId);
		List<PermissionDto> permissionDtos = new ArrayList<>();
		for(Permission p : permissionList){
			permissionDtos.add(new PermissionDto(p));
		}
		return permissionDtos;
	}

	@Override
	public boolean removePermission(int id) {
		permissionRepository.deleteById(id);
		return true;
	}

}
