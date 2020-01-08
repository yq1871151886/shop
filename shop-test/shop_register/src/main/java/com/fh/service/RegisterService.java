package com.fh.service;

import com.fh.beans.User;
import com.fh.beans.po.UserPo;
import com.fh.beans.vo.UserVo;
import com.fh.enumbean.LoginCode;
import com.fh.utils.PageBean;

public interface RegisterService {

    LoginCode addUser(User user);

    LoginCode deleteUserById(String id);

    LoginCode UpdateUser(User user);

    PageBean<UserVo> queryUserPageList(PageBean pageBean,UserPo userPo);

}
