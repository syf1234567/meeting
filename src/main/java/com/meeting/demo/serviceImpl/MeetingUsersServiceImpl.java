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

    public List<MeetingUsers> getAll(){
        return meetingUserMapper.selectList(null);
    }

    public MeetingUsers getById(Integer id){
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

    public void insert(MeetingUsers meetingUsers){
        meetingUserMapper.insert(meetingUsers);
    }
    public MeetingUsers insert(String openId) {
        MeetingUsers meetingUsers = new MeetingUsers();
        meetingUsers.setOpenId(openId);
        meetingUserMapper.insert(meetingUsers);
        return meetingUsers;
    }

    public void update(MeetingUsers meetingUsers) {
        meetingUserMapper.updateById(meetingUsers);
    }

}
