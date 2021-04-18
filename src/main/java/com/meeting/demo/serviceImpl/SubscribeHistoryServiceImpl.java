package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingRoom;
import com.meeting.demo.entity.MeetingUsers;
import com.meeting.demo.entity.SubscribeHistory;
import com.meeting.demo.entity.dto.SubscribeHistoryDto;
import com.meeting.demo.mapper.MeetingRoomMapper;
import com.meeting.demo.mapper.MeetingUserMapper;
import com.meeting.demo.mapper.SubscribeHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscribeHistoryServiceImpl {

    @Autowired
    private SubscribeHistoryMapper subscribeHistoryMapper;

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    public List<SubscribeHistory> getByRoomIdAndDay(Integer roomId,String day){
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("day",day).eq("room_id",roomId).eq("status","已预约");
        queryWrapper.or().eq("status","开门").eq("day",day).eq("room_id",roomId);
        return subscribeHistoryMapper.selectList(queryWrapper);
    }

    public SubscribeHistory getByRoomIdAndDayAndTime(Integer roomId,String day,String time){
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("day",day).eq("room_id",roomId).eq("subscribe_time",time).eq("status","已预约");
        return subscribeHistoryMapper.selectOne(queryWrapper);
    }

    public void insert(SubscribeHistory subscribeHistory){
        if(getByRoomIdAndDayAndTime(subscribeHistory.getRoomId(),subscribeHistory.getDay(),subscribeHistory.getSubscribeTime())==null){
            subscribeHistoryMapper.insert(subscribeHistory);
        }else{

        }
    }

    public List<SubscribeHistory> getByUserId(Integer roomId,String day,Integer userId){
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id",roomId).ge("day",day).eq("status","已预约").eq("user_id",userId);
        return subscribeHistoryMapper.selectList(queryWrapper);
    }

    public void update(SubscribeHistory subscribeHistory){
        subscribeHistoryMapper.updateById(subscribeHistory);
    }

    public List<SubscribeHistoryDto> selectAll(){
        QueryWrapper<SubscribeHistory> subscribeHistoryQueryWrapper = new QueryWrapper<>();
        subscribeHistoryQueryWrapper.orderByDesc("day");
        List<SubscribeHistory> subscribeHistories = subscribeHistoryMapper.selectList(subscribeHistoryQueryWrapper);
        List<SubscribeHistoryDto> subscribeHistoryDto = new ArrayList<>();
        for (SubscribeHistory subscribeHistory : subscribeHistories) {
            MeetingRoom meetingRoom = meetingRoomMapper.selectById(subscribeHistory.getRoomId());
            MeetingUsers meetingUsers = meetingUserMapper.selectById(subscribeHistory.getUserId());
            SubscribeHistoryDto subscribeHistoryDto1 = new SubscribeHistoryDto(subscribeHistory.getId(),subscribeHistory.getDay(),subscribeHistory.getSubscribeTime(),meetingRoom,subscribeHistory.getStatus(),meetingUsers);
            subscribeHistoryDto.add(subscribeHistoryDto1);
        }
        return subscribeHistoryDto;
    }

    public void delete(Integer id){
        subscribeHistoryMapper.deleteById(id);
    }
}
