package com.ajeesia.personal.mapper;

import com.ajeesia.personal.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

//    查询用户信息、角色、权限
    SysUser selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    String queryById(String id);

    SysUser findByName(String userName);


}