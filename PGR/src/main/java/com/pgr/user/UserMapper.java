package com.pgr.user;

import org.apache.ibatis.annotations.Mapper;

import com.pgr.model.UserDomain;
import com.pgr.model.UserEntity;

@Mapper
public interface UserMapper {
	public int insUser(UserEntity p);
	public UserEntity selUser(UserEntity p);
	public UserEntity findUserByUserId(UserEntity p);
	public int updateUserPassword(UserEntity p);
<<<<<<< HEAD
	public int profileChange(UserDomain p);
=======
	public int pwChange(UserDomain p);
	int updProperty(UserEntity p);
>>>>>>> f09c6f47dd9500ad488033ef66aad41a3377605a
}
