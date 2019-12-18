package com.study.service;

import com.study.repository.entity.generate.User;

/**
 * @author chenglutao
 * @date 2019/11/30 14:10
 */
public interface UserService {

    User detail(Integer id);
    void add(String name);
    void update(Integer id, String name);
    void delete(Integer id);
}
