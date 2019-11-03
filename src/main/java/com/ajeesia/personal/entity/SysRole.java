package com.ajeesia.personal.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class SysRole implements Serializable {
    private String id;

    private String roleName;

    private Set<SysMenu> sysMenus = new HashSet<>();

}