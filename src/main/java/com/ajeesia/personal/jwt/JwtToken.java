package com.ajeesia.personal.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: ajeesia
 * @UpdateDate: 2019/11/11 21:32
 * @Version: 1.0
 */
public class JwtToken {
    public String createToke(){
        String secret = "secret"; // token 密钥
        Algorithm algorithm = Algorithm.HMAC256("secret");

        //头部信息
        Map<String,Object> map = new HashMap<>();
        map.put("alg","HS256");
        map.put("typ","JWT");

        Date date = new Date();
        return null;
    }
}
