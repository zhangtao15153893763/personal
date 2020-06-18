package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class SysRole implements Serializable {
    @TableId
    private Integer id;

    private String roleName;

    private Date createTime;

    private Date updateTime;
}