package com.ajeesia.personal.controller;

import com.ajeesia.personal.Result.Result;
import com.ajeesia.personal.entity.TbUserLike;
import com.ajeesia.personal.service.TbUserLikeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description:
 * @Author: ajeesia
 * @Date: 2020/6/18 16:58
 * @Version: 1.0
 */
public class TbUserLikeController {

    @Resource
    private TbUserLikeService tbUserLikeService;

    @PostMapping("/add")
    public Result add(@RequestBody TbUserLike tbUserLike) {
        Result result = new Result();
        tbUserLike.setCreateTime(new Date());
        tbUserLike.setUpdateTime(new Date());
        if (tbUserLikeService.save(tbUserLike)) {
            result.setCode(200);
            result.setMsg("操作成功");
        }
        return result;
    }
}
