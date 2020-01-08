package com.fh.service.impl;

import com.fh.beans.User;
import com.fh.beans.po.UserPo;
import com.fh.beans.vo.UserVo;
import com.fh.dao.RegisterDao;
import com.fh.enumbean.LoginCode;
import com.fh.enumbean.LoginEnum;
import com.fh.service.RegisterService;
import com.fh.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;


    @Override
    public LoginCode addUser(User user) {
        registerDao.insert(user);
        return LoginCode.success(LoginEnum.MAKE_SUCCESS);
    }

    @Override
    public LoginCode deleteUserById(String id) {
        registerDao.deleteById(id);
        return LoginCode.success(LoginEnum.MAKE_SUCCESS);
    }

    @Override
    public LoginCode UpdateUser(User user) {
        registerDao.updateById(user);
        return LoginCode.success(LoginEnum.MAKE_SUCCESS);
    }

    @Override
    public PageBean<UserVo> queryUserPageList(PageBean pageBean,UserPo user) {
        Long aLong = registerDao.queryRegisterCount(user);
        pageBean.setRecordsFiltered(aLong);
        pageBean.setRecordsTotal(aLong);
        List<UserVo> userVos = registerDao.queryRegisterPageList(pageBean, user);
        pageBean.setData(userVos);
        return pageBean;
    }
}
