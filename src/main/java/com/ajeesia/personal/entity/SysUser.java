package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class SysUser implements Serializable {

    @TableId
    private Integer id;

    private String userName;

    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    private Date createTime;

    private Date updateTime;
}