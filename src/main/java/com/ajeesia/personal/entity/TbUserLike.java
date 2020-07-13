package com.ajeesia.personal.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

/**
 * @Description: 点赞
 * @Author: ajeesia
 * @Date: 2020/6/18 16:56
 * @Version: 1.0
 */
public class TbUserLike {

    @TableId
    private Integer id;

    @TableField
    private Integer userId;

    @TableField
    private Integer useredId;

    @TableField
    private Date createTime;

    @TableField
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUseredId() {
        return useredId;
    }

    public void setUseredId(Integer useredId) {
        this.useredId = useredId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public TbUserLike() {
    }

    public TbUserLike(Integer id, Integer userId, Integer useredId, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.useredId = useredId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
