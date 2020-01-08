package com.fh.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {


}
