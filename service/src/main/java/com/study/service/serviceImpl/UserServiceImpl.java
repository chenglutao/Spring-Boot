package com.study.service.serviceImpl;

import com.study.repository.dao.generate.UserMapper;
import com.study.repository.entity.generate.User;
import com.study.repository.entity.generate.UserExample;
import com.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    public User getName(String name) {
        log.info("name----->{}", name);
        if (StringUtils.isBlank(name)) {
            return null;
        }
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0);
    }
}
