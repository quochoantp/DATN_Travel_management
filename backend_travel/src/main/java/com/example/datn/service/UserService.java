package com.example.datn.service;

import com.example.datn.dto.UserJwtDTO;
import com.example.datn.dto.UserStatusDTO;
import com.example.datn.entity.User;
import com.example.datn.repository.UserRepository;
import com.example.datn.util.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getListUserStatus(String id, String username, String status){
        List<User> userList = new ArrayList<>();
        if(id == null || id.equals("null")){
            id = "0";
        }
        if(status == null || status.equals("null")){
            status = "2";
        }
        if(username == null || username.equals("null")){
            username = "";
        }
        if(userRepository.userList(Long.valueOf(id), username,Integer.valueOf(status)).isPresent()){
            return userRepository.userList(Long.valueOf(id), username,Integer.valueOf(status)).get();
        }
        return userList;
    }
    public void updateStatusUser(UserStatusDTO userStatusDTO){
        userRepository.updateStatus(userStatusDTO.getId(),userStatusDTO.getStatus());
    }

    public User getUserByUserName(String userName){
        User user = new User();
        if(userRepository.getUserByUsername(userName).isPresent()){
            return userRepository.getUserByUsername(userName).get();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserJwtDTO(user);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user.getPhoneNumber(),user.getIdentityCard(),user.getAddress(), DateFormatter.format(user.getCreatedDate()), user.getDob(), user.getEmail(), user.getFullname(), user.getGender(), user.getPassword(), user.getUsername());
    }
}
