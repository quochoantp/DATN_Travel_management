package com.example.datn.repository;

import com.example.datn.entity.User;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select distinct new com.example.datn.entity.User(u.id,u.username,u.fullname,u.phoneNumber,u.identityCard,u.email,u.address,u.dob,u.gender,u.password,u.createdDate,u.status) from User u where ((lower(u.username) like lower(concat('%',?2,'%')))or(?2 = '' ))  and ((u.status = ?3 )or(?3 = 2)) and ((u.id = ?1 )or(?1 = 0))")
    public Optional<List<User>> userList(Long id, String username, Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE user u set u.status = ?2 where u.id = ?1",nativeQuery = true)
    public void updateStatus(Long id, Integer status);

    public Optional<User> getUserByUsername(String username);

    User findByUsername(String username);

    @Modifying
    @Query(value = "insert into user (phone_number,identity_card,address, created_date, dob, email, fullname, gender, password, username) VALUES (:phone_number, :identity_card, :address, :created_date, :dob, :email, :fullname, :gender, :password, :username)", nativeQuery = true)
    @Transactional
    void save(@Param("phone_number") String phone_number,@Param("identity_card") String identity_card, @Param("address") String address, @Param("created_date") String created_date, @Param("dob") String dob, @Param("email") String email, @Param("fullname") String fullname, @Param("gender") String gender, @Param("password") String password, @Param("username") String username);
}
