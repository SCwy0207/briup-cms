package com.briup.cms;

import com.briup.cms.common.util.RedisUtil;
import com.briup.cms.dao.SlideshowMapper;
import com.briup.cms.service.SlideshowService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YuYan
 * @date 2023-12-12 17:34:11
 */
@SpringBootTest
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    /* 专门存字符串类型的值 */
    // @Autowired
    // StringRedisTemplate stringRedisTemplate;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Student implements Serializable {
        private Integer id;
        private String name;
        private Integer age;
        private String gender;
    }

    @Test
    public void test1() {
        Student s1 = new Student(1001, "tom", 22, "male");
        Student s2 = new Student(1002, "jack", 23, "male");
        Student s3 = new Student(1003, "lucy", 24, "female");
        /* Redis中存储三个学生的考试成绩 */
        /* 学生对象当成key，分数作为value */
        redisTemplate.opsForValue().set(s1, 100);
        redisTemplate.opsForValue().set(s2, 95);
        redisTemplate.opsForValue().set(s3, 90);

        System.out.println(redisTemplate.opsForValue().get(s1));
        System.out.println(redisTemplate.opsForValue().get(s2));
        System.out.println(redisTemplate.opsForValue().get(s3));
    }


    @Autowired
    SlideshowService slideshowService;

    @Test
    public void test0() {
        // SlideshowExt slideshowExt = slideshowService.getById(1);
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("method", "getById");
        keyMap.put("class", "SlideshowServiceImpl");
        keyMap.put("argType", "Integer");
        keyMap.put("args", 2);
        // System.out.println("slideshowExt = " + slideshowExt);
        // redisTemplate.opsForValue().set(keyMap, slideshowExt);
        Object o = redisTemplate.opsForValue().get(keyMap);
        System.out.println(o);
    }

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    SlideshowMapper slideshowMapper;
    // @Test
    // public void test2() {
    //     IPage page = new Page(1, 1);
    //     LambdaQueryWrapper lqw = new LambdaQueryWrapper();
    //     IPage newPage = slideshowMapper.selectPage(page, lqw);
    //     System.out.println(newPage.getClass().getName());
    //     redisUtil.set();
    // }


}
