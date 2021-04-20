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

    public List<MeetingRoom> getAll() {
        return meetingRoomMapper.selectList(null);
    }

    public List<MeetingRoom> getByUserId(Integer userId, String day) {
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("status","已预约").ge("day", day).select("DISTINCT room_id");
        queryWrapper.or().eq("user_id", userId).eq("status","开门").ge("day", day).select("DISTINCT room_id");
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        List<SubscribeHistory> subscribeHistories1 = subscribeHistoryMapper.selectList(queryWrapper);
        for (SubscribeHistory subscribeHistory : subscribeHistories1) {
            QueryWrapper<MeetingRoom> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("id", subscribeHistory.getRoomId());
            MeetingRoom meetingRoom = meetingRoomMapper.selectOne(queryWrapper1);
            if (meetingRoom != null) {
                meetingRooms.add(meetingRoom);
            }
        }
        return meetingRooms;
    }

    public void delete(Integer id){
        meetingRoomMapper.deleteById(id);
    }

    public MeetingRoom getById(Integer id){
        return meetingRoomMapper.selectById(id);
    }

    public void update(MeetingRoom meetingRoom){
        meetingRoomMapper.updateById(meetingRoom);
    }

    public void insert(MeetingRoom meetingRoom){
        meetingRoomMapper.insert(meetingRoom);
    }

}
