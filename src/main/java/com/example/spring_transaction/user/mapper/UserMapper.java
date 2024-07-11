package com.example.spring_transaction.user.mapper;

import com.example.spring_transaction.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);
    long count();

    User selectById(@Param("id") Long id);

    int update(@Param("user") User user);

    int insertAll(@Param("users") User[] users);
}
