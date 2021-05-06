package com.meeting.demo.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meeting.demo.entity.MeetingRoom;
import com.meeting.demo.entity.MeetingUsers;
import com.meeting.demo.entity.ProTimes;
import com.meeting.demo.entity.SubscribeHistory;
import com.meeting.demo.entity.dto.SubscribeHistoryDto;
import com.meeting.demo.mapper.MeetingRoomMapper;
import com.meeting.demo.mapper.MeetingUserMapper;
import com.meeting.demo.mapper.SubscribeHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class SubscribeHistoryServiceImpl {

    @Autowired
    private SubscribeHistoryMapper subscribeHistoryMapper;

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    public ProTimes getTimes(Integer id){
        Integer[] open = new Integer[7];
        Integer[] pro = new Integer[7];
        for (int i = 7; i > 0; i--) {
            String date = "";
            if(getDateAdd(i).charAt(0)=='0'){
                date=getDateAdd(i).substring(1);
            }
            QueryWrapper<SubscribeHistory> subscribeHistoryQueryWrapper = new QueryWrapper<>();
            subscribeHistoryQueryWrapper.eq("room_id",id).eq("status","已预约").eq("day",date);
            open[7-i]= subscribeHistoryMapper.selectList(subscribeHistoryQueryWrapper).size();
            QueryWrapper<SubscribeHistory> subscribeHistoryQueryWrapper1 = new QueryWrapper<>();
            subscribeHistoryQueryWrapper1.eq("room_id",id).eq("status","开门").eq("day",date);
            pro[7-i]= subscribeHistoryMapper.selectList(subscribeHistoryQueryWrapper1).size();
        }
        return new ProTimes(open,pro);
    }

    private static String getDateAdd(int days){
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return sf.format(c.getTime());
    }

    public List<SubscribeHistory> getByRoomIdAndDay(Integer roomId, String day) {
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("day", day).eq("room_id", roomId).eq("status", "已预约");
        queryWrapper.or().eq("status", "开门").eq("day", day).eq("room_id", roomId);
        return subscribeHistoryMapper.selectList(queryWrapper);
    }

    public SubscribeHistory getByRoomIdAndDayAndTime(Integer roomId, String day, String time, String status) {
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("day", day).eq("room_id", roomId).eq("subscribe_time", time).eq("status", status);
        return subscribeHistoryMapper.selectOne(queryWrapper);
    }

    public void insert(SubscribeHistory subscribeHistory) {
        if (getByRoomIdAndDayAndTime(subscribeHistory.getRoomId(), subscribeHistory.getDay(), subscribeHistory.getSubscribeTime(), subscribeHistory.getStatus()) == null) {
            subscribeHistoryMapper.insert(subscribeHistory);
        } else {

        }
    }

    public List<SubscribeHistory> getByUserId(Integer roomId, String day, Integer userId) {
        QueryWrapper<SubscribeHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", roomId).ge("day", day).eq("status", "已预约").eq("user_id", userId);
        return subscribeHistoryMapper.selectList(queryWrapper);
    }

    public void update(SubscribeHistory subscribeHistory) {
        subscribeHistoryMapper.updateById(subscribeHistory);
    }

    public List<SubscribeHistoryDto> selectAll() {
        QueryWrapper<SubscribeHistory> subscribeHistoryQueryWrapper = new QueryWrapper<>();
        subscribeHistoryQueryWrapper.orderByDesc("day");
        List<SubscribeHistory> subscribeHistories = subscribeHistoryMapper.selectList(subscribeHistoryQueryWrapper);
        List<SubscribeHistoryDto> subscribeHistoryDto = new ArrayList<>();
        for (SubscribeHistory subscribeHistory : subscribeHistories) {
            MeetingRoom meetingRoom = meetingRoomMapper.selectById(subscribeHistory.getRoomId());
            MeetingUsers meetingUsers = meetingUserMapper.selectById(subscribeHistory.getUserId());
            SubscribeHistoryDto subscribeHistoryDto1 = new SubscribeHistoryDto(subscribeHistory.getId(), subscribeHistory.getDay(), subscribeHistory.getSubscribeTime(), meetingRoom, subscribeHistory.getStatus(), meetingUsers);
            subscribeHistoryDto.add(subscribeHistoryDto1);
        }
        return subscribeHistoryDto;
    }

    public void delete(Integer id) {
        subscribeHistoryMapper.deleteById(id);
    }
}
