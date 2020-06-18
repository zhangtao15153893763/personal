package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class SysRoleMenu {
    @TableId
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private Date createTime;

    private Date updateTime;

}