package com.yidatec.mapper;

import com.yidatec.model.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author QuShengWen
 */
@Mapper
public interface PermissionMapper {

	@Select("SELECT * FROM T_PERMISSION ORDER BY sort")
	List<Permission> findAll();
	
	int insertPermission(Permission permission);

	@Select("SELECT P.id AS id, P.name AS name FROM T_ROLE_PERMISSION RP  LEFT OUTER JOIN T_PERMISSION P ON P.id = RP.permissionId WHERE RP.roleId = #{roleId} ORDER BY P.sort")
	List<Permission> findByRoleId(String roleId);
}
