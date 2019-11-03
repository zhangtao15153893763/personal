package com.ajeesia.personal.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMenu implements Serializable {
    private String id;
    private String menuName;

}