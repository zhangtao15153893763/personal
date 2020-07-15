package com.ajeesia.utils;

import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @Author: ajeesia
 * @Date: 2020/7/13 18:06
 * @Version: 1.0
 */
@Component
public class UUIDUtils {

    /**
     * 生成32位uuid
     * @return
     */
    public String generate32UUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-","");
    }

}
