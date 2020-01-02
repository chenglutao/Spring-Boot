package com.study.webapi.controller;

import com.study.repository.entity.generate.User;
import com.study.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenglutao
 * @date 2019/11/30 14:02
 */
@Slf4j
@RestController
@Api(value = "用户管理")
public class MyController {

    @Autowired
    UserService userService;


    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParam(name = "name", value = "添加用户", required = true, dataType = "String", paramType = "query")
    @ApiResponse(code = 400, message = "请检查参数", response = String.class)
    public Object add(@RequestParam("name") String name) {
        userService.add(name);
        return "ok";
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query")
    @ApiResponses({
            @ApiResponse(code = 401, message = "请检查参数", response = String.class),
            @ApiResponse(code = 404, message = "找不到页面", response = String.class)
    })
    public Object delete(@RequestParam("id") Integer id) {
        userService.delete(id);
        return "ok";
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "修改名字", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 401, message = "请检查参数", response = String.class),
            @ApiResponse(code = 404, message = "找不到页面", response = String.class)
    })
    public Object update(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        userService.update(id, name);
        return "ok";
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取用户详情", notes = "获取用户详情")
    @ApiImplicitParam(name = "id", value = "获取用户详情", required = true, dataType = "String", paramType = "query")
    public Object detail(@RequestParam("id") Integer id) {
        User detail = userService.detail(id);
        return detail;
    }

    @GetMapping("email")
    @ApiOperation(value = "邮件服务", notes = "邮件服务")
    public Object email(){
        try{
            userService.asyncSendHtml();
            return "ok";
        } catch (Exception e){
            return e.getMessage();
        }

    }
}
