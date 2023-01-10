package com.cmqsystem.jedis;

import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.Random;

/**
 * @Description TODO
 * @Author admin
 * @Date 2023/1/9
 **/
public class PhoneCode {

    public static void main(String[] args) {
        verifyCode("13509803316");

//        getRedisCode("13509803316","695484");
    }


    public static void getRedisCode(String phone,String code) {
        Jedis jedis = new Jedis("43.139.108.162",6379);
        jedis.auth("root");
        String codeKey = "VerifyCode"+phone+":code";
        String redisCode = jedis.get(codeKey);

        if (redisCode==null) {
            System.out.println("验证码过期，请重新获取！！");
            jedis.close();
            return;
        }

        if (Objects.equals(redisCode,code)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        jedis.close();
    }


    /**
     * @Description 每个手机每天只能发送3次，验证码放在redis中，设置过期时间
     * */
    public static void verifyCode(String phone) {
        Jedis jedis = new Jedis("43.139.108.162",6379);
        jedis.auth("root");
        String countKey = "VerifyCode"+phone+":count";

        String codeKey = "VerifyCode"+phone+":code";

        String count = jedis.get(countKey);
        if (count == null) {
            jedis.setex(countKey,24*60*60,"1");
        } else if(Integer.parseInt(count)<=2) {
            // 发送次数+1
            jedis.incr(countKey);
        } else {
            System.out.println("今天发送次数已经超过3次");
            jedis.close();
            return;
        }

        String vcode = getCode();
        System.out.println(vcode);
        jedis.setex(codeKey,120,vcode);
        jedis.close();
    }

    /**
     * @Description 获取随机验证码
     **/
    public static String getCode() {
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }


}
