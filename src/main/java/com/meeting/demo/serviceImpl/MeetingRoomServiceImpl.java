package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingRoom;
import com.meeting.demo.entity.SubscribeHistory;
import com.meeting.demo.mapper.MeetingRoomMapper;
import com.meeting.demo.mapper.SubscribeHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingRoomServiceImpl {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Autowired
    private SubscribeHistoryMapper subscribeHistoryMapper;

    public List<MeetingRoom> getAll(){
        return meetingRoomMapper.selectList(null);
    }

    public List<MeetingRoom> getByUserId(Integer userId,String day){
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId).ge("day",day).select("DISTINCT room_id");
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        List<SubscribeHistory> subscribeHistories1 = subscribeHistoryMapper.selectList(queryWrapper);
        for (SubscribeHistory subscribeHistory : subscribeHistories1) {
            QueryWrapper<MeetingRoom> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id",subscribeHistory.getRoomId());
            MeetingRoom meetingRoom = meetingRoomMapper.selectOne(queryWrapper1);
            meetingRooms.add(meetingRoom);
        }
        return meetingRooms;
    }

}
