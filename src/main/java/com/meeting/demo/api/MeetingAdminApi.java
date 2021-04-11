package com.meeting.demo.api;

import com.meeting.demo.serviceImpl.MeetingAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meetingAdmin")
@CrossOrigin
public class MeetingAdminApi {

    @Autowired
    private MeetingAdminServiceImpl meetingAdminService;

    @RequestMapping("/login")
    public String login(String userName,String password){
        return meetingAdminService.login(userName,password);
    }
}
