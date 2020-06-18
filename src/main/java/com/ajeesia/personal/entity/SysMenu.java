package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysMenu implements Serializable {
    @TableId
    private Integer id;

    private String menuName;

    private Date createTime;

    private Date updateTime;
}