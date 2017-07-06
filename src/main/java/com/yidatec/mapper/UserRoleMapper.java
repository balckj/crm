package com.yidatec.mapper;

import com.yidatec.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author QuShengWen
 */
@Mapper
public interface UserRoleMapper {
	/**
	 * 检索
	 * @return
	 */
	@Select("SELECT * FROM T_USER_ROLE;")
	List<UserRole> findAll();

	
	/**
	 * 插入
	 * @param userRole
	 * @return
	 */
	@Insert("INSERT INTO T_USER_ROLE (userId,roleId) VALUES (#{userId},#{roleId})")
	int insertUserRole(UserRole userRole);
	
	/**
	 * 根据所属id删除相应信息
	 * @param userId
	 * @return
	 */

	@Delete("DELETE FROM T_USER_ROLE WHERE userId=#{userId}")
	int deleteUserRole(String userId);

}