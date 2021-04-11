package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingAdmin;
import com.meeting.demo.mapper.MeetingAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingAdminServiceImpl {

    @Autowired
    private MeetingAdminMapper meetingAdminMapper;

    public String login(String userName,String password){
        QueryWrapper<MeetingAdmin> meetingAdminQueryWrapper = new QueryWrapper<>();
        meetingAdminQueryWrapper.eq("user_name",userName).eq("password",password);
        if(meetingAdminMapper.selectCount(meetingAdminQueryWrapper)>0){
            return "登录成功";
        }
        else {
            return "登录失败";
        }
    }
}
