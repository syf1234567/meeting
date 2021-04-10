package com.meeting.demo.api;

import com.meeting.demo.entity.MeetingRoom;
import com.meeting.demo.serviceImpl.MeetingRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/meetingRoom")
public class MeetingRoomApi {

    @Autowired
    private MeetingRoomServiceImpl meetingRoomService;

    @RequestMapping("/getAll")
    public List<MeetingRoom> getAll(){
        return meetingRoomService.getAll();
    }

    @RequestMapping("/getByUserId")
    public List<MeetingRoom> getByUserId(Integer userId,String day){
        return meetingRoomService.getByUserId(userId,day);
    }

}
