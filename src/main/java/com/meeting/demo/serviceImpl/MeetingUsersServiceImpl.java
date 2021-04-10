package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingUsers;
import com.meeting.demo.mapper.MeetingUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MeetingUsersServiceImpl {

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    public MeetingUsers getByOpenId(String openId) {
        QueryWrapper<MeetingUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("open_id", openId);
        return meetingUserMapper.selectOne(queryWrapper);
    }

    public MeetingUsers insert(String openId) {
        MeetingUsers meetingUsers = new MeetingUsers();
        meetingUsers.setOpenId(openId);
        meetingUserMapper.insert(meetingUsers);
        return meetingUsers;
    }

    public void update(MeetingUsers meetingUsers){
        meetingUserMapper.updateById(meetingUsers);
    }

}
