package com.ajeesia.personal.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class SysUser implements Serializable {

    private String id;

    private String userName;

    private String password;

    private Set<SysRole> sysRoles = new HashSet<>();
}