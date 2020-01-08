package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.beans.User;
import com.fh.beans.po.UserPo;
import com.fh.beans.vo.UserVo;
import com.fh.utils.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RegisterDao extends BaseMapper<User> {

    Long queryRegisterCount(@Param("user") UserPo user);
    List<UserVo> queryRegisterPageList(@Param("pageBean") PageBean pageBean,@Param("user") UserPo user);


}
