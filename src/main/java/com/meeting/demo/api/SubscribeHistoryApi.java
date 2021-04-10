package com.meeting.demo.api;

import com.meeting.demo.entity.SubscribeHistory;
import com.meeting.demo.serviceImpl.SubscribeHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/subscribeHistory")
@CrossOrigin
@RestController
public class SubscribeHistoryApi {

    @Autowired
    private SubscribeHistoryServiceImpl subscribeHistoryService;

    @RequestMapping("/getByRoomIdAndDay")
    public List<SubscribeHistory> getByRoomIdAndDay(Integer roomId,String day){
        return subscribeHistoryService.getByRoomIdAndDay(roomId,day);
    }

    @RequestMapping("/insert")
    public void insert(SubscribeHistory subscribeHistory){
        subscribeHistoryService.insert(subscribeHistory);
    }

    @RequestMapping("/getByUserId")
    public List<SubscribeHistory> getByUserId(Integer roomId,String day,Integer userId){
        return subscribeHistoryService.getByUserId(roomId,day,userId);
    }

    @RequestMapping("/update")
    public void update(SubscribeHistory subscribeHistory){
        subscribeHistoryService.update(subscribeHistory);
    }
}
