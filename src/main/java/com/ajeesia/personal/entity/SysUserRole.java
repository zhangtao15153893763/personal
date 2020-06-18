package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class SysUserRole {
    @TableId
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date createTime;

    private Date updateTime;

}