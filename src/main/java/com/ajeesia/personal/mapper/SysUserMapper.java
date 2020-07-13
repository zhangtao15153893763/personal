package com.ajeesia.personal.mapper;

import com.ajeesia.personal.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    SysUser getUserByUserName(String userName);
}