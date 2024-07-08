package com.example.spring_transaction.user.mapper;

import com.example.spring_transaction.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(@Param("user") User user);
    long count();

    User selectById(@Param("id") Long id);
}
