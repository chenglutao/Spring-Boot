package com.study.service.serviceImpl;

import com.study.repository.dao.generate.UserMapper;
import com.study.repository.entity.generate.User;
import com.study.repository.entity.generate.UserExample;
import com.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenglutao
 * @date 2019/11/30 14:12
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User detail(Integer id) {
        log.info("id----->{}", id);
        if (id == null) {
            return null;
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)){
            return null;
        }
        return users.get(0);
    }

    @Override
    public void add(String name) {
        User user = new User();
        user.setName(name);
        userMapper.insert(user);
    }

    @Override
    public void update(Integer id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }
}
