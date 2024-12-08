package com.api.redis.dao;

import com.api.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private static final String key = "USER123";

    //save user
    public User save(User user) {
        redisTemplate.opsForHash().put(key, user.getUserId(), user);
        return user;
    }

    //get user
    public User getUser(String id){
        return (User) redisTemplate.opsForHash().get(key, id);
    }

    // find all
    public Map<Object, Object> findAll(){
        return redisTemplate.opsForHash().entries(key);
    }

    //update
    public void update(User user){
        redisTemplate.opsForHash().put(key, user.getUserId(), user);
    }

    //delete
    public void delete(String id){
        redisTemplate.opsForHash().delete(key, id);
    }

}
