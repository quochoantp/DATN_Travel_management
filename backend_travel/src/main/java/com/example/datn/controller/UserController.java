package com.example.datn.controller;

import com.example.datn.dto.UserStatusDTO;
import com.example.datn.entity.User;
import com.example.datn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getListUserStatus(@RequestParam(name = "id" , required = false) String id,
                                        @RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "status", required = false) String status){
        return userService.getListUserStatus(id,username,status);
    }

    @PostMapping("/user/status")
    public void updateStatusUser(@RequestBody UserStatusDTO userStatusDTO){
      userService.updateStatusUser(userStatusDTO);
    }

    @GetMapping("/user/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }
}
