package com.meeting.demo.api;

import com.meeting.demo.entity.Feedback;
import com.meeting.demo.serviceImpl.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackApi {

    @Autowired
    private FeedbackServiceImpl feedbackService;

    @RequestMapping("/insert")
    public void insert(Feedback feedback) {
        HashMap hashMap = new HashMap();
        feedbackService.insert(feedback);
    }

    @RequestMapping("/getAll")
    public List<Feedback> getAll(){
        return feedbackService.getAll();
    }
}
