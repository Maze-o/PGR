package com.pgr.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.UserDomain;
import com.pgr.model.UserEntity;

@Mapper
public interface UserMapper {
	public int insUser(UserEntity p);
	public UserEntity selUser(UserEntity p);
	public UserEntity findUserByUserId(UserEntity p);
	public List<UserEntity> selTopUser();
	public int updateUserPassword(UserEntity p);
	public int profileChange(UserDomain p);

	int updProperty(UserEntity p);
	
}
