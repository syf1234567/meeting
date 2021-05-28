package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingUsers;
import com.meeting.demo.mapper.MeetingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MeetingUsersServiceImpl {

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    public String modifyPassword(Integer id,String password1,String password2){
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id).eq("password",password1);
        MeetingUsers meetingUsers = meetingUserMapper.selectOne(queryWrapper);
        if(meetingUsers==null){
            return "旧密码错误";
        }
        meetingUsers.setPassword(password2);
        meetingUserMapper.updateById(meetingUsers);
        return "修改成功";
    }

    public Object register(String userName) {
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        MeetingUsers meetingUsers = meetingUserMapper.selectOne(queryWrapper);
        if (null != meetingUsers) {
            if(meetingUsers.getIsRegister()){
                return "已经注册过了";
            }
            meetingUsers.setIsRegister(true);
            meetingUserMapper.updateById(meetingUsers);
            return meetingUsers;
        }else{
            meetingUsers = new MeetingUsers();
            meetingUsers.setId(34);
            meetingUsers.setPassword("");
            meetingUsers.setUserName("");
            meetingUsers.setIsRegister(false);
            return meetingUsers;
            //return "未导入账号";
        }
    }

    public List<MeetingUsers> getAll() {
        return meetingUserMapper.selectList(null);
    }

    public MeetingUsers getById(Integer id) {
        return meetingUserMapper.selectById(id);
    }

    public MeetingUsers wxLogin(String userName, String password) {
        QueryWrapper<MeetingUsers> meetingUsersQueryWrapper = new QueryWrapper<>();
        meetingUsersQueryWrapper.eq("user_name", userName).eq("password", password);
        return meetingUserMapper.selectOne(meetingUsersQueryWrapper);
    }

    public MeetingUsers getByOpenId(String openId) {
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openId);
        return meetingUserMapper.selectOne(queryWrapper);
    }

    public String insert(MeetingUsers meetingUsers) {
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", meetingUsers.getUserName());
        if (meetingUserMapper.selectCount(queryWrapper) > 0) {
            return "用户名已存在";
        }
        meetingUserMapper.insert(meetingUsers);
        return "添加成功";
    }

    public MeetingUsers insert(String openId) {
        MeetingUsers meetingUsers = new MeetingUsers();
        meetingUsers.setOpenId(openId);
        meetingUserMapper.insert(meetingUsers);
        return meetingUsers;
    }

    public void deleteById(Integer id) {
        meetingUserMapper.deleteById(id);
    }

    public String update(MeetingUsers meetingUsers) {
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", meetingUsers.getUserName()).ne("id", meetingUsers.getId());
        if (meetingUserMapper.selectCount(queryWrapper) > 0) {
            return "用户名已存在";
        }
        meetingUserMapper.updateById(meetingUsers);
        return "更新成功";
    }

}
