package com.yidatec.mapper;

import com.yidatec.model.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author QuShengWen
 */
@Mapper
public interface RolePermissionMapper {
	@Select("SELECT * FROM T_ROLE_PERMISSION;")
	List<RolePermission> findAll();


	/**
	 * 插入
	 * @param rolePermission
	 * @return
	 */
	@Insert("INSERT INTO T_ROLE_PERMISSION (roleId,permissionId) VALUES (#{roleId},#{permissionId})")
	int insertRolePermission(RolePermission rolePermission);
	
	/**
	 * 删除
	 * @param roleId
	 * @return
	 */
	@Delete("DELETE FROM T_ROLE_PERMISSION WHERE roleId=#{roleId}")
	int deleteRolePermission(String roleId);

}
